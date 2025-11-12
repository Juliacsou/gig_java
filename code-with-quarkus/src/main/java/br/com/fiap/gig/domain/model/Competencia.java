package br.com.fiap.gig.domain.model;

public class Competencia {
    private int id_competencia;
    private String nome_competencia;
    private float peso;

    public Competencia (){}

    public Competencia(int id_competencia, String nome_competencia, float peso) {
        this.id_competencia = id_competencia;
        this.nome_competencia = nome_competencia;
        this.peso = peso;
    }

    public int getId_competencia() {return id_competencia;}
    public void setId_competencia(int id_competencia) {this.id_competencia = id_competencia;}
    public String getNome_competencia() {return nome_competencia;}
    public void setNome_competencia(String nome_competencia) {this.nome_competencia = nome_competencia;}
    public float getPeso() {return peso;}
    public void setPeso(float peso) {this.peso = peso;}
}
