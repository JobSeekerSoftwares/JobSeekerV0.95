package com.jobseeker.jobseekerusuario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class FinalCadastroActivity extends AppCompatActivity {

    public String[] dadosPessoais;
    public String[] dadosProfissionais;
    public String usuarioTexto = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_cadastro);

        Intent intentRecebe = getIntent();
        dadosPessoais = intentRecebe.getStringArrayExtra("Dados pessoais");
        dadosProfissionais = intentRecebe.getStringArrayExtra("Dados profissionais");

        TextView usuario = (TextView) findViewById(R.id.textView10);

        for(int i=0; i<12; i++){
            usuarioTexto = usuarioTexto + "/n" + dadosPessoais[i];
        }

        for(int i=0; i<7; i++){
            usuarioTexto = usuarioTexto + "/n" + dadosProfissionais[i];
        }

        usuario.setText(usuarioTexto);

    }
}
