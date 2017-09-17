import java.util.Random;

public class Fighter{
	private final String name;
	private int x;
	private int y;
	private int maxRange;
	private int sight;
	private int health;
	private int str;
	private int intel;
	private int dex;
	private int charisma;
	
	private Random random = new Random();
	private Routine routine;
	private Types type;
	private Fighter enemy;
	private Weapon equipped;
	
	Arena arena;
	

	public Fighter(String name,Types type, int[] stats){
		this.name = name;
		this.x = stats[0];
		this.y = stats[1];
		this.type = type;
		this.str = stats[2];
		this.intel = stats[3];
		this.health = stats[4];
		this.dex = stats[5];
		this.charisma = stats[6];
		this.sight = stats[7];
		
		//HEY maybe make a map for all the stats instead of the... clusterfuck it is now
		
		this.typeStats();
		
	}
	
	public void update(){
		if(routine.getState() == null){
			//checks to see if the routine has started and starts it if not
			routine.start();
		}
		routine.act(this, this.arena);
	}
	
	public void setArena(Arena arena){
		this.arena = arena;
	}
	
	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}
	
	public void setX (int x){
		//HEY THESE MAY BE A CAUSE OF VISUAL ERRORS
		if(x < this.arena.getWidth() && x >= 0){
			this.x = x;
		}
		else{
			throw new IndexOutOfBoundsException("Out of play area");
		}
	}
	
	public void setY (int y){
		if(y < this.arena.getLength() && y >= 0){
			this.y = y;
		}
		else{
			throw new IndexOutOfBoundsException("Out of play area");
		}
	}
	
	public boolean isAlive(){
		return this.getHP() > 0;
	}
	
	public void setRoutine(Routine routine){
		this.routine = routine;
	}
	
	public String toString(){
		return ("Fighter: {name=" + name + ", x=" + this.x + ", y=" + this.y + ", health="
				+ this.health + ", range=" + this.maxRange + ", max damage=" + (int)(this.equipped.dmg()*this.str) + "} \n");
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getSight(){
		return this.sight;
	}
	
	public void setNearest(Fighter near){ //Rework
		this.enemy = near;
	}
	
	public int getRange(){
		return this.maxRange;
	}
	
	public int getHP(){
		return this.health;
	}
	
	public int getStr(){
		return this.str;
	}
	
	public int getDmg(){
		return this.equipped.isStr() ? (int)(this.str*this.equipped.dmg()) : (int)(this.dex*this.equipped.dmg());
	}
	
	public int getDex(){
		return this.dex;
	}
	
	public int getIntel(){
		return this.intel;
	}
	
	public int getCharisma(){
		return this.charisma;
	}
	
	//Changes the stats of the individual fighter based on their type
	private void typeStats(){
		switch (type){
			case ARCHER: this.str -= 2;
					 this.dex += 3;
					 this.maxRange = 7;
					 if(this.sight < this.maxRange){
						 this.sight = this.maxRange;
					 }
					 break;
					 
			case FIGHTER: this.str += 2;
					  this.health +=1;
					  this.dex -= 1;
					  this.intel -= 1;
					  this.maxRange = 3;
					  if(this.sight < this.maxRange){
							 this.sight = this.maxRange;
					  }
					  break;
					  
		/*
		case INVENTOR: this.str -= 1;
						this.dex -= 1;
						this.charisma -= 1;
						this.intel += 4
						this.maxRange = 5;
						 if(this.sight < this.maxRange){
							 this.sight = this.maxRange;
					  	}
					  	break;
					  	
					  	SAVE THESE FOR LATER WITH TRAITS AND CHESTS AND STUFF
					  	
	  	case ALCHEMIST:
	  	
	  	case DOCTOR:
						*/
		}
	}
	
	public void takeDamage(int damage){
		this.health -= damage;
	}
	
	public void setEnemy(Fighter enemy){
		this.enemy = enemy;
	}
	
	public Fighter getEnemy(){
		return this.enemy;
	}
	
	public Weapon getWeapon(){
		return this.equipped;
	}
	
	public void setWeapon(Weapon weapon){
		this.equipped = weapon;
	}
}
