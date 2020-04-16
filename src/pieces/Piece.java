package pieces;

import micron.Game;

public abstract class Piece implements Cloneable {
    private final int VALUE;
    private final String NAME;
    private final int MOVENR;
    private int[][] MOVES;   //y Koordinate, dann x
    private final boolean BLACK;
    private final boolean requiresFreePath; //Die Figuren Läufer, Dame und Turm können nur ziehen, wenn der Weg zum ZIelfeld frei ist
    private String CODE;

    public Piece(String name, int value, int moveNr, int[][] moves, Boolean black, String CODE, boolean requiresFreePath) {
        this.VALUE = value;
        this.NAME = name;
        this.MOVENR = moveNr;
        this.BLACK = black;
        this.CODE = CODE;
        this.requiresFreePath = requiresFreePath;
        if (this.MOVENR == moves.length) {
            this.MOVES = moves;
        } else {
            System.out.println("Fehler Zuganzahnzahl bei " + this.NAME);
            this.MOVES = null;
        }
    }

    public String getNAME() {
        return this.NAME;
    }

    public String getCODE() {
        return this.CODE;
    }

    public Boolean getBLACK() {
        return this.BLACK;
    }

    public int[][] getMoves() {
        return this.MOVES;
    }
    public void setMOVES(int [][] moves){
        this.MOVES = moves;
    }

    public void setCode(String code) {
        this.CODE = code;
    }

    public boolean LegitMove(int[] start, int[] ziel, Piece[][] board, boolean consoleOutput) {  //Methode überprüft die Gültigkeit eines Sprungs, ohne zu springen. Der vorletzte Parameter gibt an, ob der "Weg zum Zielfeld" frei sein muss (Turm, Dame, Läufer)
        boolean reachable = false;
        if (ziel[0] < 8 && ziel[0] > -1 && start[0] < 8 && start[0] > -1) {  // Wenn Start und Ziel überhaupt im Spielfeld sind
          //  int changeDir = this instanceof Pawn && !this.getBLACK() ? -1: 1;// Wird -1 für weiße Bauern, damit diese von unten nach oben laufen
            for (int i = 0; i < MOVES.length; i++) {
                if (start[0] + MOVES[i][0]/* *changeDir*/ == ziel[0] && start[1] + MOVES[i][1]/* *changeDir*/ == ziel[1]) { // Wenn die Figur das Feld prinzipiell erreichen kann
                    reachable = true;
                }
            }
            if (reachable) {
                if (board[ziel[0]][ziel[1]] == null || board[ziel[0]][ziel[1]].getBLACK() != this.getBLACK()) { // Wenn das Feld frei oder vom Gegner besetzt ist
                    if (willBeInCheck(board,start, ziel, consoleOutput)) //Prüfen ob der König nach diesem Zug im Schach stehen wird
                        return false;
                    if (!board[start[0]][start[1]].requiresFreePath) //Wenn nicht der Weg zum Zielfeld frei sein muss (also König, Bauer, Springer)
                        return true;

                    else { //für Dame, Turm, Läufer
                        int upwards = 0; //-1, wenn Zielkoordinate oberhalb der Startkoordinate (dh. Ziel ist kleiner!), sonst 1
                        int right = 0; //-1, wenn Ziellkoordinate links von der Startkoordinate liegt, sonst 1
                        String direction = ""; //Kann Horizental sein (dh. Spalte gleich), oder Vertikal (dh. Zeile gleich), oder diagonal (nichts gleich)

                        if (ziel[0] == start[0] && start[1] != ziel[1]) {//Horizontaler Zug
                            direction = "horizontal";
                            upwards = start[1] > ziel[1] ? -1 : 1; //1 bei Zug nach rechts
                        } else if (ziel[1] == start[1] && start[0] != ziel[0]) { //Vertikaler Zug
                            direction = "vertical";
                            upwards = start[0] > ziel[0] ? -1 : 1; //1 bei Zug nach unten
                        } else if (ziel[0] != start[0] && start[1] != ziel[1]) { //Diagonaler Zug
                            direction = "diagonal";
                            right = start[1] > ziel[1] ? -1 : 1; //1 bei Zug nach rechts
                            upwards = start[0] > ziel[0] ? -1 : 1; //1 bei Zug nach unten
                        } else {
                            if (consoleOutput)
                            System.err.println("Ziel und Start gleich...");
                        }
                        switch (direction) {
                            case "horizontal":
                                for (int i = 1; i < (Math.abs(ziel[1] - start[1])); i++) {  //Nun die Felder, welche Zwischen Start und Ziel liegen, überprüfen
                                    if (board[start[0]][start[1] + i * upwards] != null) { //Wenn das Feld nicht frei ist
                                        if (consoleOutput) System.out.println("Zug unmöglich");
                                        return false;
                                    }
                                }
                                return true;
                            case "vertical":
                                for (int i = 1; i < (Math.abs(ziel[0] - start[0])); i++) {  //Nun die Felder, welche Zwischen Start und Ziel liegen, überprüfen
                                    if (board[start[0] + i * upwards][start[1]] != null) { //Wenn das Feld nicht frei ist
                                        if (consoleOutput) System.out.println("Zug unmöglich");
                                        return false;
                                    }
                                }
                                return true;

                            case "diagonal":
                                for (int i = 1; i < (Math.abs(ziel[0] - start[0])); i++) {  //Nun die Felder, welche Zwischen Start und Ziel liegen, überprüfen
                                    if (board[start[0] + i * upwards][start[1] + i * right] != null) { //Wenn das Feld nicht frei ist
                                        if (consoleOutput) System.out.println("Zug unmöglich");
                                        return false;
                                    }
                                }
                                return true;
                        }

                    }

                }
            }
        }
        if (consoleOutput) System.out.println("Zug unmöglich");
        return false;
    }


    public Piece[][] domove(int[] start, int[] ziel, Piece[][] board) {
            Piece newPiece = null;
            if (this instanceof King)
            newPiece=new King(this.getBLACK());
            else if (this instanceof Knight)
                newPiece=new Knight(this.getBLACK());
            else if (this instanceof Pawn)
                newPiece= new Pawn(this.getBLACK());
            else if (this instanceof Rook)
                newPiece= new Rook(this.getBLACK());
            else if (this instanceof Bishop)
                newPiece= new Bishop(this.getBLACK());
            else if (this instanceof Queen)
                newPiece= new Queen(this.getBLACK());


            board[start[0]][start[1]] = null; //Lösche jetzige Position
            board[ziel[0]][ziel[1]]= newPiece; //Setzte neue Figur
            return board;

    }
    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }
    private boolean willBeInCheck(Piece[][] currentBoard, int[] start, int [] ziel,  boolean consoleOutput){
        Piece[][] futureBoard = new Piece[8][8];
        for (int i = 0; i < currentBoard.length; i++) {
            for (int j = 0; j < currentBoard.length; j++) {
                futureBoard[i][j] = currentBoard[i][j];
            }
        }
        try{
            futureBoard[ziel[0]][ziel[1]] = (Piece) this.clone();
            futureBoard[start[0]][start[1]] = null;

        } catch (Exception e){e.printStackTrace();}
        if (Game.inCheck(futureBoard, this.getBLACK())){
            if (consoleOutput) System.out.println("Ungültiger Zug: Dein König stände dannach im Schach!");
            return true;
        }
        return false;
    }
}

