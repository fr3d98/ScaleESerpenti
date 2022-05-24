import java.util.Set;

public enum CardBoxes implements Element {
	INSTANCE;

	private int pos;
	
	@Override
	public int action(Player p) {
		Card c= Deck.INSTANCE.pick();
		switch (c) {
		case BENCH:
			return Bench.INSTANCE.action(p);
		case REST:
			return Rest.INSTANCE.action(p);
		case SPRING:
			return Spring.INSTANCE.action(p);
		case DACES:
			return DaceBoxes.INSTANCE.action(p);
		case NOSTOP:
			p.noStopCardTaken();
			return p.getCurrPos();
		default:
			throw new IllegalStateException("NO MORE CASES!");
		}
	}

	@Override
	public void locateElement(Set<Integer> set, int... pos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getActionPoint() {
		// TODO Auto-generated method stub
		return 0;
	}

}
