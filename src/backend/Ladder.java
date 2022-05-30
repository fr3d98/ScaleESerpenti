package backend;

class Ladder extends Mover implements Element {
	
	@Override
	public int action(Player p) {
		//the ladder moves the players up
		if(p.getCurrPos()!=bottom)
			throw new IllegalStateException("Player's position doesn't match with ladder's");
		System.out.println(p+" goes up with ladder in position "+ top);
		return top;
	}

	@Override
	public int getActionPoint() {
		return this.getBottom();
	}
}
