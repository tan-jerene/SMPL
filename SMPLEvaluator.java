import java.util.*;

public class SMPLEvaluator implements SMPLVisitor<SMPLContext, String> {

    private final ArithEvaluator arithEval;

    public SMPLEvaluator() {
        this.arithEval = new ArithEvaluator();
    }

    public String getResult() {
	   return lastResult;
    }

    /**
     * Evaluate a program, returning the lastResulting frame.
     * @param program The program to be evaluated
     * @param env The top level environment providing bindings to the program
     * @return The String returned by the program when evaluated.
     * @throws SMPLException if any semantic errors are encountered during 
     * evaluation
     */
    @Override
    public String visitSMPLProgram(SMPLProgram program, SMPLContext env) throws SMPLException {
    
        String tmp = stmts.visit(this, env);
        if(tmp != String.DEFAULT)
            lastResult = tmp;    
    	return lastResult;
    }

    /**
     * Evaluate a sequence of statements
     * @param seq The statement sequence to be evaluated.
     * @param env The environment w.r.t which the sequence is to be evaluated
     * @return The result of the last statement in the sequence.
     * @throws SMPLException if an error was encountered in the sequence.
     */
    @Override
    public String visitSMPLSequence(SMPLSequence seq, SMPLContext env) throws SMPLException {
        
        ArrayList<SMPLStatement> stmts = seq.getStatements();
        String result = String.DEFAULT;
        for(SMPLStatement stmt : stmts){
            result = stmt.visit(this, env);
        }
        return result;
    }

    /**
     * @return a freshly created global context suitable for visiting top level
     * expressions.
     */
    public SMPLContext mkInitialContext() {
       return new SMPLContextImpl();
    }

    /*********************************STATEMENTS**************************************/
    /**
     *Implements all visitor functions in the SMPLVisitor interface that are in 
     *classes that extend SMPLStatement.
     */

    /**
     * Evaluate an assignment statement (creating a binding for the variable on
     * the LHS of the assignment to the value obtained from evaluating the RHS
     * of the assignment).
     * @param assignment The assignment statement
     * @param context The context in which the assignment should be evaluated
     * @return The string value yielded by the right hand side of the assignment
     * @throws SMPLException
     */
    @Override
    public String visitSMPLAssignment(SMPLAssignment assignment, SMPLContext context) throws SMPLException {

        String result = assignment.getExpression().visit(this, context);
        context.putS(assignment.getVarName(), result);
    	return result;
    }

    /**
     * Evaluate an case-statement.
     * @param caseStmt The case-statement
     * @param context The context in which the case-statement should be evaluated
     * @return String objext that is generated after evaluating the case-statement
     * @throws hpl.sys.SMPLException if something goes wrong while invoking the 
     * function definition.
     */
    @Override
    public String visitSMPLCaseStmt(SMPLCaseStmt caseStmt, SMPLContext state) throws SMPLException {
        ArrayList<SMPLPredConExp> lst = caseStmt.getLst();
        String result = String.DEFAULT;
        for( SMPLPredConExp predConExp : lst){
            predicate = predConExp.getPredicate();
            boolean val = predicate.visit(arithEval, state.getNumEnv()).booleanValue();
            if(val){
                SMPLSequence consequent = predConExp.getConsequent();
                return consequent.visit(this, state);
            }
        }
    }

    /**
     * Evaluate an if-statement.
     * @param ifStmt The if-statement
     * @param context The context in which the if-statement should be evaluated
     * @return String objext that is generated after evaluating the if-statement
     * @throws hpl.sys.SMPLException if something goes wrong while invoking the 
     * function definition.
     */
    @Override
    public String visitSMPLIfStmt(SMPLIfStmt ifStmt, SMPLContext state) throws SMPLException {
        ASTLogBinaryExp predicate = ifStmt.getPredicate();
        boolean val = predicate.visit(arithEval, state.getNumEnv()).booleanValue();
        if(val){
            SMPLSequence consequent = ifStmt.getIfClause();
            return consequent.visit(this, state);
        }
        else{
            SMPLSequence alternate = ifStmt.getElseClause();
            return alternate.visit(this, state);
        }
    }

