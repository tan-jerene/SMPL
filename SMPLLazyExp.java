/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ThreeMusketeers
 * Created on 25-Oct-2015
 */
public class SMPLLazyExp extends SMPLExp {

    ASTExp exp;


    public SMPLLazyExp(ASTExp exp) {
        this.exp = exp;
    }

    public ASTExp getLst() {
        return exp;
    }

    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
        return v.visitSMPLLazyExp(this, state);
    }

}
