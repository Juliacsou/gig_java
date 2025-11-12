package br.com.fiap.gig.domain.model;

public class Score {
    private int id_score;
    private float score_total;
    private float nota_media;
    private Usuario usuario;

    public Score (){}

    public Score(int id_score, float score_total, float nota_media, Usuario usuario) {
        this.id_score = id_score;
        this.score_total = score_total;
        this.nota_media = nota_media;
        this.usuario = usuario;
    }

    public int getId_score() {return id_score;}
    public void setId_score(int id_score) {this.id_score = id_score;}
    public float getScore_total() {return score_total;}
    public void setScore_total(float score_total) {this.score_total = score_total;}
    public float getNota_media() {return nota_media;}
    public void setNota_media(float nota_media) {this.nota_media = nota_media;}
    public Usuario getUsuario() {return usuario;}
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}

}
