package receiver;

import java.util.ArrayList;
import java.util.Iterator;

import observer.Observer;
import observer.Subject;

public class MoteurEdition extends Subject implements IMoteurEdition{

	
	private Buffer buffer;
	private Selection selection;
	private PressePapier pressePapier;
	
	/**
	 * Constructeur du MoteurEdition.
	 * Initialisation "neutres" des diff�rentes classes m�tiers (Buffer, S�lection, PressePapier)
	 */
	public MoteurEdition(){
		observers = new ArrayList<Observer>();
		this.buffer = new Buffer();
		this.selection = new Selection();
		this.pressePapier = new PressePapier("");
	}
	
	/**
	 * Reproduction de la commande Couper classique.
	 * On r�cup�re le contenu de la S�lection, on le met dans le PP puis on retire ce contenu du Buffer.
	 */
	@Override
	public void couper() {
		if(selection.getLongueur() > 0){
			String contenuSelection = this.selection.getContenu();
			pressePapier.setContenu(contenuSelection);
			this.buffer.getContenu().delete(this.selection.getDebut(), this.selection.getDebut()+this.selection.getLongueur());
			this.selection.initSelection();
			notifyObservers();
		}
	}
	
	/**
	 * Reproduction de la commande Copier classique.
	 * R�cup�ration du contenu de la S�lection que l'on stocke dans le PP.
	 * Remise � 0 de la longueur et contenu de la S�lection.
	 */
	@Override
	public void copier() {
		if(selection.getLongueur() > 0 ){
			String contenuSelection = selection.getContenu();
			pressePapier.setContenu(contenuSelection);
			this.selection.initSelection();
			notifyObservers();
		}
	}
	
	/**
	 * S�lection d'un texte dans le Buffer.
	 * Initialisation du d�but, de la longueur et de son contenu.
	 */
	@Override
	public void selectionner(Integer debut, Integer longueur) {
		try{
			this.selection.setDebut(debut);
			this.selection.setLongueur(longueur);
			this.selection.setContenu(this.buffer.getContenu().toString().substring(debut, debut+longueur));
		}catch (Exception e){
			System.out.println("Erreur de cr�ation : objet S�lection");
		}
	}
	
	/**
	 * Saisie d'un texte dans le Buffer.
	 * Puis on modifie le debut de la selection en y rajoutant la longueur du texte
	 * @param texte : texte � saisir dans le Buffer
	 */
	@Override
	public void saisir(String texte){
		this.buffer.setBuffer(new StringBuffer(texte), this.selection.getDebut(), this.selection.getLongueur()+this.selection.getDebut());
		this.selection.setDebut(this.selection.getDebut()+texte.length());
		notifyObservers();
	}
	
	/**
	 * Reproduction de la commande Coller classique.
	 * R�cup�ration du contenu du PressePapier.
	 * Cr�ation d'un StringBuffer contenant le contenu du PressePapier.
	 * Ajout au buffer au niveau du d�but de la selection.
	 * D�but de la S�lection modifi�e (Endroit de Collage et ajout de la longueur du texte dans le PP).
	 * Remise � 0 de la longueur et contenu de la S�lection.
	 */
	@Override
	public void coller() {
		StringBuffer newcontenu = new StringBuffer(pressePapier.getContenu());
		this.buffer.setBuffer(newcontenu, this.selection.getDebut(), this.selection.getLongueur()+this.selection.getDebut());	
		selection.setDebut(selection.getDebut()+pressePapier.getContenu().length());
		this.selection.initSelection();
		System.out.println("Ca l'a coller : "+ this.getPressePapier().getContenu().toString());
		notifyObservers();
	}
	
	/**
	 * Reproduction de la commande Effacer classique.
	 * Suppression du caract�re pr�cedent le d�but de la s�lection.
	 * Si longueur de S�lection sup�rieure � 0, on supprime la longueur de la chaine texte � partir du d�but de la s�lection.
	 */
	@Override
	public void effacer() {
		int debut = this.selection.getDebut();
		System.out.println(debut);
		if(this.getSelection().getLongueur() == 0 && debut != 0){
			this.buffer.getContenu().delete(debut-1, debut);
			this.selection.setDebut(debut-1);		
		}
		if(this.selection.getLongueur() > 0){
			System.out.println("la longueur "+this.selection.getLongueur());
			this.buffer.getContenu().delete(debut, debut+this.selection.getLongueur());
			this.selection.initSelection();
			notifyObservers();
		}
		if (debut == 0 && this.selection.getLongueur() == 0){
			notifyObservers();
			return;
		}
		notifyObservers();
	}
	
	@Override
	public void supprimer() {
		if(this.selection.getLongueur() > 0){
			this.buffer.getContenu().delete(this.selection.getDebut(), this.selection.getDebut()+this.selection.getLongueur());
			this.selection.initSelection();
		}
		else{
			int debut = this.selection.getDebut();
			this.buffer.getContenu().delete(debut, debut+1);
			this.selection.setDebut(debut);		
		}
		notifyObservers();
	}
	
	/**
	 * Retourne le buffer.
	 * @return : le Buffer
	 */
	public Buffer getBuffer(){
		return this.buffer;
	}
	
	/**
	 * Retourne le PP.
	 * @return : le PP
	 */
	public PressePapier getPressePapier(){
		return this.pressePapier;
	}
	
	/**
	 * Retourne la S�lection.
	 * @return : le S�lection
	 */
	public Selection getSelection(){
		return this.selection;
	}

	/**
	 * Permets de notifier l'obersver (l'IHM) d'un changement
	 * @see observer.Subject#notifyObservers()
	 */
	@Override
	public void notifyObservers() {
		for (Iterator<Observer> it = observers.iterator(); it.hasNext();) {
			Observer o = it.next();
			o.notifyMe();
		}
	}

	@Override
	public void registerObserver(Observer o) {
		observers.add(o);

	}

	@Override
	public void unregisterObserver(Observer o) {
		observers.remove(o);
	}

	
	
}

