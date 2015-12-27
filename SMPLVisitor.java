/**
 * @author ThreeMusketeers
 * @version 1.0
 * @param <S> The type of the state used by the visitor
 * @param <T> The return type of the visitor
 */
public interface SMPLVisitor<S, T> extends ASTVisitor<SMPLExp, S, T> {

    /* Program (Top level sequence of statements) */
    public T visitSMPLProgram(SMPLProgram program, S arg) throws SMPLException;

    public T visitSMPLSequence(SMPLSequence seq, S state) throws SMPLException;

    public T visitSMPLAssignment(SMPLAssignment assignment, S state) throws SMPLException;

    public T visitSMPLPrintStmt(SMPLPrintStmt printStmt, S state) throws SMPLException; 
    
    public T visitSMPLWaitStmt(SMPLWaitStmt waitStmt, S state) throws SMPLException;

    public T visitSMPLIfStmt(SMPLIfStmt ifStmt, S state) throws SMPLException;

    public T visitSMPLFunCall(SMPLFunCall funCall, S state) throws SMPLException;
    
    public T visitSMPLDefine(SMPLDefine define, S state) throws SMPLException; 

    public T visitSMPLProcExp(SMPLProcExp procExp, S state) throws SMPLException; 

    public T visitSMPLPairExp(SMPLPairExp pairExp, S state) throws SMPLException; 

    public T visitSMPLPairQueryExp(SMPLPairQueryExp pairQueryExp, S state) throws SMPLException; 

    public T visitSMPLListExp(SMPLListExp listExp, S state) throws SMPLException; 

    public T visitSMPLVecNExp(SMPLVecNExp vecNExp, S state) throws SMPLException;

    public T visitSMPLVecSizeExp(SMPLVecSizeExp vecSizeExp, S state) throws SMPLException;

    public T visitSMPLVecList(SMPLVecList vecList, S state) throws SMPLException;

    public T visitSMPLBindExp(SMPLBindExp bindExp, S state) throws SMPLException;

    public T visitSMPLPredConExp(SMPLPredConExp predConExp, S state) throws SMPLException;

    public T visitSMPLCaseStme(SMPLCaseStme caseStmt, S state) throws SMPLException;

    public T visitSMPLLazyExp(SMPLLazyExp lazyExp, S state) throws SMPLException;

    public T visitSMPLMultiAssign(SMPLMultiAssign mAssign, S state) throws SMPLException;
}
