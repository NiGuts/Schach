package pieces;

public class Rook extends Piece {
    private static final int VALUE=5;
    private static final String NAME="Rook";
    private static final int MOVENR=28;
    private static final String CODEw="\u2656";
    private static final String CODEs="\u265C";
    private static final int[][] MOVES=  {
            {1,0},{2,0},{3,0},{4,0},{5,0},{6,0},{7,0},
            {0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},
            {-1,0},{-2,0},{-3,0},{-4,0},{-5,0},{-6,0},{-7,0},
            {0,-1},{0,-2},{0,-3},{0,-4},{0,-5},{0,-6},{0,-7},
    };
    private boolean untouched = false; //Für Rochade

    public Rook (Boolean black){

        super(NAME, VALUE, MOVENR, MOVES, black, CODEs, true);
        String tmp;
        if (black){ tmp=CODEs; } else { tmp=CODEw;}
        setCode(tmp);
    }
    public Rook (boolean black, boolean untouched){
        this(black);
        this.untouched = untouched;
    }


   /* protected Boolean LegitMove(int [] start, int[] ziel, Piece[][] board ) {  //Methode überprüft die Gültigkeit eines Sprungs, ohne zu springen
        Boolean reachable=false;
        int what=-1; //gibt an ob der turm sich in Zeilen (1) oder Spalten (0) bewegt
        int inv=-1;// Gegenteil zu what
        int c=1;
        if (ziel[0] < 8 && ziel[0] > -1 && start[0] < 8 && start[0] > -1) {  // Wenn Start und Ziel überhaupt im Spielfeld sind

                if (start[0] == ziel[0]){  // Wenn die das Feld in der selben Zeile dh. prinzipiell erreichbar ist
                    what=0; reachable=true;}
                else if (start[1]  == ziel[1]) { // Wenn die das Feld in der selben Spalte dh. prinzipiell erreichbar ist
                   what=1; reachable=true; }
                if(what==1){inv=0;} if (what==0){inv=1;}
            }if (reachable== true)
                if ( board[ziel[0]][ziel[1]] == null || board[ziel[0]][ziel[1]].getBLACK() != this.getBLACK()) { // Wenn das Feld frei oder vom Gegner besetzt ist
                    if(ziel[inv]<start[inv])  // Wenn das Feld links oder über dem Turm ist
                        c=-1;
                    for (int i=start[inv]; i!=ziel[inv]; i+=c){//Läuft alle Felder zwischen jetziger Position und Zielfeld ab
                       if (what==0){
                        if (board[start[0]][i]!=null){
                            System.out.println("Jemand im Weg! (Zeile)");
                            return false;
                        }}
                        else if (what==1){
                            if (board[i][start[1]]!=null){
                                System.out.println("Jemand im Weg!(Spalte)" + board[i][start[1]].getNAME());
                                return false;
                            }}
                    }
                    return true;
                }

        System.out.println("Zug unmöglich");
        return false;
    }

*/

    public boolean isUntouched() {
        return untouched;
    }


}
