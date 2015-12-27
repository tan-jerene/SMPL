/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * ASTNode to represent pair type definitions.
 * @author ThreeMusketeers
 */
public class SMPLConcatExp extends SMPLExp {
    private final SMPLListExp original;
    private final SMPLListExp addition;
    
    public SMPLConcatExp(SMPLListExp original, SMPLListExp addition) {
        this.original = original;
        this.addition = addition;
    }

    /**
     *
     * @return The original list.
     */
    public SMPLListExp getOriginal() {
        return original;
    }

    /**
     *
     * @return The list to be concatenated.
     */
    public SMPLListExp getAddition() {
        return addition;
    }
    
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
        return v.visitSMPLConcatExp(this, state);
    }
}
