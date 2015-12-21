/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel Coore <daniel.coore@uwimona.edu.jm>
 * Created on 28-Oct-2015
 * @param <E> The type of subexpression expected in this binary operation expression.
 */
public class ASTCmpBinaryExp<E extends ASTExp<E>> extends ASTExp<E> {
    
    String operator;
    ASTExp<E> exp1;
    ASTExp<E> exp2;

    public ASTCmpBinaryExp(String operator, ASTExp<E> exp1, ASTExp<E> exp2) {
        this.operator = operator;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    public String getOperator() {
        return operator;
    }

    public ASTExp<E> getExp1() {
        return exp1;
    }

    public ASTExp<E> getExp2() {
        return exp2;
    }

    @Override
    public <S, T> T visit(ASTVisitor<E, S, T> v, S state) throws SMPLException {
        return v.visitCmpBinaryExp(this, state);
    }
}
