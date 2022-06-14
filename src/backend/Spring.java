package backend;
import java.util.Set;

public enum Spring implements Element {
	INSTANCE;
	
	private int pos;

	@Override
	public int action(Player p, StringBuilder sb) {
		//moves the player again according to last score
		int nPos=p.getLastScore()+p.getCurrPos();
		sb.append("Il giocatore ha preso una molla e si sposta nuovamente di "+p.getLastScore()+'\n');
		return nPos;
		}

	@Override
	public void locateElement(Set<Integer> set, int... pos) {
		if(pos.length>1)
			throw new IllegalArgumentException("Spring only works on one position.");
		if(set.contains(pos[0]))
			throw new IllegalArgumentException("Can't put in this position, already taken.");
		this.pos=pos[0];
		set.add(pos[0]);
	}

	@Override
	public int getActionPoint() {
		return pos;
	}

}
