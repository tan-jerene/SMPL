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

    ASTExp comparison;
    SMPLSequence ifClause;
    SMPLSequence elseClause;


    public SMPLIfStmt(ASTExp comparison, SMPLSequence ifClause, SMPLSequence elseClause) {
        this.comparison = comparison;
        this.ifClause = ifClause;
        this.elseClause = elseClause;
    }

    public ASTExp getComparison() {
        return separator;
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
