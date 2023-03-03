package com.epf.rentmanager.model;

import java.sql.Date;
import java.time.LocalDate;

public class Reservation {

    private int id;
    private long client_id;
    private int vehicule_id;
    private LocalDate debut;
    private LocalDate fin;

    public Reservation(int id, long client, int vehicule_id, LocalDate debut, LocalDate fin) {
        this.id = id;
        this.client_id = client;
        this.vehicule_id = vehicule_id;
        this.debut = debut;
        this.fin = fin;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", client_id=" + client_id +
                ", vehicule_id=" + vehicule_id +
                ", debut=" + debut +
                ", fin=" + fin +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public int getVehicule_id() {
        return vehicule_id;
    }

    public void setVehicule_id(int vehicule_id) {
        this.vehicule_id = vehicule_id;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public LocalDate getFin() {
        return fin;
    }

    public Date toSQLFin() {
        return Date.valueOf(fin);
    }

    public Date toSQLDebut() {
        return Date.valueOf(debut);
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }
}
