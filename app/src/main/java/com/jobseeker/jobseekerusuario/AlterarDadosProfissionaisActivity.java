package com.jobseeker.jobseekerusuario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jobseeker.jobseekerusuario.Model.Trabalhador;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlterarDadosProfissionaisActivity extends AppCompatActivity {

    public String[] dadosPessoais;
    public String[] dadosProfissionais = {"", "", "", "", "", "", ""};
    EditText obj, perfil, exp, formacao, cursos, idiomas, complem;
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
        bt.setText("Aplicar mudanças");
    }

    public void irFinal(View v){


        usuario.setObjetivo(obj.getText().toString());
        usuario.setPerfil(perfil.getText().toString());
        usuario.setExperiencia(exp.getText().toString());
        usuario.setFormacao(formacao.getText().toString());
        usuario.setCursoComplementar(cursos.getText().toString());
        usuario.setIdiomas(idiomas.getText().toString());
        usuario.setInfoProfissional(complem.getText().toString());


        ApiClient.getJobClient().changeDataProfissional(usuario).enqueue(new Callback<Trabalhador>() {
            @Override
            public void onResponse(Call<Trabalhador> call, Response<Trabalhador> response ){
                if (response.isSuccessful() ) {
                    Intent intent = new Intent(getBaseContext(), MainMenuActivity.class);
                    intent.putExtra("usuario", usuario);
                    Toast.makeText(getApplicationContext() , "Informacoes profissionais atualizadas", Toast.LENGTH_SHORT).show();
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
