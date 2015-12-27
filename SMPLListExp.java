/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * ASTNode to represent pair type definitions.
 * @author ThreeMusketeers
 */
public class SMPLListExp extends SMPLExp {
    private final ArrayList<ASTExp> vals;
    
    public SMPLListExp(ASTExp vals) {
        this.vals = vals;
    }

    /**
     *
     * @return The first expression in the pair to be created.
     */
    public ASTExp getVals() {
        return vals;
    }

    
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
        return v.visitSMPLListExp(this, state);
    }
}
