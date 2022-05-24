class Snake extends Mover implements Element{
	
	@Override
	public int action(Player p) {
		// snake moves the player down
		if(p.getCurrPos()!=top)
			throw new IllegalStateException("Player's position doesn't match with snake's");
		p.setLastPos(p.getCurrPos());
		p.setCurrPos(bottom);
		
		return bottom;
	}

	@Override
	public int getActionPoint() {
		// TODO Auto-generated method stub
		return this.getTop();
	}
	

}
