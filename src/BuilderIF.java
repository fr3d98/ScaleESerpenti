public interface BuilderIF {
	
	//builds the game map
	
	public GameMap getGameMap();
	
	public void buildSnakes(int n);
	public void buildLadders(int n);
	public void buildRestBoxes(int n);
	public void buildPrizeBoxes(int n);
}
