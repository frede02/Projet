import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Grille implements Serializable {

	private Dimension dimension;
	private int espace;
	private Color Couleur;

	private List<Rectangle2D.Double> rects;

	public Grille(Dimension dimension, int espace) {
		Couleur = new Color(0, 0, 0, 0.1f);
		scaleGrille(dimension, espace);

	}

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		if (dimension == null)
			this.dimension = new Dimension(0, 0);
		else
			this.dimension = dimension;
	}

	public int getEspace() {
		return espace;
	}

	public void setEspace(int espace) {
		if (espace <= 0)
			this.espace = 20;
		else
			this.espace = espace;
	}

	public void scaleGrille(Dimension dimension, int espace) {
		setEspace(espace);
		scaleGrille(dimension);
	}

	public void scaleGrille(Dimension dimension) {
		setDimension(dimension);
		createGrille();
	}

	private void createGrille() {
		rects = new ArrayList<Rectangle2D.Double>();
		for (int x = espace; x <= dimension.getWidth(); x += 2 * espace) {
			rects.add(new Rectangle2D.Double(x, -1, espace, (int) dimension.getHeight()));
		}

		for (int y = espace; y < dimension.getHeight(); y += 2 * espace) {
			rects.add(new Rectangle2D.Double(0, y, (int) dimension.getWidth(), espace));

		}

	}

	public void draw(Graphics g) {
		g.setColor(Couleur);
		for (Rectangle2D.Double rect : rects) {
			g.drawRect((int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
		}

	}

}
