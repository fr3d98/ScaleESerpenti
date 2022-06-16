package backend;
public interface BuilderIF {
	
	//builds the game map
	
	public GameMap getGameMap();
	
	public Player[] buildPlayers(int N);
	public int buildSpace(int rows, int columns);
	public void buildSnakesRandom(int N);
	public void buildLaddersRandom(int N);
	public void buildBenchesRandom(int N);
	public void buildRestsRandom(int N);
	public void buildDaceBoxesRandom(int N);
	public void buildCardBoxesRandom(int N);
	public void buildSpringBoxesRandom(int N);
	public void buildSnakes(int[] ... s);
	public void buildLadders(int[] ... n);
	public void buildBenches(int ... pos);
	public void buildRests(int ... pos );
	public void buildDaceBoxes(int ...pos);
	public void buildCardBoxes(int ...pos);
	public void buildSpringBoxes(int ...pos);
	public void buildDoubleSix();
	public Daces buildDaces(int N);
	public Deck buildDeck();
	
}
