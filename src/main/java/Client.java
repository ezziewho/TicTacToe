import TTTDodatki.TicTacToe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Client {


    static int moveCounter = 0;
    private static final String SERVER_IP = "127.0.0.1"; //const do portu
    private static final int SERVER_PORT = 9090; //const do portu

    public static void main(String[] args) throws IOException {


        Socket socket = new Socket(SERVER_IP, SERVER_PORT); //tworzymy polaczenie z serwerem

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())); //musimy jakos odbierac informacje wiec odbieramy je od socket servera
        // BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in)); // czytamy z klawiatury
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true); //tu zeby wysylac do serwera
        Scanner in = new Scanner(System.in);


        //String[] board = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        char[] board = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        System.out.print("Czekam na przeciwnika... \n");
        String serverResponse = input.readLine(); //czytamy linie z serwera
        System.out.print("[SERVER]: " + serverResponse + "\n"); //mowi ci kim jestes
        System.out.print("PODAJ NICK: "); //mowi ci kim jestes
        String nick = in.nextLine();
        out.println(nick);


        TicTacToe.print_board(board); //odbieramy stringa i on ustawi twoj symbol na reszte gry
        serverResponse = input.readLine();

        char symbol = serverResponse.charAt(0);
        char symbolPrzeciwnika = '-';

        //String symbolS =  Character.toString(symbol);
        //System.out.print("SYMBOL TWOJ " + symbolS + "\n");


        // String symbolPrzeciwnikaS =  Character.toString(symbolPrzeciwnika);


        if (symbol == 'X') {
            symbolPrzeciwnika = 'O';
            //symbolPrzeciwnikaS = "O";
            TicTacToe.Ruch(symbol, board, in, out); //od razu wysylamy do severa ruch krzyzyka
        }

        if (symbol == 'O') {
            symbolPrzeciwnika = 'X';
            //symbolPrzeciwnikaS = "X";
        }
        int i =0;

        try {
            while (serverResponse != null) {

                System.out.print("... \n");

                serverResponse = input.readLine(); //odbieramy ruch przeciwnika albo info o przegranej, wygranej

                if (serverResponse.charAt(0) == '-') {
                    if(symbol=='X'){
                        String ostatniRuch = input.readLine();
                        int oR = Integer.valueOf(ostatniRuch);
                        TicTacToe.RuchPrzeciwnika(symbolPrzeciwnika, board, oR);
                    }
                    System.out.print("Remis \n");
                    break;
                }

                if (i>0 && serverResponse.charAt(0) == symbol) {
                    System.out.print("Wygrana \n");
                    out.println(serverResponse);
                    break;
                }

                if (i>0 &&serverResponse.charAt(0) == symbolPrzeciwnika) {
                    String ostatniRuch = input.readLine();
                    int oR = Integer.valueOf(ostatniRuch);
                    TicTacToe.RuchPrzeciwnika(symbolPrzeciwnika, board, oR);
                    System.out.print("Przegrana \n");
                    break;
                }



                int pole = Integer.valueOf(serverResponse);
                TicTacToe.RuchPrzeciwnika(symbolPrzeciwnika, board, pole);

                TicTacToe.Ruch(symbol, board, in, out); //robimy swoj ruch

                i++;
            }

        } catch (
                IOException e) { //jak jest throw exeption to program sie wywala wiec nie chcemy tak tylko chcemy sie rpoblemem zajac
            // czyli jesli cos sie wywali to to cos jest przechowane w obiekcie i lapane e to obiekt zaieracjacy info o errorze

            System.err.println("IOExeption  in clientHandler");
            System.err.println(e.getStackTrace());
        }
        finally {
            System.out.print("KONIEC \n");
            socket.close();
            System.exit(0);
        }
    }
}
