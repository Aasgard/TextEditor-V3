package originator;

import caretaker.Enregistreur;
import command.Supprimer;
import memento.Memento;
import memento.MementoSupprimer;
import receiver.MoteurEdition;

public class SupprimerEnregistrable extends Supprimer implements CommandEnregistrable {

	private Enregistreur enregistreur;
	
	public SupprimerEnregistrable(MoteurEdition newem,  Enregistreur enregistreur) {
		super(newem);
		this.enregistreur = enregistreur;
	}
	
	@Override
	public void execute() {
		super.execute();
		enregistreur.enregistrer(this);
	}
	
	@Override
	public Memento getMemento() {
		MementoSupprimer mem = new MementoSupprimer();
		mem.getEtatMemento().setPressepapier(em.getPressePapier());
		mem.getEtatMemento().setSelection(em.getSelection());
		return mem;
	}

	@Override
	public void setMemento(Memento m) {
		super.execute();
	}

}
