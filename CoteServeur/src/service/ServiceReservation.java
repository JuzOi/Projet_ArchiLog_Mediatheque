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
	
	
	public ServiceReservation(Socket socket) {
		super(socket);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader(getClient().getInputStream ( )));
			PrintWriter out = new PrintWriter (getClient().getOutputStream ( ), true);
			
			out.println("Veuillez saisir votre numéro d'abonné et le numéro du document que vous souhaitez réserver");
			String reponse = in.readLine();
			String[] parts = reponse.split(" ");
			int numAbonne = Integer.parseInt(parts[0]);
			int numDocument = Integer.parseInt(parts[1]);
			
			IDocument d = getDocument(numDocument);
			Abonne a = getAbonne(numAbonne);
			
			try {
				d.reserver(a);
			} catch (ReservationException e) {
				e.getMessage();
			}
		} catch (IOException e) {
			
		}
		
	}

}
