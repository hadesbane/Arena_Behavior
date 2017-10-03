import java.util.Random;

public class Wander extends Routine {
	
	private static Random random;
	private MoveTo move;
	
	public Wander(){
		this.random = new Random();
		
	}
	
	@Override
	public void start(){
		super.start();
	}
	
	public void reset(){
		this.state = null;
		if(this.move != null){
			this.move.reset();
		}
	}
	
	@Override
	public void act(Fighter fighter, Arena arena){
		if(this.move == null){
			this.move = new MoveTo(random.nextInt(arena.getWidth()), random.nextInt(arena.getLength()));
			this.move.start();
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
