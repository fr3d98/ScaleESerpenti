package backend;
public interface GameMap {
	
	public int putPlayerIn(Player p, int pos);
	/**
	 * puts the player in his next position according to the element prensent in
	 * position "pos", then returns the actual position
	 */
	public Element getElementIn(int pos);
	
	public int playARound();
	
	

}
