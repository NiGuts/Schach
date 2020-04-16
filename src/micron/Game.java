package micron;

import pieces.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Game {
    static Piece[][] board = new Piece[8][8];

    public Game() {
        setupBoard();
    }

    public void setupBoard() {
        board = new Piece[8][8];
        //****************Schwarzes Feld ****************
        board[0][0] = new Rook(true, true);
        board[0][7] = new Rook(true, true);
        board[0][1] = new Knight(true);
        board[0][6] = new Knight(true);
        board[0][5] = new Bishop(true);
        board[0][2] = new Bishop(true);
        board[0][3] = new Queen(true);
        board[0][4] = new King(true, true);
        for (int i = 0; i < 8; i++)
            board[1][i] = new Pawn(true);

        //****************Weißes Feld ****************
        board[7][0] = new Rook(false, true);
        board[7][7] = new Rook(false, true);
        board[7][1] = new Knight(false);
        board[7][6] = new Knight(false);
        board[7][5] = new Bishop(false);
        board[7][2] = new Bishop(false);
        board[7][3] = new Queen(false);
        board[7][4] = new King(false, true);
        for (int i = 0; i < 8; i++)
            board[6][i] = new Pawn(false);


        board[6][2] = new Queen(true);
        board[6][3] = null;
        board[6][4] = null;

    }

    public void start() {
        int moveCounter = 0;
        int[][] move;
        while (true) {
            if (moveCounter % 2 == 0) {//Wenn gerader Spielzug: Weiß ist dran!
                System.out.print("Weiß ist dran. Spielzug eingeben:");
                move = getInput(false);
            } else {         //Wenn ungerader Spielzug: Schwarz ist dran!
                System.out.print("Schwarz ist dran. Spielzug eingeben:");
                move = getInput(true);
            }
            int[] start = move[0];
            int[] ziel = move[1];
            board[start[0]][start[1]].domove(start, ziel, board);
            drawBoard();
            moveCounter++;
            System.out.println("Schwarz ist im Schach: " + inCheck(board,true));
            System.out.println("Weiß ist im Schach: " + inCheck(board, false));
        }
    }

    private int[][] getInput(boolean blackMove) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = "";
        boolean legitMove = false;
        int[] start = new int[2];
        int[] ziel = new int[2];

        try {
            while (s != null && !s.equals("q") && !legitMove) {
                s = br.readLine();
                String[] words = s.split(" ");
                String name = words[0];
                if (name.toLowerCase().equals("rochade")) {
                    King king = blackMove ? ((King)board[0][4]):(King)board[7][4];
                    Rook rightRook = blackMove ? ((Rook)board[0][0]):(Rook)board[7][7];
                    Rook leftRook = blackMove ? ((Rook)board[0][7]):(Rook)board[7][0];
                    if (king.isUntouched()) { //Wenn König nicht bewegt
                        if (words[1].toLowerCase().equals("right")) {
                            if (blackMove && rightRook.isUntouched()) { //Wenn rechter Turm nicht bewegt und schwarz zieht
                                board[0][4] = null;
                                board[0][0] = null;
                                board[0][1] = new King(true);
                                board[0][2] = new Rook(true);
                                break;
                                }
                            else if (!blackMove && rightRook.isUntouched()) { //Wenn rechter Turm nicht bewegt und weiß zieht
                                board[7][4] = null;
                                board[7][7] = null;
                                board[7][6] = new King(false);
                                board[7][5] = new Rook(false);
                                break;
                            }
                            else{
                                System.out.println("Rechts ist keine Rochade mehr möglich: Rechter Turm wurde bereits bewegt\nGib einen neuen Zug ein:"); continue;}
                        }

                        else if (words[1].toLowerCase().equals("left")) {
                            if (blackMove && leftRook.isUntouched()) { //Wenn linker Turm nicht bewegt und schwarz zieht
                                board[0][4] = null; //König löschen
                                board[0][7] = null; //Linken Turm löschen
                                board[0][5] = new King(true);
                                board[0][4] = new Rook(true);
                                break;
                            }
                            else if (!blackMove && leftRook.isUntouched()) { //Wenn linker Turm nicht bewegt und weiß zieht
                                board[7][4] = null; //König löschen
                                board[7][0] = null; //Linken Turm löschen
                                board[7][2] = new King(false);
                                board[7][3] = new Rook(false);
                                break;
                            }
                            else{
                                System.out.print("Links ist keine Rochade mehr möglich: Linker Turm wurde bereits bewegt.\nGib einen neuen Zug ein:"); continue;}
                        }
                    } else {System.out.println("Keine Rochade mehr möglich: König wurde bereits bewegt.\nGib einen neuen Zug ein:"); continue;}
                }
                start[0] = Integer.parseInt(words[1].split(",")[0]);
                start[1] = Integer.parseInt(words[1].split(",")[1]);

                ziel[0] = Integer.parseInt(words[2].split(",")[0]);
                ziel[1] = Integer.parseInt(words[2].split(",")[1]);

                if (board[start[0]][start[1]] == null || !(board[start[0]][start[1]].getNAME().toUpperCase().equals(name.toUpperCase()))) {
                    System.out.println("Es gibt keinen " + name + " an " + start[0] + "," + start[1]);
                    System.out.print("Gib einen neuen Zug ein: ");
                    continue;
                }

                if (board[start[0]][start[1]].getBLACK() != blackMove) {
                    System.out.println("Du darfst nur mit eigenen Figuren ziehen.");
                    System.out.print("Gib einen neuen Zug ein: ");
                    continue;
                } else {
                    if (board[start[0]][start[1]].LegitMove(start, ziel, board, true)) {
                        legitMove = true;
                    } else {
                        System.out.print("Gib einen neuen Zug ein: ");
                        continue;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Ungülige Eingabe!");
            System.out.print("Gib einen neuen Zug ein: ");
            getInput(blackMove);
            // e.printStackTrace();
        }
        int[][] toReturn = new int[2][];
        toReturn[0] = start;
        toReturn[1] = ziel;
        return toReturn;
    }

    protected void drawBoard() {  //int[][] board
        System.out.print(" ");
        for (int i = 0; i < 8; i++)
            System.out.print("  " + i + "    ");
        System.out.println();
        for (int i = 0; i < 8; i++)
            System.out.print("  ---  ");
        System.out.println();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.board[i][j] != null) {
                    System.out.print(" | " + this.board[i][j].getCODE() + " | ");
                } else {
                    System.out.print(" |   | ");
                }

            }
            System.out.print(" " + i);
            System.out.println();
            for (int x = 0; x < 8; x++)
                System.out.print("  ---  ");
            System.out.println();

        }
        System.out.print(" ");
        for (int i = 0; i < 8; i++)
            System.out.print("  " + i + "    ");
        System.out.println();
        System.out.println();


    }

    public static boolean inCheck(Piece[][] board, boolean black) {
        int[] currentKingPosition = new int[2];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != null && board[i][j].getNAME().equals("King") && board[i][j].getBLACK() == black) { //Eigener König
                    currentKingPosition[0] = i;
                    currentKingPosition[1] = j;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != null && board[i][j].getBLACK() != black) { //Gegnerische Figur
                    Piece currentPiece = board[i][j];
                    int[] start = {i, j};
                    for (int k = 0; k < currentPiece.getMoves().length; k++) {
                        int[] ziel = {i + currentPiece.getMoves()[k][0], j + currentPiece.getMoves()[k][1]};
                        if (currentKingPosition[0] == ziel[0] && currentKingPosition[1] == ziel[1] && currentPiece.LegitMove(start, ziel, board, false)) { //Wenn König im Schach
                            return true;
                        }
                    }
                }
            }

        }
        return false;
    }


}

