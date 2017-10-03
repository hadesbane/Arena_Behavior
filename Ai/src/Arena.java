import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
	private final int width;
	private final int length;
	
	private List<Fighter> fighters = new ArrayList<Fighter>();
	
	private List<Fighter> deadPool = new ArrayList<Fighter>();
	
	public Arena(int width, int length) {
		this.width = width;
		this.length = length;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getLength(){
		return this.length;
	}
	
	public void addFighter(Fighter fighter){
		if (this.fighters.size() < this.length*this.width){
			fighters.add(fighter);
			fighter.setArena(this);
		}
		else{
			throw new IndexOutOfBoundsException("Too many fighters in the arena!");
		}
	}
	
	public boolean isTileWalkable(int x, int y){
		for (Fighter fighter : fighters){
			if(fighter.getX()== x && fighter.getY() == y){
				return false;
			}
		}
		return true;
	}
	
	public List<Fighter> getFighters(){
		return fighters;
	}
	
	public void toDeadPool(Fighter fighter){
		this.fighters.remove(fighter);
		this.deadPool.add(fighter);
	}
	
	public void placeFighters(){
		for(Fighter fighter : this.fighters){
			this.placeFighter(fighter);
		}
	}
	
	public void placeFighter(Fighter fighter){
		Random random = new Random();
		int x = random.nextInt(this.width+1);
		int y = random.nextInt(this.length+1);
		boolean taken = this.spotTaken(x, y);
		while(taken){
			x = random.nextInt(this.width+1);
			y = random.nextInt(this.length+1);
		}
		fighter.setX(x);
		fighter.setY(y);
	}
	
	private boolean spotTaken(int x, int y){
		for(Fighter curr: this.fighters){
			
		}
	}
}
