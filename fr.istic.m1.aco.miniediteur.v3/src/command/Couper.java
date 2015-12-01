package command;

import receiver.MoteurEdition;

public class Couper implements Command {
	
	protected static MoteurEdition em;
	
	public Couper(MoteurEdition newem){
		em = newem;
	}

	@Override
	public void execute() {
		em.couper();
	}

}
