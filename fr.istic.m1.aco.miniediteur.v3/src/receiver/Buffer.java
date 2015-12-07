package receiver;


public class Buffer implements Cloneable{
	
	private StringBuffer contenu;
	
	/**
	 * Constructeur par d�faut de la classe Buffer
	 * Initialisation de son contenu � vide
	 */
	public Buffer(){
		super();
		this.contenu = new StringBuffer("");
	}
	
	/**
	 * Changement des informations du Buffer.
	 * @param newContenu : Nouveau contenu � donner au Buffer
	 * @param debut : D�but de l'insertion dans le Buffer
	 * @param fin : Fin de l'insertion dans le Buffer
	 */
	public void setBuffer(StringBuffer newContenu, int debut, int fin){
		System.out.println("Debut de la selection "+debut +"\n fin de la selection "+fin);
		if(fin > 0){
			this.contenu.delete(debut, fin);
		}
		this.contenu.insert(debut, newContenu.toString() );
	}
	
	/**
	 * Retourne le contenu du Buffer
	 * @return : StringBuffer, contenu du Buffer
	 */
	public StringBuffer getContenu(){
		return this.contenu;
	}

	
	/**
	 * Cr�er un clone du buffer.
	 * @return : Buffer
	 */
	public Buffer clone(){
		Buffer o = null;
		try{
			o =(Buffer)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace(System.err);
		}
		
			o.contenu = new StringBuffer(this.contenu);
		return o;
	}
}

