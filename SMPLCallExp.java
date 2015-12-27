import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * ASTNode to represent "substr" function which defines the data to be processed in order to return the substring of the original.
 * @author ThreeMusketeers
 */
public class SMPLCallExp extends SMPLExp {
    SMPLProcExp fn;
    SMPLListExp lst;
    
    public SMPLCallExp(SMPLProcExp fn, SMPLListExp lst) {
        this.fn = fn;
        this.lst = lst;
    }

    /**
     *
     * @return Name of binding.
     */
    public SMPLExp getFn() {
        return fn;
    }

    /**
     *
     * @return Value in the binding.
     */
    public SMPLListExp getVal() {
        return lst;
    }
    
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
        return v.visitSMPLCallExp(this, state);
    }
}
