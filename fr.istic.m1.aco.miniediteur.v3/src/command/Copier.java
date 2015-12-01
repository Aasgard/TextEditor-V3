package command;

import receiver.MoteurEdition;

public class Copier implements Command {
	
	protected MoteurEdition em;
	
	public Copier(MoteurEdition newem){
		em = newem;
	}

	@Override
	public void execute() {	
		em.copier();
	}

}
