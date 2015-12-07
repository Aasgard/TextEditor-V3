package originator;

import caretaker.Enregistreur;
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

	/**
	 * Constructeur de la commande enregistrable Effacer.
	 * Hérite de la commande Effacer.
	 * Initialise l'enregistreur.
	 * @param newem
	 * @param enregistreur
	 */
	public EffacerEnregistrable(MoteurEdition newem, Enregistreur enregistreur) {
		super(newem);
		this.enregistreur = enregistreur;
	}

	/**
	 * Appel à la fonction execute de la classe mère : Effacer.
	 * Puis appel la fonction enregistrer(CommandeEnregistrable) de l'enregistreur.
	 */
	@Override
	public void execute() {
		if(me.getSelection().getDebut()!=0 || me.getSelection().getLongueur() !=0){
			enregistreur.enregistrer(this);
			super.execute();	
		}
	}

	/**
	 * Récupère le memento correspondant à la commande Enregistrable.
	 *  En initialisant l'EtatMemento avec les clones de la selection, du buffer et du pressepapier.
	 * @return MementoEffacer
	 */
	@Override
	public Memento getMemento() {
		MementoEffacer mem = new MementoEffacer();
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
	 * Permet de rejouer la commande du Memento en appelant la fonction execute de la classe mère : Effacer.
	 * En modifiant la selection du moteur edition avec celle enregistrée dans le memento.
	 * @param Memento
	 */
	@Override
	public void setMemento(Memento m) {
		ConcreteMemento mem = (ConcreteMemento) m;
		int debut = mem.getEtatMemento().getSelection().getDebut();
		int longueur = mem.getEtatMemento().getSelection().getLongueur();
		me.getSelection().setSelection(debut, longueur);
		super.execute();
	}


}
