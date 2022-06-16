package backend;

class Player {
	
	private int currPos; //posizione corrente
	private int lastPos; //posizione precedente
	private int cardinal; //posizione cardinale
	private int lastScore;
	private int roundsToWait;
	
	private boolean noStopCard;
	
	
	public Player() {
		currPos=1;
		noStopCard=false;
		lastPos=0;
		roundsToWait=0;
	}
	
	public void reset() {
		currPos=1;
		noStopCard=false;
		lastPos=0;
		roundsToWait=0;
	}
	
	public int throwDaces() {
		//player consumes a round throwing daces
		/*
		 */
		int ris=Daces.INSTANCE.throww(this);
		this.setLastScore(ris);
		return ris;
	}
	
	public boolean hasNoStopCard() {return noStopCard;}
	
	public void noStopCardTaken() {noStopCard=true;}
	
	public void noStopCardConsumed() {
		if(!hasNoStopCard())
			throw new IllegalStateException("Player doesn't hava a stop card!");
		noStopCard=false;
	}
	
	public void moveTo(int pos) {
		lastPos=currPos;
		currPos=pos;
	}
	
	public void moveForward(int q) {
		lastPos=currPos;
		currPos+=q;
	}
	
	public void moveBackward(int q) {
		lastPos=currPos;
		currPos-=q;
	}
	
	public int getCardinal() {
		return cardinal;
	}
	public void setCardinal(int cardinal) {
		this.cardinal = cardinal;
	}
	public int getCurrPos() {
		return currPos;
	}
	public void setCurrPos(int currPos) {
		this.currPos = currPos;
	}
	public int getLastPos() {
		return lastPos;
	}
	public void setLastPos(int lastPos) {
		this.lastPos = lastPos;
	}

	public int getLastScore() {
		return lastScore;
	}

	public void setLastScore(int lastScore) {
		this.lastScore = lastScore;
	}

	public int getRoundsToWait() {
		return roundsToWait;
	}

	public void setRoundsToWait(int roundsToWait) {
		this.roundsToWait = roundsToWait;
	}
	
	@Override
	public String toString() {
		return "Player "+this.cardinal;
	}
	
	
	public static void main(String...args) {
		
		BuilderIF builder= new Builder();
		
		builder.buildSpace(10, 10);
		builder.buildSnakesRandom(8);
		builder.buildLaddersRandom(8);
		builder.buildSpringBoxesRandom(8);
		builder.buildRestsRandom(8);
		builder.buildCardBoxesRandom(8);
		builder.buildDaces(2);
		

		GameMap gm=builder.getGameMap();
		

		for(int i=0; i<100; i++) {
			System.out.println(gm.getElementIn(i));
		}
		/**
		Player[] players = builder.buildPlayers(4);
		System.out.println("All players are in: ");
		for(Player p : players) {
			System.out.println(p.getCardinal()+" in: ["+p.getCurrPos()+"]");
		}
		
		boolean won=false;
		int i=1;
		while (!won) {
			System.out.println("________Round "+i+"________");
			for (Player p : players) {
				gm.playARound();
				if (p.getCurrPos() == 100) {
					won=true;
					break;
				}
				if(p.getCurrPos()>100)throw new IllegalStateException("Can't go out the map!");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			i++;
			
			
		}
	*/
		
		
	}
	
	
}
