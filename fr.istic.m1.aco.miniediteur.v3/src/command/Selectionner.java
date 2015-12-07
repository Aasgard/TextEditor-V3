package command;

import invoker.IHM;
import receiver.MoteurEdition;

public class Selectionner implements Command {

	protected MoteurEdition me;
	protected IHM ihm;
	
	/**
	 * Constructeur par d�faut de la classe Selectionner.
	 * @param moteure : objet MoteurEdition
	 * @param newihm : objet IHM
	 */
	public Selectionner(MoteurEdition moteure, IHM newihm){
		this.me = moteure;
		this.ihm = newihm;
	}
	
	/**
	 * Appelle la fonction selectionner(int debut, int fin).
	 * R�cup�ration du d�but et fin de la s�lection aupr�s de l'IHM.
	 * Option: trace de la S�lection
	 */
	public void execute() {
		int debut = ihm.getDebutSelection();
		int fin = ihm.getLongueurSelection();
		System.out.println("D�but de S�lection : " + debut);
		me.selectionner(debut, fin);
	}

}

