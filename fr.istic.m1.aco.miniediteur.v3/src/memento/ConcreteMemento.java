package memento;

public abstract class ConcreteMemento implements Memento {

	private EtatMemento etatMemento;
	
	/**
	 * Constructeur de la classe abstraite ConcreteMemento.
	 * Initialise un EtatMemento.
	 */
	public ConcreteMemento(){
		this.etatMemento = new EtatMemento();
	}

	/**
	 * Retourne l'EtatMemento
	 * @return : EtatMemento
	 */
	public EtatMemento getEtatMemento() {
		return this.etatMemento;
	}

	/**
	 * Modifier l'EtatMemento avec celui passé en argument .
	 * @param etatMemento
	 */
	public void setEtatMemento(EtatMemento etatMemento) {
		this.etatMemento = etatMemento;
	}
	
}
