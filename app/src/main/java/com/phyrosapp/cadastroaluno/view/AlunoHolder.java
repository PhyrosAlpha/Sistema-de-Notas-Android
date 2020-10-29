package com.phyrosapp.cadastroaluno.view;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.phyrosapp.cadastroaluno.R;

public class AlunoHolder extends RecyclerView.ViewHolder {
    public TextView nomeAluno;
    public ImageButton btnDeletar;
    public ImageButton btnAcessar;


    public AlunoHolder(View itemView) {
        super(itemView);

        nomeAluno = (TextView) itemView.findViewById(R.id.nomeAluno);
        btnAcessar = (ImageButton) itemView.findViewById(R.id.btnAcessar);
        btnDeletar = (ImageButton) itemView.findViewById(R.id.btnDeletar);
    }
}
