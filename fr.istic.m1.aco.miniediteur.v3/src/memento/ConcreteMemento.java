package memento;

public abstract class ConcreteMemento implements Memento {

	private EtatMemento etatMemento;
	
	public ConcreteMemento(){
		this.etatMemento = new EtatMemento();
	}

	public EtatMemento getEtatMemento() {
		return this.etatMemento;
	}

	public void setEtatMemento(EtatMemento etatMemento) {
		this.etatMemento = etatMemento;
	}
	
}
