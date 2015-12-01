package command;

import receiver.MoteurEdition;

public class Effacer implements Command {
	
	protected MoteurEdition em;
	
	public Effacer(MoteurEdition newem){
		em = newem;
	}

	@Override
	public void execute() {	
		em.effacer();
	}

}
