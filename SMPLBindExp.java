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
public class SMPLBindExp extends SMPLExp {
    String name;
    AstExp val;
    
    public SMPLBindExp(String name, ASTExp val) {
        this.name = name;
        this.val = val;
    }

    /**
     *
     * @return Name of binding.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return Value in the binding.
     */
    public ASTExp getVal() {
        return val;
    }
    
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
        return v.visitSMPLBindExp(this, state);
    }
}
