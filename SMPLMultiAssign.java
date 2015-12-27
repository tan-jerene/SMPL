/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ThreeMusketeers
 * Created on 20-Dec-2015
 */
public class SMPLMultiAssign extends SMPLStatement {

    ArrayList<ASTExp> varList;
    ASTExp expression;

    public SMPLMultiAssign(ArrayList<ASTExp> varList, ASTExp expression) {
        this.varList = varList; //Determines whether print or println; "" - print, "\n" - println
        this.expression = expression; //Value to be printed
    }

    public  ArrayList<ASTExp> getVarList() {
        return varList;
    }

    public ASTExp getExpression() {
        return expression;
    }
    
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
        return v.visitSMPLMultiAssign(this, state);
    }

}
