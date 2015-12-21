JLEX = jlex
BASE_PATH = /home/teri/Desktop/LanguageProcessors/SMPL/

CUP = cup
CUPFLAGS = -parser
JAVAC = javac
JAVA = java
CLASSPATH = /home/teri/Desktop/LanguageProcessors/assignment2/java_cup.runtime.jar:$(BASE_PATH)

all: build run

build: lex parser compile

lex: $(BASE_PATH)SMPLLexer
	$(JLEX) $(BASE_PATH)SMPLLexer

parser: $(BASE_PATH)SMPLParser.cup 
	$(CUP) $(CUPFLAGS) SMPLParser $(BASE_PATH)SMPLParser.cup

compile: $(BASE_PATH)sym.java $(BASE_PATH)SMPLParser.java 
	$(JAVAC) -classpath $(CLASSPATH) $(BASE_PATH)*.java

run: $(BASE_PATH)SMPLRepl.class
	$(JAVA) -classpath $(CLASSPATH) SMPLrepl

clean:
	
