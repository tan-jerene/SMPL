package SMPL.lang;

import SMPL.sys.SMPLException;

/**
 * @author <a href="mailto:newts@uwimona.edu.jm">Daniel Coore</a>
 * @version 1.0
 * @param <S> The type of the state used by the visitor
 * @param <T> The return type of the visitor
 */
public interface SMPLVisitor<S, T> extends ASTVisitor<SMPLExp, S, T> {

    /* Program (Top level sequence of statements) */
    public T visitSMPLProgram(SMPLProgram program, S arg) throws SMPLException ;

    public T visitSMPLSequence(SMPLSequence seq, S state) throws SMPLException;

    public T visitSMPLAssignment(SMPLAssignment assignment, S state) throws SMPLException ;

    public T visitSMPLPrintStmt(SMPLPrintStmt printStmt, S state) throws SMPLException;
    
    public T visitSMPLWaitStmt(SMPLWaitStmt waitStmt, S state) throws SMPLException;

    public T visitSMPLIfStmt(SMPLIfStmt ifStmt, S state) throws SMPLException;

    public T visitSMPLFunCall(SMPLFunCall funCall, S state) throws SMPLException;
    
    public T visitSMPLFunDef(SMPLFunDef funDef, S state) throws SMPLException; //added for SMPLFunDef Nov 15, 4:51pm

}
