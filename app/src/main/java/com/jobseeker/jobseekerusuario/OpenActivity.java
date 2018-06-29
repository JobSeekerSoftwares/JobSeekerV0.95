package com.jobseeker.jobseekerusuario;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.sample.fchat.data.SettingsAPI;
import com.app.sample.fchat.data.Tools;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jobseeker.jobseekerusuario.Model.Empregador;
import com.jobseeker.jobseekerusuario.Model.Trabalhador;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OpenActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {

    private static final int RC_SIGN_IN = 100;
    private SignInButton signInButton;
    private ProgressBar loginProgress;

    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mFirebaseAuth;
    DatabaseReference ref;
    SettingsAPI set;
    public static final String USERS_CHILD = "users";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        loginProgress = (ProgressBar) findViewById(R.id.login_progress);

        // Set click listeners
        signInButton.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // Initialize FirebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance();
        set = new SettingsAPI(this);
        deslogar();
        if (getIntent().getStringExtra("mode") != null) {
            if (getIntent().getStringExtra("mode").equals("logout")) {
                deslogar();
            }
        }

        // for system bar in lollipop
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Tools.systemBarLolipop(this);
        }
    }
    public void deslogar(){
        mGoogleApiClient.connect();
        mGoogleApiClient.registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(@Nullable Bundle bundle) {
                mFirebaseAuth.signOut();
                Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                set.deleteAllSettings();
            }

            @Override
            public void onConnectionSuspended(int i) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            default:
                return;
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        Log.v("andre","entrou aqui");
        startActivityForResult(signInIntent, RC_SIGN_IN);
        Log.v("andre","saiu aqui");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        Log.v("andre","recbeu os dados");
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Log.v("andre","singnou");
            if (result.isSuccess()) {
                signInButton.setVisibility(View.GONE);
                loginProgress.setVisibility(View.VISIBLE);
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                Log.v("andre","entrou aqui");
                Snackbar.make(getWindow().getDecorView(), "Login failed", Snackbar.LENGTH_LONG).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(final GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.v("andre","entrou aqui2");
                            Snackbar.make(getWindow().getDecorView(), "Authentication failed.", Snackbar.LENGTH_LONG).show();
                        } else {
                            ref= FirebaseDatabase.getInstance().getReference(USERS_CHILD);
                            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot snapshot) {
                                    final String usrNm = acct.getDisplayName();
                                    final String usrId = acct.getId();
                                    final String usrDp = acct.getPhotoUrl().toString();
                                    final String usrMl = acct.getEmail();

                                    set.addUpdateSettings("myid", usrId);
                                    set.addUpdateSettings("myname", usrNm);
                                    set.addUpdateSettings("mydp", usrDp);
                                    set.addUpdateSettings("myMl", usrMl);

                                    if (!snapshot.hasChild(usrId)) {
                                        ref.child(usrId + "/name").setValue(usrNm);
                                        ref.child(usrId + "/photo").setValue(usrDp);
                                        ref.child(usrId + "/id").setValue(usrId);
                                        ref.child(usrId + "/email").setValue(usrMl);
                                    }
                                    Log.v("andre","Verificando django");
                                    verifIfExists();
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            });

                        }
                    }
                });
    }

    public void verifIfExists() {
        ApiClient.getJobClient().getPessoaByGmail(set.readSetting("myid")).enqueue(new Callback<List<Trabalhador>>() {
            public void onResponse(Call<List<Trabalhador>> call, Response<List<Trabalhador>> response ){
                if (response.isSuccessful() ) {

                    List<Trabalhador> jobs = response.body();
                    if(jobs.size() != 0){
                        Intent intent = new Intent(OpenActivity.this, MainMenuActivity.class);
                        intent.putExtra("usuario", jobs.get(0));
                        startActivity( intent);
                        finish();
                    }else{
                        Intent intent = new Intent(OpenActivity.this, DadosPessoaisActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    System.out.println(response.errorBody());
                    Log.v("andre","errou django aqui");
                    Toast.makeText(getApplicationContext() , "Problemas com o banco de dados, tente novamente mais tarde", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Trabalhador>> call, Throwable t) {
                t.printStackTrace();
                Log.v("andre","Erro ao se conectar ao django");
            }
        });

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Snackbar.make(getWindow().getDecorView(), "Google Play Services error.", Snackbar.LENGTH_LONG).show();
    }

}
