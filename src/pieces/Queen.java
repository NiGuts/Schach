package pieces;

public class Queen extends Piece {
    private static final int VALUE=3;
    private static final String NAME="Queen";
    private static final int MOVENR=56;
    private static final String CODEw="\u2655";
    private static final String CODEs="\u265B";
    private static final int[][] MOVES=  {
            {1,1},{2,2},{3,3},{4,4},{5,5},{6,6},{7,7},
            {1,-1},{2,-2},{3,-3},{4,-4},{5,-5},{6,-6},{7,-7},
            {-1,1},{-2,2},{-3,3},{-4,4},{-5,5},{-6,6},{-7,7},
            {-1,-1},{-2,-2},{-3,-3},{-4,-4},{-5,-5},{-6,-6},{-7,-7},
            {1,0},{2,0},{3,0},{4,0},{5,0},{6,0},{7,0},
            {0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},
            {-1,0},{-2,0},{-3,0},{-4,0},{-5,0},{-6,0},{-7,0},
            {0,-1},{0,-2},{0,-3},{0,-4},{0,-5},{0,-6},{0,-7},

    };

    public Queen (Boolean black){
        super(NAME, VALUE, MOVENR, MOVES, black, CODEs, true);
        String tmp;
        if (black){ tmp=CODEs; } else { tmp=CODEw;}
        setCode(tmp);
    }
}
