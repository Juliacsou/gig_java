package br.com.fiap.gig.domain.model;

public class Usuario {
    private String nome_usuario;
    private String cpf_usuario;
    private String senha;
    private String dt_nasc;
    private String end_usuario;
    private String mail_usuario;

    public Usuario(){ }

    public Usuario(String nome_usuario, String cpf_usuario, String senha, String dt_nasc, String end_usuario, String mail_usuario) {
        this.nome_usuario = nome_usuario;
        this.cpf_usuario = cpf_usuario;
        this.senha = senha;
        this.dt_nasc = dt_nasc;
        this.end_usuario = end_usuario;
        this.mail_usuario = mail_usuario;
    }

    public String getNome_usuario() {return nome_usuario;}
    public void setNome_usuario(String nome_usuario) {this.nome_usuario = nome_usuario;}
    public String getCpf_usuario() {return cpf_usuario;}
    public void setCpf_usuario(String cpf_usuario) {this.cpf_usuario = cpf_usuario;}
    public String getSenha() {return senha;}
    public void setSenha(String senha) {this.senha = senha;}
    public String getDt_nasc() {return dt_nasc;}
    public void setDt_nasc(String dt_nasc) {this.dt_nasc = dt_nasc;}
    public String getEnd_usuario() {return end_usuario;}
    public void setEnd_usuario(String end_usuario) {this.end_usuario = end_usuario;}
    public String getMail_usuario() {return mail_usuario;}
    public void setMail_usuario(String mail_usuario) {this.mail_usuario = mail_usuario;}
}
