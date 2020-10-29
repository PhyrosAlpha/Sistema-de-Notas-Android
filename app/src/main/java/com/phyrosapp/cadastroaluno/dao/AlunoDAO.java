package com.phyrosapp.cadastroaluno.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.phyrosapp.cadastroaluno.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO{
    private final String TABLE_ALUNO = "aluno";
    private DbGateway gw;

    public AlunoDAO(Context ctx){
        gw = DbGateway.getInstance(ctx);

    }

    public boolean salvarAluno(Aluno aluno){
        String rgm = aluno.getRgm();
        String nome = aluno.getNome();
        String cep = aluno.getCep();
        String email = aluno.getEmail();
        String endereco = aluno.getEndereco();
        String telefone = aluno.getTelefone();
        String curso = aluno.getCurso();

        ContentValues cv = new ContentValues();
        cv.put("rgm", rgm);
        cv.put("nome", nome);
        cv.put("cep", cep);
        cv.put("email", email);
        cv.put("endereco", endereco);
        cv.put("telefone", telefone);
        cv.put("curso", curso);

        return gw.getDatabase().insert(TABLE_ALUNO, null, cv) > 0;
    }

    public List<Aluno> retornarAlunos(){
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT rgm, nome FROM aluno ORDER BY nome";
        Cursor cursor = gw.getDatabase().rawQuery(sql, null);
        while(cursor.moveToNext()){
            Aluno aluno = new Aluno();
            String rgm = cursor.getString(cursor.getColumnIndex("rgm"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            aluno.setRgm(rgm);
            aluno.setNome(nome);
            alunos.add(aluno);
        }
        cursor.close();
        return alunos;
    }

    public Aluno consultarAluno(String rgm){
        System.out.println(rgm);
        Aluno aluno = new Aluno();
        String sql = "SELECT * FROM aluno WHERE rgm='" + rgm + "';";
        Cursor cursor = gw.getDatabase().rawQuery(sql, null);
        System.out.println(sql);

        if(cursor != null && cursor.moveToFirst()){
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setRgm(cursor.getString(cursor.getColumnIndex("rgm")));
            aluno.setCep(cursor.getString(cursor.getColumnIndex("cep")));
            aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            aluno.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            aluno.setCurso(cursor.getString(cursor.getColumnIndex("curso")));
            cursor.close();
        }
        System.out.println(aluno.getCurso());
        return aluno;
    }

    public boolean excluirAluno(String rgm){
        return gw.getDatabase().delete(TABLE_ALUNO, "rgm=?", new String[]{rgm + ""}) > 0;

    }

}
