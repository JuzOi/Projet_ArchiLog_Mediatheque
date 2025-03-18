package service;

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
		System.out.println("*********Connexion "+this.numero+" démarrée :"+this.getClient().getInetAddress());
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader(getClient().getInputStream ( )));
			PrintWriter out = new PrintWriter (getClient().getOutputStream ( ), true);

			out.println("Veuillez saisir le numéro du document que vous souhaitez retourner");
			String reponse = in.readLine();
			int numDocument = Integer.parseInt(reponse);

			IDocument d = getDocument(numDocument);

			if (d != null)
				synchronized (d) {
					d.retourner();
				}

		} catch (IOException e) {}

		System.out.println("*********Connexion " + numero + " terminée");
		try {getClient().close();} catch (IOException e2) {}
	}

	protected void finalize() throws Throwable {
		getClient().close();
	}

}
