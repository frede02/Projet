import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import javax.swing.filechooser.FileNameExtensionFilter;

public class GraphePanel extends JPanel implements MouseListener, MouseMotionListener, ComponentListener {

	private Grille Grille;
	private boolean drawGrille;

	private Graphe graphe;

	private boolean mouseLeftButton = false;
	@SuppressWarnings("unused")
	private boolean mouseRightButton = false;

	private int mouseX;
	private int mouseY;

	private Sommet sommetUnderCursor;
	private Lien lienUnderCursor;

	private boolean choixsommetB = false;
	private Sommet newLiensommetA;
	private Sommet newLiensommetB;

	public GraphePanel(Graphe g) {
		if (g == null) {
			graphe = new Graphe("Graphe");
		} else {
			setGraphe(g);
		}

		Grille = new Grille(getSize(), 50);
		drawGrille = true;

		addMouseMotionListener(this);
		addMouseListener(this);

		addComponentListener(this);

		setFocusable(true);
		requestFocusInWindow();

	}

	public Graphe getGraphe() {
		return graphe;
	}

	public void setGraphe(Graphe graphe) {
		if (graphe == null)
			this.graphe = new Graphe("Graphe");
		else
			this.graphe = graphe;
	}

	public void ExampleGraphe() {
		graphe = new Graphe("Example");
		Sommet a = new SpecialSommet(250, 400, Color.WHITE, 15, "A");
		Sommet b = new SpecialSommet(350, 280, Color.WHITE, 15, "B");
		Sommet c = new SpecialSommet(300, 170, Color.WHITE, 15, "C");
		Sommet d = new SpecialSommet(550, 150, Color.WHITE, 15, "D");

		Sommet e = new SpecialSommet(470, 310, Color.WHITE, 15, "E");
		Sommet f = new SpecialSommet(550, 400, Color.WHITE, 15, "F");
		Sommet g = new SpecialSommet(650, 180, Color.WHITE, 15, "G");
		Sommet h = new SpecialSommet(650, 300, Color.WHITE, 15, "H");

		Lien ab = new Lien(a, b);
		Lien ac = new Lien(a, c);
		Lien af = new Lien(a, f);
		Lien bc = new Lien(b, c);
		Lien be = new Lien(b, e);
		Lien cd = new Lien(c, d);
		Lien de = new Lien(d, e);
		Lien dg = new Lien(d, g);
		Lien eh = new Lien(e, h);
		Lien ef = new Lien(e, f);
		Lien fh = new Lien(f, h);
		Lien gh = new Lien(g, h);

		graphe.addsommet(a);
		graphe.addsommet(b);
		graphe.addsommet(c);
		graphe.addsommet(d);
		graphe.addsommet(e);
		graphe.addsommet(f);
		graphe.addsommet(g);
		graphe.addsommet(h);

		graphe.addLien(ab);
		graphe.addLien(ac);
		graphe.addLien(af);
		graphe.addLien(bc);
		graphe.addLien(be);
		graphe.addLien(cd);
		graphe.addLien(de);
		graphe.addLien(dg);
		graphe.addLien(ef);
		graphe.addLien(eh);
		graphe.addLien(gh);
		graphe.addLien(fh);

		repaint();
	}

	@Override

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (Grille != null && drawGrille)
			Grille.draw(g);

