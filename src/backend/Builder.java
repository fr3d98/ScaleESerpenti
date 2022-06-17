package backend;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Builder implements BuilderIF{
	
	private Set<Integer> occupied;
	/**
	 * This set in given as a parameter to Elements and is filled by them.
	 * This is necessary since some elements occupy only one position but others may occupy two.
	 */
	
	private int bound;
	

	@Override
	public GameMap getGameMap() {
		return HashGameMap.INSTANCE;
	}
	

	@Override
	public int buildSpace(int rows, int columns) {
		int n= rows*columns;
		HashGameMap.INSTANCE.setN(n);
		HashGameMap.INSTANCE.setnCols(columns);
		HashGameMap.INSTANCE.setnRows(rows);
		occupied=new HashSet<>(n);
		bound=n;
		return n;
		
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
		for(int i : pos) {
			Element e= CardBoxes.INSTANCE;
			e.locateElement(occupied, i);
			HashGameMap.INSTANCE.addNewElement(e);
		}
		
	}
	
	@Override
	public void buildSpringBoxes(int ...pos) {
		for(int i : pos) {
		 Element e = Spring.INSTANCE;
		 e.locateElement(occupied, i);
		 HashGameMap.INSTANCE.addNewElement(e);
		}
	}


	@Override
	public Daces buildDaces(int N) {
		if(N!=1 && N!=2)
			throw new IllegalArgumentException("You can play only with one or thwo daces!");
		if(bound==0)throw new IllegalStateException("Must create space first!");
		Daces.INSTANCE.setNumberOfDaces(N);
		Daces.INSTANCE.setMapDimension(bound);
		return Daces.INSTANCE;
		
	}


	@Override
	public Deck buildDeck() {
		Deck.INSTANCE.generateDeck();
		return Deck.INSTANCE;
	}


	@Override
	public Player[] buildPlayers(int N) {
		if(N<2)
			throw new IllegalArgumentException("There must be 2 or more players.");
		Player[] players= new Player[N];
		for(int i=0; i<N; i++) {
			players[i]=new Player();
			players[i].setCardinal(i+1);
		}
		HashGameMap.INSTANCE.setPlayers(players);
		return players;
	}


	@Override
	public void buildSnakesRandom(int N) {
		if(occupied==null || bound==0)
			throw new IllegalStateException("Must create space first.");
		Random r=new Random();
		for(int i=0; i<N; i++) {
			int top, bottom;
			do {
				//snake's head can't be in last position
				top=r.nextInt(bound); bottom=r.nextInt(bound)+1;
			}while(top<=bottom || occupied.contains(top) || occupied.contains(bottom));
			int[] pos= {bottom,top};
			buildSnakes(pos);
		}
	}


	@Override
	public void buildLaddersRandom(int N) {
		if(occupied==null || bound==0)
			throw new IllegalStateException("Must create space first.");
		Random r=new Random();
		for(int i=0; i<N; i++) {
			int top, bottom;
			do {
				//ladder bottom can't be in first position
				top=r.nextInt(bound)+1; bottom=r.nextInt(bound-1)+2;
			}while(top<=bottom || occupied.contains(top) || occupied.contains(bottom));
			int[] pos= {bottom,top};
			buildLadders(pos);
		}
		
	}


	@Override
	public void buildBenchesRandom(int N) {
		if(occupied==null || bound==0)
			throw new IllegalStateException("Must create space first.");
		Random r=new Random();
		for(int i=0; i<N; i++) {
			int pos;
			do {
				pos=r.nextInt(bound-1)+1;
			}while(occupied.contains(pos));
			buildBenches(pos);
		}
	}


	@Override
	public void buildRestsRandom(int N) {
		if(occupied==null || bound==0)
			throw new IllegalStateException("Must create space first.");
		Random r=new Random();
		for(int i=0; i<N; i++) {
			int pos;
			do {
				pos=r.nextInt(bound-1)+1;
			}while(occupied.contains(pos));
			buildRests(pos);
		}
	}


	@Override
	public void buildDaceBoxesRandom(int N) {
		if(occupied==null || bound==0)
			throw new IllegalStateException("Must create space first.");
		Random r=new Random();
		for(int i=0; i<N; i++) {
			int pos;
			do {
				pos=r.nextInt(bound-1)+1;
			}while(occupied.contains(pos));
			buildDaceBoxes(pos);
		}
	}


	@Override
	public void buildCardBoxesRandom(int N) {
		if(occupied==null || bound==0)
			throw new IllegalStateException("Must create space first.");
		Random r=new Random();
		for(int i=0; i<N; i++) {
			int pos;
			do {
				pos=r.nextInt(bound-1)+1;
			}while(occupied.contains(pos));
			buildCardBoxes(pos);
		}
	}
	
	@Override
	public void buildSpringBoxesRandom(int N) {
		if(occupied==null || bound==0)
			throw new IllegalStateException("Must create space first.");
		Random r=new Random();
		for(int i=0; i<N; i++) {
			int pos;
			do {
				pos=r.nextInt(bound-1)+1;
			}while(occupied.contains(pos));
			buildSpringBoxes(pos);
		}
	}
	
	Set<Integer> getSet() {
		return occupied;
	}
	
	public static void main(String[] args) {
		Builder b=new Builder();
		
		b.buildSpace(10, 10);
	}


	@Override
	public void buildDoubleSix() {
		HashGameMap.INSTANCE.enableDoubleSix();
	}
}
