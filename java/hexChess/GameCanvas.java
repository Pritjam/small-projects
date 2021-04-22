import java.awt.*;
import javax.swing.JFrame;

public class GameCanvas extends Canvas {

    private static Tile[][] map;

	public void paint( Graphics g ) {
        for(Tile[] arr : map) {
            for(Tile hex : arr) {
                if(hex != null) {
                    hex.draw(g, 500, 400, 30);
                }
            }
        }
	}
	
	public static void main(String[] args) {
		JFrame win = new JFrame("Polygon Demo");
		win.setSize(1000, 800);
        map = new Tile[9][9];
        for(int q = -4; q <= 4; q++) {
            for(int r = (q < 0) ? -4 - q : -4; r <= ((q < 0) ? 4 : 4 - q); r++) {
                map[r + 4][q + 4] = new Tile(q, r);
            }
        }
        map[7][0].setUnit(new Unit(Rank.PAWN, Color.black));
        map[7][1].setUnit(new Unit(Rank.BISHOP, Color.black));
        map[7][2].setUnit(new Unit(Rank.PAWN, Color.black));
        map[7][3].setUnit(new Unit(Rank.PAWN, Color.black));
        map[7][4].setUnit(new Unit(Rank.BISHOP, Color.black));
        map[7][5].setUnit(new Unit(Rank.PAWN, Color.black));


        map[8][0].setUnit(new Unit(Rank.KNIGHT, Color.black));
        map[8][1].setUnit(new Unit(Rank.QUEEN, Color.black));
        map[8][2].setUnit(new Unit(Rank.KNIGHT, Color.black));
        map[8][3].setUnit(new Unit(Rank.KING, Color.black));
        map[8][4].setUnit(new Unit(Rank.KNIGHT, Color.black));

		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.add( new GameCanvas() );
		win.setVisible(true);
	}
}