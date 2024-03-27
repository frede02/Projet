import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

enum LienType {
	BASIC_LIEN("Lien basic"),
	SPECIAL_LIEN("Lien Special"),
	FLECHE_LIEN("Lien Fleche");

	String lienType;

	LienType(String lienType) {
		this.lienType = lienType;
	}

	@Override
	public String toString() {
		return lienType;
	}
}

public class Lien implements Serializable {

	protected Sommet sommetA;
	protected Sommet sommetB;

	protected LienType lienType;

	public Lien(Sommet a, Sommet b) {
		sommetA = a;
		sommetB = b;
		lienType = LienType.BASIC_LIEN;
	}

	public Sommet getSommetA() {
		return sommetA;
	}

	public void setSommetA(Sommet sommetA) {
		this.sommetA = sommetA;
	}

	public Sommet getSommetB() {
		return sommetB;
	}

	public void setSommetB(Sommet sommetB) {
		this.sommetB = sommetB;
	}

	public void draw(Graphics g) {
		int xa = sommetA.getX();
		int ya = sommetA.getY();
		int xb = sommetB.getX();
		int yb = sommetB.getY();
		g.setColor(Color.BLACK);

		g.drawLine(xa, ya, xb, yb);
	}

	public boolean isUnderCursor(int mx, int my) {

		if (mx < Math.min(sommetA.getX(), sommetB.getX()) ||
				mx > Math.max(sommetA.getX(), sommetB.getX()) ||
				my < Math.min(sommetA.getY(), sommetB.getY()) ||
				my > Math.max(sommetA.getY(), sommetB.getY())) {
			return false;
		}

		int A = sommetB.getY() - sommetA.getY();
		int B = sommetB.getX() - sommetA.getX();

		double distance = Math.abs(A * mx - B * my + sommetB.getX() * sommetA.getY() - sommetB.getY() * sommetA.getX())
				/ Math.sqrt(A * A + B * B);
		return distance <= 5;
	}

	public void move(int dx, int dy) {
		sommetA.move(dx, dy);
		sommetB.move(dx, dy);
	}

	@Override
	public String toString() {
		return "[" + lienType.toString() + "]: (" + sommetA.toString() + ") ===> (" + sommetB.toString() + ") ";
	}
}