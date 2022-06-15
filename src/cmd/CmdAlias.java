package cmd;

public enum CmdAlias {
	BET('b'),
	CREDIT('$'),
	DEAL('d'),
	HOLD('h'),
	ADVICE('a'),
	STATISTICS('s');
	
	private final char alias;
	
	CmdAlias(char c) {
		this.alias = c;
	}

    public char getAlias() {
        return alias;
    }
    
    public static CmdAlias getCmd(Character c) throws IllegalArgumentException{
		switch(c) {
		case 'b':
			return CmdAlias.BET;
		case '$':
			return CmdAlias.CREDIT;
		case 'd':
			return CmdAlias.DEAL;
		case 'h':
			return CmdAlias.HOLD;
		case 'a':
			return CmdAlias.ADVICE;
		case 's':
			return CmdAlias.STATISTICS;
		default :
			throw new IllegalArgumentException(c + " does not correspond to a command");
		}
    }
}
