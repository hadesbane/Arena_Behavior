import java.util.ArrayDeque;
import java.util.Deque;


//Creates the base for the behavior tree branches such as sequence and Selector
public abstract class BehaviorPath extends Routine{

	protected Deque<Routine> children; //Holds the child routines 
	protected Deque<Routine> graveyard; //space so that the children aren't lost after use
	
	public BehaviorPath(){
		this(new ArrayDeque<Routine>());
	}
	
	public BehaviorPath(Deque<Routine> list){
		this.children = list;
		this.graveyard = new ArrayDeque<Routine>();
	}
	
	public void addRoutine(Routine routine){
		this.children.addLast(routine);
	}
	
	public boolean isEmpty(){
		return this.children == null && this.graveyard == null;
	}
	
	public void start(){
		super.start();
	}
	
	@Override
	public void reset(){
		start();
	}
	
	@Override
	public boolean isBranchable(){
		return true;
	}
	
	//Means I don't have to constantly remake the routines
	protected void recycle(){
		while(!this.graveyard.isEmpty()){
			this.children.push(this.graveyard.pop());
		}
	}
	
}
