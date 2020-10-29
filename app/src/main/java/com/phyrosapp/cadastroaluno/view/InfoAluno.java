package com.phyrosapp.cadastroaluno.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.phyrosapp.cadastroaluno.R;
import com.phyrosapp.cadastroaluno.dao.AlunoDAO;
import com.phyrosapp.cadastroaluno.model.Aluno;

import org.w3c.dom.Text;

public class InfoAluno extends AppCompatActivity {
    public Aluno aluno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_aluno);

        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        String rgm = params.getString("rgm");

        carregarDadosAluno(rgm);
        exibirDadosAluno();
    }

    public void voltar(View view){
        finish();
    }

    private void carregarDadosAluno(String rgm){
        AlunoDAO alunoDAO = new AlunoDAO(this);
        aluno = alunoDAO.consultarAluno(rgm);
    }

    private void exibirDadosAluno(){
        TextView lblNome = (TextView) findViewById(R.id.lblNomeInfo);
        TextView lblRgm = (TextView) findViewById(R.id.lblRgmInfo);
        TextView lblCep = (TextView) findViewById(R.id.lblCepInfo);
        TextView lblEndereco = (TextView) findViewById(R.id.lblEnderecoInfo);
        TextView lblTelefone = (TextView) findViewById(R.id.lblTelefoneInfo);
        TextView lblEmail = (TextView) findViewById(R.id.lblEmailInfo);
        TextView lblCurso = (TextView) findViewById(R.id.lblCursoInfo);

        String nome = aluno.getNome();
        String rgm = aluno.getRgm();
        String cep = aluno.getCep();
        String endereco = aluno.getEndereco();
        String telefone = aluno.getTelefone();
        String email = aluno.getEmail();
        String curso = aluno.getCurso();

        lblNome.setText(nome);
        lblRgm.setText(rgm);
        lblCep.setText(cep);
        lblEndereco.setText(endereco);
        lblTelefone.setText(telefone);
        lblEmail.setText(email);
        lblCurso.setText(curso);
    }
}