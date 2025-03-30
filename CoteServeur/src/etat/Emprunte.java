package etat;

import document.EtatDocument;
import exception.EmpruntException;
import exception.ReservationException;
import mediatheque.Abonne;
import mediatheque.IDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Timer;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.mail.Session;

public class Emprunte extends Etat {
    private Abonne abonne;
    private List<Abonne> listeAlertes;
    private Timer dureeEmprunt;
    public Emprunte(IDocument document, Abonne abonne) {
        this.abonne = abonne;
        this.listeAlertes = new ArrayList<>();
        long deuxSemaines = 14L * 24 * 60 * 60 * 1000;
        dureeEmprunt = new Timer(abonne.getNom());
        dureeEmprunt.schedule(new FinEmprunt(document, abonne), deuxSemaines);
    }
    @Override
    public EtatDocument reserver(IDocument document, Abonne ab) throws ReservationException {
        listeAlertes.add(ab);
        throw new ReservationException("Ce " + document.toString() + " est déjà emprunté");
    }

    @Override
    public EtatDocument emprunter(IDocument document, Abonne ab) throws EmpruntException {
        throw new EmpruntException("Ce " + document.toString() + " est déjà emprunté");
    }

    @Override
    public EtatDocument retourner(IDocument document) {
        notifierAbonnes();
        dureeEmprunt.cancel();
        if (Math.random() > 0.6)
            abonne.bannir();
        return new Disponible();
    }

    private void notifierAbonnes() {
        for (Abonne abonne : listeAlertes) {
            envoyerAlerteMail(abonne);
        }
        listeAlertes.clear();
    }

    private void envoyerAlerteMail(Abonne abonne) {
        final String from = "jeanfrancois.brette@u-paris.fr";
        final String host = "smtp.wakantanka.com";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);

        Session session = Session.getDefaultInstance(properties);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(abonne.getEmail()));
            message.setSubject("Signal de fumée : Document disponible !");
            message.setText("Le document que vous avez demandé est maintenant disponible. Rendez-vous à la médiathèque !");

            Transport.send(message);
            System.out.println("Alerte envoyée à " + abonne.getNom());
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
