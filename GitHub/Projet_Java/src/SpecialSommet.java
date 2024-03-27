import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class SpecialSommet extends Sommet {

	public static final Integer[] RAYON_VALUES = { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36,
			38, 40, 42, 44, 46, 48, 50 };
	protected Color couleur;
	private String text;

	public SpecialSommet(int x, int y, Color c, int r, String text) {
		super(x, y);
		this.r = r;
		type = SommetType.SPECIAL_SOMMET;
		couleur = c;
		setText(text);
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		if (r < RAYON_VALUES[0])
			this.r = RAYON_VALUES[0];
		else if (r > RAYON_VALUES[RAYON_VALUES.length - 1])
			this.r = RAYON_VALUES[RAYON_VALUES.length - 1];
		else
			this.r = r;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		if (text == null) {
			this.text = "";
		} else {
			this.text = text;
		}

	}

	public void changeRayon(int step) {
		setR(r + step);
	}

	public void draw(Graphics g) {
		g.setColor(couleur);
		g.fillOval(x - r, y - r, r + r, r + r);
		g.setColor(Color.BLACK);
		g.drawOval(x - r, y - r, r + r, r + r);

		FontMetrics fm = g.getFontMetrics();
		int tx = x - fm.stringWidth(text) / 2;
		int ty = y - fm.getHeight() / 2 + fm.getAscent();
		g.drawString(text, tx, ty);
	}

	@Override
	public String toString() {
		String couleurHex = "#" + Integer.toHexString(couleur.getRGB()).substring(2).toUpperCase();
		return super.toString() + "{r: " + Integer.toString(r) + ", c: " + couleurHex + ", t: " + text + "}";
	}

}
