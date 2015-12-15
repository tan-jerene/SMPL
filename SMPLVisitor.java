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

    /* Painter statements */
    public T visitSMPLAssignment(SMPLAssignment assignment, S state) throws SMPLException ;

    public T visitSMPLPaintStmt(SMPLPaintStmt paintStmt, S state) throws SMPLException;
    
    public T visitSMPLWaitStmt(SMPLWaitStmt waitStmt, S state) throws SMPLException;
    // Put your method to handle if statements here (if you do this problem)

    /* Painter Expressions */

    public T visitSMPLImagePainter(SMPLImagePainter exp, S state) throws SMPLException;    
    
    public T visitSMPLFunCall(SMPLFunCall funCall, S state) throws SMPLException;
    
    public T visitSMPLFunDef(SMPLFunDef funDef, S state) throws SMPLException; //added for SMPLFunDef Nov 15, 4:51pm

}
