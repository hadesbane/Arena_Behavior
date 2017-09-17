import java.util.Random;

public class Wander extends Routine {
	
	private static Random random;
	private final Arena arena;
	private MoveTo move;
	
	public Wander(Arena arena){
		this.arena = arena;
		this.random = new Random();
		this.move = new MoveTo(random.nextInt(this.arena.getWidth()), random.nextInt(this.arena.getLength()));
	}
	
	@Override
	public void start(){
		super.start();
		this.move.start();
	}
	
	public void reset(){
		this.move = new MoveTo(random.nextInt(this.arena.getWidth()), random.nextInt(this.arena.getLength()));
	}
	
	@Override
	public void act(Fighter fighter, Arena arena){
		//No clue why passing in a different arena
		if(!this.move.isRunning()){
			return;
		}
		this.move.act(fighter, arena);
		if(this.move.isSuccess()){
			succeed();
		}
		else if(this.move.isFailure()){
			fail();
		}
	}
}
