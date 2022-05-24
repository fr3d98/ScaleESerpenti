import java.util.HashMap;
import java.util.Map;

public enum HashGameMap implements GameMap {
	
	INSTANCE;

	private Map<Integer, Element> elements= new HashMap<>();
	
	
	public void addNewElement(Element e) {
		int pos=e.getActionPoint();
		if(elements.containsKey(pos))throw new IllegalArgumentException("Can't put element in this position!");
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

	
	public static void main(String ... args) {
		System.out.print(HashGameMap.INSTANCE);
		Snake e= new Snake();
		e.setTop(13);
		e.setBottom(15);
		HashGameMap.INSTANCE.addNewElement(e);
		
		Element el= HashGameMap.INSTANCE.getElementIn(13);
		System.out.println(el.getActionPoint());
	}

}
