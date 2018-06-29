package com.jobseeker.jobseekerusuario;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.app.sample.fchat.InitiateChatActivity;
import com.jobseeker.jobseekerusuario.Model.Empregador;

import java.util.ArrayList;
import java.util.List;

public class MoreInfoActivity extends ListActivity {
    private ArrayAdapter<String> adapter;
    private ArrayList<String> wordList;
    private Empregador job;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        wordList = new ArrayList<String>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        job = getIntent().getParcelableExtra("job");
        if(job == null){
            Toast.makeText(getApplicationContext() , "Erro encontrado, nao clique nas opcoes", Toast.LENGTH_SHORT).show();
            wordList.add("Erro");
        }else{
            wordList.add(job.getNome());
            wordList.add(job.getEmail());
            wordList.add(job.getRequisitos());
            wordList.add(job.getExperiencia());
            wordList.add(job.getFormacao());
            wordList.add(job.getDisponibilidade());
            wordList.add(job.getLocal());
            wordList.add(job.getSalario());

        }

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                wordList);

        setListAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void goInitiateChat(View view) {
        Intent intent = new Intent(getBaseContext(), InitiateChatActivity.class);
        intent.putExtra("idGmailEmpregador", job.getIdGmail());
        startActivity(intent);
    }

}
