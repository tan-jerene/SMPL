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
		65,
		67
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
		/* 47 */ YY_START,
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
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NOT_ACCEPT,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NOT_ACCEPT,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NOT_ACCEPT,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NOT_ACCEPT,
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
		/* 133 */ YY_NOT_ACCEPT,
		/* 134 */ YY_NOT_ACCEPT,
		/* 135 */ YY_NOT_ACCEPT,
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
		/* 188 */ YY_NO_ANCHOR,
		/* 189 */ YY_NOT_ACCEPT,
		/* 190 */ YY_NO_ANCHOR,
		/* 191 */ YY_NO_ANCHOR,
		/* 192 */ YY_NOT_ACCEPT,
		/* 193 */ YY_NOT_ACCEPT,
		/* 194 */ YY_NOT_ACCEPT,
		/* 195 */ YY_NOT_ACCEPT,
		/* 196 */ YY_NOT_ACCEPT,
		/* 197 */ YY_NOT_ACCEPT,
		/* 198 */ YY_NOT_ACCEPT,
		/* 199 */ YY_NOT_ACCEPT,
		/* 200 */ YY_NOT_ACCEPT,
		/* 201 */ YY_NOT_ACCEPT,
		/* 202 */ YY_NOT_ACCEPT,
		/* 203 */ YY_NOT_ACCEPT,
		/* 204 */ YY_NOT_ACCEPT
	};
	private int yy_cmap[] = unpackFromString(1,130,
"4:8,5:2,2,4,5,68,4:18,5,60,74,3,4,65,4,73,9,10,63,61,6,62,1,64,72,70:9,8,7," +
"59,24,59,67,18,40,52,35,23,21,27,69,54,26,69:2,38,69,22,34,32,47,33,42,36,5" +
"0,48,56,69,58,44,11,4,12,66,69,4,39,51,31,20,17,16,69,53,25,69:2,37,69,19,3" +
"0,28,45,29,41,15,49,46,55,69,57,43,13,4,14,4:2,71,0")[0];

	private int yy_rmap[] = unpackFromString(1,205,
"0,1,2,1,3,1:4,4,1:2,5,1:4,6,7,8,1,9,1:3,10,1,2,1:18,11,12,1:5,13,1:5,14,1:5" +
",15,1,16,17,18,19,1,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,3" +
"8,6,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62" +
",63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,11,12,2,83,84," +
"85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,10" +
"7,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,1" +
"26,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144," +
"145,146,147,148")[0];

	private int yy_nxt[][] = unpackFromString(149,75,
"1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,70,5,77,16,80,83,85,87,89,91,93,95,5,97" +
",190,99,101,103,191,105,107,109,111,113,115,117,119,121,5:8,123,125,5:2,127" +
",129,5:2,17,91,18,19,20,21,22,23,24,3,5,25,69,188,131,26,-1:145,27,-1,27,-1" +
":3,4,-1,4:65,-1,4:2,-1,4:3,-1:12,28,-1:11,29,-1:58,30,-1:90,71,-1:111,35,-1" +
":75,36,-1:76,37,-1:11,136,-1:68,25,-1,25,-1:72,46,-1,46,-1:3,47,-1,47,-1:11" +
",47:3,-1,47:5,-1,47:34,-1,47:2,-1:5,47,-1,47:2,-1,47,-1:27,170,-1:86,176,-1" +
":37,1,74:70,1,74:2,66,1,75,-1,75:60,186,187,75:6,1,75:3,-1:17,140,-1:72,137" +
":3,-1,137:5,-1,137:34,-1:10,137:2,-1,137,-1:55,68,-1:47,171,-1:86,178,-1:37" +
",74:70,-1,74:2,-1:2,75,-1,75:60,186,187,75:6,-1,75:3,-1:20,38,-1:73,76,-1:1" +
"7,79,-1:7,82,-1:30,75,-1,75:60,78,187,75:6,-1,75:3,-1:41,141,-1:63,84,-1:45" +
",75,-1,75:60,186,81,75:6,-1,75:3,-1:46,39,-1:2,194,-1:42,86,-1:72,40,-1:81," +
"88,-1:15,90,-1:8,92,-1:43,41,-1:92,94,-1:63,38,-1:72,96,-1:95,142,-1:80,39," +
"-1,196,-1:40,31,-1:2,32,-1:91,40,-1:60,32,-1:4,31,-1:74,41,-1:76,98,-1:9,10" +
"0,-1:60,199,-1:4,143,-1:73,33,-1:70,144,-1:69,104,-1:18,106,-1:74,145,-1:68" +
",108,-1:6,110,-1:63,42,-1:78,33,-1:70,43,-1:68,114,-1:16,116,-1:60,200,-1:7" +
",146,-1:94,118,-1:46,147,-1:65,120,-1:7,189,-1:13,122,-1:75,148,-1:55,124,-" +
"1:4,192,-1:13,126,-1:67,42,-1:60,128,-1:88,43,-1:63,130,-1:73,149,-1:78,197" +
",-1:23,132,-1:40,44,-1:85,198,-1:23,133,-1:67,151,-1:48,34,-1:7,134,-1:85,4" +
"4,-1:59,34,-1:4,135,-1:92,153,-1:69,193,-1:55,45,-1:94,195,-1:57,45,-1:66,1" +
"39:3,-1,139:5,-1,139:34,-1:10,139:2,-1,139,-1:53,155,-1:75,157,-1:41,158,-1" +
":77,159,-1:125,48,-1:20,49,-1:72,50,-1:78,50,-1:84,51,-1:72,52,-1:65,53,-1:" +
"89,51,-1:72,52,-1:64,72,-1:73,49,-1:67,54,-1:116,55,-1:53,54,-1:96,55,-1:33" +
",56,-1:98,166,-1:54,56,-1:95,167,-1:48,168,-1:28,201,-1:56,169,-1:19,202,-1" +
":42,57,-1:95,57,-1:75,58,-1:75,58,-1:51,59,-1:95,73,-1:53,172,-1:95,173,-1:" +
"87,203,-1:75,204,-1:43,177,-1:77,179,-1:81,60,-1:78,60,-1:58,181,-1:78,183," +
"-1:72,61,-1:70,62,-1:81,61,-1:88,62,-1:69,63,-1:72,184,-1:80,63,-1:72,185,-" +
"1:98,64,-1:75,64,-1:17,75,-1,75:60,78,-1,75:6,-1,75:3,-1,75,-1,75:60,-1,81," +
"75:6,-1,75:3,-1,138,-1:68,25,-1,25,-1:43,150,-1:50,102,-1:78,112,-1:95,152," +
"-1:57,160,-1:88,162,-1:61,161,-1:88,163,-1:77,154,-1:75,156,-1:49,164,-1:77" +
",165,-1:101,174,-1:75,175,-1:43,180,-1:77,182,-1:52");

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
						{ yybegin(COMMENT);  }
					case -38:
						break;
					case 38:
						{ return new Symbol(sym.END);}
					case -39:
						break;
					case 39:
						{ return new Symbol(sym.EQV); }
					case -40:
						break;
					case 40:
						{ return new Symbol(sym.NOT); }
					case -41:
						break;
					case 41:
						{ return new Symbol(sym.DEF); }
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
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext()); }
					case -48:
						break;
					case 48:
						{
      return new Symbol(sym.CHAR, yytext().substring(1, yytext()-1));
    }
					case -49:
						break;
					case 49:
						{ return new Symbol(sym.THEN); }
					case -50:
						break;
					case 50:
						{ return new Symbol(sym.ELSE); }
					case -51:
						break;
					case 51:
						{ return new Symbol(sym.PROC); }
					case -52:
						break;
					case 52:
						{ return new Symbol(sym.PAIR); }
					case -53:
						break;
					case 53:
						{return new Symbol(sym.READ); }
					case -54:
						break;
					case 54:
						{ return new Symbol(sym.LIST); }
					case -55:
						break;
					case 55:
						{ return new Symbol(sym.LAZY); }
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
						{ return new Symbol(sym.PRINT); }
					case -60:
						break;
					case 60:
						{ return new Symbol(sym.SUBSTR); }
					case -61:
						break;
					case 61:
						{ return new Symbol(sym.PRINTLN); }
					case -62:
						break;
					case 62:
						{return new Symbol(sym.READINT); }
					case -63:
						break;
					case 63:
						{ return new Symbol(sym.BINFUNC); }
					case -64:
						break;
					case 64:
						{ return new Symbol(sym.BINQUERY); }
					case -65:
						break;
					case 65:
						{
			// constant string
			// System.out.println(yytext());
			return new Symbol(sym.STRING, yytext());
		}
					case -66:
						break;
					case 66:
						{
			yybegin(YYINITIAL);
		}
					case -67:
						break;
					case 67:
						{ }
					case -68:
						break;
					case 69:
						
					case -69:
						break;
					case 70:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -70:
						break;
					case 71:
						{ return new Symbol(sym.CMP, yytext()); }
					case -71:
						break;
					case 72:
						{return new Symbol(sym.READ); }
					case -72:
						break;
					case 73:
						{ return new Symbol(sym.PRINT); }
					case -73:
						break;
					case 74:
						{
			// constant string
			// System.out.println(yytext());
			return new Symbol(sym.STRING, yytext());
		}
					case -74:
						break;
					case 75:
						{ }
					case -75:
						break;
					case 77:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -76:
						break;
					case 78:
						{ }
					case -77:
						break;
					case 80:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -78:
						break;
					case 81:
						{ }
					case -79:
						break;
					case 83:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -80:
						break;
					case 85:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -81:
						break;
					case 87:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -82:
						break;
					case 89:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -83:
						break;
					case 91:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -84:
						break;
					case 93:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -85:
						break;
					case 95:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -86:
						break;
					case 97:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -87:
						break;
					case 99:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -88:
						break;
					case 101:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -89:
						break;
					case 103:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -90:
						break;
					case 105:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -91:
						break;
					case 107:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -92:
						break;
					case 109:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -93:
						break;
					case 111:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -94:
						break;
					case 113:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -95:
						break;
					case 115:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -96:
						break;
					case 117:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -97:
						break;
					case 119:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -98:
						break;
					case 121:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -99:
						break;
					case 123:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -100:
						break;
					case 125:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -101:
						break;
					case 127:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -102:
						break;
					case 129:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -103:
						break;
					case 131:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -104:
						break;
					case 188:
						{
	       		// INTEGER
	       		return new Symbol(sym.INTEGER, new Integer(yytext()));
	       	}
					case -105:
						break;
					case 190:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -106:
						break;
					case 191:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -107:
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
