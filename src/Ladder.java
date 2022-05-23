
class Ladder extends Mover implements Element {
	
	@Override
	public int action(Player p) {
		//the ladder moves the players up
		if(p.getCurrPos()!=bottom)
			throw new IllegalStateException("Player's position doesn't match with ladder's");
		p.setLastPos(p.getCurrPos());
		p.setCurrPos(top);
		
		return top;
	}

	@Override
	public int getActionPoint() {
		// TODO Auto-generated method stub
		return this.getBottom();
	}
}
