package originator;

import careTaker.Enregistreur;
import command.Couper;
import memento.ConcreteMemento;
import memento.EtatMemento;
import memento.Memento;
import memento.MementoCouper;
import receiver.MoteurEdition;
import receiver.PressePapier;
import receiver.Selection;

public class CouperEnregistrable extends Couper implements CommandEnregistrable {

	private Enregistreur enregistreur;
	
	public CouperEnregistrable(MoteurEdition newem, Enregistreur enregistreur) {
		super(newem);
		this.enregistreur = enregistreur;
	}

	@Override
	public void execute() {
		if(em.getSelection().getLongueur() > 0){
			super.execute();
			enregistreur.enregistrer(this);
		}
	}
	
	@Override
	public Memento getMemento() {
		MementoCouper mem = new MementoCouper();
		EtatMemento eM = new EtatMemento();
		PressePapier pp = (PressePapier) em.getPressePapier().clone();
		eM.setPressepapier(pp);
		Selection sel = (Selection) em.getSelection().clone();
		eM.setSelection(sel);
		eM.getSelection().setContenu(em.getSelection().getContenu());
		mem.setEtatMemento(eM);
		return mem;
	}

	@Override
	public void setMemento(Memento m) {
		ConcreteMemento mem = (ConcreteMemento) m;
		int debut = mem.getEtatMemento().getSelection().getDebut();
		int longueur = mem.getEtatMemento().getSelection().getLongueur();
		em.getSelection().setSelection(debut, longueur);
		String contenu = mem.getEtatMemento().getSelection().getContenu();
		em.getSelection().setContenu(contenu);
		super.execute();
	}

}
