class Snake extends Mover implements Element{
	
	@Override
	public int action(Player p) {
		// snake moves the player down
		if(p.getCurrPos()!=top)
			throw new IllegalStateException("Player's position doesn't match with snake's");
		p.moveTo(bottom);
		
		return bottom;
	}

	@Override
	public int getActionPoint() {
		return this.getTop();
	}
	

}
