import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.Panel;
import java.awt.Image;

public class GrapheFenetre extends JFrame implements ActionListener {

	private static final String TITRE = "Logiciel de graphe";
	private static final String INFO = "Sauvegarder: ALT + s\n"
			+ "Supprimer le graphe: ALT + Z\n"
			+ "Charger un fichier: ALT + C\n"
			+ "Afficher la liste des sommets: ALT + T\n"
			+ "Afficher la liste des liens: ALT + L\n"
			+ "Afficher l'exemple: ALT + E\n"
			+ "activer/désactiver la grille: ALT + G\n";
	private static final String AUTOSAVE_FICHIER = "AUTOSAVE.bin";

	WindowAdapter windowListener = new WindowAdapter() {
		@Override
		public void windowClosed(WindowEvent e) {
			JOptionPane.showMessageDialog(null, "Programme fermé!");
		}

		@Override
		public void windowClosing(WindowEvent e) {
			windowClosed(e);
		}
	};

	public static void main(String[] args) {
		new GrapheFenetre();

	}

	static GraphePanel graphePanel = new GraphePanel(null);

	Panel fond = new Panel();

	JLabel texteoutil = new JLabel("Outils");
	JLabel Titre = new JLabel("Graphe");

	Box Outils = Box.createHorizontalBox();

	ImageIcon icon = new ImageIcon("images/sauv.png");
	JButton sauvbouton = new JButton(new ImageIcon("images/sauv.png"));

	ImageIcon icon2 = new ImageIcon("images/charger.png");
	JButton chargerbouton = new JButton(new ImageIcon("images/charger.png"));

	ImageIcon icon4 = new ImageIcon("images/blanc.png");
	JButton nouveaugraphe = new JButton(new ImageIcon("images/blanc.png"));

	JButton aidebouton = new JButton(new ImageIcon("images/aide.png"));

	public JCheckBoxMenuItem grillebouton = new JCheckBoxMenuItem("", new ImageIcon("images/grille.png"), true);

	JButton sommetbouton = new JButton("Sommet");
	JButton lienbouton = new JButton("Lien");
	JButton exemplebouton = new JButton("Exemple");

	private GrapheFenetre() {
		super(TITRE);
		setSize(1900, 1080);
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(graphePanel);
		UIManager.put("OptionPane.messageFont", new Font("Monospaced", Font.BOLD, 12));

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent event) {
				graphePanel.serializeGraphe(AUTOSAVE_FICHIER);
			}

