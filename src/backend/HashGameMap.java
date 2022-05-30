package backend;
import java.util.HashMap;
import java.util.Map;

public enum HashGameMap implements GameMap {
	
	INSTANCE;
	
	private int N; //number of positions

	private Map<Integer, Element> elements= new HashMap<>();
	
	private Player[]  players;
	
	private int actualPlayer=0;
	
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
	

	@Override
	public int putPlayerIn(Player p, int pos) {
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
	public Element getElementIn(int pos) {
		return this.elements.get(pos);
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
		if(p.getCurrPos()+ris>N) ris=ris+p.getCurrPos()-N;
		int nPos=p.getCurrPos()+ris;
		actualPlayer=(++actualPlayer)%players.length;
		System.out.println("DEBUG actual player now is: "+actualPlayer);
		return putPlayerIn(p,nPos);
	}

}
