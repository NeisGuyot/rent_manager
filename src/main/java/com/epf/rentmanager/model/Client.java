package com.epf.rentmanager.model;

import java.sql.Date;
import java.time.LocalDate;

public class Client {
    private long id;
    private String nom;
    private String prenom;
    private String mail;
    private LocalDate naissance;

    public Client(long id, String nom, String prenom, String mail, LocalDate naissance) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.naissance = naissance;
    }

    public Client(String nom, String prenom, String mail, LocalDate naissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.naissance = naissance;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mail='" + mail + '\'' +
                ", naissance=" + naissance +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public LocalDate getNaissance() {
        return naissance;
    }

    public Date toSQLNaissance() {
        return Date.valueOf(naissance);
    }

    public void setNaissance(LocalDate naissance) {
        this.naissance = naissance;
    }
}
