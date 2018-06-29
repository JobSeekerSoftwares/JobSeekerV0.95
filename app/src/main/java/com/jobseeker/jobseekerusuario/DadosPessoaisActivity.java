package com.jobseeker.jobseekerusuario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.app.sample.fchat.data.SettingsAPI;

public class DadosPessoaisActivity extends AppCompatActivity{

    private Spinner spinner1, spinner2;
    private static final String[] estadosCivis = {"Casado(a)", "Solteiro(a)", "Divorciado(a)", "Separado(a)", "Viúvo(a)"};
    private static final String[] ufs = {"AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO"};
    public String[] dadosPessoais = {"", "", "", "", "", "", "", "", "", "", "", "", ""};
    EditText nome, nasc, cpf, email, nacio, end, cidade, tel, cel, sobre;
    Spinner civil, uf;
    SettingsAPI set;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_pessoais);
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
        email.setText(set.readSetting("myMl"));
        email.setKeyListener(null);

        civil = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(DadosPessoaisActivity.this,
                android.R.layout.simple_spinner_item, estadosCivis);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        civil.setAdapter(adapter1);

        uf = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(DadosPessoaisActivity.this,
                android.R.layout.simple_spinner_item, ufs);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        uf.setAdapter(adapter2);

    }



    public void irInicio(View v){
        Intent intent = new Intent(this, OpenActivity.class);
        startActivity(intent);



    }

    public void irDadosProfissionais(View v){
        Intent intent = new Intent(this, DadosProfissionaisActivity.class);

        dadosPessoais[0] = nome.getText().toString();
        dadosPessoais[1] = nasc.getText().toString();
        dadosPessoais[2] = cpf.getText().toString();
        dadosPessoais[3] = email.getText().toString();
        dadosPessoais[4] = nacio.getText().toString();
        dadosPessoais[5] = civil.getSelectedItem().toString();
        dadosPessoais[6] = end.getText().toString();
        dadosPessoais[7] = cidade.getText().toString();
        dadosPessoais[8] = uf.getSelectedItem().toString();
        dadosPessoais[9] = tel.getText().toString();
        dadosPessoais[10] = cel.getText().toString();
        dadosPessoais[11] = sobre.getText().toString();
        dadosPessoais[12] = set.readSetting("myid");

        intent.putExtra("Dados pessoais", dadosPessoais);

        startActivity(intent);
    }

}
