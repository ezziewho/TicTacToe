import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 9090; //const do portu
    private static ArrayList<ClientHandler> clients = new ArrayList<>(); //potrzbujemy gdzies przechowywac wszystkie watki ktore stworzymy
    private static ExecutorService pool = Executors.newFixedThreadPool(10);
    private static PrintWriter out;

    //PrintWriter

    public static void main(String[] args) throws IOException {

        //char[] board = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}; //server tez musi miec swojego boarda

        ServerSocket listener = new ServerSocket(PORT); //tworzymy obiekt server i nazywamy go listener bo nasluchuje for client connections

        while (true) {
            System.out.println("[SERVER] IS WAITING FOR CLIENT CONNECTION..."); // to sie wyswietli w serwerze

            Socket client = listener.accept(); //listener accapt to metoda ktora pozwala listenerowi na nawiazanie polaczenia, i tworzy obiekt socket ktory odwpwiada na to konkretne polaczenie
            System.out.println("[SERVER] CONNECTED TO CLIENT 1...");


            Socket client2 = listener.accept(); // drugi klient
            System.out.println("[SERVER] CONNECTED TO CLIENT 2...");


            ClientHandler clientThread = new ClientHandler(client, client2); //kiedy juz zaakcaptujrmy polaczenie to tworzymy obiekt ClientHandlera
            clients.add(clientThread); //dodajemy se go do listy klientow
            pool.execute(clientThread);


        }
    }
}
