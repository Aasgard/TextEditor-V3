package command;

import careTaker.Enregistreur;

public class Defaire implements Command {

	private Enregistreur enregistreur;
	
	public Defaire(Enregistreur enregistreur){
		this.enregistreur = enregistreur;
	}
	
	@Override
	public void execute() {
		enregistreur.defaire();
	}

}
