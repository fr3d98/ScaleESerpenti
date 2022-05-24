import java.util.Set;

abstract class Box implements Element {
	
	int pos;

	@Override
	public abstract int action(Player p);
	
	@Override
	public int getActionPoint() {
		return this.getPos();
	}
	
	@Override
	public void locateElement(Set<Integer> set, int ... pos) {
		if(set.contains(pos[0]))
			throw new IllegalArgumentException("Position already taken! Choose differently.");
		this.pos=pos[0];
		set.add(pos[0]);
	}

	int getPos() {
		return pos;
	}

	void setPos(int pos) {
		this.pos = pos;
	}
	
	

}
