package pieces;

public class Pawn extends Piece {

    private static int VALUE = 1;
    private static String NAME = "Pawn";
    private static int MOVENR = 4;
    private static String CODEw = "\u2659";
    private static String CODEs = "\u265F";
    private static int[][] MOVES = {{1, 0}, {1, 1}, {1, -1}, {2, 0}}; //y Koordinate, dann x
    private static final int[][] blackMoves = {{1, 0}, {1, 1}, {1, -1}, {2, 0}};
    private static final int[][] whiteMoves = {{-1, 0}, {-1, 1}, {-1, -1}, {-2, 0}};

    public Pawn(Boolean black) {

        super(NAME, VALUE, MOVENR, MOVES, black, CODEs, false);
        String tmp;
        if (black) {
            tmp = CODEs;
            MOVES = blackMoves;
        } else {
            tmp = CODEw;
            MOVES = whiteMoves;
        }
        setCode(tmp);
        super.setMOVES(MOVES);

    }

    @Override
    public boolean LegitMove(int[] start, int[] ziel, Piece[][] board, boolean consoleOutput) {
        if (super.LegitMove(start, ziel, board, consoleOutput)) {       //Wenn Zug gültig
            if (start[1] != ziel[1]) { // Wenn diagonaler Zug
                if (board[ziel[0]][ziel[1]] == null || board[ziel[0]][ziel[1]].getBLACK() == this.getBLACK()) { //Wenn keine gegnerische Figur
                    if (consoleOutput) System.out.println("Zug ungültig, da der Bauer nur beim schlagen Diagonal schlagen darf");
                    return false;
                }
            }
            if (this.getBLACK()) {
                if ((start[0] != 1 && (ziel[0] - start[0] > 1))) { //Wenn sich der schwarze Bauer nicht mehr an der Startposition (dh. 1. Reihe) befindet, er aber versucht zwei Felder zu ziehen
                    if (consoleOutput)    System.out.println("Nur am Anfang erlaubt!");
                    return false;
                }
            } else {
                if ((start[0] != 6 && (start[0] - ziel[0] > 1))) { //Wenn sich der weiße Bauer nicht mehr an der Startposition (dh. 7. Reihe) befindet, er aber versucht zwei Felder zu ziehen
                    if (consoleOutput) System.out.println("Nur am Anfang erlaubt!");
                    return false;
                }
            }



        } else {
            return false;
        }
        return true;
    }

    public Piece[][] domove(int[] start, int[] ziel, Piece[][] board) {
    if (ziel[0] == 0 || ziel[0] == 7) { //Wenn der Bauer am Spielfeldrand ist und sich verwandeln darf
        upgradePiece();
    } return super.domove(start,ziel,board);
    }
    private void upgradePiece() {
        //TODO: add verwandeln
        System.out.println("Tolle Verwandlung!");
    }
}
