package backend;
import java.util.Set;
public enum DaceBoxes implements Element {
	INSTANCE;
	
	private int pos;

	@Override
	public int action(Player p) {
		System.out.println(p+" gets to throw daces!");
		int q=p.throwDaces();
		int pos=q+p.getCurrPos();
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
		System.out.println("DaceBox located in "+this.pos);
	}

	@Override
	public int getActionPoint() {
		return pos;
	}

}
