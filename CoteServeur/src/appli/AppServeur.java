package appli;

import document.Dvd;
import document.Livre;
import etat.Disponible;
import mediatheque.Abonne;
import mediatheque.IDocument;
import serveur.Serveur;
import serveur.Service;
import service.ServiceEmprunt;
import service.ServiceReservation;
import service.ServiceRetour;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppServeur {
    public static void main(String[] args) {
        try {
            List<IDocument> documents = initDocuments();
            List<Abonne> abonnes = initAbonnes();

            Service.setLesDocuments(documents, abonnes);

            new Thread(new Serveur(ServiceReservation.class, 2000)).start();
            new Thread(new Serveur(ServiceEmprunt.class, 3000)).start();
            new Thread(new Serveur(ServiceRetour.class, 4000)).start();

        } catch (IOException e){
            System.err.println("Pb lors de la création des serveurs : " + e);
        }
    }

    public static List<IDocument> initDocuments() {
        List<IDocument> documents = new ArrayList<>();

        documents.add(new Livre(1, "Les Misérables", 1500, new Disponible()));
        documents.add(new Livre(2, "Le Petit Prince", 96, new Disponible()));
        documents.add(new Livre(3, "1984", 328, new Disponible()));
        documents.add(new Livre(4, "L'Étranger", 123, new Disponible()));
        documents.add(new Livre(5, "Harry Potter à l'école des sorciers", 350, new Disponible()));
        documents.add(new Livre(6, "Le Seigneur des Anneaux", 1178, new Disponible()));
        documents.add(new Livre(7, "La Peste", 320, new Disponible()));
        documents.add(new Livre(8, "Le Comte de Monte-Cristo", 1312, new Disponible()));
        documents.add(new Livre(9, "Don Quichotte", 863, new Disponible()));
        documents.add(new Livre(10, "Le Rouge et le Noir", 576, new Disponible()));

        documents.add(new Dvd(11, "Inception", false, new Disponible()));
        documents.add(new Dvd(12, "Le Roi Lion", false, new Disponible()));
        documents.add(new Dvd(13, "Interstellar", false, new Disponible()));
        documents.add(new Dvd(14, "Le Parrain", true, new Disponible()));
        documents.add(new Dvd(15, "Pulp Fiction", true, new Disponible()));
        documents.add(new Dvd(16, "La La Land", false, new Disponible()));
        documents.add(new Dvd(17, "Joker", true, new Disponible()));
        documents.add(new Dvd(18, "Le Fabuleux Destin d’Amélie Poulain", false, new Disponible()));
        documents.add(new Dvd(19, "Fight Club", true, new Disponible()));
        documents.add(new Dvd(20, "Shrek", false, new Disponible()));

        return documents;
    }

    public static List<Abonne> initAbonnes() {
        List<Abonne> abonnes = new ArrayList<>();

        abonnes.add(new Abonne(1, "Alice Martin", LocalDate.of(1990, 5, 12), "alice.martin@email.com"));
        abonnes.add(new Abonne(2, "Bob Dupont", LocalDate.of(1985, 3, 28), "bob.dupont@email.com"));
        abonnes.add(new Abonne(3, "Chloé Durant", LocalDate.of(2002, 7, 15), "chloe.durant@email.com"));
        abonnes.add(new Abonne(4, "David Moreau", LocalDate.of(2010, 10, 5), "david.moreau@email.com"));
        abonnes.add(new Abonne(5, "Emma Leclerc", LocalDate.of(2005, 2, 18), "emma.leclerc@email.com"));
        abonnes.add(new Abonne(6, "Fabien Garnier", LocalDate.of(1998, 8, 30), "fabien.garnier@email.com"));
        abonnes.add(new Abonne(7, "Gaëlle Marchand", LocalDate.of(1975, 12, 22), "gaelle.marchand@email.com"));
        abonnes.add(new Abonne(8, "Hugo Mercier", LocalDate.of(2008, 9, 10), "hugo.mercier@email.com"));
        abonnes.add(new Abonne(9, "Inès Bernard", LocalDate.of(2000, 11, 3), "ines.bernard@email.com"));
        abonnes.add(new Abonne(10, "Jules Petit", LocalDate.of(2012, 1, 20), "jules.petit@email.com"));
        abonnes.add(new Abonne(11, "Karine Leroy", LocalDate.of(1993, 4, 14), "karine.leroy@email.com"));
        abonnes.add(new Abonne(12, "Louis Fontaine", LocalDate.of(1980, 6, 25), "louis.fontaine@email.com"));
        abonnes.add(new Abonne(13, "Marie Lambert", LocalDate.of(2003, 3, 6), "marie.lambert@email.com"));
        abonnes.add(new Abonne(14, "Nicolas Perrin", LocalDate.of(2007, 7, 9), "nicolas.perrin@email.com"));
        abonnes.add(new Abonne(15, "Océane Girard", LocalDate.of(1995, 10, 17), "oceane.girard@email.com"));

        return abonnes;
    }
}
