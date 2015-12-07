package originator;

import caretaker.Enregistreur;
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
	
	/**
	 * Constructeur de la commande enregistrable Couper.
	 * H�rite de la commande Couper.
	 * Initialise l'enregistreur.
	 * @param newem
	 * @param enregistreur
	 */
	public CouperEnregistrable(MoteurEdition newem, Enregistreur enregistreur) {
		super(newem);
		this.enregistreur = enregistreur;
	}

	/**
	 * Appel � la fonction execute de la classe m�re : Couper.
	 * Puis appel la fonction enregistrer(CommandeEnregistrable) de l'enregistreur.
	 */
	@Override
	public void execute() {
		if(me.getSelection().getLongueur() > 0){
			super.execute();
			enregistreur.enregistrer(this);
		}
	}
	
	/**
	 * R�cup�re le memento correspondant � la commande Enregistrable.
	 *  En initialisant l'EtatMemento avec les clones de la selection et du pressepapier.
	 * @return MementoCouper
	 */
	@Override
	public Memento getMemento() {
		MementoCouper mem = new MementoCouper();
		EtatMemento eM = new EtatMemento();
		PressePapier pp = (PressePapier) me.getPressePapier().clone();
		eM.setPressepapier(pp);
		Selection sel = (Selection) me.getSelection().clone();
		eM.setSelection(sel);
		eM.getSelection().setContenu(me.getSelection().getContenu());
		mem.setEtatMemento(eM);
		return mem;
	}

	/**
	 * Permet de rejouer la commande du Memento en appelant la fonction execute de la classe m�re : Couper.
	 * En modifiant la selection du moteur edition avec celle enregistr�e dans le memento.
	 * @param Memento
	 */
	@Override
	public void setMemento(Memento m) {
		ConcreteMemento mem = (ConcreteMemento) m;
		int debut = mem.getEtatMemento().getSelection().getDebut();
		int longueur = mem.getEtatMemento().getSelection().getLongueur();
		me.getSelection().setSelection(debut, longueur);
		String contenu = mem.getEtatMemento().getSelection().getContenu();
		me.getSelection().setContenu(contenu);
		super.execute();
	}

}
