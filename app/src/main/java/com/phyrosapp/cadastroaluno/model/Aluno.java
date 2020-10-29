package com.phyrosapp.cadastroaluno.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Aluno implements Serializable {
    private String nome;
    private String rgm;
    private String cep;
    private String endereco;
    private String telefone;
    private String email;
    private String curso;
    private ArrayList<Disciplina> disciplinas;
    //public static final long  serialVersionUID = 100L;

    public Aluno() {
        this.disciplinas = new ArrayList<Disciplina>();
    }

    public int checarNumeroDisciplinas(){
        return disciplinas.size();
    }

    public void addDisciplina(Disciplina disciplina){
        this.disciplinas.add(disciplina);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRgm() {
        return rgm;
    }

    public void setRgm(String rgm) {
        this.rgm = rgm;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurso() {
        return curso;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }

    public ArrayList<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
}
