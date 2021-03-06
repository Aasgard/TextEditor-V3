package originator;

import caretaker.Enregistreur;
import command.Selectionner;
import invoker.IHM;
import memento.Memento;
import memento.MementoSelectionner;
import receiver.MoteurEdition;

public class SelectionnerEnregistrable extends Selectionner implements CommandEnregistrable{

	private Enregistreur enregistreur;
	
	public SelectionnerEnregistrable(MoteurEdition em, IHM newihm, Enregistreur enregistreur) {
		super(em, newihm);
		this.enregistreur = enregistreur;
	}

	@Override
	public void execute() {
		super.execute();	
	}
	
	@Override
	public Memento getMemento() {
		return new MementoSelectionner(me.getSelection().getDebut(), me.getSelection().getLongueur());
	}

	@Override
	public void setMemento(Memento memento) {
		MementoSelectionner mem = (MementoSelectionner) memento;
		me.selectionner(mem.getDebutSelection(), mem.getLongueurSelection());
	}

}
