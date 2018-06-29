package com.jobseeker.jobseekerusuario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jobseeker.jobseekerusuario.Model.Trabalhador;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DadosProfissionaisActivity extends AppCompatActivity {

    public String[] dadosPessoais;
    public String[] dadosProfissionais = {"", "", "", "", "", "", ""};
    EditText obj, perfil, exp, formacao, cursos, idiomas, complem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_profissionais);

        Intent intentRecebe = getIntent();
        dadosPessoais = intentRecebe.getStringArrayExtra("Dados pessoais");

        obj = (EditText) findViewById(R.id.editText12);
        perfil = (EditText) findViewById(R.id.editText13);
        exp = (EditText) findViewById(R.id.editText14);
        formacao = (EditText) findViewById(R.id.editText15);
        cursos = (EditText) findViewById(R.id.editText16);
        idiomas = (EditText) findViewById(R.id.editText17);
        complem = (EditText) findViewById(R.id.editText18);
    }

    public void irFinal(View v){


        dadosProfissionais[0] = obj.getText().toString();
        dadosProfissionais[1] = perfil.getText().toString();
        dadosProfissionais[2] = exp.getText().toString();
        dadosProfissionais[3] = formacao.getText().toString();
        dadosProfissionais[4] = cursos.getText().toString();
        dadosProfissionais[5] = idiomas.getText().toString();
        dadosProfissionais[6] = complem.getText().toString();

        final Trabalhador mTrabalhador = new Trabalhador(dadosPessoais[0], dadosPessoais[1],
                dadosPessoais[2], dadosPessoais[3], dadosPessoais[4], dadosPessoais[5],
                dadosPessoais[6], dadosPessoais[7], dadosPessoais[8], dadosPessoais[9],
                dadosPessoais[10], dadosPessoais[11], dadosPessoais[12], dadosProfissionais[0],
                dadosProfissionais[1], dadosProfissionais[2],dadosProfissionais[3],
                dadosProfissionais[4], dadosProfissionais[5], dadosProfissionais[6]);
        ApiClient.getJobClient().uploadDadosTrabalhador(mTrabalhador).enqueue(new Callback<Trabalhador>() {
            @Override
            public void onResponse(Call<Trabalhador> call, Response<Trabalhador> response) {
                if (response.isSuccessful() ) {
                    Toast.makeText(DadosProfissionaisActivity.this, "Sucesso!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DadosProfissionaisActivity.this, MainMenuActivity.class);
                    intent.putExtra("usuario", mTrabalhador);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext() , "Problema no banco de dados, tente novamente mais tarde", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Trabalhador> call, Throwable t) {
                Toast.makeText(DadosProfissionaisActivity.this,"Falha no cadastro! problemas de concexao com o banco de dados, tente novamente mais tarde",Toast.LENGTH_SHORT).show();
            }
        });


    }

}
