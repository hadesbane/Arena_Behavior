//This is a behavior that repeats what it is given for either a set time(>0 given)
//or an indefinite time(-1 given). 
public class Repeat extends Routine{
	private int times;
	private Routine routine;
	private int baseTimes; //original given int
	
	public Repeat(){
		this(null, -1);
	}
	
	//This constructor is for when it wants to go in perpetuate or until death
	public Repeat(Routine routine){
		this(routine, -1);
	}

	//Tells repeat what to a how long to repeat
	public Repeat(Routine routine, int times){
		super();
		this.routine = routine;
		this.times = times;
		this.baseTimes = times;
	}
	
	public Routine getRoutine(){
		return this.routine;
	}
	
	@Override
	public void start(){
		super.start();
		this.routine.start();
	}
	
	@Override 
	public void reset(){
		this.times = baseTimes;
		this.routine.reset();
		this.state = null;
	}
	
	@Override
	//Fails when the fighter is dead or what is repeating fails
	public void act(Fighter fighter, Arena arena){
		if(!fighter.isAlive() || this.routine.isFailure()){
			fail();
		}
		else if(this.routine.isSuccess()){
			if(this.times == 0){
				succeed();
				return;
			}
			else{
				this.times--;
				this.routine.reset();
			}
		}
		if(this.routine.isRunning()){
			this.routine.act(fighter, arena);
		}
		else{
			this.routine.start();
		}
	}
	
	public void setRoutine(Routine routine){
		this.routine = routine;
	}
}
