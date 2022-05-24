import java.util.HashSet;
import java.util.Set;

public class Builder implements BuilderIF{
	
	private Set<Integer> occupied;

	@Override
	public GameMap getGameMap() {
		GameMap gm= HashGameMap.INSTANCE;
		//TODO
		return gm;
	}
	

	@Override
	public void buildSpace(int rows, int columns) {
		int n= rows*columns;
		occupied=new HashSet<>(n);
		
	}

	@Override
	public void buildSnakes(int[]... s) {
		//first head, than tail
		for( int[] a : s) {
			if(a.length!=2)throw new IllegalArgumentException("Coordinates are made of two numbers!");
			Element e = new Snake();
			e.locateElement(occupied, a);
			HashGameMap.INSTANCE.addNewElement(e);
		}
	}

	@Override
	public void buildLadders(int[]... l) {
		// first top, than bottom
		for( int[] a : l) {
			if(a.length!=2)throw new IllegalArgumentException("Coordinates are made of thwo numbers!");
			Element e= new Ladder();
			e.locateElement(occupied, a);
			HashGameMap.INSTANCE.addNewElement(e);
		}
		
	}

	@Override
	public void buildBenches(int... pos) {
		for(int i : pos) {
			Element e= Bench.INSTANCE;
			e.locateElement(occupied, i);
			HashGameMap.INSTANCE.addNewElement(e);
		}
	}

	@Override
	public void buildRests(int... pos) {
		for(int i : pos) {
			Element e= Rest.INSTANCE;
			e.locateElement(occupied, i);
			HashGameMap.INSTANCE.addNewElement(e);
		}
	}

	@Override
	public void buildDaceBoxes(int... pos) {
		for(int i : pos) {
			Element e = DaceBoxes.INSTANCE;
			e.locateElement(occupied, i);
			HashGameMap.INSTANCE.addNewElement(e);
		}
	}

	@Override
	public void buildCardBoxes(int... pos) {
		
	}


	@Override
	public void buildDaces(int N) {
		Daces.INSTANCE.setNumberOfDaces(N);
	}


	

	

}
