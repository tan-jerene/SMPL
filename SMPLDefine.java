/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;

/**
 * Last Editted:- 14/12/2015_5:30pm
 */
public class SMPLDefine extends SMPLStatement { 
    private final String funName;
    private final SMPLExp exp;
    
    public SMPLDefine(String fnName, SMPLExp expression) {
        funName = fnName;
        exp = expression;
    }

    /**
     *
     * @return The name of the function in this function application expression
     */
    public String getFunName() {
        return funName;
    }

    /**
     *
     * @return The list of all function arguments in this expression.
     */
    public SMPLExp getExpression() {
        return exp;
    }

    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
        return v.visitSMPLDefine(this, state);
    }

}
