import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

enum SommetType {
	BASIC_SOMMET("Sommet basic "),
	SPECIAL_SOMMET("Sommet Special ");

	String SommetType;

	SommetType(String SommetType) {
		this.SommetType = SommetType;
	}

	@Override
	public String toString() {
		return SommetType;
	}
}

public class Sommet implements Serializable {

	protected int x;
	protected int y;
	protected int r;

	protected SommetType type;

	public Sommet(int x, int y) {
		this.x = x;
		this.y = y;
		this.r = 5;
		type = SommetType.BASIC_SOMMET;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(x - r, y - r, r + r, r + r);
		g.setColor(Color.BLACK);
		g.drawOval(x - r, y - r, r + r, r + r);
	}

	public boolean isUnderCursor(int mx, int my) {
		int a = x - mx;
		int b = y - my;

		return a * a + b * b <= r * r;
	}

	public void move(int dx, int dy) {
		x += dx;
		y += dy;
	}

	@Override
	public String toString() {
		return "[" + type.toString() + "]: (" + Integer.toString(x) + ", " + Integer.toString(y) + ") ";
	}

}
