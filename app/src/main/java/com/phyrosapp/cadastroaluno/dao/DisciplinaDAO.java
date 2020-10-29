package com.phyrosapp.cadastroaluno.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.phyrosapp.cadastroaluno.model.Aluno;
import com.phyrosapp.cadastroaluno.model.Disciplina;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO{
    private final String TABLE_DISCIPLINA = "disciplina";
    private DbGateway gw;

    public DisciplinaDAO(Context ctx){
        gw = DbGateway.getInstance(ctx);
    }

    public boolean salvarDisciplina(String rgm, String nomeDisciplina){

        ContentValues cv = new ContentValues();
        cv.put("rgm", rgm);
        cv.put("disciplina", nomeDisciplina);

        return gw.getDatabase().insert(TABLE_DISCIPLINA, null, cv) > 0;
    }

    public boolean alteraDadosDisciplina(Disciplina disciplina){
        int id = disciplina.getIdDisciplina();
        double notaA1 = disciplina.getNotaA1();
        double notaA2 = disciplina.getNotaA2();
        double notaFinal = disciplina.getNotaFinal();
        double notaMedia = disciplina.getMedia();
        String status = disciplina.getStatus();

        ContentValues cv = new ContentValues();
        cv.put("notaA1", notaA1);
        cv.put("notaA2", notaA2);
        cv.put("notaFinal", notaFinal);
        cv.put("notaMedia", notaMedia);
        cv.put("status", status);

        return gw.getDatabase().update(TABLE_DISCIPLINA, cv, "idDisciplina=?", new String[]{id + ""} ) > 0;
    }


    public Disciplina consultarDisciplina(int idDisciplina){
        String id = Integer.toString(idDisciplina);
        Disciplina disciplina = new Disciplina();
        String sql = "SELECT * FROM disciplina WHERE idDisciplina=" + idDisciplina + ";";
        Cursor cursor = gw.getDatabase().rawQuery(sql, null);

        if(cursor != null && cursor.moveToFirst()){
            System.out.println(cursor.getDouble(cursor.getColumnIndex("notaA1")));
            disciplina.setIdDisciplina(cursor.getInt(cursor.getColumnIndex("idDisciplina")));
            disciplina.setDisciplina(cursor.getString(cursor.getColumnIndex("disciplina")));
            disciplina.setNotaA1(cursor.getDouble(cursor.getColumnIndex("notaA1")));
            disciplina.setNotaA2(cursor.getDouble(cursor.getColumnIndex("notaA2")));
            disciplina.setNotaFinal(cursor.getDouble(cursor.getColumnIndex("notaFinal")));
            disciplina.setMedia(cursor.getDouble(cursor.getColumnIndex("notaMedia")));
            disciplina.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            cursor.close();
        }

        System.out.println(disciplina.getDisciplina());
        return disciplina;
    }



    public List<Disciplina> retornarDisciplinas(String rgm){
        List<Disciplina> disciplinas = new ArrayList<>();
        String sql = "SELECT idDisciplina, disciplina, status FROM disciplina Where rgm='" + rgm + "' ORDER BY disciplina;";
        Cursor cursor = gw.getDatabase().rawQuery(sql, null);

        while(cursor.moveToNext()){
            Disciplina disciplina = new Disciplina();
            int idDisciplina = cursor.getInt(cursor.getColumnIndex("idDisciplina"));
            String nomeDisciplina = cursor.getString(cursor.getColumnIndex("disciplina"));
            String status = cursor.getString(cursor.getColumnIndex("status"));
            disciplina.setIdDisciplina(idDisciplina);
            disciplina.setDisciplina(nomeDisciplina);
            disciplina.setStatus(status);
            disciplinas.add(disciplina);
        }
        cursor.close();
        return disciplinas;
    }

    public boolean excluirDisciplina(int id){
        return gw.getDatabase().delete(TABLE_DISCIPLINA, "idDisciplina=?", new String[]{id + ""}) > 0;
    }

}
