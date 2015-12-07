package receiver;

public class Selection implements Cloneable{

	private int debut;
	private int longueur;
	private String contenu;
	
	/**
	 * Constructeur par d�faut de la classe S�lection.
	 * @param debut : d�but de la S�lection
	 * @param longueur : longueur de la S�lection
	 */
	public void setSelection(int debut, int longueur){
		this.debut = debut;
		this.longueur = longueur;
	}
	
	/**
	 * Changement des param�tres de la S�lection courante
	 * @return int : Retourne le d�but de la Selection
	 */
	public int getDebut() {
		return debut;
	}

	/**
	 * Retourne le d�but de la S�lection
	 * @param debut : debut de la selection
	 */
	public void setDebut(int debut) {
		this.debut = debut;
	}
	
	/**
	 * Change la valeur de d�but de la S�lection
	 * @return int : longueur de la selection
	 */
	public int getLongueur() {
		return longueur;
	}
	
	/**
	 * Change la valeur de longueur de la S�lection
	 * @param longueur : int, longueur de la S�lection
	 */
	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}
	
	/**
	 * Retourne le contenu de la S�lection
	 * @return : String, contenu de la S�lection
	 */
	public String getContenu() {
		return this.contenu;
	}
	
	/**
	 * Changement de la valeur du contenu
	 * @param contenu : nouvelle valeur pour le contenu de la S�lection.
	 */
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	/**
	 * R�initialisation de la longueur et du contenu de la S�lection (Respectivement 0 et vide).
	 */
	public void initSelection(){
		this.longueur = 0;
		this.contenu = "";
	}
	
	/**
	 * Cr�er un clone de la selection.
	 * @return : Selection
	 */
	public Object clone(){
		Object o = null;
		try{
			o = super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace(System.err);
		}
		
		return o;
	}
	
}
