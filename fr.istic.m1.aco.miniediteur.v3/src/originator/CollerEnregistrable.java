package originator;

import caretaker.Enregistreur;
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
	
	/**
	 * Constructeur de la commande enregistrable Coller.
	 * Hérite de la commande Coller.
	 * Initialise l'enregistreur.
	 * @param newem
	 * @param enregistreur
	 */
	public CollerEnregistrable(MoteurEdition newem, Enregistreur enregistreur){
		super(newem);
		this.enregistreur = enregistreur;
	}
	
	/**
	 * Appel à la fonction execute de la classe mère : Coller.
	 * Puis appel la fonction enregistrer(CommandeEnregistrable) de l'enregistreur.
	 */
	@Override
	public void execute() {
		enregistreur.enregistrer(this);
		super.execute();
		
	}

	/**
	 * Récupère le memento correspondant à la commande Enregistrable.
	 * En initialisant l'EtatMemento avec les clones de la selection, du buffer et du pressepapier.
	 * @return MementoColler
	 */
	@Override
	public Memento getMemento() {
		MementoColler mem =  new MementoColler();
		EtatMemento eM = new EtatMemento();
		Buffer b = (Buffer) me.getBuffer().clone();
		eM.setBufferCopie(b);
		PressePapier pp = (PressePapier) me.getPressePapier().clone();
		eM.setPressepapier(pp);
		Selection sel = (Selection) me.getSelection().clone();
		eM.setSelection(sel);
		eM.getSelection().setContenu(me.getSelection().getContenu());
		mem.setEtatMemento(eM);
		return mem;
	}

	/**
	 * Permet de rejouer la commande du Memento en appelant la fonction execute de la classe mère : Coller.
	 * En modifiant la selection et le pressepapier du moteur edition avec ceux enregistrés dans le memento.
	 * @param Memento
	 */
	@Override
	public void setMemento(Memento m) {
		ConcreteMemento mem = (ConcreteMemento) m;
		String texte = mem.getEtatMemento().getPressepapier().getContenu();
		me.getPressePapier().setContenu(texte);
		int longueur = mem.getEtatMemento().getSelection().getLongueur();
		int debut = mem.getEtatMemento().getSelection().getDebut();
		me.getSelection().setSelection(debut, longueur);
		super.execute();
	}

}