		if (graphe != null)
			graphe.draw(g);
	}

	public void createNewGraphe() {
		setGraphe(new Graphe("Graphe"));
		repaint();
	}

	public void serializeGraphe(String NomFichier) {
		if (graphe == null)
			return;

		if (!NomFichier.endsWith(".bin")) {
			NomFichier += ".bin";
		}
		try {
			Graphe.serializeGraphe(NomFichier, graphe);
			JOptionPane.showMessageDialog(null, "Enregistré dans un fichier " + NomFichier);
		} catch (GrapheException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void serializeGraphe() {
		if (graphe == null)
			return;

		JFileChooser fc = new JFileChooser(".");
		fc.setMultiSelectionEnabled(false);
		FileNameExtensionFilter filtre = new FileNameExtensionFilter("Fichier binaire *.bin", "bin");
		fc.addChoosableFileFilter(filtre);
		fc.setFileFilter(filtre);

		int Optionchoisi = fc.showSaveDialog(this);

		if (Optionchoisi == JFileChooser.APPROVE_OPTION) {
			File FichierSelectionner = fc.getSelectedFile();
			String NomFichier = FichierSelectionner.getAbsolutePath();
			if (!NomFichier.endsWith(".bin")) {
				FichierSelectionner = new File(NomFichier + ".bin");
			}
			try {
				Graphe.serializeGraphe(FichierSelectionner, graphe);
				JOptionPane.showMessageDialog(null,
						"Enregistré dans un fichier " + FichierSelectionner.getAbsolutePath());
			} catch (GrapheException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	public void deserializeGraphe(String NomFichier) {
		if (graphe == null)
			return;

		if (!NomFichier.endsWith(".bin")) {
			NomFichier += ".bin";
		}
		try {
			graphe = Graphe.deserializeGraphe(NomFichier);
			JOptionPane.showMessageDialog(null, "Chargé à partir du fichier " + NomFichier);
			repaint();
		} catch (GrapheException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void deserializeGraphe() {
		if (graphe == null)
			return;

		JFileChooser fc = new JFileChooser(".");
		fc.setMultiSelectionEnabled(false);
		FileNameExtensionFilter filtre = new FileNameExtensionFilter("Fichier binaire *.bin", "bin");
		fc.addChoosableFileFilter(filtre);
		fc.setFileFilter(filtre);

		int Optionchoisi = fc.showOpenDialog(this);
		if (Optionchoisi == JFileChooser.APPROVE_OPTION) {
			File FichierSelectionner = fc.getSelectedFile();
			try {
				graphe = Graphe.deserializeGraphe(FichierSelectionner);
				JOptionPane.showMessageDialog(null,
						"Chargé à partir du fichier " + FichierSelectionner.getAbsolutePath());
				repaint();
			} catch (GrapheException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	public void ActiverGrille(boolean drawGrille) {
		this.drawGrille = drawGrille;
		if (this.drawGrille) {
			Grille.scaleGrille(getSize());
		}
		repaint();
	}

	public void showSommetsListe() {
		String sommetsList = graphe.getListeSommets();
		JOptionPane.showMessageDialog(this, sommetsList, "Liste des sommets", JOptionPane.INFORMATION_MESSAGE);
	}

	public void showLiensListe() {
		String sommetsList = graphe.getListeLiens();
		JOptionPane.showMessageDialog(this, sommetsList, "Liste des liens", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (mouseLeftButton) {
			moveGrapheDrag(e.getX(), e.getY());
		} else {
			setMouseCursor(e);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		setMouseCursor(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			mouseLeftButton = true;
		}

		if (e.getButton() == MouseEvent.BUTTON3) {
			mouseRightButton = true;
		}

		setMouseCursor(e);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			mouseLeftButton = false;
			finalizeAddLien();
		}

		if (e.getButton() == MouseEvent.BUTTON3) {
			mouseRightButton = false;
			choixsommetB = false;
			if (sommetUnderCursor != null) {
				createSommetPopupMenu(e, sommetUnderCursor);
			} else if (lienUnderCursor != null) {
				createLienPopupMenu(e, lienUnderCursor);
			} else {
				createPlainPopupMenu(e);
			}
		}
		setMouseCursor(e);
	}

	private void createPlainPopupMenu(MouseEvent e) {
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem newSommetMenuItem = new JMenuItem("Nouveau sommet");
		popupMenu.add(newSommetMenuItem);
		newSommetMenuItem.addActionListener((action) -> {
			createNewSommet(e.getX(), e.getY());
		});
		popupMenu.show(e.getComponent(), e.getX(), e.getY());
	}

	private void createSommetPopupMenu(MouseEvent e, Sommet n) {
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem removeSommetMenuItem = new JMenuItem("Supprimer le sommet");
		popupMenu.add(removeSommetMenuItem);
		removeSommetMenuItem.addActionListener((action) -> {
			supprimerSommet(n);
		});

		popupMenu.addSeparator();

		JMenuItem addLienMenuItem = new JMenuItem("Ajouter un Lien");
		popupMenu.add(addLienMenuItem);
		addLienMenuItem.addActionListener((action) -> {
			initializeAddLien(n);
		});

		if (sommetUnderCursor instanceof SpecialSommet) {
			popupMenu.addSeparator();
			JMenuItem changeSommetRayonMenuItem = new JMenuItem("Change la taille du sommet");
			popupMenu.add(changeSommetRayonMenuItem);
			changeSommetRayonMenuItem.addActionListener((action) -> {
				changeSommetRayon(n);
			});

			JMenuItem changeSommetcouleurMenuItem = new JMenuItem("Change la couleur sommet");
			popupMenu.add(changeSommetcouleurMenuItem);
			changeSommetcouleurMenuItem.addActionListener((action) -> {
				changeSommetcouleur(n);
			});

			JMenuItem changeTextMenuItem = new JMenuItem("Change le nom du sommet");
			popupMenu.add(changeTextMenuItem);
			changeTextMenuItem.addActionListener((action) -> {
				changeSommetText(n);
			});

		}

		popupMenu.show(e.getComponent(), e.getX(), e.getY());

	}

	private void createLienPopupMenu(MouseEvent event, Lien e) {
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem supprimerLienMenuItem = new JMenuItem("Supprimer le lien");
		popupMenu.add(supprimerLienMenuItem);
		supprimerLienMenuItem.addActionListener((action) -> {
			supprimerLien(e);
		});
		if (e instanceof SpecialLien) {
			popupMenu.addSeparator();
			JMenuItem changeLienStrokeMenuItem = new JMenuItem("Change la taille du Lien");
			popupMenu.add(changeLienStrokeMenuItem);
			changeLienStrokeMenuItem.addActionListener((action) -> {
				changeLienStroke(e);
			});
			JMenuItem changeLiencouleurMenuItem = new JMenuItem("Change la couleur Lien");
			popupMenu.add(changeLiencouleurMenuItem);
			changeLiencouleurMenuItem.addActionListener((action) -> {
				changeLiencouleur(e);
			});
		}
		popupMenu.show(event.getComponent(), event.getX(), event.getY());
	}

	public void setMouseCursor(MouseEvent e) {
		if (e != null) {
			sommetUnderCursor = graphe.findSommetUnderCursor(e.getX(), e.getY());
			if (sommetUnderCursor == null) {
				lienUnderCursor = graphe.findLienUnderCursor(e.getX(), e.getY());
			}
			mouseX = e.getX();
			mouseY = e.getY();
		}
		int mouseCursor;
		if (sommetUnderCursor != null) {
			mouseCursor = Cursor.HAND_CURSOR;
		} else if (lienUnderCursor != null) {
			mouseCursor = Cursor.CROSSHAIR_CURSOR;
		} else if (choixsommetB) {
			mouseCursor = Cursor.WAIT_CURSOR;
		} else if (mouseLeftButton) {
			mouseCursor = Cursor.MOVE_CURSOR;
		} else {
			mouseCursor = Cursor.DEFAULT_CURSOR;
		}
		setCursor(Cursor.getPredefinedCursor(mouseCursor));
	}

	private void moveGrapheDrag(int mx, int my) {
		int dx = mx - mouseX;
		int dy = my - mouseY;

		if (sommetUnderCursor != null) {
			sommetUnderCursor.move(dx, dy);
		} else if (lienUnderCursor != null) {
			lienUnderCursor.move(dx, dy);
		} else {
			graphe.moveGraphe(dx, dy);
		}

		mouseX = mx;
		mouseY = my;
		repaint();
	}

	private void createNewSommet(int mx, int my) {
		try {
			SommetType sommetType = (SommetType) JOptionPane.showInputDialog(this, "Choisissez le type de sommet",
					"Nouveau sommet",
					JOptionPane.DEFAULT_OPTION, null, SommetType.values(), SommetType.BASIC_SOMMET);
			if (sommetType == SommetType.BASIC_SOMMET) {
				graphe.addsommet(new Sommet(mx, my));
			} else if (sommetType == SommetType.SPECIAL_SOMMET) {
				Color couleur = JColorChooser.showDialog(this, "Choisir la couleur", Color.WHITE);
				int Rayon = ((Integer) JOptionPane.showInputDialog(this, "Choisir la taille", "Nouveau Sommet",
						JOptionPane.DEFAULT_OPTION, null, SpecialSommet.RAYON_VALUES, SpecialSommet.RAYON_VALUES[0]))
						.intValue();
				String text = JOptionPane.showInputDialog(this, "Entrer le nom:", "Nouveau Sommet",
						JOptionPane.QUESTION_MESSAGE);
				graphe.addsommet(new SpecialSommet(mx, my, couleur, Rayon, text));
			} else {
				throw new NullPointerException();
			}
			repaint();
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(this, "Operation annulé.", "Annulé", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	private void supprimerSommet(Sommet n) {
		graphe.supprimerSommet(n);
		repaint();
	}

	private void initializeAddLien(Sommet n) {
		if (sommetUnderCursor != null) {
			newLiensommetA = n;
			choixsommetB = true;
			setMouseCursor(null);
		}
	}

	private void finalizeAddLien() {
		if (choixsommetB) {
			if (sommetUnderCursor != null) {
				if (sommetUnderCursor.equals(newLiensommetA)) {
					JOptionPane.showMessageDialog(this, "Choisissez un autre sommet!", "Erreur!",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						newLiensommetB = sommetUnderCursor;
						LienType lienType = (LienType) JOptionPane.showInputDialog(this, "Choisissez le type de lien",
								"Nouveau lien",
								JOptionPane.DEFAULT_OPTION, null, LienType.values(), LienType.BASIC_LIEN);
						if (lienType == LienType.BASIC_LIEN) {
							graphe.addLien(new Lien(newLiensommetA, newLiensommetB));
						} else if (lienType == LienType.SPECIAL_LIEN) {
							Color couleur = JColorChooser.showDialog(this, "Choisir la couleur", Color.BLACK);
							int stroke = ((Integer) JOptionPane.showInputDialog(this, "Choisir la taille",
									"Nouveau lien",
									JOptionPane.DEFAULT_OPTION, null, SpecialLien.STROKE_VALUES,
									SpecialLien.STROKE_VALUES[0])).intValue();
							graphe.addLien(new SpecialLien(newLiensommetA, newLiensommetB, couleur, stroke));
						} else if (lienType == LienType.FLECHE_LIEN) {
							graphe.addLien(new LienFleche(newLiensommetA, newLiensommetB));
						} else {
							throw new NullPointerException();
						}
						repaint();
					} catch (NullPointerException e) {
						JOptionPane.showMessageDialog(this, "Opération annulée.", "Annulée",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
			choixsommetB = false;
		}
	}

	private void supprimerLien(Lien e) {
		graphe.supprimerLien(e);
		repaint();
	}

	private void changeSommetRayon(Sommet n) {
		try {
			int Rayon = ((Integer) JOptionPane.showInputDialog(this, "Choisissez le rayon:", "Modifier Sommet",
					JOptionPane.QUESTION_MESSAGE, null, SpecialSommet.RAYON_VALUES, SpecialSommet.RAYON_VALUES[0]))
					.intValue();
			((SpecialSommet) n).setR(Rayon);
			repaint();
		} catch (ClassCastException e) {
			JOptionPane.showMessageDialog(this, "Ce sommet ne peut pas avoir un rayon différent.", "Erreur!",
					JOptionPane.ERROR_MESSAGE);
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(this, "Opération annulée.", "Annulée", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	private void changeSommetcouleur(Sommet n) {
		try {
			Color couleur = JColorChooser.showDialog(this, "Choisissez une nouvelle couleur",
					((SpecialSommet) n).getcouleur());
			((SpecialSommet) n).setcouleur(couleur);
			repaint();
		} catch (ClassCastException e) {
			JOptionPane.showMessageDialog(this, "Ce sommet ne peut pas avoir une couleur différente.", "Erreur!",
					JOptionPane.ERROR_MESSAGE);
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(this, "Opération annulée.", "Annulée", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	private void changeSommetText(Sommet n) {
		String text = JOptionPane.showInputDialog(this, "Entrer le nom:", "Modifier sommet",
				JOptionPane.QUESTION_MESSAGE);
		try {
			((SpecialSommet) n).setText(text);
			repaint();
		} catch (ClassCastException e) {
			JOptionPane.showMessageDialog(this, "Ce sommet ne peut pas contenir de nom.", "Erreur!",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(this, "Opération annulée.", "Annulée", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void changeLienStroke(Lien e) {
		try {
			int stroke = ((Integer) JOptionPane.showInputDialog(this, "Choisissez le trait", "Modifier lien",
					JOptionPane.DEFAULT_OPTION, null, SpecialLien.STROKE_VALUES, SpecialLien.STROKE_VALUES[0]))
					.intValue();
			((SpecialLien) e).setStroke(stroke);
			repaint();
		} catch (ClassCastException exc) {
			JOptionPane.showMessageDialog(this, "Ce lien ne peut pas avoir un trait différent.", "Erreur!",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (NullPointerException exc) {
			JOptionPane.showMessageDialog(this, "Opération annulée.", "Annulée", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void changeLiencouleur(Lien e) {
		try {
			Color couleur = JColorChooser.showDialog(this, "Choisir la couleur", Color.BLACK);
			((SpecialLien) e).setcouleur(couleur);
			repaint();
		} catch (ClassCastException exc) {
			JOptionPane.showMessageDialog(this, "Ce lien ne peut pas avoir une couleur différente.", "Erreur!",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (NullPointerException exc) {
			JOptionPane.showMessageDialog(this, "Opération annulée.", "Annulée", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentResized(ComponentEvent e) {
		Object eSource = e.getSource();
		if (eSource == this && drawGrille) {
			Grille.scaleGrille(getSize());
			repaint();
		}
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}
}
