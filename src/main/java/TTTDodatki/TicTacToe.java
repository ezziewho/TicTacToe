package TTTDodatki;

import java.io.PrintWriter;
import java.util.Scanner;

public class TicTacToe {


    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    /*public static void print_board(char[] board) {
        //char[] board = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};


        // if(board[0] == 'X' )

        System.out.println("+-----------+");
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("+-----------+");
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("+-----------+");
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        System.out.println("+-----------+");

    }*/

    public static void print_board(char[] board) {
        //char[] board = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        String[] board1 = new String[10];

        for (int i = 0; i < 10; i++) {
            board1[i] = Character.toString(board[i]);
            if (board[i] == 'X') board1[i] = ConsoleColors.PURPLE_BACKGROUND + board[i] + ConsoleColors.RESET;
            if (board[i] == 'O') board1[i] = ConsoleColors.GREEN_BOLD_BRIGHT + board[i] + ConsoleColors.RESET;

        }

        System.out.println("+-----------+");
        System.out.println("| " + board1[0] + " | " + board1[1] + " | " + board1[2] + " |");
        System.out.println("+-----------+");
        System.out.println("| " + board1[3] + " | " + board1[4] + " | " + board1[5] + " |");
        System.out.println("+-----------+");
        System.out.println("| " + board1[6] + " | " + board1[7] + " | " + board1[8] + " |");
        System.out.println("+-----------+");

    }

    public static void ruch(char[] board, int pole, char symbol) {
        board[pole] = symbol;
    }

    public static void Ruch(char symbol, char[] board, Scanner in, PrintWriter out) {
        /*
        System.out.println("Wybierz pole: "); //identico

        //String uzytkownik = in.next();
        int pole = in.nextInt(); //identico
        //int pole = Integer.parseInt(uzytkownik);

        while (board[pole] == 'X' || board[pole] == 'O' || czy_pole_istnieje(pole) == 0) {
            System.out.println("Wybrales zle pole! Wybierz pole: ");
            pole = in.nextInt();
        }//identico

        //String command = pole;
        ruch(board, pole, symbol); //identico
        print_board(board);
        out.println(pole);             //wysylamy info o polu do serwera
*/

        System.out.println("Wybierz pole: "); //identico

        String uzytkownik = in.next();

        int pole = 9;
        try
        {
            Integer.parseInt(uzytkownik);
            pole = Integer.parseInt(uzytkownik);
        }
        catch (NumberFormatException e)
        {
            pole = 9;
        }

        //int pole = in.nextInt(); //identico
        //int pole = Integer.parseInt(uzytkownik);

        while (board[pole] == 'X' || board[pole] == 'O' || pole>8 ) {
            System.out.println("Wybrales zle pole! Wybierz pole: ");
            uzytkownik = in.next();

            try
            {
                Integer.parseInt(uzytkownik);
                //System.out.println(uzytkownik + " is a valid integer");
                pole = Integer.parseInt(uzytkownik);
            }
            catch (NumberFormatException e)
            {
                //System.out.println(uzytkownik + " is not a valid integer");
                pole = 9;
            }



        }//identico

        //String command = pole;
        ruch(board, pole, symbol); //identico
        print_board(board);
        out.println(pole);             //wysylamy info o polu do serwera
    }


    public static void RuchPrzeciwnika(char symbol, char[] board, int pole) {
        System.out.print("Przeciwnik ruszyl sie na miejsce: " + pole + "\n");
        ruch(board, pole, symbol); //identico
        print_board(board);

    }


    public static char checkwin(char[] board, char symbol) {

        int check = 0;
        if (board[0] == symbol && board[1] == symbol && board[2] == symbol) check = 1;
        if (board[3] == symbol && board[4] == symbol && board[5] == symbol) check = 1;
        if (board[6] == symbol && board[7] == symbol && board[8] == symbol) check = 1;
        if (board[0] == symbol && board[3] == symbol && board[6] == symbol) check = 1;
        if (board[1] == symbol && board[4] == symbol && board[7] == symbol) check = 1;
        if (board[2] == symbol && board[5] == symbol && board[8] == symbol) check = 1;
        if (board[0] == symbol && board[4] == symbol && board[8] == symbol) check = 1;
        if (board[2] == symbol && board[4] == symbol && board[6] == symbol) check = 1;

        if (check == 1) return symbol;

        else {
            return '-';
        }
    }

    public static int checkdraw(char[] board) {

        int check = 0;

        if (board[0] !=  '0') check++;
        if (board[1] !=  '1' ) check++;
        if (board[2] !=  '2' ) check++;
        if (board[3] !=  '3' ) check++;
        if (board[4] !=  '4' ) check++;
        if (board[5] !=  '5' ) check++;
        if (board[6] !=  '6' ) check++;
        if (board[7] !=  '7' ) check++;
        if (board[8] !=  '8' ) check++;


        //jesli chceck = 8 to juz wszyscy swoje postawili
        return check;
    }
    public static int czy_pole_istnieje(int pole) {
        int czy_istnieje = 0;

        if (pole == 0 || pole == 1 || pole == 2
                || pole == 3 || pole == 4 || pole == 5
                || pole == 6 || pole == 7 || pole == 8) czy_istnieje = 1;

        return czy_istnieje;

    }
}
