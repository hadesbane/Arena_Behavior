import java.util.LinkedList;
import java.util.Queue;

//This takes in a number of routines and does them in order given.
//When one of the routines succeeds, this routine succeeds
public class Selector extends BehaviorPath{
	
	@Override
	public void act(Fighter fighter, Arena arena){
		if(this.children.peek()!= null){
				Routine curr = this.children.peek();
				if(curr.state == null){
					curr.start();
				}
				if(curr.isRunning()){
					curr.act(fighter, arena);
				}
				if(curr.isSuccess()){
					this.succeed();
					this.recycle();
					return;
				}
				if(curr.isFailure() && !this.children.isEmpty()){
					this.graveyard.addFirst(curr);
					curr = this.children.removeFirst();
				}
				else{
					this.fail();
					this.recycle();
				}
		}
	}
}
