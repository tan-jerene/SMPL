/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * ASTNode to represent pair type definitions.
 * @author ThreeMusketeers
 */
public class SMPLPairExp extends SMPLExp {
    private final ASTExp exp1;
    private final ASTExp exp2;
    
    public SMPLPairExp(ASTExp exp1, ASTExp exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    /**
     *
     * @return The first expression in the pair to be created.
     */
    public ASTExp getExp1() {
        return exp1;
    }

    /**
     *
     * @return The second expression in the pair to be created.
     */
    public ASTExp getExp2() {
        return exp2;
    }
    
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
        return v.visitSMPLPairExp(this, state);
    }
}
