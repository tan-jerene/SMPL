/* Specification for ECOLI tokens */
// user customisations
import java_cup.runtime.*;


public class SMPLLexer implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

    public int getChar()
    {
	return yychar + 1;
    }
    public int getLine()
    {
	return yyline + 1;
    }
    public String getText()
    {
	return yytext();
    }
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public SMPLLexer (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public SMPLLexer (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private SMPLLexer () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int STRING = 1;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0,
		66
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NOT_ACCEPT,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NOT_ACCEPT,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NO_ANCHOR,
		/* 139 */ YY_NO_ANCHOR,
		/* 140 */ YY_NO_ANCHOR,
		/* 141 */ YY_NO_ANCHOR,
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NO_ANCHOR,
		/* 148 */ YY_NO_ANCHOR,
		/* 149 */ YY_NO_ANCHOR,
		/* 150 */ YY_NO_ANCHOR,
		/* 151 */ YY_NO_ANCHOR,
		/* 152 */ YY_NO_ANCHOR,
		/* 153 */ YY_NO_ANCHOR,
		/* 154 */ YY_NO_ANCHOR,
		/* 155 */ YY_NO_ANCHOR,
		/* 156 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"4:8,5:2,2,4,5,2,4:18,5,57,68,3,4,62,4:2,9,10,60,58,6,59,1,61,67,66:9,8,7,56" +
",21,56,64,4,39,51,33,20,18,25,65,53,23,65:2,37,65,19,32,30,46,31,41,35,49,4" +
"7,55,65:2,43,11,4,12,63,65,4,38,50,29,17,15,24,65,52,22,65:2,36,65,16,28,26" +
",44,27,40,34,48,45,54,65:2,42,13,4,14,4:2,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,157,
"0,1,2,1,3,1:11,4,5:2,1:7,6,1,2,7:17,8,7:13,9,10,7:4,11,1,8,5,12,1,13,2,14,1" +
"5,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,4" +
"0,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,6" +
"5,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,7,82,83,84,85,86,87,88,89" +
",90,91,92,93,94,95")[0];

	private int yy_nxt[][] = unpackFromString(96,69,
"1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,134,138,139,140,141,17,70,74,142:2,1" +
"43,142,75,144,145,142,76,146,147,148,149,150,151,152,153,154,142:12,155,156" +
",18,69,19,20,21,22,23,24,25,142,26,135,27,-1:135,28:2,-1:2,4,-1,4:66,-1:15," +
"142,77,142:4,-1,142:22,78,142:11,-1:9,142,79:2,-1:22,71,-1:48,68,-1:64,26:2" +
",-1:16,142:6,-1,142:34,-1:9,142,79:2,-1:67,46:2,-1:16,142:6,-1,142:14,132,1" +
"42:19,-1:9,142,79:2,-1:16,142:6,-1,142:15,133,142:18,-1:9,142,79:2,-1,1,72:" +
"67,67,-1:15,142,29,142:4,-1,142:2,30,142:31,-1:9,142,79:2,-1:2,72:67,-1:16," +
"142:4,29,142,-1,142:3,31,142:30,-1:9,142,79:2,-1:16,142:6,-1,142:5,32,142:2" +
"8,-1:9,142,79:2,-1:16,142:6,-1,142:9,32,142:24,-1:9,142,79:2,-1:16,142:2,33" +
",142:3,-1,142:34,-1:9,142,79:2,-1:16,142:6,-1,142:23,34,142:2,106,142:7,-1:" +
"9,142,79:2,-1:16,79:6,-1,79:34,-1:9,79:3,-1:16,142:6,-1,142:12,35,142:21,-1" +
":9,142,79:2,-1:16,142:6,-1,142:2,36,142:31,-1:9,142,79:2,-1:16,142:5,37,-1," +
"142:34,-1:9,142,79:2,-1:16,142:6,-1,142:25,38,142,107,142:6,-1:9,142,79:2,-" +
"1:16,142:6,-1,142:13,35,142:20,-1:9,142,79:2,-1:16,142:6,-1,142:3,39,142:30" +
",-1:9,142,79:2,-1:16,142:6,-1,108,142:5,109,142:27,-1:9,142,79:2,-1:16,142:" +
"6,-1,110,142:33,-1:9,142,79:2,-1:16,142:6,-1,142:5,40,142:28,-1:9,142,79:2," +
"-1:16,142:6,-1,142:5,41,142:28,-1:9,142,79:2,-1:16,142:6,-1,142,111,142:8,1" +
"12,142:23,-1:9,142,79:2,-1:16,142:6,-1,142,113,142:32,-1:9,142,79:2,-1:16,1" +
"42:6,-1,142:9,42,142:24,-1:9,142,79:2,-1:16,142:6,-1,142:9,43,142:24,-1:9,1" +
"42,79:2,-1:16,114,142:5,-1,142:34,-1:9,142,79:2,-1:16,142:3,115,142:2,-1,14" +
"2:34,-1:9,142,79:2,-1:16,142:6,-1,142:12,44,142:21,-1:9,142,79:2,-1:16,142:" +
"6,-1,142:18,116,142:15,-1:9,142,79:2,-1:16,142:6,-1,142:13,44,142:20,-1:9,1" +
"42,79:2,-1:16,142:6,-1,142:19,117,142:14,-1:9,142,79:2,-1:16,142:2,45,142:3" +
",-1,142:34,-1:9,142,79:2,-1:16,142:5,45,-1,142:34,-1:9,142,79:2,-1:16,142:6" +
",-1,142:20,118,142:13,-1:9,142,79:2,-1:16,142:6,-1,142:28,119,142:5,-1:9,14" +
"2,79:2,-1:16,142:6,-1,142:21,120,142:12,-1:9,142,79:2,-1:16,142:6,-1,142:29" +
",121,142:4,-1:9,142,79:2,-1:16,142:6,-1,142:16,124,142:17,-1:9,142,79:2,-1:" +
"16,142:6,-1,142:17,125,142:16,-1:9,142,79:2,-1:16,142,126,142:4,-1,142:34,-" +
"1:9,142,79:2,-1:16,142:6,-1,142:7,47,142:26,-1:9,142,79:2,-1:16,142:6,-1,14" +
"2:5,48,142:28,-1:9,142,79:2,-1:16,142:4,127,142,-1,142:34,-1:9,142,79:2,-1:" +
"16,142:6,-1,142:11,49,142:22,-1:9,142,79:2,-1:16,142:6,-1,142:9,50,142:24,-" +
"1:9,142,79:2,-1:16,142,51,142:4,-1,142:34,-1:9,142,79:2,-1:16,142:4,52,142," +
"-1,142:34,-1:9,142,79:2,-1:16,142:6,-1,142:12,53,142:21,-1:9,142,79:2,-1:16" +
",142:6,-1,142:13,54,142:20,-1:9,142,79:2,-1:16,55,142:5,-1,142:34,-1:9,142," +
"79:2,-1:16,142:6,-1,142:18,128,142:15,-1:9,142,79:2,-1:16,142:3,56,142:2,-1" +
",142:34,-1:9,142,79:2,-1:16,142:6,-1,142:19,129,142:14,-1:9,142,79:2,-1:16," +
"142:6,-1,142:12,57,142:21,-1:9,142,79:2,-1:16,142:6,-1,142:13,57,142:20,-1:" +
"9,142,79:2,-1:16,142:6,-1,142:14,58,142:19,-1:9,142,79:2,-1:16,142:6,-1,142" +
":15,59,142:18,-1:9,142,79:2,-1:16,142:6,-1,142:12,60,142:21,-1:9,142,79:2,-" +
"1:16,142:6,-1,142:13,61,142:20,-1:9,142,79:2,-1:16,142:6,-1,142:12,130,142:" +
"21,-1:9,142,79:2,-1:16,142:6,-1,142:13,131,142:20,-1:9,142,79:2,-1:16,142:6" +
",-1,142:5,62,142:28,-1:9,142,79:2,-1:16,142:6,-1,142:9,63,142:24,-1:9,142,7" +
"9:2,-1:16,142,64,142:4,-1,142:34,-1:9,142,79:2,-1:16,142:4,65,142,-1,142:34" +
",-1:9,142,79:2,-1:16,142:6,-1,142:6,80,142:27,-1:9,142,79:2,-1:2,73,-1:64,2" +
"6:2,-1:16,142:6,-1,122,142:33,-1:9,142,79:2,-1:16,142:6,-1,142,123,142:32,-" +
"1:9,142,79:2,-1:16,81,142:5,-1,142:34,-1:9,142,79:2,-1:16,142:4,82,142,-1,1" +
"42:24,83,142:9,-1:9,142,79:2,-1:16,142:6,-1,142:10,84,142:23,-1:9,142,79:2," +
"-1:16,142:3,85,142:2,-1,142:34,-1:9,142,79:2,-1:16,142:6,-1,142:5,86,142:10" +
",87,142:17,-1:9,142,79:2,-1:16,142:2,88,142:3,-1,142:16,89,142:17,-1:9,142," +
"79:2,-1:16,142:6,-1,142:9,90,142:7,91,142:16,-1:9,142,79:2,-1:16,142:5,92,-" +
"1,142:17,93,142:16,-1:9,142,79:2,-1:16,142:6,-1,142:30,94,142:3,-1:9,142,79" +
":2,-1:16,142:6,-1,142:31,95,142:2,-1:9,142,79:2,-1:16,96,142:5,-1,97,142:33" +
",-1:9,142,79:2,-1:16,142:3,98,142:2,-1,142,99,142:32,-1:9,142,79:2,-1:16,14" +
"2,100,142:4,-1,142:34,-1:9,142,79:2,-1:16,142:4,101,142,-1,142:34,-1:9,142," +
"79:2,-1:16,142:6,-1,102,142:25,103,142:7,-1:9,142,79:2,-1:16,142:6,-1,142,1" +
"04,142:25,105,142:6,-1:9,142,79:2,-1:16,142:6,-1,142:16,136,142:17,-1:9,142" +
",79:2,-1:16,142:6,-1,142:17,137,142:16,-1:9,142,79:2,-1");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

	return new Symbol(sym.EOF);
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{ //. on a line by itself is EOF
			  return new Symbol(sym.EOF);}
					case -3:
						break;
					case 3:
						{
                        //skip newline, but reset char counter
                        yychar = 0;
                      }
					case -4:
						break;
					case 4:
						{ // ignore line comments
                      }
					case -5:
						break;
					case 5:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -6:
						break;
					case 6:
						{ // ignore whitespace
                      }
					case -7:
						break;
					case 7:
						{ return new Symbol(sym.COMMA);}
					case -8:
						break;
					case 8:
						{ return new Symbol(sym.SEMI);}
					case -9:
						break;
					case 9:
						{ return new Symbol(sym.COLON);}
					case -10:
						break;
					case 10:
						{ return new Symbol(sym.LPAREN);}
					case -11:
						break;
					case 11:
						{ return new Symbol(sym.RPAREN);}
					case -12:
						break;
					case 12:
						{ return new Symbol(sym.LBRACKET);}
					case -13:
						break;
					case 13:
						{ return new Symbol(sym.RBRACKET);}
					case -14:
						break;
					case 14:
						{ return new Symbol(sym.LCBRACKET);}
					case -15:
						break;
					case 15:
						{ return new Symbol(sym.RCBRACKET);}
					case -16:
						break;
					case 16:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -17:
						break;
					case 17:
						{ return new Symbol(sym.ASSIGN); }
					case -18:
						break;
					case 18:
						{ return new Symbol(sym.CMP, yytext()); }
					case -19:
						break;
					case 19:
						{ return new Symbol(sym.PLUS); }
					case -20:
						break;
					case 20:
						{ return new Symbol(sym.MINUS); }
					case -21:
						break;
					case 21:
						{ return new Symbol(sym.TIMES); }
					case -22:
						break;
					case 22:
						{ return new Symbol(sym.DIV); }
					case -23:
						break;
					case 23:
						{ return new Symbol(sym.MOD); }
					case -24:
						break;
					case 24:
						{ return new Symbol(sym.POW); }
					case -25:
						break;
					case 25:
						{ return new Symbol(sym.QUERY); }
					case -26:
						break;
					case 26:
						{
	       		// INTEGER
	       		return new Symbol(sym.INTEGER, new Integer(yytext()));
	       	}
					case -27:
						break;
					case 27:
						{
			yybegin(STRING);
		}
					case -28:
						break;
					case 28:
						{
			// FRACTION
			return new Symbol(sym.FRACTION, new Double(yytext()));
		}
					case -29:
						break;
					case 29:
						{ return new Symbol(sym.IN); }
					case -30:
						break;
					case 30:
						{ return new Symbol(sym.IF); }
					case -31:
						break;
					case 31:
						{ return new Symbol(sym.IF); }
					case -32:
						break;
					case 32:
						{ return new Symbol(sym.OR); }
					case -33:
						break;
					case 33:
						{ return new Symbol(sym.END);}
					case -34:
						break;
					case 34:
						{ return new Symbol(sym.EQV); }
					case -35:
						break;
					case 35:
						{ return new Symbol(sym.NOT); }
					case -36:
						break;
					case 36:
						{ return new Symbol(sym.DEF); }
					case -37:
						break;
					case 37:
						{ return new Symbol(sym.END);}
					case -38:
						break;
					case 38:
						{ return new Symbol(sym.EQV); }
					case -39:
						break;
					case 39:
						{ return new Symbol(sym.DEF); }
					case -40:
						break;
					case 40:
						{ return new Symbol(sym.CDR); }
					case -41:
						break;
					case 41:
						{ return new Symbol(sym.CAR); }
					case -42:
						break;
					case 42:
						{ return new Symbol(sym.CDR); }
					case -43:
						break;
					case 43:
						{ return new Symbol(sym.CAR); }
					case -44:
						break;
					case 44:
						{ return new Symbol(sym.LET); }
					case -45:
						break;
					case 45:
						{ return new Symbol(sym.AND); }
					case -46:
						break;
					case 46:
						{
			return new Symbol(sym.REAL, new Double(yytext()));
		}
					case -47:
						break;
					case 47:
						{ return new Symbol(sym.PROC); }
					case -48:
						break;
					case 48:
						{ return new Symbol(sym.PAIR); }
					case -49:
						break;
					case 49:
						{ return new Symbol(sym.PROC); }
					case -50:
						break;
					case 50:
						{ return new Symbol(sym.PAIR); }
					case -51:
						break;
					case 51:
						{ return new Symbol(sym.THEN); }
					case -52:
						break;
					case 52:
						{ return new Symbol(sym.THEN); }
					case -53:
						break;
					case 53:
						{ return new Symbol(sym.LIST); }
					case -54:
						break;
					case 54:
						{ return new Symbol(sym.LIST); }
					case -55:
						break;
					case 55:
						{ return new Symbol(sym.SIZE); }
					case -56:
						break;
					case 56:
						{ return new Symbol(sym.SIZE); }
					case -57:
						break;
					case 57:
						{ return new Symbol(sym.WAIT); }
					case -58:
						break;
					case 58:
						{ return new Symbol(sym.EQUAL); }
					case -59:
						break;
					case 59:
						{ return new Symbol(sym.EQUAL); }
					case -60:
						break;
					case 60:
						{ return new Symbol(sym.PRINT); }
					case -61:
						break;
					case 61:
						{ return new Symbol(sym.PRINT); }
					case -62:
						break;
					case 62:
						{ return new Symbol(sym.SUBSTR); }
					case -63:
						break;
					case 63:
						{ return new Symbol(sym.SUBSTR); }
					case -64:
						break;
					case 64:
						{ return new Symbol(sym.PRINTLN); }
					case -65:
						break;
					case 65:
						{ return new Symbol(sym.PRINTLN); }
					case -66:
						break;
					case 66:
						{
			// constant string
			// System.out.println(yytext());
			return new Symbol(sym.STRING, yytext());
		}
					case -67:
						break;
					case 67:
						{
			yybegin(YYINITIAL);
		}
					case -68:
						break;
					case 69:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -69:
						break;
					case 70:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -70:
						break;
					case 71:
						{ return new Symbol(sym.CMP, yytext()); }
					case -71:
						break;
					case 72:
						{
			// constant string
			// System.out.println(yytext());
			return new Symbol(sym.STRING, yytext());
		}
					case -72:
						break;
					case 74:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -73:
						break;
					case 75:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -74:
						break;
					case 76:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -75:
						break;
					case 77:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -76:
						break;
					case 78:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -77:
						break;
					case 79:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -78:
						break;
					case 80:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -79:
						break;
					case 81:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -80:
						break;
					case 82:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -81:
						break;
					case 83:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -82:
						break;
					case 84:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -83:
						break;
					case 85:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -84:
						break;
					case 86:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -85:
						break;
					case 87:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -86:
						break;
					case 88:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -87:
						break;
					case 89:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -88:
						break;
					case 90:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -89:
						break;
					case 91:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -90:
						break;
					case 92:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -91:
						break;
					case 93:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -92:
						break;
					case 94:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -93:
						break;
					case 95:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -94:
						break;
					case 96:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -95:
						break;
					case 97:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -96:
						break;
					case 98:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -97:
						break;
					case 99:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -98:
						break;
					case 100:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -99:
						break;
					case 101:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -100:
						break;
					case 102:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -101:
						break;
					case 103:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -102:
						break;
					case 104:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -103:
						break;
					case 105:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -104:
						break;
					case 106:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -105:
						break;
					case 107:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -106:
						break;
					case 108:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -107:
						break;
					case 109:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -108:
						break;
					case 110:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -109:
						break;
					case 111:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -110:
						break;
					case 112:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -111:
						break;
					case 113:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -112:
						break;
					case 114:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -113:
						break;
					case 115:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -114:
						break;
					case 116:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -115:
						break;
					case 117:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -116:
						break;
					case 118:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -117:
						break;
					case 119:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -118:
						break;
					case 120:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -119:
						break;
					case 121:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -120:
						break;
					case 122:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -121:
						break;
					case 123:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -122:
						break;
					case 124:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -123:
						break;
					case 125:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -124:
						break;
					case 126:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -125:
						break;
					case 127:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -126:
						break;
					case 128:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -127:
						break;
					case 129:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -128:
						break;
					case 130:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -129:
						break;
					case 131:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -130:
						break;
					case 132:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -131:
						break;
					case 133:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -132:
						break;
					case 134:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -133:
						break;
					case 135:
						{
	       		// INTEGER
	       		return new Symbol(sym.INTEGER, new Integer(yytext()));
	       	}
					case -134:
						break;
					case 136:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -135:
						break;
					case 137:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -136:
						break;
					case 138:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -137:
						break;
					case 139:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -138:
						break;
					case 140:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -139:
						break;
					case 141:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -140:
						break;
					case 142:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -141:
						break;
					case 143:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -142:
						break;
					case 144:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -143:
						break;
					case 145:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -144:
						break;
					case 146:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -145:
						break;
					case 147:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -146:
						break;
					case 148:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -147:
						break;
					case 149:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -148:
						break;
					case 150:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -149:
						break;
					case 151:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -150:
						break;
					case 152:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -151:
						break;
					case 153:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -152:
						break;
					case 154:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -153:
						break;
					case 155:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -154:
						break;
					case 156:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -155:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
