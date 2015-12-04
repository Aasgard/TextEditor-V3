package receiver;

import java.util.ArrayList;
import java.util.Iterator;

import observer.Observer;
import observer.Subject;

public class MoteurEdition extends Subject implements IMoteurEdition{

	
	private Buffer buffer;
	private Selection selection;
	private PressePapier pressePapier;
	

	public MoteurEdition(){
		observers = new ArrayList<Observer>();
		this.buffer = new Buffer();
		this.selection = new Selection();
		this.pressePapier = new PressePapier("");
	}
	
	/**
	 * On met la sélection dans le PP.
	 * On enlève le contenu de la sélection du buffer courant.
	 * On réinitialise la sélection.
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
	 * On met la sélection dans le PP
	 * On garde le contenu de la sélection du buffer courant sans modifier la sélection.
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
	 * On crée un nouvel objet de selection
	 * prenant en paramètre le debut de la selection et sa longueur
	 */
	@Override
	public void selectionner(Integer debut, Integer longueur) {
		try{
			this.selection.setDebut(debut);
			this.selection.setLongueur(longueur);
			this.selection.setContenu(this.buffer.getContenu().toString().substring(debut, debut+longueur));
		}catch (Exception e){
			System.out.println("Erreur de création : objet Sélection");
		}
	}
	
	/**
	 * On insère le texte dans le buffer à l'endroit de la sélection.
	 */
	@Override
	public void saisir(String texte){
		this.buffer.setBuffer(new StringBuffer(texte), this.selection.getDebut(), this.selection.getLongueur()+this.selection.getDebut());
		this.selection.setDebut(this.selection.getDebut()+texte.length());
		notifyObservers();
	}
	
	/**
	 * On insère le contenu du presse papier dans le buffer
	 * au niveau de la selection puis on déplace la selection à la fin de notre contenu coller
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
	
	public Buffer getBuffer(){
		return this.buffer;
	}
	
	public PressePapier getPressePapier(){
		return this.pressePapier;
	}
	
	public Selection getSelection(){
		return this.selection;
	}

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

