package pieces;

public class Bishop extends Piece {
    private static final int VALUE=3;
    private static final String NAME="Bishop";
    private static final int MOVENR=28;
    private static final String CODEw="\u2657";
    private static final String CODEs="\u265D";
    private static final int[][] MOVES=  {
            {1,1},{2,2},{3,3},{4,4},{5,5},{6,6},{7,7},
            {1,-1},{2,-2},{3,-3},{4,-4},{5,-5},{6,-6},{7,-7},
            {-1,1},{-2,2},{-3,3},{-4,4},{-5,5},{-6,6},{-7,7},
            {-1,-1},{-2,-2},{-3,-3},{-4,-4},{-5,-5},{-6,-6},{-7,-7},

    };

    public Bishop (Boolean black){
        super(NAME, VALUE, MOVENR, MOVES, black, CODEs, true);
        String tmp;
        if (black){ tmp=CODEs; } else { tmp=CODEw;}
        setCode(tmp);
    }
}
