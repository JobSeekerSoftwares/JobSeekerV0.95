package com.jobseeker.jobseekerusuario;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jobseeker.jobseekerusuario.Adapter.EmpregadorAdapter;
import com.jobseeker.jobseekerusuario.Adapter.EmpregadorAdapter2;
import com.jobseeker.jobseekerusuario.Model.Empregador;

import java.util.ArrayList;

public class ListJobActivity extends AppCompatActivity {
    private ArrayList<Empregador> mEmpregadorList;
    private RecyclerView mRecyclerVieww;
    private EmpregadorAdapter mAdapter;
    private EmpregadorAdapter2 mAdapter2;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_job);
        Intent intent = getIntent();
        mEmpregadorList = intent.getParcelableArrayListExtra("jobs");
      //  buildRecyclerView();
        buildRecyclerView2();
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
                Intent intent = new Intent(ListJobActivity.this, MoreInfoActivity.class);
                intent.putExtra("job", mEmpregadorList.get(position));
                startActivity(intent);
            }
        });
    }

    public void buildRecyclerView2() {

        mRecyclerVieww = findViewById(R.id.recyclerVieww);

        mRecyclerVieww.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);


        mAdapter2 = new EmpregadorAdapter2((ArrayList)mEmpregadorList);

        mRecyclerVieww.setLayoutManager(mLayoutManager);

        mRecyclerVieww.setAdapter(mAdapter2);

        mAdapter2.setOnItemClickListener(new EmpregadorAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(ListJobActivity.this, MoreInfoActivity.class);
                intent.putExtra("job", mEmpregadorList.get(position));
                startActivity(intent);
            }
        });
    }


}




//Log.i("debugSeeker","Achei o erro");