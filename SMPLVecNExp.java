/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * ASTNode to represent "substr" function which defines the data to be processed in order to return the substring of the original.
 * @author ThreeMusketeers
 */
public class SMPLVecNExp extends SMPLExp {
    private final ASTExp obj1;
    private final ASTExp obj2;
    
    public SMPLVecNExp(ASTExp obj1, ASTExp obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    /**
     *
     * @return The beginning index of the substring.
     */
    public ASTExp getObj1() {
        return obj1;
    }

    /**
     *
     * @return The end index of the substring.
     */
    public ASTExp getObj2() {
        return obj2;
    }
    
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
        return v.visitSMPLVecNExp(this, state);
    }
}
