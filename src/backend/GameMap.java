package backend;
public interface GameMap {
	
	public String getElementIn(int pos);
	
	public String playARound(int player);
	
	public int getPlayerPosition(int player);
	
	public int getDaceRis();
	
	public boolean isPlayerBlocked(int player);

}
