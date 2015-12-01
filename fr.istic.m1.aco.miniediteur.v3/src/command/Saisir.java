package command;

import invoker.IHM;
import receiver.MoteurEdition;

public class Saisir implements Command {
	
	protected MoteurEdition em;
	protected IHM ihm;
	
	public Saisir(MoteurEdition newem, IHM newihm){
		em = newem;
		ihm = newihm;
	}
	
	@Override
	public void execute() {
		String texte = String.valueOf(ihm.getCar());
		em.saisir(texte);
	}

}
