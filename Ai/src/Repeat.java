//This is a repeating behavior *duh*
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
	}
	
	@Override
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
				this.routine.start();
				//This resets the routine passed, not Repeat itself
			}
		}
		if(this.routine.isRunning()){
			this.routine.act(fighter, arena);
		}
	}
	
	public void setRoutine(Routine routine){
		this.routine = routine;
	}
}
