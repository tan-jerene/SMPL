import java.util.*;

public class SMPLEvaluator implements SMPLVisitor<HPLContext, String> {

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
     * @return The frame that results from evaluating the program.  This is 
     *         not usually a useful value, because the objective of a program is usually
     *         to display a Painter (ie a side effect).
     * @throws HPLException if any semantic errors are encountered during 
     * evaluation
     */
    /************************** CURRENTLY BEING WORKED ON - 15/12/2015_12:05pm **************************/
    @Override
    public Painter visitPIRProgram(PIRProgram program, HPLContext env) 
            throws HPLException {
        
	// traverse the program with the given environment
	// to obtain the resulting painter object.
        PIRSequence stmts = program.getSeq();
	Painter tmp = stmts.visit(this, env);
	// don't record null results, so that image on screen will persist
	if (tmp != Painter.DEFAULT)
	    lastResult = tmp;
	return lastResult;
    }

    /* Painter special forms */

    /**
     * Evaluate an assignment statement (creating a binding for the variable on
     * the LHS of the assignment to the value obtained from evaluating the RHS
     * of the assignment).
     * @param assignment The assignment statement
     * @param context The context in which the assignment should be evaluated
     * @return The painter value yielded by the right hand side of the assignment
     * @throws HPLException
     */
    @Override
    public Object visitPIRAssignment(PIRAssignment assignment,
                                      HPLContext context) throws HPLException {
	Painter result = assignment.getExp().visit(this, context);
	context.putP(assignment.getVar(), result);
	return result;
    }

    /**
     * Evaluate a sequence of statements
     * @param seq The statement sequence to be evaluated.
     * @param env The environment w.r.t which the sequence is to be evaluated
     * @return The result of the last statement in the sequence.
     * @throws HPLException if an error was encountered in the sequence.
     */
    @Override
    public Painter visitPIRSequence(PIRSequence seq, HPLContext env)
	throws HPLException
    {
	ArrayList<PIRStatement> stmts = seq.getStatements();
	Painter result = Painter.DEFAULT;

	
        for (PIRStatement stmt : stmts) {
            result = stmt.visit(this, env);
        }

	return result;
    }

    /**
     * Evaluate an IMG-PAINTER statement (one that reads an image from a file)
     * @param exp An expression using the img-painter special form
     * @param env The context for evaluating the read from file
     * @return The painter object denoted by the image encoded in the file
     * @throws HPLException If the file does not exist or could not be read.
     */
    @Override
    public Painter visitPIRImagePainter(PIRImagePainter exp, HPLContext env)
	throws HPLException {
	return new PrimitivePainter(exp.getFile());
    }

    /* ---------------  Edit here for Problem 5  -------------------------  */

    /**
     * @return a freshly created global context suitable for visiting top level
     * expressions.
     */
    public HPLContext mkInitialContext() {
	return new HPLContextImpl();
    }

    // *** Implement a method for function definition (according to your
    //     modifications to HPLVisitor interface)

    /**
     * Evaluate a function definition.
     * @param funDef The function definition expression
     * @param context The environment w.r.t. which the call is evaluated
     * @return HPLFunction object that arises from applying the function.
     * @throws hpl.sys.HPLException if something goes wrong while invoking the 
     * function definition.
     */
    @Override
    public Painter visitPIRFunDef(PIRFunDef funDef, HPLContext context) throws HPLException {
        String funName = funDef.getFunName();
        ArrayList<String> nParams = funDef.getNumericalArgExps();
        ArrayList<String> pParams = funDef.getPainterArgExps();
	PIRSequence pSeq = funDef.getStatementSequence();
	HPLFunction func = new HPLFunction(funName, nParams, pParams, pSeq, context);


	return Painter.DEFAULT;
    }

    /**
     * Evaluate a function call.
     * @param funCall The function call expression
     * @param context The environment w.r.t. which the call is evaluated
     * @return The (painter) object that arises from applying the function.
     * @throws hpl.sys.HPLException if something goes wrong while invoking the 
     * function call.
     */
    @Override
    public Painter visitPIRFunCall(PIRFunCall funCall, HPLContext context) throws HPLException {
        String funName = funCall.getFunName();
        ArrayList<ASTExp<AIRExp>> nArgExps = funCall.getNumericalArgExps();
        ArrayList<ASTExp<PIRExp>> pArgExps = funCall.getPainterArgExps();

		ArrayList<Painter> pArgs = new ArrayList<Painter>();
		ArrayList<Double> nArgs = new ArrayList<Double>();
			
		// ** Incomplete implementation **
			// evaluate the argument parameters ...
		for (int i = 0; i<pArgExps.size(); i++) {
			pArgs.add(pArgExps.get(i).visit(this, context));        
		}
		for (int i = 0; i<nArgExps.size(); i++) {
			nArgs.add(nArgExps.get(i).visit(arithEval, context.getNumEnv()));	
		}
		HPLFunction func = context.getF(funName);
		ArrayList<String> pStrArgs = func.getPainterParams();
		ArrayList<String> nStrArgs = func.getNumericalParams();
			// extend the closing environment with bindings for painter parameters
		context.extendP(pStrArgs, pArgs); 
			// also extend with bindings for numerical parameters
		context.extendN(nStrArgs, nArgs);
        // and we extend with empty function frame to keep local functions local
        	context.extendF(new ArrayList<String>(), new ArrayList<HPLFunction>());
        // now return a painter that will execute the body when rendered.
        return new CompoundPainter(this, func.getBody(), context); // (** fix this **)
    }

    /* ----------------- End of Section for Problem 5 ----------------- */


    @Override
    public String visitSMPLPrintStmt(SMPLPrintStmt printStmt, HPLContext env)
	throws HPLException {
        String separator = printStmt.getSeparator();
        ASTExp expression = printStmt.getExpression();

        return p;
    }

    @Override
    public String visitSMPLIfStmt(SMPLIfStmt ifStmt, HPLContext state) throws HPLException {
        ASTCmpBinaryExp predicate = ifStmt.getPredicate();
        Boolean val = predicate.visit(arithEval, state.getNumEnv()).booleanValue();
        if(val){
            PIRSequence consequent = ifStmt.getConsequent();
            return consequent.visit(this, state);
        }
        else{
            PIRSequence alternate = ifStmt.getAlternate();
            return alternate.visit(this, state);
        }
    }

    /**
     * Evaluate a painter variable reference.
     * @param var The variable referencing a painter
     * @param context The context containing the environment in which to look
     * up the variable.
     * @return The painter object bound to the given variable.
     * @throws HPLException if there is no painter bound to the given variable.
     */
    @Override
    public Painter visitVar(ASTVar<PIRExp> var, HPLContext context)
	throws HPLException {
	return context.getP(var.getId());
    }

    @Override
    public Painter visitUnaryExp(ASTUnaryExp<PIRExp> exp, HPLContext state)
	throws HPLException  {
	// should never get here unless language changes
	throw new HPLException("Unknown unary operation applied to painter: " +
			       exp);
    }
    
    @Override
    public Painter visitBinaryExp(ASTBinaryExp<PIRExp> exp, HPLContext state)
	throws HPLException {
	// should never get here unless language changes
	throw new HPLException("Unknown binary operation applied to painters: "+
			       exp);
    }
}
