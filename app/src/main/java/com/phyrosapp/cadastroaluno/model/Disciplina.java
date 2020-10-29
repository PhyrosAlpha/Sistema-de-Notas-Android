package com.phyrosapp.cadastroaluno.model;

import java.io.Serializable;

public class Disciplina implements Serializable {
    private int idDisciplina;
    private String disciplina;
    private Double notaA1;
    private Double notaA2;
    private Double notaFinal;
    private Double media;
    private String status;  //Aprovado; Reprovado; Final

    public Disciplina() {

    }
    // public static final long  serialVersionUID = 100L;
    public void calcularMedia(){
        if(this.status.equals("Inativo")){
            setMedia(getNotaA1() + getNotaA2());
            avaliar();
        }else if(this.status.equals("Final")){
            double nota = maiorNota();
            setMedia(nota + getNotaFinal());
            avaliarFinal();
        }
    }

    private double maiorNota(){
        if(this.getNotaA1() > this.getNotaA2())
            return getNotaA1();
        else if(this.getNotaA1() < this.getNotaA2())
            return getNotaA2();
        else
            return getNotaA1();
    }

    private void avaliar(){
        if(media >= 6){
            setStatus("Aprovado");
        }else setStatus("Final");
    }

    private void avaliarFinal(){
        if(media >= 6){
            setStatus("Aprovado");
        }else setStatus("Reprovado");
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }
    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }
    public Disciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public Double getNotaA1() {
        return notaA1;
    }

    public void setNotaA1(Double notaA1) {
        this.notaA1 = notaA1;
    }

    public Double getNotaA2() {
        return notaA2;
    }

    public void setNotaA2(Double notaA2) {
        this.notaA2 = notaA2;
    }

    public Double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(Double notaFinal) {
        this.notaFinal = notaFinal;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}