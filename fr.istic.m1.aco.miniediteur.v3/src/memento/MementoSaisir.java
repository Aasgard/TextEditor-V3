package memento;

public class MementoSaisir extends ConcreteMemento implements Memento {

	private String texte;
	
	public MementoSaisir(String texte){
		this.setTexte(texte);
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}
}
