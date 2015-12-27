/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * SMPLNode to represent "car" function calls. 
 * @author
 */
public class SMPLCarExp extends SMPLExp {
    private final ASTExp pair;
    
    public SMPLCarExp(SMPLExp pair) {
        this.pair = pair;
    }

    /**
     *
     * @return The pair to be evaluated
     */
    public ASTExp getPair() {
        return pair;
    }
    
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
        return v.visitSMPLCarExp(this, state);
    }
}
