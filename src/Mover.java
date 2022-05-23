import java.util.Random;
import java.util.Set;

abstract class Mover implements Element {
	
	int top, bottom;

	@Override
	public abstract int action(Player p);
	
	
	@Override
	public void locateElement(Set<Integer> set, int N) {
		Random r= new Random();
		int top, bottom;
		do {
			top=r.nextInt(N)+1;
			bottom=r.nextInt(N)+1;
		}while(top<=bottom || set.contains(top) || set.contains(bottom));
		this.setTop(top); this.setBottom(bottom);
		set.add(top); set.add(bottom);
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getBottom() {
		return bottom;
	}

	public void setBottom(int bottom) {
		this.bottom = bottom;
	}
	
	

}
