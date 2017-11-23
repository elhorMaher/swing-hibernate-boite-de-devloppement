/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isetjb.projet13;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author MALEK
 */
@Entity
@Table(name = "tache")
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    
     @Column(name = "titre", nullable = true)
    private String titre;
     
     @Column(name = "description", nullable = true)
    private String description;
 
     @Column(name = "dureeJours", nullable = true)
    private float dureeJours;
    

    @ManyToOne
    @JoinColumn(name = "id_developpeur", foreignKey = @ForeignKey(name = "Developpeur_ID_FK"))
    private Developpeur developpeur;
    
    @ManyToOne
    @JoinColumn(name = "id_projet", foreignKey = @ForeignKey(name = "Projet_ID_FK"))
    private Projet projet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getDureeJours() {
        return dureeJours;
    }

    public void setDureeJours(float dureeJours) {
        this.dureeJours = dureeJours;
    }

    public Developpeur getDeveloppeur() {
        return developpeur;
    }

    public void setDeveloppeur(Developpeur developpeur) {
        this.developpeur = developpeur;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

   
    
}
