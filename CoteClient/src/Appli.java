import bttp2.Codage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Appli {
    public static void main(String[] arg) {
        String[] args = {"BTTP:localhost:4000"};
        if (args.length != 1 || !args[0].startsWith("BTTP:")) {
            System.err.println("Usage : BTTP:host:port");
            return;
        }

        String[] parts = args[0].substring(5).split(":");
        if (parts.length != 2) {
            return;
        }

        String host = parts[0];
        int port;
        try {
            port = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return;
        }

        try (
                Socket socket = new Socket(host, port);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Scanner scanner = new Scanner(System.in)
        ){
            String question = Codage.decoder(in.readLine());

            System.out.println(question);

            String reponse = scanner.nextLine();
            out.println(Codage.coder(reponse));


            String retour = Codage.decoder(in.readLine());
            if (retour != null)
                System.out.println(retour);
        } catch (IOException e){
            System.err.println("Erreur de communication : " + e.getMessage());
        }
        System.out.printf("Déconnexion terminée.");
    }
}
