package receiver;

public class Selection implements Cloneable{

	private int debut;
	private int longueur;
	private String contenu;
	
	/**
	 * Constructeur par défaut de la classe Sélection.
	 * @param debut : début de la Sélection
	 * @param longueur : longueur de la Sélection
	 */
	public void setSelection(int debut, int longueur){
		this.debut = debut;
		this.longueur = longueur;
	}
	
	/**
	 * Changement des paramètres de la Sélection courante
	 * @return int : Retourne le début de la Selection
	 */
	public int getDebut() {
		return debut;
	}

	/**
	 * Retourne le début de la Sélection
	 * @param debut : debut de la selection
	 */
	public void setDebut(int debut) {
		this.debut = debut;
	}
	
	/**
	 * Change la valeur de début de la Sélection
	 * @return int : longueur de la selection
	 */
	public int getLongueur() {
		return longueur;
	}
	
	/**
	 * Change la valeur de longueur de la Sélection
	 * @param longueur : int, longueur de la Sélection
	 */
	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}
	
	/**
	 * Retourne le contenu de la Sélection
	 * @return : String, contenu de la Sélection
	 */
	public String getContenu() {
		return this.contenu;
	}
	
	/**
	 * Changement de la valeur du contenu
	 * @param contenu : nouvelle valeur pour le contenu de la Sélection.
	 */
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	/**
	 * Réinitialisation de la longueur et du contenu de la Sélection (Respectivement 0 et vide).
	 */
	public void initSelection(){
		this.longueur = 0;
		this.contenu = "";
	}
	
	/**
	 * Créer un clone de la selection.
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
