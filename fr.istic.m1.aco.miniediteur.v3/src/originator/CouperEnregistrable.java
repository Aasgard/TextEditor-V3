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
	 * Hérite de la commande Couper.
	 * Initialise l'enregistreur.
	 * @param newem
	 * @param enregistreur
	 */
	public CouperEnregistrable(MoteurEdition newem, Enregistreur enregistreur) {
		super(newem);
		this.enregistreur = enregistreur;
	}

	/**
	 * Appel à la fonction execute de la classe mère : Couper.
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
	 * Récupère le memento correspondant à la commande Enregistrable.
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
	 * Permet de rejouer la commande du Memento en appelant la fonction execute de la classe mère : Couper.
	 * En modifiant la selection du moteur edition avec celle enregistrée dans le memento.
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
