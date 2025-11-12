package br.com.fiap.gig.domain.model;

public class Avaliacao {
    private int id_avaliacao;
    private String com_avaliacao;
    private int nota;
    private String dt_avaliacao;
    private Usuario avaliador;
    private Usuario avaliado;

    public Avaliacao (){}

    public Avaliacao(int id_avaliacao, String com_avaliacao, int nota, String dt_avaliacao, Usuario avaliador, Usuario avaliado) {
        this.id_avaliacao = id_avaliacao;
        this.com_avaliacao = com_avaliacao;
        this.nota = nota;
        this.dt_avaliacao = dt_avaliacao;
        this.avaliador = avaliador;
        this.avaliado = avaliado;
    }

    public int getId_avaliacao() {return id_avaliacao;}
    public void setId_avaliacao(int id_avaliacao) {this.id_avaliacao = id_avaliacao;}
    public String getCom_avaliacao() {return com_avaliacao;}
    public void setCom_avaliacao(String com_avaliacao) {this.com_avaliacao = com_avaliacao;}
    public int getNota() {return nota;}
    public void setNota(int nota) {this.nota = nota;}
    public String getDt_avaliacao() {return dt_avaliacao;}
    public void setDt_avaliacao(String dt_avaliacao) {this.dt_avaliacao = dt_avaliacao;}
    public Usuario getAvaliador() {return avaliador;}
    public void setAvaliador(Usuario avaliador) {this.avaliador = avaliador;}
    public Usuario getAvaliado() {return avaliado;}
    public void setAvaliado(Usuario avaliado) {this.avaliado = avaliado;}
}
