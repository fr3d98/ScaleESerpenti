package backend;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GameMap {

	public String getElementIn(int pos);
	
	public String playARound();
	
	public int getPlayerPosition(int player);
	
	public int getDaceRis();
	
	public boolean isPlayerBlocked(int player);
	
	public void restart();
	
	public int getPlayersNumber();
	
	public int getnRows();
	
	public int getnCols();
	
	public int getnDaces();
	
	public int getRounds();
	
	public int getActPlayer();
	
	public boolean isDoubleSix();
	
	public GameMapState save();
	
	public void restore(GameMapState gms);
	
	class GameMapState implements Serializable{
		private static final long serialVersionUID = 3606904805372695279L;
		private int N, nRows, nCols, rounds , nDaces;
		private Map<Integer, Element> elements;
		
		private List<Card> deck;

		private boolean doubleSix;
		private Player[] players;
		private int daceRis, lastPlayer, actPlayer;
		
		public GameMapState(int N, Map<Integer, Element> elements, boolean doubleSix, Player[] players, int daceRis, int nDaces
				,int rows, int cols, int rounds, int lastPlayer, int actPlayer) {
			this.N=N; this.daceRis=daceRis;
			this.elements=elements; this.doubleSix=doubleSix;
			this.players=players;
			this.nCols=cols; this.nRows=rows; this.rounds=rounds;
			this.nDaces=nDaces;
			deck=Deck.INSTANCE.getDeck();
			this.lastPlayer=lastPlayer;
			this.actPlayer=actPlayer;
		}

		public int getN() {
			return N;
		}
		public Map<Integer, Element> getElements() {
			return elements;
		}

		public List<Card> getDeck() {
			return deck;
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
		public int getnDaces() {
			return nDaces;
		}

		public int getLastPlayer() {
			return lastPlayer;
		}

		public int getActPlayer() {
			return actPlayer;
		}
			
	}
}
