/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package hpl.lang;

// import hpl.sys.HPLException;
// import java.util.ArrayList;

/**
 * Last Editted:- 14/12/2015_5:30pm
 */
public class SMPLFunDef extends SMPLStatement { 
    private final String funName;
    private final ArrayList<String> fArgExps;
    private final SMPLSequence fSeq;
    
    public HPLFunDef(String fnName, ArrayList<String> fArgs, SMPLSequence fSequence) {
        funName = fnName;
        fArgExps = fArgs;
	    fSeq = fSequence;
    }

    /**
     *
     * @return The name of the function in this function application expression
     */
    public String getFunName() {
        return funName;
    }

    /**
     *
     * @return The list of all function arguments in this expression.
     */
    public ArrayList<String> getFunctionArgExps() {
        return fArgExps;
    }

    /**
     *
     * @return The expression sequence within the function definition.
     */
    public SMPLSequence getStatementSequence() {
	   return fSeq;
    }

    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
        return v.visitSMPLFunDef(this, state);
    }

}
