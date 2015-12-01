package originator;

import careTaker.Enregistreur;
import command.Saisir;
import invoker.IHM;
import memento.EtatMemento;
import memento.Memento;
import memento.MementoSaisir;
import receiver.MoteurEdition;
import receiver.PressePapier;
import receiver.Selection;

public class SaisirEnregistrable extends Saisir implements CommandEnregistrable {

	private Enregistreur enregistreur;
	
	public SaisirEnregistrable(MoteurEdition newem, IHM newihm, Enregistreur enregistreur) {
		super(newem, newihm);
		this.enregistreur = enregistreur;
	}

	@Override
	public void execute() {
		super.execute();
		enregistreur.enregistrer(this);
	}
	
	@Override
	public Memento getMemento() {
		MementoSaisir mem = new MementoSaisir(String.valueOf(ihm.getCar()));
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
		MementoSaisir mem = ((MementoSaisir) m);
		int debut = mem.getEtatMemento().getSelection().getDebut();
		int longueur = mem.getEtatMemento().getSelection().getLongueur();
		em.getSelection().setSelection(debut-1, longueur);
		String texte = mem.getTexte();
		em.saisir(texte);
	}


}