    /**
     * Evaluate an print statement.
     * @param printStmt The print statement
     * @param context The context in which the print statement should be evaluated
     * @return String objext that is generated after evaluating the print statement
     * @throws hpl.sys.SMPLException if something goes wrong while invoking the 
     * function definition.
     */ 
    @Override
    public String visitSMPLPrintStmt(SMPLPrintStmt printStmt, SMPLContext env)
    throws SMPLException {
        String separator = printStmt.getSeparator();
        ASTExp expression = printStmt.getExpression();
        p = expression+separator;
        return p;
    }

    /**
     * Evaluate a function definition.
     * @param funDef The function definition expression
     * @param context The environment w.r.t. which the call is evaluated
     * @return SMPLFunction object that arises from applying the function.
     * @throws hpl.sys.SMPLException if something goes wrong while invoking the 
     * function definition.
     */
    @Override
    public String visitSMPLFunDef(SMPLFunDef funDef, SMPLContext context) throws SMPLException {
        /*String funName = funDef.getFunName();
        ArrayList<String> nParams = funDef.getNumericalArgExps();
	SMPLSequence pSeq = funDef.getStatementSequence();
	SMPLFunction func = new SMPLFunction(funName, nParams, pParams, pSeq, context);*/


	   return "Still working on it - visitSMPLFunDef";
    }

    /**
     * Evaluate a function call.
     * @param funCall The function call expression
     * @param context The environment w.r.t. which the call is evaluated
     * @return The (painter) object that arises from applying the function.
     * @throws hpl.sys.SMPLException if something goes wrong while invoking the 
     * function call.
     */
    @Override
    public String visitSMPLFunCall(SMPLFunCall funCall, SMPLContext context) throws SMPLException {
        /*String funName = funCall.getFunName();
        ArrayList<ASTExp<AIRExp>> nArgExps = funCall.getNumericalArgExps();

		ArrayList<Double> nArgs = new ArrayList<Double>();
			
		// ** Incomplete implementation **
			// evaluate the argument parameters ...
		for (int i = 0; i<nArgExps.size(); i++) {
			nArgs.add(nArgExps.get(i).visit(arithEval, context.getNumEnv()));	
		}
		SMPLFunction func = context.getF(funName);
		ArrayList<String> nStrArgs = func.getNumericalParams();
			// extend the closing environment with bindings for painter parameters
			// also extend with bindings for numerical parameters
		context.extendN(nStrArgs, nArgs);
        // and we extend with empty function frame to keep local functions local
        	context.extendF(new ArrayList<String>(), new ArrayList<SMPLFunction>());
        // now return a painter that will execute the body when rendered.*/
        return "Still working on it - visitSMPLFunCall"; // (** fix this **)
    }

    /* ----------------- End of Section for Problem 5 ----------------- */

    @Override
    public String visitSMPLIfStmt(SMPLIfStmt ifStmt, SMPLContext state) throws SMPLException {
        ASTCmpBinaryExp predicate = ifStmt.getPredicate();
        Boolean val = predicate.visit(arithEval, state.getNumEnv()).booleanValue();
        if(val){
            SMPLSequence consequent = ifStmt.getConsequent();
            return consequent.visit(this, state);
        }
        else{
            SMPLSequence alternate = ifStmt.getAlternate();
            return alternate.visit(this, state);
        }
    }

    /**
     * Evaluate a painter variable reference.
     * @param var The variable referencing a painter
     * @param context The context containing the environment in which to look
     * up the variable.
     * @return The painter object bound to the given variable.
     * @throws SMPLException if there is no painter bound to the given variable.
     */
    @Override
    public String visitVar(ASTVar<SMPLExp> var, SMPLContext context)
	throws SMPLException {
	   return context.getS(var.getId());
    }

    @Override
    public Painter visitUnaryExp(ASTUnaryExp<SMPLExp> exp, SMPLContext state)
	throws SMPLException  {
	// should never get here unless language changes
	throw new SMPLException("Unknown unary operation applied to painter: " +
			       exp);
    }
    
    @Override
    public Painter visitBinaryExp(ASTBinaryExp<SMPLExp> exp, SMPLContext state)
	throws SMPLException {
	// should never get here unless language changes
	throw new SMPLException("Unknown binary operation applied to painters: "+
			       exp);
    }
}
