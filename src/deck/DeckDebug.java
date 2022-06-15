package deck;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import card.Card;

public class DeckDebug extends Deck{
	
	private static DeckDebug instance = null;
	
	private DeckDebug() {
	}
	
	public static DeckDebug getInstance() {
		if(instance == null)
			instance = new DeckDebug();
		return instance;
	}
	
	public void build(String fileName) throws FileNotFoundException {
		try (Scanner sc = new Scanner(new File(fileName))){
			while(sc.hasNext()) {
				String line = sc.next();
				String[] tokens = line.split(" ");
				for(int i = 0; i < tokens.length; i++) {
					instance.addCard(Card.stringToCard(tokens[i]));
				}
			}
		}
	}
	
}
