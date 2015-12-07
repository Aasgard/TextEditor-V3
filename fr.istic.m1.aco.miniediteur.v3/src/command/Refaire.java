package command;

import caretaker.Enregistreur;


public class Refaire implements Command {

	private Enregistreur enregistreur;
	
	/**
	 * Constructeur par défaut de la classe Refaire.
	 * @param enregistreur : Enregistreur à refaire
	 */
	public Refaire(Enregistreur enregistreur){
		this.enregistreur = enregistreur;
	}
	
	/**
	 * Appel de la fonction refaire() de l'enregistreur.
	 */
	@Override
	public void execute() {
		enregistreur.refaire();
	}

}
