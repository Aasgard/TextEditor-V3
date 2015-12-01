package originator;

import careTaker.Enregistreur;
import command.Effacer;
import memento.ConcreteMemento;
import memento.EtatMemento;
import memento.Memento;
import memento.MementoEffacer;
import receiver.Buffer;
import receiver.MoteurEdition;
import receiver.PressePapier;
import receiver.Selection;

public class EffacerEnregistrable extends Effacer implements CommandEnregistrable {

	private Enregistreur enregistreur;

	public EffacerEnregistrable(MoteurEdition newem, Enregistreur enregistreur) {
		super(newem);
		this.enregistreur = enregistreur;
	}


	@Override
	public void execute() {
		if(em.getSelection().getDebut()!=0){
			enregistreur.enregistrer(this);
			super.execute();	
		}
	}

	@Override
	public Memento getMemento() {
		MementoEffacer mem = new MementoEffacer();
		EtatMemento eM = new EtatMemento();
		Buffer b = (Buffer) em.getBuffer().clone();
		eM.setBufferCopie(b);
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
		super.execute();
	}


}
