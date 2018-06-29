package com.jobseeker.jobseekerusuario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jobseeker.jobseekerusuario.Model.Empregador;
import com.jobseeker.jobseekerusuario.Model.Trabalhador;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DadosTrabalhoActivity extends AppCompatActivity {

    public String[] dadosPessoais;
    public String[] dados = {"", "", "", "", "", "", ""};
    EditText cargo, requisitos, formacao, experiencia, salario, local, disponibilidade;
    private Trabalhador usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_trabalho);

        //nome == cargo
        cargo = (EditText) findViewById(R.id.editText32);
        requisitos = (EditText) findViewById(R.id.editText33);
        formacao= (EditText) findViewById(R.id.editText34);
        experiencia = (EditText) findViewById(R.id.editText35);
        salario = (EditText) findViewById(R.id.editText36);
        local = (EditText) findViewById(R.id.editText37);
        disponibilidade = (EditText) findViewById(R.id.editText38);

        usuario = getIntent().getParcelableExtra("usuario");
    }

    public void irFinal(View v){


        dados[0] = cargo.getText().toString();
        dados[1] = requisitos.getText().toString();
        dados[2] = formacao.getText().toString();
        dados[3] = experiencia.getText().toString();
        dados[4] = salario.getText().toString();
        dados[5] = local.getText().toString();
        dados[6] = disponibilidade.getText().toString();

        final Empregador mEmpregador = new Empregador(dados[0], usuario.getEmail(),
                usuario.getIdGmail(), dados[1], dados[2], dados[3], dados[4], dados[5], dados[6]);
        ApiClient.getJobClient().uploadDadosEmpregador(mEmpregador).enqueue(new Callback<Empregador>() {
            @Override
            public void onResponse(Call<Empregador> call, Response<Empregador> response) {
                if (response.isSuccessful() ) {
                    Toast.makeText(DadosTrabalhoActivity.this, "Sucesso!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DadosTrabalhoActivity.this, EmpregadorMenuActivity.class);
                    intent.putExtra("usuario",usuario);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext() , "Problema no banco de dados, tente novamente mais tarde", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Empregador> call, Throwable t) {
                Toast.makeText(DadosTrabalhoActivity.this,"Falha no cadastro! problemas de concexao com o banco de dados, tente novamente mais tarde",Toast.LENGTH_SHORT).show();
            }
        });


    }

}
