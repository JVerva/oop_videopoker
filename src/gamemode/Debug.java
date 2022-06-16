package gamemode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import cmd.Cmd;
import cmd.CmdAlias;
import deck.DeckDebug;

public class Debug {
	
	static ArrayList<String> cmds = new ArrayList<String>();
	
	public static void play(String cmdFileName, String deckFileName) throws FileNotFoundException, IndexOutOfBoundsException {
		Debug.getCmds(cmdFileName);
		DeckDebug.getInstance().build(deckFileName);
		for(int i=0; i<cmds.size(); i++) {
			System.out.print("-cmd " + cmds.get(i).charAt(0));
			CmdAlias cmd = CmdAlias.getCmd(cmds.get(i).charAt(0));
			if(cmd.equals(CmdAlias.BET)){
				try {
					Integer param = Integer.parseInt(cmds.get(i+1));
					System.out.println(" " + param.toString());
					Cmd.execute(cmd, param);
					i++;
				}catch(NumberFormatException e) {
					System.out.println();
					Cmd.execute(cmd);
				}
			}else if(cmd.equals(CmdAlias.HOLD)) {
				try {
					Integer[] param = new Integer[]{Integer.parseInt(cmds.get(i+1)), Integer.parseInt(cmds.get(i+2))};
					System.out.print(" ");
					for(int j = 0; j<param.length; j++) {
						System.out.print(param[j] + " ");
					}
					System.out.println();
					Cmd.execute(cmd, param);
					i++;
					i++;
				}catch(NumberFormatException e){
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
