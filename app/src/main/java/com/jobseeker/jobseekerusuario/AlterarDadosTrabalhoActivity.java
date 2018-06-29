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

public class AlterarDadosTrabalhoActivity extends AppCompatActivity {

    public String[] dadosPessoais;
    public String[] dados = {"", "", "", "", "", "", ""};
    String oldCargo;
    private Trabalhador usuario;
    EditText cargo, requisitos, formacao, experiencia, salario, local, disponibilidade;
    private Empregador emprego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_trabalho);
        Intent intent = getIntent();
        usuario = intent.getParcelableExtra("usuario");
        emprego = intent.getParcelableExtra("jobChange");
        //nome == cargo
        cargo = (EditText) findViewById(R.id.editText32);
        requisitos = (EditText) findViewById(R.id.editText33);
        formacao= (EditText) findViewById(R.id.editText34);
        experiencia = (EditText) findViewById(R.id.editText35);
        salario = (EditText) findViewById(R.id.editText36);
        local = (EditText) findViewById(R.id.editText37);
        disponibilidade = (EditText) findViewById(R.id.editText38);

        cargo.setText(emprego.getNome());
        requisitos.setText(emprego.getRequisitos());
        formacao.setText(emprego.getFormacao());
        experiencia.setText(emprego.getExperiencia());
        salario.setText(emprego.getSalario());
        local.setText(emprego.getLocal());
        disponibilidade.setText(emprego.getDisponibilidade());

        oldCargo = cargo.getText().toString();
    }

    public void irFinal(View v){


        emprego.setNome(cargo.getText().toString());
        emprego.setRequisitos(requisitos.getText().toString());
        emprego.setFormacao(formacao.getText().toString());
        emprego.setExperiencia(experiencia.getText().toString());
        emprego.setSalario(salario.getText().toString());
        emprego.setLocal(local.getText().toString());
        emprego.setDisponibilidade(disponibilidade.getText().toString());

        ApiClient.getJobClient().changeDataTrabalho(emprego, oldCargo).enqueue(new Callback<Empregador>() {
            @Override
            public void onResponse(Call<Empregador> call, Response<Empregador> response) {
                if (response.isSuccessful() ) {
                    Toast.makeText(AlterarDadosTrabalhoActivity.this, "Sucesso!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AlterarDadosTrabalhoActivity.this, EmpregadorMenuActivity.class);
                    intent.putExtra("usuario",usuario);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext() , "Problema no banco de dados, tente novamente mais tarde", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Empregador> call, Throwable t) {
                Toast.makeText(AlterarDadosTrabalhoActivity.this,"Falha no cadastro! problemas de concexao com o banco de dados, tente novamente mais tarde",Toast.LENGTH_SHORT).show();
            }
        });


    }

}
