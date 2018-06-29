package com.jobseeker.jobseekerusuario.Adapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jobseeker.jobseekerusuario.Model.Empregador;
import com.jobseeker.jobseekerusuario.R;

import java.util.ArrayList;


public class EmpregadorAdapter2 extends RecyclerView.Adapter<EmpregadorAdapter2.ExampleViewHolder> {
    private ArrayList<Empregador> mEmpregadorList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        TextView nomeEmpresa;
        TextView valorSalario;
        TextView nomeLocal;
        TextView horario;


        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            nomeEmpresa = itemView.findViewById(R.id.txtNomeEmpresa);
            nomeLocal = itemView.findViewById(R.id.txtNomeLocal);
            valorSalario = itemView.findViewById(R.id.txtValorSalario);
            horario = itemView.findViewById(R.id.txtHorario);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public EmpregadorAdapter2(ArrayList<Empregador> empregadorList) {
        mEmpregadorList = empregadorList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vaga_linha, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        Empregador currentItem = mEmpregadorList.get(position);
        holder.nomeEmpresa.setText(currentItem.getNome());
        holder.valorSalario.setText(currentItem.getSalario());
        holder.nomeLocal.setText(currentItem.getLocal());
        holder.horario.setText(currentItem.getDisponibilidade());

    }

    @Override
    public int getItemCount() {
        if(mEmpregadorList == null){return 0;}
        return mEmpregadorList.size();
    }
}