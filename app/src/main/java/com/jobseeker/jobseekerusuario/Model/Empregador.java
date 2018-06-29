package com.jobseeker.jobseekerusuario.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Empregador implements Parcelable {

    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("idGmail")
    @Expose
    private String idGmail;
    @SerializedName("requisitos")
    @Expose
    private String requisitos;
    @SerializedName("formacao")
    @Expose
    private String formacao;
    @SerializedName("experiencia")
    @Expose
    private String experiencia;
    @SerializedName("salario")
    @Expose
    private String salario;
    @SerializedName("local")
    @Expose
    private String local;
    @SerializedName("disponibilidade")
    @Expose
    private String disponibilidade;

    public Empregador(String nome, String email, String idGmail, String requisitos, String formacao,
            String experiencia, String salario, String local, String disponibilidade){
        this.nome = nome;
        this.email = email;
        this.idGmail = idGmail;
        this.requisitos=requisitos;
        this.formacao=formacao;
        this.experiencia=experiencia;
        this.salario=salario;
        this.local=local;
        this.disponibilidade=disponibilidade;
    }

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Empregador(Parcel in) {
        nome = in.readString();
        email = in.readString();
        idGmail = in.readString();
        requisitos=in.readString();
        formacao=in.readString();
        experiencia=in.readString();
        salario=in.readString();
        local=in.readString();
        disponibilidade=in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(nome);
        out.writeString(email);
        out.writeString(idGmail);
        out.writeString(requisitos);
        out.writeString(formacao);
        out.writeString(experiencia);
        out.writeString(salario);
        out.writeString(local);
        out.writeString(disponibilidade);
    }

    public static final Parcelable.Creator<Empregador> CREATOR = new Parcelable.Creator<Empregador>() {
        @Override
        public Empregador createFromParcel(Parcel in) {
            return new Empregador(in);
        }

        @Override
        public Empregador[] newArray(int size) {
            return new Empregador[size];
        }
    };



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdGmail() {
        return idGmail;
    }

    public void setIdGmail(String idGmail) {
        this.idGmail = idGmail;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

}