package serveur;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur implements Runnable {
	private ServerSocket listen_socket;
	private Class<? extends Service> service;
	
	public Serveur(int port, Class<? extends Service> service) throws IOException {
		this.listen_socket = new ServerSocket(port);
		this.service = service;
	}

	@Override
	public void run() {
		try {
			System.err.println("Lancement du serveur au port : "+this.listen_socket.getLocalPort());
			while(true) 
				new Thread(service.getConstructor(Socket.class).newInstance(listen_socket.accept())).start();
			
		}catch(IOException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			try {
				this.listen_socket.close();
			}catch(IOException e1) {}
			System.err.println("Arrêt du serveur au port : " + listen_socket.getLocalPort());
		}
	}
	
	protected void finalize() {
		try {
			listen_socket.close();
		} catch(IOException e) {}
	}

}
