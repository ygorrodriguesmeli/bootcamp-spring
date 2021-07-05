package com.meli.linktracker.entity;

public class Link {
    private long id;
    private int numberOfRedirects;
    private String senha;

    public Link(long id, String senha) {
        this.id = id;
        this.numberOfRedirects = 0;
        this.senha = senha;
    }

    public Link(long id) {
        this.id = id;
        this.numberOfRedirects = 0;
        this.senha = "";
    }

    public long getId() {
        return id;
    }

    public int getNumberOfRedirects() {
        return numberOfRedirects;
    }

    public String getSenha() {
        return senha;
    }

    public void setNumberOfRedirects(int numberOfRedirects) {
        this.numberOfRedirects = numberOfRedirects;
    }

    public void addNumberOfRedirects() {
        this.numberOfRedirects++;
    }
}
