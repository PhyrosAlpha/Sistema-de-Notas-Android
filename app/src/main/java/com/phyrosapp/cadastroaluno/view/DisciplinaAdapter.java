package com.phyrosapp.cadastroaluno.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import com.phyrosapp.cadastroaluno.R;
import com.phyrosapp.cadastroaluno.dao.DisciplinaDAO;
import com.phyrosapp.cadastroaluno.model.Disciplina;

import java.util.List;

public class DisciplinaAdapter extends RecyclerView.Adapter<DisciplinaHolder> {
    private final List<Disciplina> disciplina;

    public DisciplinaAdapter(List<Disciplina> disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public DisciplinaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DisciplinaHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.disciplina_universidade, parent, false));
    }

    @Override
    public void onBindViewHolder(DisciplinaHolder holder, int position) {
        holder.nomeDisciplina.setText(disciplina.get(position).getDisciplina());

        String id = Integer.toString(disciplina.get(position).getIdDisciplina());
        holder.idDisciplina.setText(id);

        holder.lblDisciplinaHolder.setText(disciplina.get(position).getStatus());
        colorStatus(holder.lblDisciplinaHolder, disciplina.get(position));

        final Disciplina disciplinaDeletada = disciplina.get(position);
        holder.btnDeletarDisciplina.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = v;
                animationButton(v);
                DisciplinaDAO dao = new DisciplinaDAO(view.getContext());
                boolean sucesso = dao.excluirDisciplina(disciplinaDeletada.getIdDisciplina());
                if (sucesso) {
                    removerDisciplina(disciplinaDeletada);
                    Snackbar.make(view, "Disciplina foi deletada", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else {
                    Snackbar.make(view, "Houve algum problema inesperado", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }

            }
        });

        final Disciplina disciplinaAcessada = disciplina.get(position);
        holder.btnAcessarDisciplina.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationButton(v);
                Intent acessarDisciplina = new Intent(v.getContext(), TelaDisciplina.class);
                Bundle params = new Bundle();
                int idDisciplina = disciplinaAcessada.getIdDisciplina();
                System.out.println(idDisciplina);
                params.putInt("idDisciplina", idDisciplina);
                acessarDisciplina.putExtras(params);
                v.getContext().startActivity(acessarDisciplina);
            }
        });

    }

    @Override
    public int getItemCount() {
        return disciplina != null ? disciplina.size() : 0;
    }

    public void removerDisciplina(Disciplina disciplinaDeletada){
        int position = this.disciplina.indexOf(disciplinaDeletada);
        disciplina.remove(position);
        notifyItemRemoved(position);
    }

    public void colorStatus(TextView v, Disciplina disciplinaSelect){
        if(disciplinaSelect.getStatus().equals("Aprovado")){
            v.setTextColor(ContextCompat.getColor(v.getContext(), R.color.aprovado));
        }else if(disciplinaSelect.getStatus().equals("Reprovado")){
            v.setTextColor(ContextCompat.getColor(v.getContext(), R.color.reprovado));
        }
    }

    public void animationButton(View v){
        Animation buttonAnimation = AnimationUtils.loadAnimation(v.getContext(), R.anim.main_animation);
        v.startAnimation(buttonAnimation);

    }
}
