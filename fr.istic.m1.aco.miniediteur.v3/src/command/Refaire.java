package command;

import caretaker.Enregistreur;


public class Refaire implements Command {

	private Enregistreur enregistreur;
	
	public Refaire(Enregistreur enregistreur){
		this.enregistreur = enregistreur;
	}
	
	@Override
	public void execute() {
		enregistreur.refaire();
	}

}
