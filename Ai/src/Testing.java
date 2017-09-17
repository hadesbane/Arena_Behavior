import java.io.FileNotFoundException;

public class Testing {
	
	public static void main(String[] args) throws FileNotFoundException{
		Arena arena = new Arena(10,10);
		FighterFactory fact = new FighterFactory();
		
		
		Fighter fighter = fact.makeFighter(arena);
		arena.addFighter(fighter);
	
		Routine routine = new Repeat(new Wander(arena), 5);
		fighter.setRoutine(routine);
		System.out.println(fighter);
		
		for(int i = 0; i < 15; i++){
			fighter.update();
			System.out.println(fighter);
		}
	}
}