			@Override
			public void windowClosing(WindowEvent event) {
				windowClosed(event);
			}
		});

		addActionListeners();
		createMenuBar();

		setVisible(true);

		showInfo();

		graphePanel.deserializeGraphe(AUTOSAVE_FICHIER);
	}

	private void addActionListeners() {

		sauvbouton.addActionListener(this);
		chargerbouton.addActionListener(this);
		nouveaugraphe.addActionListener(this);
		aidebouton.addActionListener(this);
		grillebouton.addActionListener(this);
		sommetbouton.addActionListener(this);
		lienbouton.addActionListener(this);
		exemplebouton.addActionListener(this);
	}

	private void createMenuBar() {

		Outils.setMaximumSize(new Dimension(Short.MAX_VALUE, 100));
		Outils.setBackground(Color.black);
		Outils.setFocusable(false);
		fond.add(Outils);
		ImageIcon icon = new ImageIcon("images/sauv.png");
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		sauvbouton.setIcon(icon);
		sauvbouton.setBackground(Color.white);
		sauvbouton.setMaximumSize(new Dimension(100, Short.MAX_VALUE));

		ImageIcon icon2 = new ImageIcon("images/doc.png");
		Image img2 = icon2.getImage();
		Image newimg2 = img2.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		icon2 = new ImageIcon(newimg2);
		chargerbouton.setIcon(icon2);
		chargerbouton.setBackground(Color.white);
		chargerbouton.setMaximumSize(new Dimension(100, Short.MAX_VALUE));

		ImageIcon icon4 = new ImageIcon("images/blanc.png");
		Image img4 = icon4.getImage();
		Image newimg4 = img4.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		icon4 = new ImageIcon(newimg4);
		nouveaugraphe.setIcon(icon4);
		nouveaugraphe.setBackground(Color.white);
		nouveaugraphe.setMaximumSize(new Dimension(100, Short.MAX_VALUE));

		ImageIcon icon5 = new ImageIcon("images/info.png");
		Image img5 = icon5.getImage();
		Image newimg5 = img5.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		icon5 = new ImageIcon(newimg5);
		aidebouton.setIcon(icon5);
		aidebouton.setBackground(Color.white);
		aidebouton.setMaximumSize(new Dimension(100, Short.MAX_VALUE));

		ImageIcon icon6 = new ImageIcon("images/grille.png");
		Image img6 = icon6.getImage();
		Image newimg6 = img6.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		icon6 = new ImageIcon(newimg6);
		grillebouton.setIcon(icon6);
		grillebouton.setBackground(Color.white);
		grillebouton.setMaximumSize(new Dimension(100, Short.MAX_VALUE));

		sauvbouton.setMnemonic(KeyEvent.VK_S);
		nouveaugraphe.setMnemonic(KeyEvent.VK_Z);
		chargerbouton.setMnemonic(KeyEvent.VK_C);
		sommetbouton.setMnemonic(KeyEvent.VK_T);
		lienbouton.setMnemonic(KeyEvent.VK_L);
		exemplebouton.setMnemonic(KeyEvent.VK_E);
		grillebouton.setMnemonic(KeyEvent.VK_G);
		aidebouton.setMnemonic(KeyEvent.VK_A);

		Outils.add(nouveaugraphe);
		Outils.add(Box.createHorizontalGlue());
		Outils.add(Box.createRigidArea(new Dimension(10, 0)));
		Outils.add(chargerbouton);
		Outils.add(Box.createHorizontalGlue());
		Outils.add(Box.createRigidArea(new Dimension(10, 0)));
		Outils.add(sauvbouton);
		Outils.add(Box.createHorizontalGlue());
		Outils.add(Box.createRigidArea(new Dimension(10, 0)));
		Outils.add(grillebouton);
		Outils.add(Box.createHorizontalGlue());
		Outils.add(Box.createRigidArea(new Dimension(10, 0)));
		Outils.add(aidebouton);

		Titre.setForeground(Color.BLACK);
		Titre.setText("Logiciel de Graphe");
		Titre.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		Titre.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 500));
		add(Titre, BorderLayout.WEST);

		texteoutil.setText("Outils");
		texteoutil.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		texteoutil.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0, 102, 255)),
						BorderFactory.createEmptyBorder(15, 10, 15, 10)));
		add(texteoutil, BorderLayout.NORTH);

		sommetbouton.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		sommetbouton.setForeground(Color.WHITE);
		sommetbouton.setBackground(new Color(0, 102, 255));
		sommetbouton.setFocusPainted(false);
		sommetbouton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

		lienbouton.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lienbouton.setForeground(Color.WHITE);
		lienbouton.setBackground(new Color(0, 102, 255));
		lienbouton.setFocusPainted(false);
		lienbouton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

		exemplebouton.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		exemplebouton.setForeground(Color.WHITE);
		exemplebouton.setBackground(new Color(0, 102, 255));
		exemplebouton.setFocusPainted(false);
		exemplebouton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

		Outils.add(Box.createHorizontalGlue());
		Outils.add(Box.createRigidArea(new Dimension(20, 0)));
		Outils.add(sommetbouton);
		Outils.add(Box.createRigidArea(new Dimension(20, 0)));
		Outils.add(lienbouton);
		Outils.add(Box.createRigidArea(new Dimension(20, 0)));
		Outils.add(exemplebouton);
		Outils.add(Box.createHorizontalGlue());
		add(Outils, BorderLayout.CENTER);

		fond.setBackground(new Color(134, 191, 195));
		BoxLayout conteneur = new BoxLayout(fond, BoxLayout.Y_AXIS);
		fond.add(Titre);
		fond.setLayout(conteneur);

		fond.add(graphePanel);

		setContentPane(fond);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object eSource = e.getSource();

		if (eSource == nouveaugraphe) {
			graphePanel.createNewGraphe();
		}

		if (eSource == exemplebouton) {
			graphePanel.ExampleGraphe();
		}

		if (eSource == sauvbouton) {
			graphePanel.serializeGraphe();
		}

		if (eSource == chargerbouton) {
			graphePanel.deserializeGraphe();
		}
		if (eSource == exemplebouton) {
			graphePanel.ExampleGraphe();
		}

		if (eSource == lienbouton) {
			graphePanel.showLiensListe();
		}

		if (eSource == sommetbouton) {
			graphePanel.showSommetsListe();
		}

		if (eSource == grillebouton) {
			graphePanel.ActiverGrille(grillebouton.isSelected());
		}

		if (eSource == aidebouton) {
			showInfo();
		}
	}

	private void showInfo() {
		JOptionPane.showMessageDialog(this, INFO, "Aide", JOptionPane.INFORMATION_MESSAGE);
	}
}
