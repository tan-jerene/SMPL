/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Placeholder class to represent a generic expression.  
 * Should never be instantiated directly, instead one of the subclasses (e.g.
 * AIRExp or LIRExp would be instantiated instead).
 * @author 
 * Created on 28-Oct-2015
 * @param <E> The type of subexpressions expected in this expression
 */
public abstract class ASTExp<E extends ASTExp<E>> extends ASTNode {
    
    public abstract <S, T> T visit (ASTVisitor<E, S, T> v, S state) throws SMPLException;

}
