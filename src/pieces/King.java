package pieces;

public class King extends Piece {
    private static final int VALUE=100;
    private static final  String NAME="King";
    private static final int MOVENR=8;
    private static final String CODEw="\u2654";
    private static final String CODEs="\u265A";
    private static final int[][] MOVES=  {{0,1}, {0,-1}, {1,0}, {-1,0},{1,1}, {-1,1},{1,-1},{-1,1} };
    private  boolean untouched = false;  //Für Rochade

    public King (boolean black){
      super(NAME, VALUE, MOVENR, MOVES, black, CODEs, false);
        String tmp;
        if (black){ tmp=CODEs; } else { tmp=CODEw;}
        setCode(tmp);
    }
    public King (boolean black, boolean untouched){
        this(black);
        this.untouched = untouched;
    }

    public boolean isUntouched() {
        return untouched;
    }
}
