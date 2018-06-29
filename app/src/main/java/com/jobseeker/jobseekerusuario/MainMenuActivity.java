package com.jobseeker.jobseekerusuario;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.sample.fchat.ActivityMain;
import com.app.sample.fchat.ChatActivity;
import com.app.sample.fchat.data.Tools;
import com.jobseeker.jobseekerusuario.Model.Empregador;
import com.jobseeker.jobseekerusuario.Model.Trabalhador;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainMenuActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Trabalhador usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        prepareActionBar(toolbar);
        usuario = getIntent().getParcelableExtra("usuario");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Tools.systemBarLolipop(this);
        }
    }

    private void prepareActionBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_logout: {
                Intent logoutIntent=new Intent(this,OpenActivity.class);
                logoutIntent.putExtra("mode","logout");
                startActivity(logoutIntent);
                finish();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void changeDataPessoal(View view) {
        Intent intent = new Intent(this, AlterarDadosPessoaisActivity.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }

    public void changeDataProfissional(View view) {
        Intent intent = new Intent(this, AlterarDadosProfissionaisActivity.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }

    public void changeToEmpregador(View view) {
        Intent intent = new Intent(this, EmpregadorMenuActivity.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }


    public void getAllData(View view) {
        ApiClient.getJobClient().getAllJobsDisponiveis().enqueue(new Callback<List<Empregador>>() {
            public void onResponse(Call<List<Empregador>> call, Response<List<Empregador>> response ){
                if (response.isSuccessful() ) {

                    List<Empregador> jobs = response.body();
                    Intent intent = new Intent(getBaseContext(), ListJobActivity.class);
                    intent.putParcelableArrayListExtra("jobs",(ArrayList)jobs);
                    startActivity(intent);

                } else {
                    System.out.println(response.errorBody());
                    Toast.makeText(getApplicationContext() , "Erro encontrado", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Empregador>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void seeJobs(View view) {
        Intent intent = new Intent(getBaseContext(), BuscaPersonalizadaActivity.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }

    public void goChat(View view) {
        Intent intent = new Intent(getBaseContext(), ChatActivity.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }
}
