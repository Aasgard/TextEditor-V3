package originator;

import careTaker.Enregistreur;
import command.Coller;
import memento.ConcreteMemento;
import memento.EtatMemento;
import memento.Memento;
import memento.MementoColler;
import receiver.Buffer;
import receiver.MoteurEdition;
import receiver.PressePapier;
import receiver.Selection;

public class CollerEnregistrable extends Coller implements CommandEnregistrable  {

	private Enregistreur enregistreur;
	
	public CollerEnregistrable(MoteurEdition newem, Enregistreur enregistreur){
		super(newem);
		this.enregistreur = enregistreur;
	}
	
	@Override
	public void execute() {
		enregistreur.enregistrer(this);
		super.execute();
		
	}

	@Override
	public Memento getMemento() {
		MementoColler mem =  new MementoColler();
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
		String texte = mem.getEtatMemento().getPressepapier().getContenu();
		em.getPressePapier().setContenu(texte);
		int longueur = mem.getEtatMemento().getSelection().getLongueur();
		int debut = mem.getEtatMemento().getSelection().getDebut();
		em.getSelection().setSelection(debut, longueur);
		super.execute();
	}

}
