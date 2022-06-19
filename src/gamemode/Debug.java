package gamemode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import cmd.Cmd;
import cmd.CmdAlias;
import deck.Deck;

public class Debug {
	
	static ArrayList<String> cmds = new ArrayList<String>();
	
	public static void play(String cmdFileName, String deckFileName) throws FileNotFoundException, IndexOutOfBoundsException {
		Debug.getCmds(cmdFileName);
		Deck.build(deckFileName);
		
		for(int i=0; i<cmds.size(); i++) {
			
			System.out.print("-cmd " + cmds.get(i).charAt(0));
			CmdAlias cmd = CmdAlias.getCmd(cmds.get(i).charAt(0));
			
			if(cmd.equals(CmdAlias.BET)||cmd.equals(CmdAlias.HOLD)){
				ArrayList<Integer> param = new ArrayList<Integer>();
				try {
					int j = 0;
					while(j+i+1<cmds.size()) {
						param.add(Integer.parseInt(cmds.get(j+i+1)));
						System.out.print(" " + param.get(j).toString());
						j++;
					}
				}catch(NumberFormatException e) {
				}
					System.out.println();
					if(param.size()>0) {
						Cmd.execute(cmd, param);
						i += param.size();
					}else {
						Cmd.execute(cmd);
					}
			}else {
				System.out.println();
				Cmd.execute(cmd);
			}
			System.out.println();
		}
		
	}

	public static void getCmds(String fileName) throws FileNotFoundException {
		try (Scanner sc = new Scanner(new File(fileName))){
			while(sc.hasNext()) {
				String line = sc.next();
				String[] tokens = line.split(" ");
				for(int i = 0; i< tokens.length; i++) {
					cmds.add(tokens[i]);
				}
			}
		}
	}
	
}
