import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class GrapheException extends Exception {

	public GrapheException(String message) {
		super(message);
	}
}

public class Graphe implements Serializable {

	private String grapheTitre;
	private List<Sommet> sommets;
	private List<Lien> liens;

	public Graphe(String titre) {
		setGrapheTitre(titre);
		setSommets(new ArrayList<Sommet>());
		setLiens(new ArrayList<Lien>());
	}

	public String getGrapheTitre() {
		return grapheTitre;
	}

	public void setGrapheTitre(String grapheTitre) {
		if (grapheTitre == null)
			grapheTitre = "";
		else
			this.grapheTitre = grapheTitre;
	}

	public List<Sommet> getSommets() {
		return sommets;
	}

	public void setSommets(List<Sommet> sommets) {
		this.sommets = sommets;
	}

	public List<Lien> getLiens() {
		return liens;
	}

	public void setLiens(List<Lien> liens) {
		this.liens = liens;
	}

	public void draw(Graphics g) {
		for (Lien lien : getLiens()) {
			lien.draw(g);
		}

		for (Sommet sommet : getSommets()) {
			sommet.draw(g);
		}
	}

	public void addsommet(Sommet n) {
		sommets.add(n);
	}

	public void addLien(Lien e) {
		for (Lien lien : liens) {
			if (e.equals(lien))
				return;
		}
		liens.add(e);
	}

	public Sommet findSommetUnderCursor(int mx, int my) {
		for (Sommet sommet : sommets) {
			if (sommet.isUnderCursor(mx, my)) {
				return sommet;
			}
		}
		return null;
	}

	public Lien findLienUnderCursor(int mx, int my) {
		for (Lien lien : liens) {
			if (lien.isUnderCursor(mx, my)) {
				return lien;
			}
		}
		return null;
	}

	public void supprimerSommet(Sommet sommetUnderCursor) {
		removeAttachedLiens(sommetUnderCursor);
		sommets.remove(sommetUnderCursor);
	}

	protected void removeAttachedLiens(Sommet sommetUnderCursor) {
		liens.removeIf(e -> {
			return e.getSommetA().equals(sommetUnderCursor)
					|| e.getSommetB().equals(sommetUnderCursor);
		});
	}

	public void supprimerLien(Lien lienUnderCursor) {
		liens.remove(lienUnderCursor);
	}

	public void moveGraphe(int dx, int dy) {
		for (Sommet sommet : sommets) {
			sommet.move(dx, dy);
		}
	}

	public static void serializeGraphe(String NomFichier, Graphe graphe) throws GrapheException {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(NomFichier))) {
			out.writeObject(graphe);
		} catch (IOException e) {
			throw new GrapheException("Erreur serialization!");
		}
	}

	public static void serializeGraphe(File Fichier, Graphe graphe) throws GrapheException {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(Fichier))) {
			out.writeObject(graphe);
		} catch (IOException e) {
			throw new GrapheException("Erreur serialization ");
		}
	}

	public static Graphe deserializeGraphe(String NomFichier) throws GrapheException {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(NomFichier))) {
			Graphe graphe = (Graphe) in.readObject();
			return graphe;
		} catch (FileNotFoundException e) {
			throw new GrapheException("Aucun fichier trouver!");
		} catch (IOException e) {
			throw new GrapheException("Erreur de fichier!");
		} catch (ClassNotFoundException e) {
			throw new GrapheException("Aucun objet de ce type n'a été trouvé!");//
		}
	}

	public static Graphe deserializeGraphe(File Fichier) throws GrapheException {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(Fichier))) {
			Graphe graphe = (Graphe) in.readObject();
			return graphe;
		} catch (IOException e) {
			throw new GrapheException("Erreur de fichier!");
		} catch (ClassNotFoundException e) {
			throw new GrapheException("Aucun objet de ce type n'a été trouvé!");
		}
	}

	public String getListeSommets() {
		int index = 1;
		String liste = "Nombre de sommets: " + Integer.toString(sommets.size()) + "\n";
		liste += "N. [Type]: (position) {parametres}\n";
		for (Sommet sommet : sommets) {
			liste += Integer.toString(index++);
			liste += ". ";
			liste += sommet.toString();
			liste += "\n";
		}
		return liste;
	}

	public String getListeLiens() {
		int index = 1;
		String liste = "Nombre de liens: " + Integer.toString(liens.size()) + "\n";
		liste += "N. [Type]: (Sommet A) ===> (Sommet B) {parametres}\n";
		for (Lien lien : liens) {
			liste += Integer.toString(index++);
			liste += ". ";
			liste += lien.toString();
			liste += "\n";
		}
		return liste;
	}

	@Override
	public String toString() {
		return grapheTitre + "(" + sommets.size() + " sommets, " + liens.size() + " liens)";
	}

}