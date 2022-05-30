package backend;
import java.util.Random;

public enum Daces {
	INSTANCE;
	
	private int N; //Defines the number of the daces
	private Random r= new Random();
	
	void setNumberOfDaces(int n) {
		if(n!=1 && n!=2)
			throw new IllegalArgumentException("Only one or thwo daces!");
		this.N=n;
	}
	
	public int throww(Player p) {
		if(N!=1 && N!=2) 
			throw new IllegalStateException("Number of daces must be set first!");
		int ris;
		if(N==1 || p.getCurrPos()>=94)
			ris= r.nextInt(6)+1;
		else ris= r.nextInt(6)+r.nextInt(6)+2;
		System.out.println(p+ " throws daces and gets "+ris);
		return ris;
	}

}
