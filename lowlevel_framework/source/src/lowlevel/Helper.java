package lowlevel;

public class Helper {

	private static final char[] outs = {'\0','0','1','-'};
	private static final char[] outsOH = {'\0','-','1','-'};

	public static String longToOutputString(long l){
		String s = "";
		//System.out.println(Long.toBinaryString(l));
		while( (l & (long)0x3) > 0){
			char c = outs[ (int)(l & 0x3) ];		// mask last 2 bits of the number and lookup corresponding char
			s = c + s;								// prepend c to s
			l = l >>> 2;							// right shift number
		}
		return s;
	}

	public static String longToOutputStringOH(long l){
		String s = "";
		while( (l & (long)0x3) > 0){
			char c = outsOH[ (int)(l & 0x3) ];		// mask last 2 bits of the number and lookup corresponding char
			s = c + s;								// prepend c to s
			l = l >>> 2;							// right shift number
		}
		return s;
	}


	//	public static int longToInt(long l){
	//		int i = 0;
	//		
	//		while( (l & 0x3) > 0){
	//			i += l & 0x3/2;
	//			l>>=1;
	//		}
	//		return i;
	//	}

}
