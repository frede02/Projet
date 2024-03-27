import java.awt.Color;
import java.awt.Graphics;

public class LienFleche extends Lien {

	public LienFleche(Sommet a, Sommet b) {
		super(a, b);
		lienType = LienType.FLECHE_LIEN;
	}

	public void draw(Graphics g) {
		int xa = sommetA.getX();
		int ya = sommetA.getY();
		int xb = sommetB.getX();
		int yb = sommetB.getY();

		g.setColor(Color.BLACK);

		g.drawLine(xa, ya, xb, yb);
		double angle = Math.atan2(yb - ya, xb - xa);
		int x1 = (int) (xb - 50 * Math.cos(angle - Math.PI / 6));
		int y1 = (int) (yb - 50 * Math.sin(angle - Math.PI / 6));
		int x2 = (int) (xb - 50 * Math.cos(angle + Math.PI / 6));
		int y2 = (int) (yb - 50 * Math.sin(angle + Math.PI / 6));
		g.drawLine(xb, yb, x1, y1);
		g.drawLine(xb, yb, x2, y2);
	}

	@Override
	public String toString() {
		return "[" + lienType.toString() + "]: (" + sommetA.toString() + ") ===> (" + sommetB.toString() + ") ";
	}
}