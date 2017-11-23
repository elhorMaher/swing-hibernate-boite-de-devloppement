/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isetjb.projet13;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author MALEK
 */
public class Application {

    /**
     * Attribute declaration for factory to share between methods.
     */
    private static SessionFactory factory;

    public static void main(String[] args) {
        System.out.println("JavaSE + Maven + Hibernate + MySQL : Many to One Association");

        // Open connection  pool
        factory = HibernateUtil.getSessionFactory();

        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // new Client
            Client client_a = new Client();
            client_a.setNom("Ben Yaakoub");
            client_a.setPrenom("Monaam");
            client_a.setAdresse("Djerba");
            session.save(client_a);

            // new Client
            Client client_b = new Client();
            client_b.setNom("El Hor");
            client_b.setPrenom("Maher");
            client_b.setAdresse("Djerba");
            session.save(client_b);

            // new Projet
            Projet projet_a = new Projet();
            projet_a.setNom("projet_a");
            projet_a.setField("projet de client a");
            projet_a.setClient(client_a);
            session.save(projet_a);

            // new Projet
            Projet projet_b = new Projet();
            projet_b.setNom("projet_b");
            projet_b.setField("projet de client b");
            projet_b.setClient(client_b);
            session.save(projet_b);

            // new Projet
            Projet projet_c = new Projet();
            projet_c.setNom("projet_c");
            projet_c.setField("projet de client a");
            projet_c.setClient(client_a);
            session.save(projet_c);

            //new Developpeur
            Developpeur developpeur_a = new Developpeur();
            developpeur_a.setNom("developpeur_a");
            session.save(developpeur_a);

            //new Developpeur
            Developpeur developpeur_b = new Developpeur();
            developpeur_b.setNom("developpeur_b");
            session.save(developpeur_b);

            // new Tache
            Tache tache_a = new Tache();
            tache_a.setTitre("tache_a");
            tache_a.setDescription("tache_a");
            tache_a.setDureeJours(12);
            tache_a.setProjet(projet_c);
            tache_a.setDeveloppeur(developpeur_b);
            session.save(tache_a);

               // new Tache
            Tache tache_b = new Tache();
            tache_b.setTitre("tache_b");
            tache_b.setDescription("tache_b");
            tache_b.setDureeJours(25);
            tache_b.setProjet(projet_a);
            tache_b.setDeveloppeur(developpeur_a);
            session.save(tache_b);
            
            
            //new Etiquette
            Etiquette etiq_a = new Etiquette();
            etiq_a.setLibelle("etiquette a");
            etiq_a.setCouleur("rouge");
            etiq_a.getProjets().add(projet_a);
            session.save(etiq_a);

            // Projet list by executing HQL Query
            List projets = session.createQuery("FROM Projet").list();

            for (Iterator iterator = projets.iterator(); iterator.hasNext();) {
                Projet proj = (Projet) iterator.next();
                System.out.print("ID: " + proj.getId());
                System.out.print(" ===> Nom: " + proj.getNom());
                System.out.print(" ===> Field: " + proj.getField());
                System.out.println(" ===> Client: " + proj.getClient());
                System.out.println(" ===> Etiquette: " + proj.getEtiquettes());
            }

            List taches = session.createQuery("FROM Tache").list();

            for (Iterator iterator = taches.iterator(); iterator.hasNext();) {
                Tache tache = (Tache) iterator.next();
                System.out.print("ID: " + tache.getId());
                System.out.print(" ===> Titre: " + tache.getTitre());
                System.out.print(" ===> Description: " + tache.getDescription());
                System.out.println(" ===> Developpeur: " + tache.getDeveloppeur());
                System.out.println(" ===> Projet: " + tache.getProjet());
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            //e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            session.close();
        }

        // Cleaning up connection pool
        factory.close();
    }
}
