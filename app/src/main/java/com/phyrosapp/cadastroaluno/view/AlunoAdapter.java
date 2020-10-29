package com.phyrosapp.cadastroaluno.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.phyrosapp.cadastroaluno.R;
import com.phyrosapp.cadastroaluno.dao.AlunoDAO;
import com.phyrosapp.cadastroaluno.model.Aluno;

import java.util.List;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoHolder> {
    private final List<Aluno> aluno;

    public AlunoAdapter(List<Aluno> aluno){
        this.aluno = aluno;
    }

    @Override
    public AlunoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AlunoHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.aluno_universidade, parent, false));
    }

    @Override
    public void onBindViewHolder(AlunoHolder holder, int position) {
        holder.nomeAluno.setText(aluno.get(position).getNome());

        final Aluno alunoAcessado = aluno.get(position);
        holder.btnAcessar.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                animationButton(v);
                Intent acessarAluno = new Intent(v.getContext(), AlunoDisciplina.class);
                Bundle params = new Bundle();
                String rgm = alunoAcessado.getRgm();
                String nome = alunoAcessado.getNome();
                params.putString("rgm", rgm);
                params.putString("nome", nome);
                acessarAluno.putExtras(params);
                v.getContext().startActivity(acessarAluno);
            }
        });

        final Aluno alunoDeletado = aluno.get(position);
        holder.btnDeletar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = v;
                animationButton(v);
                AlunoDAO dao = new AlunoDAO(view.getContext());
                boolean sucesso = dao.excluirAluno(alunoDeletado.getRgm());
                if (sucesso) {
                    removerAluno(alunoDeletado);
                    Snackbar.make(view, "Aluno foi deletado", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                } else {
                    Snackbar.make(view, "Aluno foi deletado", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return aluno != null ? aluno.size() : 0;
    }

    public void removerAluno(Aluno alunoDeletado){
        int position = this.aluno.indexOf(alunoDeletado);
        aluno.remove(position);
        notifyItemRemoved(position);
    }

    public void animationButton(View v){
        Animation buttonAnimation = AnimationUtils.loadAnimation(v.getContext(), R.anim.main_animation);
        v.startAnimation(buttonAnimation);

    }

}
