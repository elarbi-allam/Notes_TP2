package com.elarbiallam.notes;

import java.io.Serializable;

public class Note implements Serializable {
    private String nom;
    private String description;
    private String date;
    private String priorite; // "Haute", "Moyenne", "Basse"

    public Note(String nom, String description, String date, String priorite) {
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.priorite = priorite;
    }

    // Getters
    public String getNom() { return nom; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public String getPriorite() { return priorite; }
}