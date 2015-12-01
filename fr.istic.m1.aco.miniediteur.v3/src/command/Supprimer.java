package command;

import receiver.MoteurEdition;

public class Supprimer implements Command {
	
	protected MoteurEdition em;
	
	public Supprimer(MoteurEdition newem){
		em = newem;
	}

	@Override
	public void execute() {	
		em.supprimer();
	}

}
