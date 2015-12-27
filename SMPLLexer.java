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
	private final int COMMENT = 2;
	private final int yy_state_dtrans[] = {
		0,
		66,
		68
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
		/* 48 */ YY_START,
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
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NOT_ACCEPT,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NOT_ACCEPT,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NOT_ACCEPT,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NOT_ACCEPT,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NOT_ACCEPT,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NOT_ACCEPT,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NOT_ACCEPT,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NOT_ACCEPT,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NOT_ACCEPT,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NOT_ACCEPT,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NOT_ACCEPT,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NOT_ACCEPT,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NOT_ACCEPT,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NOT_ACCEPT,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NOT_ACCEPT,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NOT_ACCEPT,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NOT_ACCEPT,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NOT_ACCEPT,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NOT_ACCEPT,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NOT_ACCEPT,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NOT_ACCEPT,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NOT_ACCEPT,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NOT_ACCEPT,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NOT_ACCEPT,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NOT_ACCEPT,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NOT_ACCEPT,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NOT_ACCEPT,
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NOT_ACCEPT,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NOT_ACCEPT,
		/* 137 */ YY_NOT_ACCEPT,
		/* 138 */ YY_NOT_ACCEPT,
		/* 139 */ YY_NOT_ACCEPT,
		/* 140 */ YY_NOT_ACCEPT,
		/* 141 */ YY_NOT_ACCEPT,
		/* 142 */ YY_NOT_ACCEPT,
		/* 143 */ YY_NOT_ACCEPT,
		/* 144 */ YY_NOT_ACCEPT,
		/* 145 */ YY_NOT_ACCEPT,
		/* 146 */ YY_NOT_ACCEPT,
		/* 147 */ YY_NOT_ACCEPT,
		/* 148 */ YY_NOT_ACCEPT,
		/* 149 */ YY_NOT_ACCEPT,
		/* 150 */ YY_NOT_ACCEPT,
		/* 151 */ YY_NOT_ACCEPT,
		/* 152 */ YY_NOT_ACCEPT,
		/* 153 */ YY_NOT_ACCEPT,
		/* 154 */ YY_NOT_ACCEPT,
		/* 155 */ YY_NOT_ACCEPT,
		/* 156 */ YY_NOT_ACCEPT,
		/* 157 */ YY_NOT_ACCEPT,
		/* 158 */ YY_NOT_ACCEPT,
		/* 159 */ YY_NOT_ACCEPT,
		/* 160 */ YY_NOT_ACCEPT,
		/* 161 */ YY_NOT_ACCEPT,
		/* 162 */ YY_NOT_ACCEPT,
		/* 163 */ YY_NOT_ACCEPT,
		/* 164 */ YY_NOT_ACCEPT,
		/* 165 */ YY_NOT_ACCEPT,
		/* 166 */ YY_NOT_ACCEPT,
		/* 167 */ YY_NOT_ACCEPT,
		/* 168 */ YY_NOT_ACCEPT,
		/* 169 */ YY_NOT_ACCEPT,
		/* 170 */ YY_NOT_ACCEPT,
		/* 171 */ YY_NOT_ACCEPT,
		/* 172 */ YY_NOT_ACCEPT,
		/* 173 */ YY_NOT_ACCEPT,
		/* 174 */ YY_NOT_ACCEPT,
		/* 175 */ YY_NOT_ACCEPT,
		/* 176 */ YY_NOT_ACCEPT,
		/* 177 */ YY_NOT_ACCEPT,
		/* 178 */ YY_NOT_ACCEPT,
		/* 179 */ YY_NOT_ACCEPT,
		/* 180 */ YY_NOT_ACCEPT,
		/* 181 */ YY_NOT_ACCEPT,
		/* 182 */ YY_NOT_ACCEPT,
		/* 183 */ YY_NOT_ACCEPT,
		/* 184 */ YY_NOT_ACCEPT,
		/* 185 */ YY_NOT_ACCEPT,
		/* 186 */ YY_NOT_ACCEPT,
		/* 187 */ YY_NOT_ACCEPT,
		/* 188 */ YY_NOT_ACCEPT,
		/* 189 */ YY_NOT_ACCEPT,
		/* 190 */ YY_NOT_ACCEPT,
		/* 191 */ YY_NOT_ACCEPT,
		/* 192 */ YY_NOT_ACCEPT,
		/* 193 */ YY_NOT_ACCEPT,
		/* 194 */ YY_NO_ANCHOR,
		/* 195 */ YY_NOT_ACCEPT,
		/* 196 */ YY_NO_ANCHOR,
		/* 197 */ YY_NO_ANCHOR,
		/* 198 */ YY_NOT_ACCEPT,
		/* 199 */ YY_NOT_ACCEPT,
		/* 200 */ YY_NOT_ACCEPT,
		/* 201 */ YY_NOT_ACCEPT,
		/* 202 */ YY_NOT_ACCEPT,
		/* 203 */ YY_NOT_ACCEPT,
		/* 204 */ YY_NOT_ACCEPT,
		/* 205 */ YY_NOT_ACCEPT,
		/* 206 */ YY_NOT_ACCEPT,
		/* 207 */ YY_NOT_ACCEPT,
		/* 208 */ YY_NOT_ACCEPT,
		/* 209 */ YY_NOT_ACCEPT,
		/* 210 */ YY_NOT_ACCEPT
	};
	private int yy_cmap[] = unpackFromString(1,130,
"4:8,5:2,68,4,5,2,4:18,5,60,74,3,4,65,4,73,9,10,63,61,6,62,1,64,72,70:9,8,7," +
"59,24,59,67,18,40,52,35,23,21,27,69,54,26,69:2,38,69,22,34,32,47,33,42,36,5" +
"0,48,56,69,58,44,11,4,12,66,69,4,39,51,31,20,17,16,69,53,25,69:2,37,69,19,3" +
"0,28,45,29,41,15,49,46,55,69,57,43,13,4,14,4:2,71,0")[0];

	private int yy_rmap[] = unpackFromString(1,211,
"0,1,2,1,3,1:4,4,1:2,5,1:4,6,7,8,1,9,1:3,10,1,2,1:19,11,12,1:5,13,1:5,14,1:5" +
",15,1,16,1:3,17,18,19,1,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36," +
"37,38,6,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,6" +
"1,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,11,12,2,83" +
",84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,10" +
"6,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,1" +
"25,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143," +
"144,145,146,147,148,149,150")[0];

	private int yy_nxt[][] = unpackFromString(151,75,
"1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,74,5,81,16,84,87,89,91,93,95,97,99,5,10" +
"1,196,103,105,107,197,109,111,113,115,117,119,121,123,125,5:8,127,129,5:2,1" +
"31,133,5:2,17,95,18,19,20,21,22,23,24,3,5,25,73,194,135,26,-1:145,27,-1,27," +
"-1:3,4,-1,4:65,-1,4:2,-1,4:3,-1:12,28,-1:11,29,-1:58,30,-1:90,75,-1:111,35," +
"-1:75,36,-1:75,37,38,-1:11,140,-1:68,25,-1,25,-1:72,47,-1,47,-1:3,48,-1,48," +
"-1:11,48:3,-1,48:5,-1,48:34,-1,48:2,-1:5,48,-1,48:2,-1,48,-1:27,174,-1:86,1" +
"80,-1:37,1,78:70,1,78:2,67,1,79:62,190,191,79:3,69,79:2,1,79:3,-1:17,144,-1" +
":72,141:3,-1,141:5,-1,141:34,-1:10,141:2,-1,141,-1:55,72,-1:47,175,-1:86,18" +
"2,-1:37,78:70,-1,78:2,-1:2,79:62,192,193,79:3,-1,79:2,-1,79:3,-1:20,39,-1:7" +
"3,80,-1:17,83,-1:7,86,-1:30,79:62,82,193,79:3,-1,79:2,-1,79:3,-1:41,145,-1:" +
"63,88,-1:45,79:62,192,85,79:3,-1,79:2,-1,79:3,-1:46,40,-1:2,200,-1:42,90,-1" +
":72,41,-1:81,92,-1:15,94,-1:8,96,-1:43,42,-1:92,98,-1:63,39,-1:72,100,-1:95" +
",146,-1:80,40,-1,202,-1:40,31,-1:2,32,-1:91,41,-1:60,32,-1:4,31,-1:74,42,-1" +
":76,102,-1:9,104,-1:60,205,-1:4,147,-1:73,33,-1:70,148,-1:69,108,-1:18,110," +
"-1:74,149,-1:68,112,-1:6,114,-1:63,43,-1:78,33,-1:70,44,-1:68,118,-1:16,120" +
",-1:60,206,-1:7,150,-1:94,122,-1:46,151,-1:65,124,-1:7,195,-1:13,126,-1:75," +
"152,-1:55,128,-1:4,198,-1:13,130,-1:67,43,-1:60,132,-1:88,44,-1:63,134,-1:7" +
"3,153,-1:78,203,-1:23,136,-1:40,45,-1:85,204,-1:23,137,-1:67,155,-1:48,34,-" +
"1:7,138,-1:85,45,-1:59,34,-1:4,139,-1:92,157,-1:69,199,-1:55,46,-1:94,201,-" +
"1:57,46,-1:66,143:3,-1,143:5,-1,143:34,-1:10,143:2,-1,143,-1:53,159,-1:75,1" +
"61,-1:41,162,-1:77,163,-1:125,49,-1:20,50,-1:72,51,-1:78,51,-1:84,52,-1:72," +
"53,-1:65,54,-1:89,52,-1:72,53,-1:64,76,-1:73,50,-1:67,55,-1:116,56,-1:53,55" +
",-1:96,56,-1:33,57,-1:98,170,-1:54,57,-1:95,171,-1:48,172,-1:28,207,-1:56,1" +
"73,-1:19,208,-1:42,58,-1:95,58,-1:75,59,-1:75,59,-1:51,60,-1:95,77,-1:53,17" +
"6,-1:95,177,-1:87,209,-1:75,210,-1:43,181,-1:77,183,-1:81,61,-1:78,61,-1:58" +
",185,-1:78,187,-1:72,62,-1:70,63,-1:81,62,-1:88,63,-1:69,64,-1:72,188,-1:80" +
",64,-1:72,189,-1:98,65,-1:75,65,-1:17,79:62,82,70,79:3,-1,79:2,-1,79:3,-1,7" +
"9:62,71,85,79:3,-1,79:2,-1,79:3,-1,79:62,82,-1,79:3,-1,79:2,-1,79:3,-1,79:6" +
"2,-1,85,79:3,-1,79:2,-1,79:3,-1,142,-1:68,25,-1,25,-1:43,154,-1:50,106,-1:7" +
"8,116,-1:95,156,-1:57,164,-1:88,166,-1:61,165,-1:88,167,-1:77,158,-1:75,160" +
",-1:49,168,-1:77,169,-1:101,178,-1:75,179,-1:43,184,-1:77,186,-1:52");

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
						{ return new Symbol(sym.CON); }
					case -17:
						break;
					case 17:
						{ return new Symbol(sym.CMP, yytext()); }
					case -18:
						break;
					case 18:
						{ return new Symbol(sym.PLUS); }
					case -19:
						break;
					case 19:
						{ return new Symbol(sym.MINUS); }
					case -20:
						break;
					case 20:
						{ return new Symbol(sym.TIMES); }
					case -21:
						break;
					case 21:
						{ return new Symbol(sym.DIV); }
					case -22:
						break;
					case 22:
						{ return new Symbol(sym.MOD); }
					case -23:
						break;
					case 23:
						{ return new Symbol(sym.POW); }
					case -24:
						break;
					case 24:
						{ return new Symbol(sym.QUERY); }
					case -25:
						break;
					case 25:
						{
	       		// INTEGER
	       		return new Symbol(sym.INTEGER, new Integer(yytext()));
	       	}
					case -26:
						break;
					case 26:
						{
			yybegin(STRING);
		}
					case -27:
						break;
					case 27:
						{
			// FRACTION
			return new Symbol(sym.FRACTION, new Double(yytext()));
		}
					case -28:
						break;
					case 28:
						{ return new Symbol(sym.VECEND); }
					case -29:
						break;
					case 29:
						{ return new Symbol(sym.ASSIGN); }
					case -30:
						break;
					case 30:
						{ return new Symbol(sym.VECHEAD); }
					case -31:
						break;
					case 31:
						{ return new Symbol(sym.IF); }
					case -32:
						break;
					case 32:
						{ return new Symbol(sym.IN); }
					case -33:
						break;
					case 33:
						{ return new Symbol(sym.OR); }
					case -34:
						break;
					case 34:
						{ return new Symbol(sym.BE);}
					case -35:
						break;
					case 35:
						{ return new Symbol(sym.INC); }
					case -36:
						break;
					case 36:
						{ return new Symbol(syM.DEC); }
					case -37:
						break;
					case 37:
						{ yybegin(COMMENT); comment_count = comment_count + 1; }
					case -38:
						break;
					case 38:
						{ yybegin(COMMENT);  }
					case -39:
						break;
					case 39:
						{ return new Symbol(sym.END);}
					case -40:
						break;
					case 40:
						{ return new Symbol(sym.EQV); }
					case -41:
						break;
					case 41:
						{ return new Symbol(sym.NOT); }
					case -42:
						break;
					case 42:
						{ return new Symbol(sym.DEF); }
					case -43:
						break;
					case 43:
						{ return new Symbol(sym.CDR); }
					case -44:
						break;
					case 44:
						{ return new Symbol(sym.CAR); }
					case -45:
						break;
					case 45:
						{ return new Symbol(sym.LET); }
					case -46:
						break;
					case 46:
						{ return new Symbol(sym.AND); }
					case -47:
						break;
					case 47:
						{
			return new Symbol(sym.REAL, new Double(yytext()));
		}
					case -48:
						break;
					case 48:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -49:
						break;
					case 49:
						{
      return new Symbol(sym.CHAR, yytext().substring(1, yytext()-1));
    }
					case -50:
						break;
					case 50:
						{ return new Symbol(sym.THEN); }
					case -51:
						break;
					case 51:
						{ return new Symbol(sym.ELSE); }
					case -52:
						break;
					case 52:
						{ return new Symbol(sym.PROC); }
					case -53:
						break;
					case 53:
						{ return new Symbol(sym.PAIR); }
					case -54:
						break;
					case 54:
						{return new Symbol(sym.READ); }
					case -55:
						break;
					case 55:
						{ return new Symbol(sym.LIST); }
					case -56:
						break;
					case 56:
						{ return new Symbol(sym.LAZY); }
					case -57:
						break;
					case 57:
						{ return new Symbol(sym.SIZE); }
					case -58:
						break;
					case 58:
						{ return new Symbol(sym.WAIT); }
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
						{ return new Symbol(sym.SUBSTR); }
					case -62:
						break;
					case 62:
						{ return new Symbol(sym.PRINTLN); }
					case -63:
						break;
					case 63:
						{return new Symbol(sym.READINT); }
					case -64:
						break;
					case 64:
						{ return new Symbol(sym.BINFUNC); }
					case -65:
						break;
					case 65:
						{ return new Symbol(sym.BINQUERY); }
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
					case 68:
						{ }
					case -69:
						break;
					case 69:
						{ }
					case -70:
						break;
					case 70:
						{ 
	comment_count = comment_count - 1; 
	//Utility.assert(comment_count >= 0);
	if (comment_count == 0) {
    		yybegin(YYINITIAL);
	}
}
					case -71:
						break;
					case 71:
						{ comment_count = comment_count + 1; }
					case -72:
						break;
					case 73:
						
					case -73:
						break;
					case 74:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -74:
						break;
					case 75:
						{ return new Symbol(sym.CMP, yytext()); }
					case -75:
						break;
					case 76:
						{return new Symbol(sym.READ); }
					case -76:
						break;
					case 77:
						{ return new Symbol(sym.PRINT); }
					case -77:
						break;
					case 78:
						{
			// constant string
			// System.out.println(yytext());
			return new Symbol(sym.STRING, yytext());
		}
					case -78:
						break;
					case 79:
						{ }
					case -79:
						break;
					case 81:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -80:
						break;
					case 82:
						{ }
					case -81:
						break;
					case 84:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -82:
						break;
					case 85:
						{ }
					case -83:
						break;
					case 87:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -84:
						break;
					case 89:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -85:
						break;
					case 91:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -86:
						break;
					case 93:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -87:
						break;
					case 95:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -88:
						break;
					case 97:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -89:
						break;
					case 99:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -90:
						break;
					case 101:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -91:
						break;
					case 103:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -92:
						break;
					case 105:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -93:
						break;
					case 107:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -94:
						break;
					case 109:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -95:
						break;
					case 111:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -96:
						break;
					case 113:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -97:
						break;
					case 115:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -98:
						break;
					case 117:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -99:
						break;
					case 119:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -100:
						break;
					case 121:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -101:
						break;
					case 123:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -102:
						break;
					case 125:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -103:
						break;
					case 127:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -104:
						break;
					case 129:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -105:
						break;
					case 131:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -106:
						break;
					case 133:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -107:
						break;
					case 135:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -108:
						break;
					case 194:
						{
	       		// INTEGER
	       		return new Symbol(sym.INTEGER, new Integer(yytext()));
	       	}
					case -109:
						break;
					case 196:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -110:
						break;
					case 197:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -111:
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
