//The thinking basis behind the fighters
public abstract class Routine {	
	
	public enum State {
		Success,
		Failure,
		Running
	}
	
	protected State state;
	
	public void start(){
		System.out.println(">> Starting routine: " + this.getClass().getSimpleName() + "\n");
		//testing measures//
		this.state = State.Running;
	}
	
	public abstract void reset();
	
	public boolean isBranchable(){
		return false;
	}
	
	public abstract void act(Fighter fighter, Arena arena);
	
	protected void succeed(){
		System.out.println(">> Routine: " + this.getClass().getSimpleName() + " Success\n");
		//testing measures//
		this.state = state.Success;
	}
	
	protected void fail(){
		System.out.println(">> Routine: " + this.getClass().getSimpleName() + " FAILURE\n");
		//testing measures//
		this.state = State.Failure;
	}
	
	public boolean isSuccess(){
		return state.equals(State.Success);
	}
	
	public boolean isFailure(){
		return state.equals(State.Failure);
	}
	
	public boolean isRunning(){
		return state.equals(State.Running);
	}
	
	public State getState(){
		return state;
	}
}
