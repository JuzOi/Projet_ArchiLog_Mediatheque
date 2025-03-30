package service;

import bttp2.Codage;
import exception.ReservationException;
import mediatheque.Abonne;
import mediatheque.IDocument;
import serveur.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServiceRetour extends Service {
	private static int cpt = 1;
	private final int numero;

	public ServiceRetour(Socket socket) {
		super(socket);
		numero = cpt++;
	}

	@Override
	public void run() {
		System.out.println("*********Connexion "+this.numero+" démarrée :"+this.getClient().getInetAddress() + " au service Retour");
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader(getClient().getInputStream ( )));
			PrintWriter out = new PrintWriter (getClient().getOutputStream ( ), true);

			out.println("Veuillez saisir le numéro du document que vous souhaitez retourner");

			String reponse = Codage.decoder(in.readLine());

			String[] lignes = reponse.split("\n");
			StringBuilder retour = new StringBuilder();

			for (String ligne : lignes) {
				if (!retour.isEmpty())
					retour.append("\n");
				try {
					int numDocument = Integer.parseInt(ligne);

					IDocument d = getDocument(numDocument);

					if (d != null){
						d.retourner();
						retour.append(d.toString()).append(" retourné avec succès");
					}
					else
						retour.append("Le document n°" + numDocument + " n'existe pas");
				}catch (Exception e) {
					retour.append("Veuillez saisir <numéro du document> valide");
				}
			}
			out.println(Codage.coder(retour.toString()));
		} catch (IOException e) {}

		System.out.println("*********Connexion " + numero + " au service Retour terminée");
		try {getClient().close();} catch (IOException e2) {}
	}

	protected void finalize() throws Throwable {
		getClient().close();
	}

}
