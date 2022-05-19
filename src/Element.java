import java.util.Set;

interface Element {
	
	int action(Player p);
	
	void locateElement(Set<Integer> set, int N);
	
	int getActionPoint();

}
