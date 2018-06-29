package com.jobseeker.jobseekerusuario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.sample.fchat.data.SettingsAPI;
import com.jobseeker.jobseekerusuario.Model.Empregador;
import com.jobseeker.jobseekerusuario.Model.Trabalhador;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlterarDadosPessoaisActivity extends AppCompatActivity{

    private Spinner spinner1, spinner2;
    private static final String[] estadosCivis = {"Casado(a)", "Solteiro(a)", "Divorciado(a)", "Separado(a)", "Viúvo(a)"};
    private static final String[] ufs = {"AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO"};
    public String[] dadosPessoais = {"", "", "", "", "", "", "", "", "", "", "", "", ""};
    EditText nome, nasc, cpf, email, nacio, end, cidade, tel, cel, sobre;
    Button bt;
    Spinner civil, uf;
    SettingsAPI set;
    Trabalhador usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_pessoais);
        usuario = getIntent().getParcelableExtra("usuario");
        set = new SettingsAPI(this);
        nome = (EditText) findViewById(R.id.editText2);
        nasc = (EditText) findViewById(R.id.editText3);
        cpf = (EditText) findViewById(R.id.editText4);
        email = (EditText) findViewById(R.id.editText5);
        nacio = (EditText) findViewById(R.id.editText6);
        end = (EditText) findViewById(R.id.editText7);
        cidade = (EditText) findViewById(R.id.editText8);
        tel = (EditText) findViewById(R.id.editText9);
        cel = (EditText) findViewById(R.id.editText10);
        sobre = (EditText) findViewById(R.id.editText11);

        nome.setText(usuario.getNome());
        nasc.setText(usuario.getNascimento());
        cpf.setText(usuario.getCpf());
        email.setText(set.readSetting("myMl"));
        email.setKeyListener(null);
        nacio.setText(usuario.getNacionalidade());
        end.setText(usuario.getEndereco());
        cidade.setText(usuario.getCidade());
        tel.setText(usuario.getTelefone());
        cel.setText(usuario.getCelular());
        sobre.setText(usuario.getInfoPessoal());

        civil = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(AlterarDadosPessoaisActivity.this,
                android.R.layout.simple_spinner_item, estadosCivis);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        civil.setAdapter(adapter1);
        int spinnerPosition = adapter1.getPosition(usuario.getEstadoCivil());
        civil.setSelection(spinnerPosition);

        uf = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(AlterarDadosPessoaisActivity.this,
                android.R.layout.simple_spinner_item, ufs);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        uf.setAdapter(adapter2);
        spinnerPosition = adapter2.getPosition(usuario.getUnidadeFederal());
        uf.setSelection(spinnerPosition);

        bt = (Button) findViewById(R.id.button2);
        bt.setText("Aplicar mudanças");

    }



    public void irInicio(View v){
        Intent intent = new Intent(this, OpenActivity.class);
        startActivity(intent);



    }

    public void irDadosProfissionais(View v){
        usuario.setNome(nome.getText().toString());
        usuario.setNascimento(nasc.getText().toString());
        usuario.setCpf(cpf.getText().toString());
        usuario.setEmail(email.getText().toString());
        usuario.setNacionalidade(nacio.getText().toString());
        usuario.setEstadoCivil(civil.getSelectedItem().toString());
        usuario.setEndereco(end.getText().toString());
        usuario.setCidade(cidade.getText().toString());
        usuario.setUnidadeFederal(uf.getSelectedItem().toString());
        usuario.setTelefone(tel.getText().toString());
        usuario.setCelular(cel.getText().toString());
        usuario.setInfoPessoal(sobre.getText().toString());
        //usuario.setIdGmail(set.readSetting("myid"));

        ApiClient.getJobClient().changeDataPessoal(usuario).enqueue(new Callback<Trabalhador>() {
            public void onResponse(Call<Trabalhador> call, Response<Trabalhador> response ){
                if (response.isSuccessful() ) {
                    Intent intent = new Intent(getBaseContext(), MainMenuActivity.class);
                    intent.putExtra("usuario", usuario);
                    Toast.makeText(getApplicationContext() , "Informacoes pessoais atualizadas", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();

                } else {
                    System.out.println(response.errorBody());
                    Toast.makeText(getApplicationContext() , "Problema no banco de dados, tente novamente mais tarde", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Trabalhador> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext() , "Problema no banco de dados, tente novamente em outro momento", Toast.LENGTH_SHORT).show();

            }
        });


    }

}
