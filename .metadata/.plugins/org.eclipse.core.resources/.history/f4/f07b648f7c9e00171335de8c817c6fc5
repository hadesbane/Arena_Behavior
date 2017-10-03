import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;

public class FighterFactory {

	private Random random;
	private NameGen name;
	private Types[] types;
	
	//this is basically just so I can get a new name every so often
	private class NameGen{
		public String file = new String("dictionary.txt");
		public Scanner scanner;
		public List<String> list;
		public Random random;
		
		public NameGen()throws FileNotFoundException{
			this.scanner = new Scanner(new File(this.file));
			this.list = new ArrayList<String>();
			this.random = new Random();
			while(this.scanner.hasNextLine()){
				this.list.add(this.scanner.nextLine());
			}
		}
		
		public String generateName(){
			int rand = this.random.nextInt(this.list.size()-1);
			return this.list.get(rand);
		}
	}
	
	public FighterFactory() throws FileNotFoundException{
		this.name = new NameGen();
		this.random = new Random();
		this.types = Types.values();
	}
	
	public Fighter makeFighter(Arena arena){
		String name = this.name.generateName();
		Types type = this.types[this.random.nextInt(this.types.length)];
		int[] stats = new int[8];
		stats[0] = this.random.nextInt(arena.getWidth()+1);
		stats[1] = this.random.nextInt(arena.getLength()+1);
		for(int i = 2; i < 8; i++){
			stats[i] = this.random.nextInt(11);
		}
		return new Fighter(name, type, stats);
	}
	
	/*
	 *NOTICE ME AND PLEASE MAKE AFTER THE FIRST ROUND
	 * 
	 * THIS IS FOR THE MACHINE LEARNING AND EVOLUTION
	 * 
	 * 
	 * 
	 * 
	 */
	public Fighter breedFighter(Fighter male, Fighter female, Arena arena){
		return new Fighter(null, null, null);
	}
	
	
}
