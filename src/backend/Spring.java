package backend;
import java.util.Set;

public enum Spring implements Element {
	INSTANCE;
	
	private int pos;

	@Override
	public int action(Player p) {
		//moves the player again according to last score
		int nPos=p.getLastScore()+p.getCurrPos();
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
