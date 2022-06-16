package backend;
import java.util.HashMap;
import java.util.Map;

public enum HashGameMap implements GameMap {
	
	INSTANCE;
	
	private int N; //number of positions

	private Map<Integer, Element> elements= new HashMap<>();
	
	private Player[]  players;
	
	
	private int daceRis;
	private boolean doubleSix=false;
	
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
	
	public void enableDoubleSix() {
		doubleSix=true;
	}
	
	
	private int putPlayerIn(Player p, int pos, StringBuilder sb) {
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
		int nPos=e.action(p, sb);
		if(nPos>N) {
			int margin=nPos-N;
			nPos=N-margin;
		}
		else if(nPos==p.getCurrPos()) {
			//no movement was made
			return nPos;
		}
		return putPlayerIn(p, nPos, sb);
	}

	@Override
	public String getElementIn(int pos) {
		Element e = elements.get(pos);
		if(e==null)return "";
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
		else if(e instanceof CardBoxes) return "CARTE";
		else if(e instanceof DaceBoxes) return "DADI";
		else if(e instanceof Spring) return "MOLLA";
		else return "";
	}

	@Override
	public String playARound(int player) {
		StringBuilder sb= new StringBuilder();
		Player p=players[player];
		int x=player+1;
		sb.append("Tocca al giocatore "+x+" in posizione "+p.getCurrPos()+'\n');
		if(p.getRoundsToWait()!=0) {
			System.out.println(p+" must wait "+p.getRoundsToWait()+" rounds to play");
			sb.append(p+" deve attendere ancora "+p.getRoundsToWait()+" giri per giocare."+'\n');
			p.setRoundsToWait(p.getRoundsToWait()-1);
			return sb.toString();
		}
		int ris=p.throwDaces();
		daceRis=ris;
		sb.append("Il giocatore "+x+" ha ottenuto "+ris+'\n');
		int nPos;
		if(p.getCurrPos()+ris>N) {
			int margin=p.getCurrPos()+ris-N;
			nPos=N-margin;
		}
		else{
			nPos=p.getCurrPos()+ris;
		}
		putPlayerIn(p,nPos, sb);
		if(p.getLastScore()==12 && doubleSix) {
			sb.append("Doppio 6, il giocatore rilancia."+'\n');
			sb.append(playARound(p.getCardinal()-1));
		}
		return sb.toString();
	}
	



	@Override
	public int getPlayerPosition(int player) {
		return players[player].getCurrPos();
	}

	@Override
	public int getDaceRis() {
		return daceRis;
	}

	@Override
	public boolean isPlayerBlocked(int player) {
		return players[player].getRoundsToWait()!=0;
	}

	@Override
	public void restart() {
		for(Player p : players) p.reset();
	}
	
	
	

}
