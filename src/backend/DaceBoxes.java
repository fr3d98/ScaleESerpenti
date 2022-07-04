package backend;
import java.util.Set;
public enum DaceBoxes implements Element {
	INSTANCE;
	
	private int pos;

	@Override
	public int action(Player p, StringBuilder sb) {
		int q=p.throwDaces();
		int pos=q+p.getCurrPos();
		sb.append("Il giocatore lancia i dadi nuovamente ed ottiene "+q+'\n');
		return pos;
	}

	@Override
	public void locateElement(Set<Integer> set, int... pos) {
		if(pos.length>1)
			throw new IllegalArgumentException("DaceBoxes only works on one position.");
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
