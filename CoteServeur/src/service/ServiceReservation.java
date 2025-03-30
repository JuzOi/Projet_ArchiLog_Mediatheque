package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import bttp2.Codage;
import exception.ReservationException;
import mediatheque.Abonne;
import mediatheque.IDocument;
import serveur.Service;

public class ServiceReservation extends Service {
	private static int cpt = 1;
	private final int numero;
	
	public ServiceReservation(Socket socket) {
		super(socket);
		numero = cpt++;
	}

	@Override
	public void run() {
		System.out.println("*********Connexion "+this.numero+" démarrée :"+this.getClient().getInetAddress() + " au service Réservation");
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader(getClient().getInputStream ( )));
			PrintWriter out = new PrintWriter (getClient().getOutputStream ( ), true);
			out.println("Veuillez saisir votre numéro d'abonné et le numéro du document que vous souhaitez réserver");

			String reponse = Codage.decoder(in.readLine());

			String[] lignes = reponse.split("\n");
			StringBuilder retour = new StringBuilder();
			for (String ligne : lignes) {
				if (!retour.isEmpty())
					retour.append("\n");
				try {
					String[] parts = ligne.split(" ");

					int numAbonne = Integer.parseInt(parts[0]);
					int numDocument = Integer.parseInt(parts[1]);

					IDocument d = getDocument(numDocument);
					Abonne a = getAbonne(numAbonne);

					if (a != null && d != null) {
						if (a.estBanni()) {
							retour.append("Vous êtes banni de la tribu");
							break;
						}
						try {
							d.reserver(a);
							retour.append(d.toString()).append(" n°").append(d.numero()).append(" réservé avec succès !");
						} catch (ReservationException e) {
							retour.append(e.getMessage());
						}
					}
				} catch (Exception e) {
					retour.append("Veuillez saisir <numéro d'abonné> <numéro du document>");
				}
			}
			out.println(Codage.coder(retour.toString()));
		} catch (IOException e) {}

		System.out.println("*********Connexion " + numero + " au service Réservation terminée");
		try {getClient().close();} catch (IOException e2) {}
	}

	protected void finalize() throws Throwable {
		getClient().close();
	}
}
