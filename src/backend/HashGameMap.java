package backend;
import java.util.HashMap;
import java.util.Map;

public enum HashGameMap implements GameMap {
	
	INSTANCE;
	
	private int N; //number of positions

	private Map<Integer, Element> elements= new HashMap<>();
	
	private Player[]  players;
	
	private int actualPlayer=0;
	private int lastPlayer=0;
	
	private int daceRis;
	
	@Override
	public int getActualPlayer() {
		return actualPlayer;
	}

	public void addNewElement(Element e) {
		int pos=e.getActionPoint();
		if(elements.containsKey(pos))throw new IllegalArgumentException("Can't put element in this position!");
		elements.put(pos, e);
	}
	
	public void setN(int N) {
		this.N=N;
	}
	
	public void setPlayers(Player[] players) {
		this.players=players;
	}
	
	
	private int putPlayerIn(Player p, int pos) {
		/**
		 * puts the player in his next position according to the element prensent in
		 * position "pos", then returns the actual position
		 */
		Element e=elements.get(pos);
		p.moveTo(pos);
		System.out.println("Player "+p+ " goes from "+p.getLastPos()+" to "+pos);
		if (e==null) {
			return p.getCurrPos(); //empty position
		}
		int nPos=e.action(p);
		if(nPos==p.getCurrPos())//no movement was made
			return nPos;
		return putPlayerIn(p, nPos);
	}

	@Override
	public String getElementIn(int pos) {
		Element e = elements.get(pos);
		if(e instanceof Snake) {
			Snake s= (Snake) e;
			return "SERPENTE "+ s.getBottom();
		}
		else if(e instanceof Ladder) {
			Ladder l= (Ladder) e;
			return "SCALA "+l.getTop();
		}
		else if(e instanceof Rest ) return "LOCANDA";
		else if(e instanceof Bench) return "PANCHINA";
		else return "";
	}

	@Override
	public int playARound() {
		Player p=players[actualPlayer];
		if(p.getRoundsToWait()!=0) {
			System.out.println(p+" must wait "+p.getRoundsToWait()+" rounds to play");
			p.setRoundsToWait(p.getRoundsToWait()-1);
			return p.getCurrPos();
		}
		int ris=p.throwDaces();
		daceRis=ris;
		if(p.getCurrPos()+ris>N) ris=ris+p.getCurrPos()-N;
		int nPos=p.getCurrPos()+ris;
		lastPlayer=actualPlayer;
		actualPlayer=(++actualPlayer)%players.length;
		return putPlayerIn(p,nPos);
	}

	@Override
	public int getLastPlayer() {
		return lastPlayer;
	}

	@Override
	public int getPlayerPosition(int player) {
		return players[player].getCurrPos();
	}

	@Override
	public int getDaceRis() {
		return daceRis;
	}
	
	
	

}
