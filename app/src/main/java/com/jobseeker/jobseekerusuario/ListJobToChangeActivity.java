package com.jobseeker.jobseekerusuario;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jobseeker.jobseekerusuario.Adapter.EmpregadorAdapter;
import com.jobseeker.jobseekerusuario.Model.Empregador;
import com.jobseeker.jobseekerusuario.Model.Trabalhador;

import java.util.ArrayList;

public class ListJobToChangeActivity extends AppCompatActivity {
    private ArrayList<Empregador> mEmpregadorList;
    private Trabalhador usuario;
    private RecyclerView mRecyclerVieww;
    private EmpregadorAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_job);
        Intent intent = getIntent();
        usuario = intent.getParcelableExtra("usuario");
        mEmpregadorList = intent.getParcelableArrayListExtra("meusJobs");
        buildRecyclerView();
    }

    public void buildRecyclerView() {

        mRecyclerVieww = findViewById(R.id.recyclerVieww);

        mRecyclerVieww.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);


        mAdapter = new EmpregadorAdapter((ArrayList)mEmpregadorList);

        mRecyclerVieww.setLayoutManager(mLayoutManager);

        mRecyclerVieww.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new EmpregadorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(ListJobToChangeActivity.this, AlterarDadosTrabalhoActivity.class);
                intent.putExtra("jobChange", mEmpregadorList.get(position));
                intent.putExtra("usuario",usuario);
                startActivity(intent);
            }
        });
    }

    //Log.i("debugSeeker","Achei o erro");
}