package com.jobseeker.jobseekerusuario.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trabalhador implements Parcelable {

    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("nascimento")
    @Expose
    private String nascimento;
    @SerializedName("cpf")
    @Expose
    private String cpf;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("nacionalidade")
    @Expose
    private String nacionalidade;
    @SerializedName("estadoCivil")
    @Expose
    private String estadoCivil;
    @SerializedName("endereco")
    @Expose
    private String endereco;
    @SerializedName("cidade")
    @Expose
    private String cidade;
    @SerializedName("unidadeFederal")
    @Expose
    private String unidadeFederal;
    @SerializedName("telefone")
    @Expose
    private String telefone;
    @SerializedName("celular")
    @Expose
    private String celular;
    @SerializedName("infoPessoal")
    @Expose
    private String infoPessoal;
    @SerializedName("idGmail")
    @Expose
    private String idGmail;
    @SerializedName("objetivo")
    @Expose
    private String objetivo;
    @SerializedName("perfil")
    @Expose
    private String perfil;
    @SerializedName("experiencia")
    @Expose
    private String experiencia;
    @SerializedName("formacao")
    @Expose
    private String formacao;
    @SerializedName("cursoComplementar")
    @Expose
    private String cursoComplementar;
    @SerializedName("idiomas")
    @Expose
    private String idiomas;
    @SerializedName("infoProfissional")
    @Expose
    private String infoProfissional;

    public Trabalhador(String nome, String nascimento, String cpf, String email,
                       String nacionalidade, String estadoCivil, String endereco, String cidade,
                        String unidadeFederal, String telefone, String celular, String infoPessoal,
                        String idGmail, String objetivo, String perfil, String experiencia,
                        String formacao, String cursoComplementar, String idiomas,
                       String infoProfissional){
        this.nome = nome;
        this.nascimento = nascimento;
        this.cpf = cpf;
        this.email = email;
        this.nacionalidade = nacionalidade;
        this.estadoCivil = estadoCivil;
        this.endereco = endereco;
        this.cidade = cidade;
        this.unidadeFederal = unidadeFederal;
        this.telefone = telefone;
        this.celular = celular;
        this.infoPessoal = infoPessoal;
        this.idGmail = idGmail;
        this.objetivo = objetivo;
        this.perfil = perfil;
        this.experiencia = experiencia;
        this.formacao = formacao;
        this.cursoComplementar = cursoComplementar;
        this.idiomas = idiomas;
        this.infoProfissional = infoProfissional;
    }

    public Trabalhador(Parcel in){
        nome = in.readString();
        nascimento = in.readString();
        cpf = in.readString();
        email = in.readString();
        nacionalidade = in.readString();
        estadoCivil = in.readString();
        endereco = in.readString();
        cidade = in.readString();
        unidadeFederal = in.readString();
        telefone = in.readString();
        celular = in.readString();
        infoPessoal = in.readString();
        idGmail = in.readString();
        objetivo = in.readString();
        perfil = in.readString();
        experiencia = in.readString();
        formacao = in.readString();
        cursoComplementar = in.readString();
        idiomas = in.readString();
        infoProfissional = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(nome);
        out.writeString(nascimento);
        out.writeString(cpf);
        out.writeString(email);
        out.writeString(nacionalidade);
        out.writeString(estadoCivil);
        out.writeString(endereco);
        out.writeString(cidade);
        out.writeString(unidadeFederal);
        out.writeString(telefone);
        out.writeString(celular);
        out.writeString(infoPessoal);
        out.writeString(idGmail);
        out.writeString(objetivo);
        out.writeString(perfil);
        out.writeString(experiencia);
        out.writeString(formacao);
        out.writeString(cursoComplementar);
        out.writeString(idiomas);
        out.writeString(infoProfissional);
    }

    public static final Parcelable.Creator<Trabalhador> CREATOR = new Parcelable.Creator<Trabalhador>() {
        @Override
        public Trabalhador createFromParcel(Parcel in) {
            return new Trabalhador(in);
        }

        @Override
        public Trabalhador[] newArray(int size) {
            return new Trabalhador[size];
        }
    };


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUnidadeFederal() {
        return unidadeFederal;
    }

    public void setUnidadeFederal(String unidadeFederal) {
        this.unidadeFederal = unidadeFederal;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getInfoPessoal() {
        return infoPessoal;
    }

    public void setInfoPessoal(String infoPessoal) {
        this.infoPessoal = infoPessoal;
    }

    public String getIdGmail() {
        return idGmail;
    }

    public void setIdGmail(String idGmail) {
        this.idGmail = idGmail;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public String getCursoComplementar() {
        return cursoComplementar;
    }

    public void setCursoComplementar(String cursoComplementar) {
        this.cursoComplementar = cursoComplementar;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public String getInfoProfissional() {
        return infoProfissional;
    }

    public void setInfoProfissional(String infoProfissional) {
        this.infoProfissional = infoProfissional;
    }
}