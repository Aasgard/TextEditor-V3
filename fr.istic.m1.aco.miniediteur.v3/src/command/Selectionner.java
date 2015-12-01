package command;


import invoker.IHM;
import receiver.MoteurEdition;

public class Selectionner implements Command {
	
	protected MoteurEdition em;
	protected IHM ihm;
	
	public Selectionner(MoteurEdition em, IHM newihm){
		this.em = em;
		this.ihm = newihm;
	}
	
	@Override
	public void execute() {
		int debut = ihm.getDebutSelection();
		int fin = ihm.getLongueurSelection();
		System.out.println("la c'est la commande : " + debut);
		em.selectionner(debut, fin);
	}

}
