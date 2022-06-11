package backend;
public interface GameMap {
	
	public String getElementIn(int pos);
	
	public int playARound();
	
	public int getActualPlayer();
	
	public int getLastPlayer();
	
	public int getPlayerPosition(int player);
	
	public int getDaceRis();

}
