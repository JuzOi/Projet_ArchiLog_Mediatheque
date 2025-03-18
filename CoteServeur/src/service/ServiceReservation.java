package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
		System.out.println("*********Connexion "+this.numero+" démarrée :"+this.getClient().getInetAddress());
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader(getClient().getInputStream ( )));
			PrintWriter out = new PrintWriter (getClient().getOutputStream ( ), true);
			
			out.println("Veuillez saisir votre numéro d'abonné et le numéro du document que vous souhaitez réserver");
			try {
				String reponse = in.readLine();

				String[] parts = reponse.split(" ");
				int numAbonne = Integer.parseInt(parts[0]);
				int numDocument = Integer.parseInt(parts[1]);

				IDocument d = getDocument(numDocument);
				Abonne a = getAbonne(numAbonne);

				if (a != null && d != null) {
					synchronized (d) {
						d.reserver(a);
					}
				}

			} catch (ReservationException e) {
				out.println(e.getMessage());
			} catch (Exception e){
				out.println(e.getMessage());
			}
		} catch (IOException e) {}

		System.out.println("*********Connexion " + numero + " terminée");
		try {getClient().close();} catch (IOException e2) {}
	}

	protected void finalize() throws Throwable {
		getClient().close();
	}
}
