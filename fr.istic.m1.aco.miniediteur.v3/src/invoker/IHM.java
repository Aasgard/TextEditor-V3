package invoker;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import command.Command;

public class IHM extends JFrame{

	private static final long serialVersionUID = 1L;

	private ArrayList<Bouton> lesBoutons;
	
	protected TextArea texteA;
	
	private Command copier;
	private Command couper;
	private Command selectionner;
	private Command saisir;
	private Command coller;
	private Command effacer;
	
	private Command defaire;
	private Command refaire;
	
	/**
	 * Constructeur par défaut de la classe IHM.
	 * Etablit les configurations de bases de la fenêtre(Titre, taille, comportements ...).
	 * Elle initialise un ArrayList servant à stocker les Boutons.
	 */
	public IHM(){
		this.setTitle("Editeur de texte - V3");
		this.setSize(new Dimension(800, 500));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		lesBoutons = new ArrayList<Bouton>();
	}
	
	/**
	 * Fonction créant la zone de texte principale de l'affichage graphique.
	 * Initialisation d'un HM de commandes. (String, Command).
	 * On y stocke les Commandes principales de la zone de texte (Sélection, Saisie, Effacer).
	 * 
	 * Construction et ajout du nouvel objet TextArea à la fenêtre graphique.
	 */
	public void createTextArea(){
		HashMap<String, Command> h = new HashMap<String, Command>();
		h.put("selectionner", selectionner);
		h.put("saisir", saisir);
		h.put("effacer", effacer);
		
		System.out.println("Commandes du TextArea : " + h);
		this.texteA = new TextArea(h);
		this.texteA.setBounds(10, 50, 775, 415);
		
		this.add(texteA);
	}
	
	/**
	 * Création et ajout des boutons à l'interface graphique.
	 * Utilisation de la classe bouton et ajout des commandes adaptées en paramètre.
	 */
	public void loadButtons(){
		Bouton bCouper = new Bouton("Couper", couper);
		bCouper.setBounds(10, 10, 90, 30);
		bCouper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				texteA.requestFocusInWindow();
			}
		});
		lesBoutons.add(bCouper);
		Bouton bCopier = new Bouton("Copier", copier);
		bCopier.setBounds(110, 10, 90, 30);
		bCopier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				texteA.requestFocusInWindow();
			}
		});
		lesBoutons.add(bCopier);
		Bouton bColler = new Bouton("Coller", coller);
		bColler.setBounds(210, 10, 90, 30);
		bColler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				texteA.requestFocusInWindow();
			}
		});
		lesBoutons.add(bColler);
		
		Bouton bDefaire = new Bouton("Defaire", defaire);
		bDefaire.setBounds(600, 10, 90, 30);
		bDefaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				texteA.requestFocusInWindow();
			}
		});
		lesBoutons.add(bDefaire);
		Bouton bRefaire = new Bouton("Refaire", refaire);
		bRefaire.setBounds(700, 10, 90, 30);
		bRefaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				texteA.requestFocusInWindow();
			}
		});
		lesBoutons.add(bRefaire);
		
		this.add(bCopier);
		this.add(bColler);
		this.add(bCouper);
		
		this.add(bDefaire);
		this.add(bRefaire);
	}
	
	/**
	 * Affiche l'interface graphique.
	 */
	public void launch(){
		this.setVisible(true);
	}
	
	/**
	 * Retourne le dernier Character du TextArea.
	 * @return : dernier caractère de la zone de texte.
	 */
	public char getCar() {
		return this.texteA.getDernierCar();
	}
	
	/**
	 * Retourne l'index du début de la sélection.
	 * @return : index du début de la sélection
	 */
	public int getDebutSelection() {
		return this.texteA.getDebutSelection();
	}

	/**
	 * Retourne l'index de fin de sélection.
	 * @return : index de fin de sélection
	 */
	public int getLongueurSelection() {
		return this.texteA.getLongueurSelection();
	}
	
	/**
	 * Initialisation des commandes (toutes).
	 * @param h : HM de String,Command , on récupère la Command associée au terme en clef.
	 * @throws Exception : Non présence de la commande dans le HM. (Erreur de configuration)
	 */
	public void setCommands(HashMap<String, Command> h) throws Exception {
		System.out.println("Commandes venant de Client : " + h);
		this.couper = h.get("couper");
		if (this.couper == null) throw new Exception("commande \"couper\" manquante");
		this.copier = h.get("copier");
		if (this.copier == null) throw new Exception("commande \"copier\" manquante");
		this.coller = h.get("coller");
		if (this.coller == null) throw new Exception("commande \"coller\" manquante");
		this.selectionner = h.get("selectionner");
		if (this.selectionner == null) throw new Exception("commande \"selectionner\" manquante");
		this.saisir = h.get("saisir");
		if (this.saisir == null) throw new Exception("commande \"saisir\" manquante");
		this.effacer = h.get("effacer");
		if (this.effacer == null) throw new Exception("commande \"effacer\" manquante");
		
		this.defaire = h.get("defaire");
		if (this.defaire == null) throw new Exception("commande \"defaire\" manquante");
		this.refaire = h.get("refaire");
		if (this.refaire == null) throw new Exception("commande \"refaire\" manquante");
	}
	
}
