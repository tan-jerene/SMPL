/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 import java.util.ArrayList;

/**
 * ASTNode to represent function calls.  Functions in SMPL+ take a (possibly
 * empty) list of painters and produce a painter.  Note that functions are
 * not themselves painters.
 * @author
 */
public class SMPLFunCall extends ASTExp {
    private final String funName;
    private final ArrayList<ASTExp> fArgExps;
    
    public SMPLFunCall(String fnName, ArrayList<ASTExp> fArgs) {
        funName = fnName;
        fArgExps = fpArgs;
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
     * @return The list of function argument expressions in this call expression
     */
    public ArrayList<ASTExp> getFunctionArgExps() {
        return fArgExps;
    }
    
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
        return v.visitSMPLFunCall(this, state);
    }
}
