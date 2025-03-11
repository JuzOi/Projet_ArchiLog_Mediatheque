package serveur;

import mediatheque.Abonne;
import mediatheque.IDocument;

import java.net.Socket;
import java.util.List;

public abstract class Service implements Runnable {
	private static List<IDocument> documents;
	private static List<Abonne> abonnes;
	
	public static void setLesDocuments(List<IDocument> documents, List<Abonne> abonnes) {
		Service.documents = documents;
		Service.abonnes = abonnes;
	}
	
	protected static IDocument getDocument(int noDocument) {
		for (IDocument d : documents) {
			if (d.numero() == noDocument)
				return d;
		}
		return null;
	}
	
	protected static Abonne getAbonne(int noAbonne) {
		for (Abonne a : abonnes) {
			if (a.numero() == noAbonne)
				return a;
		}
		return null;
	}
	
	private final Socket client;
	
	public Service(Socket socket) {
		this.client = socket;
	}
	
	public Socket getClient() {
		return client;
	}
	
	@Override
	public void run() {
		
	}
}
