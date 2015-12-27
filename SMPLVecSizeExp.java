/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * ASTNode to represent "substr" function which defines the data to be processed in order to return the substring of the original.
 * @author ThreeMusketeers
 */
public class SMPLVecSizeExp extends SMPLExp {
    private final ASTExp val;
    
    public SMPLVecSizeExp(ASTExp val) {
        this.val = val;
    }

    /**
     *
     * @return The beginning index of the substring.
     */
    public ASTExp getVal() {
        return val;
    }
    
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
        return v.visitSMPLVecSizeExp(this, state);
    }
}
