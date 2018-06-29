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


public class EmpregadorAdapter extends RecyclerView.Adapter<EmpregadorAdapter.ExampleViewHolder> {
    private ArrayList<Empregador> mEmpregadorList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextVieww1;
        public TextView mTextVieww2;
        public TextView mTextVieww3;


        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextVieww1 = itemView.findViewById(R.id.textVieww);
            mTextVieww2 = itemView.findViewById(R.id.textVieww2);
            mTextVieww3 = itemView.findViewById(R.id.textVieww3);

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

    public EmpregadorAdapter(ArrayList<Empregador> empregadorList) {
        mEmpregadorList = empregadorList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.empregador_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        Empregador currentItem = mEmpregadorList.get(position);
        holder.mImageView.setImageResource(R.drawable.ic_group_collage);
        holder.mTextVieww1.setText(currentItem.getNome());
        holder.mTextVieww2.setText(currentItem.getEmail());
        holder.mTextVieww3.setText(currentItem.getRequisitos());

    }

    @Override
    public int getItemCount() {
        if(mEmpregadorList == null){return 0;}
        return mEmpregadorList.size();
    }
}