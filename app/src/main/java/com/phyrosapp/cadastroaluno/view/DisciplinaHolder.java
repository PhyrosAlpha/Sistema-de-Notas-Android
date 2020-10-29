package com.phyrosapp.cadastroaluno.view;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.phyrosapp.cadastroaluno.R;

public class DisciplinaHolder extends RecyclerView.ViewHolder{
    public TextView nomeDisciplina;
    public TextView idDisciplina;
    public TextView lblDisciplinaHolder;
    public ImageButton btnDeletarDisciplina;
    public ImageButton btnAcessarDisciplina;


    public DisciplinaHolder(View itemView) {
        super(itemView);
        nomeDisciplina = (TextView) itemView.findViewById(R.id.nomeDisciplina);
        idDisciplina = (TextView) itemView.findViewById(R.id.numberId);
        lblDisciplinaHolder = (TextView) itemView.findViewById(R.id.lblStatusHolder);
        btnAcessarDisciplina = (ImageButton) itemView.findViewById(R.id.btnAcessarDisciplina);
        btnDeletarDisciplina = (ImageButton) itemView.findViewById(R.id.btnDeletarDisciplina);
    }

}