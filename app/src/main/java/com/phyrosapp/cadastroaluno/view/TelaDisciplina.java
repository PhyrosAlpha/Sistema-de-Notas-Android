package com.phyrosapp.cadastroaluno.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.phyrosapp.cadastroaluno.R;
import com.phyrosapp.cadastroaluno.dao.DisciplinaDAO;
import com.phyrosapp.cadastroaluno.model.Disciplina;

public class TelaDisciplina extends AppCompatActivity {
    private Disciplina disciplina;
    private TextView lblDisciplina;
    private TextView lblStatus;
    private TextView lblMedia;
    private EditText inputNotaA1;
    private EditText inputNotaA2;
    private EditText inputNotaFinal;
    private Button  btnFecharNota;
    private Button btnAtualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_disciplina);

        iniciarViewTela();
        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        assert params != null;
        int idDisciplina = params.getInt("idDisciplina");

        carregarDadosDisciplinas(idDisciplina);
        modoAvFinal();
        modoNotaFechada();
        exibirDadosTela();
    }

    public void alterarDados(View view){
        animationButton(view);
        if(validarCampos()) {
            animationButton(view);
            editarDados();
            enviarDados();
            carregarDadosDisciplinas(disciplina.getIdDisciplina());
            exibirDadosTela();
            Toast.makeText(this, "As notas foram editadas!", Toast.LENGTH_SHORT).show();
        }
    }

    public void fecharNota(View view){
        animationButton(view);
        if(validarCampos()) {
            editarDados();
            disciplina.calcularMedia();
            enviarDados();
            carregarDadosDisciplinas(disciplina.getIdDisciplina());
            modoAvFinal();
            modoNotaFechada();
            exibirDadosTela();
            msg();
        }
    }

    public void voltar(View view){
        animationButton(view);
        finish();
    }

    private void iniciarViewTela(){
        lblDisciplina = (TextView) findViewById(R.id.lblDisciplinaInfo);
        lblStatus = (TextView) findViewById(R.id.lblStatus);
        lblMedia = (TextView) findViewById((R.id.lblMedia));
        inputNotaA1 = (EditText) findViewById(R.id.inputNotaA1);
        inputNotaA2 = (EditText) findViewById(R.id.inputNotaA2);
        inputNotaFinal = (EditText) findViewById(R.id.inputNotaFinal);
        btnFecharNota = (Button) findViewById((R.id.btnCalcular));
        btnAtualizar = (Button) findViewById(R.id.btnAtualizar);
    }

    private void carregarDadosDisciplinas(int idDisciplina){
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO(getBaseContext());
        disciplina = disciplinaDAO.consultarDisciplina(idDisciplina);
    }

    private void exibirDadosTela(){
        int idDisciplina = disciplina.getIdDisciplina();
        String disciplinaNome = disciplina.getDisciplina();
        double media = disciplina.getMedia();
        double notaA1 = disciplina.getNotaA1();
        double notaA2 = disciplina.getNotaA2();
        double notaFinal = disciplina.getNotaFinal();
        String status = disciplina.getStatus();

        lblDisciplina.setText(disciplinaNome);
        lblStatus.setText(status);
        lblMedia.setText(Double.toString(media));
        inputNotaA1.setText(Double.toString(notaA1));
        inputNotaA2.setText(Double.toString(notaA2));
        inputNotaFinal.setText(Double.toString(notaFinal));

        if(status.equals("Aprovado")){
            lblStatus.setTextColor(ContextCompat.getColor(this, R.color.aprovado));
        }else if(status.equals("Reprovado")){
            lblStatus.setTextColor(ContextCompat.getColor(this, R.color.reprovado));
        }
    }

    private void editarDados(){
        double notaA1 = Double.parseDouble(inputNotaA1.getText().toString());
        double notaA2 = Double.parseDouble(inputNotaA2.getText().toString());
        double notaFinal = Double.parseDouble(inputNotaFinal.getText().toString());

        disciplina.setNotaA1(notaA1);
        disciplina.setNotaA2(notaA2);
        disciplina.setNotaFinal(notaFinal);
    }

    private void enviarDados(){
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO(getBaseContext());
        disciplinaDAO.alteraDadosDisciplina(disciplina);
    }

    private void modoAvFinal(){
        String status = disciplina.getStatus();
        if(status.equals("Final")) {
            this.inputNotaA1.setEnabled(false);
            this.inputNotaA2.setEnabled(false);
            this.inputNotaFinal.setEnabled(true);
        }
    }

    private void msg(){
        String status = this.disciplina.getStatus();
        if(status.equals("Aprovado") || status.equals("Reprovado"))
        Toast.makeText(this, "Aluno(a) foi " + status, Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "Aluno(a) está na " + status, Toast.LENGTH_SHORT).show();
    }

    private void modoNotaFechada() {
        String status = disciplina.getStatus();
        if (status.equals("Aprovado") || status.equals("Reprovado")) {
            this.inputNotaA1.setEnabled(false);
            this.inputNotaA2.setEnabled(false);
            this.inputNotaFinal.setEnabled(false);
            this.btnAtualizar.setEnabled(false);
            this.btnFecharNota.setEnabled(false);
        }
    }

    private boolean validarCampos(){
        String notaA1Txt = inputNotaA1.getText().toString();
        String notaA2Txt = inputNotaA2.getText().toString();
        String notaFinalTxt = inputNotaFinal.getText().toString();
        boolean notas;

        if(!notaA1Txt.equals("") && !notaA2Txt.equals("") && !notaFinalTxt.equals("")){
            double notaA1 = Double.parseDouble(inputNotaA1.getText().toString());
            double notaA2 = Double.parseDouble(inputNotaA2.getText().toString());
            double notaFinal = Double.parseDouble(inputNotaFinal.getText().toString());
            if(notaA1 <= 5 && notaA2 <= 5 && notaFinal <=5){
                notas = true;
            }else{
                notas = false;
                Toast.makeText(this, "Notas devem ser lançadas abaixo ou igual a 5 pontos", Toast.LENGTH_SHORT).show();
            }
        }else{
            notas = false;
            Toast.makeText(this, "Campos inválidos", Toast.LENGTH_SHORT).show();
        }

        return notas;
    }

    private void animationButton(View v){
        Animation buttonAnimation = AnimationUtils.loadAnimation(v.getContext(), R.anim.main_animation);
        v.startAnimation(buttonAnimation);
    }
}