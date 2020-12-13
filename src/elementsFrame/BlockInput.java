package elementsFrame;

/**
 * Cette class va créer les élèment de saisie utilisateur pour chaque fenêtre
 * Chaque élément est composé d'un icone, d'un texte, d'une image de font et d'un élément d'entrèe soit normal ou pour mot de passe
 * @author CAVAUD
 *
 */
public class BlockInput {
	
	private String texte;
	private int pos;
	private int pos1;
	private String img;
	private LesLabel block_1;
	private LesLabel block_2;
	private LesPasswordField block_3p;
	private LesTextField block_3;
	private LesLabel block_4;
	
	/**
	 * création des 4 éléments 
	 * @param password true si l'élément sera de type mot de passe
	 * @param texte le texte
	 * @param img l'image de l'icone
	 * @param pos la postion
	 * @param pos1 la position
	 */
	public BlockInput(boolean password, String texte, String img, int pos, int pos1) {
		setTexte(texte);
		setImg(img);
		setPos1(pos1);
		setPos(pos);
		setBlock_1(new LesLabel(getTexte(), null, getPos(), getPos1()));
		setBlock_2(new LesLabel("", getImg(), getPos(), getPos1() + 28));
		if(password == true) {
			setBlock_3p(new LesPasswordField(getPos() + 44, getPos1() + 39));
		}else {
			setBlock_3(new LesTextField(getPos() + 44, getPos1() + 39));
		}
		setBlock_4(new LesLabel("", "input" , getPos() + 32, getPos1() + 30));
	}
	
	
	/**
	 * @return the pos
	 */
	public int getPos() {
		return pos;
	}
	/**
	 * @param pos the pos to set
	 */
	public void setPos(int pos) {
		this.pos = pos;
	}
	/**
	 * @return the block_1
	 */
	public LesLabel getBlock_1() {
		return block_1;
	}
	/**
	 * @param block_1 the block_1 to set
	 */
	public void setBlock_1(LesLabel block_1) {
		this.block_1 = block_1;
	}
	/**
	 * @return the block_2
	 */
	public LesLabel getBlock_2() {
		return block_2;
	}
	/**
	 * @param block_2 the block_2 to set
	 */
	public void setBlock_2(LesLabel block_2) {
		this.block_2 = block_2;
	}
	/**
	 * @return the block_3p
	 */
	public LesPasswordField getBlock_3p() {
		return block_3p;
	}
	/**
	 * @param block_3p the block_3p to set
	 */
	public void setBlock_3p(LesPasswordField block_3p) {
		this.block_3p = block_3p;
	}
	/**
	 * @return the block_3
	 */
	public LesTextField getBlock_3() {
		return block_3;
	}
	/**
	 * @param block_3 the block_3 to set
	 */
	public void setBlock_3(LesTextField block_3) {
		this.block_3 = block_3;
	}
	/**
	 * @return the block_4
	 */
	public LesLabel getBlock_4() {
		return block_4;
	}
	/**
	 * @param block_4 the block_4 to set
	 */
	public void setBlock_4(LesLabel block_4) {
		this.block_4 = block_4;
	}
	/**
	 * @return the pos1
	 */
	public int getPos1() {
		return pos1;
	}
	/**
	 * @param pos1 the pos1 to set
	 */
	public void setPos1(int pos1) {
		this.pos1 = pos1;
	}
	/**
	 * @return the texte
	 */
	public String getTexte() {
		return texte;
	}
	/**
	 * @param texte the texte to set
	 */
	public void setTexte(String texte) {
		this.texte = texte;
	}
	/**
	 * @return the img
	 */
	public String getImg() {
		return img;
	}
	/**
	 * @param img2 the img to set
	 */
	public void setImg(String img2) {
		this.img = img2;
	}
}


