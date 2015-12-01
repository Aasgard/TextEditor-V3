package command;

import receiver.MoteurEdition;

public class Coller implements Command {
	
	protected static MoteurEdition em;
	
	public Coller(MoteurEdition newem){
		em = newem;
	}

	@Override
	public void execute() {
		em.coller();
	}

}
