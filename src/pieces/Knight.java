package pieces;

public class Knight extends Piece {
    private static int VALUE=3;
    private static  String NAME="Knight";
    private static int MOVENR=8;
    private static String CODEw="\u2658";
    private static String CODEs="\u265E";
    private static int[][] MOVES=  {{2,1}, {2,-1}, {-2,1}, {-2,-1},{1,2}, {-1,2},{1,-2},{-1,-2} };

    public Knight (Boolean black){

        super(NAME, VALUE, MOVENR, MOVES, black, CODEs, false);
        String tmp;
        if (black){ tmp=CODEs; } else { tmp=CODEw;}
        setCode(tmp);
    }


}
