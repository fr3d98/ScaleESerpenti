package backend;
import java.io.Serializable;
import java.util.Set;

abstract class Mover implements Element, Serializable {
	
	private static final long serialVersionUID = -9122825500222884053L;
	int top, bottom;

	@Override
	public abstract int action(Player p, StringBuilder sb);
	
	
	@Override
	public void locateElement(Set<Integer> set, int ... pos) {
		top=pos[1]; bottom=pos[0];
		if(top<=bottom ) {
			throw new IllegalArgumentException("top must be greater than bottom");
		}
		if(set.contains(top) || set.contains(bottom))
			throw new IllegalArgumentException("Position already taken! Choose differently.");
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
