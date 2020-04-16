package micron;

public class Launcher {

    public static void main (String[] args){
        Game g= new Game();
        g.drawBoard();
        g.start();
        System.out.println();
        System.out.println();
        g.drawBoard();
    }
}
