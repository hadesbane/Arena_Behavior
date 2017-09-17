import java.util.Random;

//In case of having a enemy attacking and need to get away, use this
//Finds the furthest tile to go to and moves near it
public class FindSafePlace extends Routine {
	private boolean backward;//tells to move right or not
	private boolean left;//tells to move left or not
	
	@Override
	public void reset() {
		start();
	}

	@Override
	public void act(Fighter fighter, Arena arena) {
		Routine move = this.getMove(fighter, fighter.getEnemy(), arena);
		move.start();
		while(move.isRunning()){
			move.act(fighter, arena);
		}
		this.state = (move.isFailure()) ? state.Failure : state.Success;
	}

	//This checks whether the fighter needs to move backwards, forwards, left, or right
	private Routine getMove(Fighter fi, Fighter en, Arena arena){
		if(en != null){
			int fiX = fi.getX();
			int fiY = fi.getY();
			int arX = arena.getWidth();
			int arY = arena.getLength();
			Random rand = new Random();
			this.backward = (fiX-en.getY()<=0 && fiY>=arY)? true:false;
			this.left = (fiX-en.getX()<=0 && fiX>=arX)? true:false;
			if(this.backward && this.left){
				return new MoveTo(rand.nextInt(fiX), rand.nextInt(fiY));
			}
			else if(this.backward){
				return new MoveTo(rand.nextInt(fiX),rand.nextInt(arY-(fiY-1))+fiY);
			}
			else if(this.left){
				return new MoveTo(rand.nextInt(arX-(fiX-1))+fiX,rand.nextInt(fiY));
			}
			else{
				return new MoveTo(rand.nextInt((arX-(fiX-1))+fiX),rand.nextInt((arY-(fiY-1))+fiY));
			}
		}
		else{
			throw new NullPointerException("No enemy to move away from");
		}
	}
}
