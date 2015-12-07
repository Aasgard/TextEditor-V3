package command;

import caretaker.Enregistreur;

public class Defaire implements Command {

	private Enregistreur enregistreur;
	
	/**
	 * Constructeur par d�faut de la classe Defaire.
	 * @param enregistreur : Enregistreur � d�faire
	 */
	public Defaire(Enregistreur enregistreur){
		this.enregistreur = enregistreur;
	}
	
	/**
	 * Appel de la fonction defaire() de l'enregistreur.
	 */
	@Override
	public void execute() {
		enregistreur.defaire();
	}

}
