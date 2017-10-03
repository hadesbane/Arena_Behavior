import java.util.LinkedList;
import java.util.Queue;

//This takes in a number of routines and does them in order given.
//When one of the routines succeeds, this routine succeeds
public class Selector extends BehaviorPath{
	
	@Override
	public void act(Fighter fighter, Arena arena){
		if(this.children.peek()!= null){
			while(!this.children.isEmpty() && !this.isSuccess()){
				Routine curr = this.children.removeFirst();
				while(curr.isRunning()){
					curr.act(fighter, arena);
				}
				if(curr.isFailure() && !this.children.isEmpty()){
					this.graveyard.addFirst(curr);
					curr = this.children.removeFirst();
				}
			}
		}
		this.recycle();
		if(this.isSuccess()){
			succeed();
		}
		else{
			fail();
		}
	}
}
