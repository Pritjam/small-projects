import java.awt.*;

public class Unit {
    private Rank rank;
    private Color color;

    public Unit(Rank r, Color c) {
        this.rank = r;
        this.color = c;
    }

    public void draw(Graphics g, int centerX, int centerY, int radius) {
        g.setColor(this.color);
        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        switch(this.rank) {                
            case PAWN:
                break;
            case BISHOP:
                g.drawOval(centerX, centerY, radius / 2, radius / 2);
                break;
            case KING:
                g.drawString("K", centerX, centerY);
                break;
            case KNIGHT:
                g.drawString("N", centerX, centerY);
                break;
            case QUEEN:
                g.drawString("Q", centerX, centerY);
                break;
            default:
                break;
        }
        Polygon hex = new Polygon();
        for (int i = 0; i < 6; i++) {
            double angle = (double) i / 3 * Math.PI;
            double dx = radius * Math.cos(angle);
            double dy = radius * Math.sin(angle);
            hex.addPoint(centerX + (int) dx, centerY + (int) dy);
        }
        g.drawPolygon(hex);

    }
}
