package br.com.fema.fipeactivity.classes;

import java.io.Serializable;

public class Tipo implements Serializable{

    public static final Tipo CARROS = new Tipo("carros");
    public static final Tipo MOTOS = new Tipo("motos");
    public static final Tipo CAMINHOES = new Tipo("caminhoes");

    private String nome;

    public Tipo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}