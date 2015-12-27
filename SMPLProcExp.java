/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;

/**
 * Last Editted:- 14/12/2015_5:30pm
 */
public class SMPLFunDef extends SMPLExp { 
    private final ArrayList<String> fFArgExps;
    private final ArrayList<String> sFArgExps;
    private final SMPLSequence fSeq;
    
    public SMPLFunDef(ArrayList<String> fFArgs, ArrayList<String> sFArgs, SMPLSequence fSequence) {
        fFArgExps = fFArgs;
        sFArgExps = sFArgs;
	    fSeq = fSequence;
    }
    
    public SMPLFunDef(ArrayList<String> fFArgs, SMPLSequence fSequence) {
        fFArgExps = fFArgs;
        sFArgExps = null;
        fSeq = fSequence;
    }

    /**
     *
     * @return The first list of all function arguments in this expression.
     */
    public ArrayList<String> getFirstFunctionArgs() {
        return fFArgExps;
    }

    /**
     *
     * @return The second list of function arguments in this expression.
     */
    public ArrayList<String> getSecondFunctionArgs() {
        return sFArgExps;
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
        return v.visitSMPLProcExp(this, state);
    }

}
