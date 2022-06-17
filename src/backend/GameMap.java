package backend;

import java.util.Map;

public interface GameMap {

	public String getElementIn(int pos);
	
	public String playARound(int player);
	
	public int getPlayerPosition(int player);
	
	public int getDaceRis();
	
	public boolean isPlayerBlocked(int player);
	
	public void restart();
	
	public int getPlayersNumber();
	
	public int getnRows();
	
	public int getnCols();
	
	public int getRounds();
	
	public int getLastPlayer();
		
	public GameMapState save();
	
	public void restore(GameMapState gms);
	
	class GameMapState{
		private int N, nRows, nCols, rounds;
		private Map<Integer, Element> elements;
		

		private boolean doubleSix;
		private Player[] players;
		private int daceRis;
		
		public GameMapState(int N, Map<Integer, Element> elements, boolean doubleSix, Player[] players, int daceRis
				,int rows, int cols, int rounds) {
			this.N=N; this.daceRis=daceRis;
			this.elements=elements; this.doubleSix=doubleSix;
			this.players=players;
			this.nCols=cols; this.nRows=rows; this.rounds=rounds;
		}

		public int getN() {
			return N;
		}
		public Map<Integer, Element> getElements() {
			return elements;
		}

		public void setElements(Map<Integer, Element> elements) {
			this.elements = elements;
		}

		public boolean isDoubleSix() {
			return doubleSix;
		}

		public Player[] getPlayers() {
			return players;
		}

		public int getDaceRis() {
			return daceRis;
		}

		public int getnRows() {
			return nRows;
		}

		public int getRounds() {
			return rounds;
		}

		public int getnCols() {
			return nCols;
		}
			
	}
}
