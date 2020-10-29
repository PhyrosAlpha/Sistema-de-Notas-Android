package com.phyrosapp.cadastroaluno.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class UniversidadeDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Universidade.db";
    private static final int DATABASE_VERSION = 1;
    private final String SQL_CREATE_ALUNO = "CREATE TABLE aluno"+
                                            "(rgm VARCHAR(8) PRIMARY KEY NOT NULL,"+
                                            "nome VARCHAR(50) NOT NULL,"+
                                            "cep VARCHAR(15),"+
                                            "email VARCHAR(50) UNIQUE,"+
                                            "endereco VARCHAR(50),"+
                                            "telefone VARCHAR(16),"+
                                            "curso VARCHAR(20) NOT NULL);";


    private final String SQL_CREATE_DISCIPLINA = "CREATE TABLE disciplina"+
                                                "(idDisciplina integer NOT NULL PRIMARY KEY AUTOINCREMENT,"+
                                                "rgm VARCHAR(8) NOT NULL,"+
                                                "disciplina VARCHAR(50) NOT NULL,"+
                                                "notaA1 DECIMAL(3,1) DEFAULT '0.0',"+
                                                "notaA2 DECIMAL(3,1) DEFAULT '0.0',"+
                                                "notaFinal DECIMAL(3,1) DEFAULT '0.0',"+
                                                "notaMedia DECIMAL(3,1) DEFAULT '0.0',"+
                                                "status VARCHAR(15) DEFAULT 'Inativo',"+
                                                "FOREIGN KEY (rgm) REFERENCES aluno(rgm));";

    private static final String SQL_DELETE_ALUNO = "DROP TABLE IF EXISTS ALUNO";
    private static final String SQL_DELETE_DISCIPLINA = "DROP TABLE IF EXISTS DISCIPLINA";



    public UniversidadeDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ALUNO);
        db.execSQL(SQL_CREATE_DISCIPLINA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_DISCIPLINA);
        db.execSQL(SQL_DELETE_ALUNO);
        onCreate(db);

    }
}
