import TTTDodatki.TicTacToe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


import entity.Game;
import entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;







////klasa dla serwera zeby obslugiwal kazdego klienta
public class ClientHandler implements Runnable {



    private Socket client;
    private Socket client2;
    private BufferedReader in;
    private PrintWriter out;

    private BufferedReader in2;
    private PrintWriter out2;

    //Scanner input_konsola = new Scanner(System.in);
    char[] board = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    //private ArrayList<ClientHandler> clients;

    int moveCounter = 0;

    //konstruktor, input to socket, bo nadal potrzebujey zeby serwer akceptowal prosbe zeby sie polaczyc
    // i to zwraca socket klienta, wiec kiedy socket gets returned to wtedy tworzymy handlera i wreczam mu socketa ktory wlasnie zostal stworzony
    public ClientHandler(Socket clientSocket, Socket clientSocket2) throws IOException {

        this.client = clientSocket;
        this.client2 = clientSocket2;
        // this.clients = clients;

        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);

        in2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
        out2 = new PrintWriter(client2.getOutputStream(), true);

    }

    @Override
    public void run() {


        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        //Game gra = new Game();
        Player gracz1 = new Player();
        Player gracz2 = new Player();

        Scanner input_konsola = new Scanner(System.in);
        //out.println(" "); //out.to co chcemy wyslac do klienta
        out.println(" Witaj w grze kolko i krzyzyk! Jestes X "); //out.to co chcemy wyslac do klienta
        out2.println(" Witaj w grze kolko i krzyzyk! Jestes O "); //out.to co chcemy wyslac do klienta

        try {
            String nickX = in.readLine();
            gracz1.setName(nickX);
            session.save(gracz1);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String nickO = in2.readLine();
            gracz2.setName(nickO);
            session.save(gracz2);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }





        out.println("X");
        out2.println("O"); //wysylamy symbol na reszte gry

        String Winner = "??";

        char gameResult = '?';

        Game gra = new Game();
        gra.setIdPlayer1(gracz1.getIdPlayer());
        gra.setIdPlayer2(gracz2.getIdPlayer());
        //gra.setResult("remis");
       // session.save(gra);

        try {
            while (client.isConnected() && client2.isConnected()) {

                String request = in.readLine(); //odbieramy od krzyzyka

                int pole = Integer.valueOf(request);
                TicTacToe.ruch(board, pole, 'X'); //zaznaczamy sobie na swoim boardzie

                //out2.println(request); //wysylamy do kolka

                if (TicTacToe.checkwin(board, 'X') == 'X') //checkwin dla krzyzyka
                {

                    String rresult = Integer.toString(gracz1.getIdPlayer());
                    gra.setResult(rresult);
                    session.save(gra);

                    Winner = "X";
                    gameResult = 'X';
                    out.println(Winner); //wysylamy do kzrysyka ze wygral
                    request = Winner;

                }

                out2.println(request); //wysylamy do kolka i to jest albo ruch albo znak ze przegral

                if (TicTacToe.checkwin(board, 'X') == 'X') //checkwin dla krzyzyka
                {
                    out2.println(pole); //wysylamy ostatnie pole do kolka zeby se wyswietlil tablice
                }

                request = in2.readLine(); //odbieramy ruch od kolka

                pole = Integer.valueOf(request);
                TicTacToe.ruch(board, pole, 'O'); //zaznaczamy ruch od kolka na tablicy

                if (TicTacToe.checkwin(board, 'O') == 'O') //checkwin dla kolka
                {
                    String rresult = Integer.toString(gracz2.getIdPlayer());
                    gra.setResult(rresult);
                    session.save(gra);

                    Winner = "O";
                    gameResult = 'O';
                    out2.println(Winner);
                    request = Winner;
                    //break;
                }


                if(TicTacToe.checkdraw(board) > 7 && Winner != "O"){

                    gra.setResult("remis");
                    session.save(gra);


                    Winner = "-";
                    gameResult = '-';
                    request = Winner;
                    out.println(request);
                    out.println(pole); //wysylamy do krzyzyka info ostanim ruchu kolka zeby se widzial
                    out2.println(request);
                }

                out.println(request); //wysylamy do krzyzyka

                if (TicTacToe.checkwin(board, 'O') == 'O') //checkwin dla kolka
                {
                    out.println(pole); //wysylamy do krzyzyka info ostanim ruchu kolka zeby se widzial
                }
            }
        } catch (
                IOException e) { //jak jest throw exeption to program sie wywala wiec nie chcemy tak tylko chcemy sie rpoblemem zajac
            // czyli jesli cos sie wywali to to cos jest przechowane w obiekcie i lapane e to obiekt zaieracjacy info o errorze

            gra.setResult("przerwana");
            session.save(gra);

            System.err.println("IOExeption  in clientHandler");
            System.err.println(e.getStackTrace());
        } finally {
            //out.print("x");
            //out.println(print_board());
            out.close(); //zamykamy polaczenie zeby serwer nie nasluchiwal caly czas
            try {
                in.close(); // i tez zamykamy llistenera
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        session.getTransaction().commit();
        session.close();




    }


}
