public interface BuilderIF {
	
	//builds the game map
	
	public GameMap getGameMap();
	
	public void buildSpace(int rows, int columns);
	public void buildSnakes(int[] ... s);
	public void buildLadders(int[] ... n);
	public void buildBenches(int ... pos);
	public void buildRests(int ... pos );
	public void buildDaceBoxes(int ...pos);
	public void buildCardBoxes(int ...pos);
	public void buildDaces(int N);
	
}
