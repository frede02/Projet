import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class SpecialLien extends Lien {

	public static final Integer[] STROKE_VALUES = { 1, 2, 3, 4, 6, 7, 8, 9, 10 };

	protected int stroke;
	protected Color couleur;

	public SpecialLien(Sommet a, Sommet b, Color c, int s) {
		super(a, b);
		couleur = c;
		stroke = s;
		lienType = LienType.SPECIAL_LIEN;
	}

	public int getStroke() {
		return stroke;
	}

	public void setStroke(int stroke) {
		if (stroke < STROKE_VALUES[0])
			this.stroke = STROKE_VALUES[0];
		else if (stroke > STROKE_VALUES[STROKE_VALUES.length - 1])
			this.stroke = STROKE_VALUES[STROKE_VALUES.length - 1];
		else
			this.stroke = stroke;
	}

	public Color getcouleur() {
		return couleur;
	}

	public void setcouleur(Color couleur) {
		if (couleur == null)
			this.couleur = Color.BLACK;
		else
			this.couleur = couleur;
	}

	public void changeStroke(int step) {
		setStroke(stroke + step);
	}

	@Override
	public void draw(Graphics g) {
		int xa = sommetA.getX();
		int ya = sommetA.getY();
		int xb = sommetB.getX();
		int yb = sommetB.getY();

		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(stroke));
		g2.setColor(couleur);
		g2.drawLine(xa, ya, xb, yb);
		g2.setStroke(new BasicStroke());
	}

	@Override
	public String toString() {
		String couleurHex = "#" + Integer.toHexString(couleur.getRGB()).substring(2).toUpperCase();
		return super.toString() + "{s: " + Integer.toString(stroke) + ", c: " + couleurHex + "}";
	}
}
