package com.jobseeker.jobseekerusuario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jobseeker.jobseekerusuario.Model.Empregador;
import com.jobseeker.jobseekerusuario.Model.Trabalhador;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuscaPersonalizadaActivity extends AppCompatActivity {

    public String[] dadosPessoais;
    public String[] dadosProfissionais = {"", "", "", "", "", "", ""};
    EditText obj, perfil, exp, formacao, cursos, idiomas, complem;
    TextView titulo;
    Trabalhador usuario;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_profissionais);

        usuario = getIntent().getParcelableExtra("usuario");


        obj = (EditText) findViewById(R.id.editText12);
        perfil = (EditText) findViewById(R.id.editText13);
        exp = (EditText) findViewById(R.id.editText14);
        formacao = (EditText) findViewById(R.id.editText15);
        cursos = (EditText) findViewById(R.id.editText16);
        idiomas = (EditText) findViewById(R.id.editText17);
        complem = (EditText) findViewById(R.id.editText18);

        obj.setText(usuario.getObjetivo());
        perfil.setText(usuario.getPerfil());
        exp.setText(usuario.getExperiencia());
        formacao.setText(usuario.getFormacao());
        cursos.setText(usuario.getCursoComplementar());
        idiomas.setText(usuario.getIdiomas());
        complem.setText(usuario.getInfoProfissional());

        bt = (Button) findViewById(R.id.button3);
        bt.setText("Buscar ofertas");
        titulo = (TextView) findViewById(R.id.textView10);
        titulo.setText("Dados para a busca");
        Toast.makeText(getApplicationContext() , "Altere conforme desejar para a busca, as mudanças não serão salvas", Toast.LENGTH_LONG).show();
    }

    public void irFinal(View v){


        dadosProfissionais[0] = obj.getText().toString();
        dadosProfissionais[1] = perfil.getText().toString();
        dadosProfissionais[2] = exp.getText().toString();
        dadosProfissionais[3] = formacao.getText().toString();
        dadosProfissionais[4] = cursos.getText().toString();
        dadosProfissionais[5] = idiomas.getText().toString();
        dadosProfissionais[6] = complem.getText().toString();

        String textao = dadosProfissionais[0]+" "+dadosProfissionais[1]+" "+
                dadosProfissionais[2]+" "+dadosProfissionais[3]+" "+dadosProfissionais[4]+" "+
                dadosProfissionais[5]+" "+dadosProfissionais[6];


        ApiClient.getJobClient().getJobsDisponiveis2(textao).enqueue(new Callback<List<Empregador>>() {
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

}
