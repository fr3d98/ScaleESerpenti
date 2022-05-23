import java.util.HashMap;
import java.util.Map;

public enum HashGameMap implements GameMap {
	
	INSTANCE;

	private Map<Integer, Element> elements;
	
	public void generateMap() {
		elements=new HashMap<>();
	}
	
	public void addNewElement(Element e) {
		int pos=e.getActionPoint();
		elements.put(pos, e);
	}
	

	@Override
	public int putPlayerIn(Player p, int pos) {
		Element e=elements.get(pos);
		if (e==null)return p.getCurrPos(); //empty position
		e.action(p);
		return p.getCurrPos();
	}

	@Override
	public Element getElementIn(int pos) {
		return this.elements.get(pos);
	}

}
