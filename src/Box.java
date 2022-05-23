import java.util.Random;
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
	public void locateElement(Set<Integer> set, int N) {
		Random r=new Random();
		int pos;
		do {
			pos=r.nextInt(N)+1;
		}while(set.contains(pos));
		this.pos=pos;
		set.add(pos);
	}

	int getPos() {
		return pos;
	}

	void setPos(int pos) {
		this.pos = pos;
	}
	
	

}
