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
public class SMPLVecStmt extends SMPLStatement {

    SMPLVecList vecList;

    public SMPLVecStmt(SMPLVecList vecList) {
        this.vecList = vecList; //Determines whether print or println; "" - print, "\n" - println
    }

    public  SMPLVecList getVarName() {
        return vecList;
    }
    
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
        return v.visitSMPLVecStmt(this, state);
    }

}
