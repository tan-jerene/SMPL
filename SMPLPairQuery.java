/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * ASTNode to represent pair type definitions.
 * @author ThreeMusketeers
 */
public class SMPLPairQuery extends SMPLExp {
    private final ASTExp exp1;
    
    public SMPLPairQuery(ASTExp exp1) {
        this.exp1 = exp1;
    }

    /**
     *
     * @return The object to be tested.
     */
    public ASTExp getExp1() {
        return exp1;
    }

    
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
        return v.visitSMPLPairQuery(this, state);
    }
}
