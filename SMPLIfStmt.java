/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel Coore <daniel.coore@uwimona.edu.jm>
 * Created on 25-Oct-2015
 */
public class SMPLIfStmt extends SMPLStatement {

    ASTLogBinaryExp predicate;
    SMPLSequence ifClause;
    SMPLSequence elseClause;


    public SMPLIfStmt(ASTExp predicate, SMPLSequence ifClause, SMPLSequence elseClause) {
        this.predicate = predicate; //Comparative Statement eg. (x > y)
        this.ifClause = ifClause; //Statements if true
        this.elseClause = elseClause; //Statements if false
    }

    public ASTLogBinaryExp getPredicate() {
        return predicate;
    }

    public SMPLSequence getIfClause() {
        return ifClause;
    }
    
    public SMPLSequence getElseClause() {
        return elseClause;
    }

    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
        return v.visitSMPLIfStmt(this, state);
    }

}
