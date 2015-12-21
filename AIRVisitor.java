/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import hpl.sys.SMPLException;

/**
 * Visitor interface for arithmetic expressions within SMPL contexts
 * @author Daniel Coore <daniel.coore@uwimona.edu.jm>
 * @param <S> The type of the state used by the visitor
 * @param <T> The type returned by the visitor
 */
public interface AIRVisitor<S, T>  extends ASTVisitor<AIRExp, S, T> {
        /* Arithmetic expressions */

    /**
     * Carry out the approrpriate operations for visiting an integer expression.
     * @param exp The integer valued expression being visited
     * @param state The state needed by this visitor.
     * @return The result of visiting the integer expression.
     * @throws SMPLException if an error is encountered.
     */
    public T visitAIRExpInt(AIRExpInt exp, S state) throws SMPLException;

    /**
     * Carry out the approrpriate operations for visiting an fractional expression.
     * @param exp The integer valued expression being visited
     * @param state The state needed by this visitor.
     * @return The result of visiting the fractional expression.
     * @throws SMPLException if an error is encountered.
     */
    public T visitAIRExpFrac(AIRExpFrac exp, S state) throws SMPLException;
    
//    /**
//     * Carry out the appropriate operations for visiting a variable expression.
//     * @param exp The integer valued expression being visited
//     * @param state The state needed by this visitor.
//     * @return The result of looking up the variable.
//     * @throws SMPLException if an error is encountered.
//     */
//    public T visitAIRExpVar(AIRExpVar exp, S state) throws SMPLException;
//    
//    /**
//     * Carry out the appropriate operations for visiting an numerical expression.
//     * @param exp The integer valued expression being visited
//     * @param state The state needed by this visitor.
//     * @return The result of visiting the integer expression.
//     * @throws SMPLException if an error is encountered.
//     */
//    public T visitAIRBinaryExp(AIRBinaryExp exp, S state) throws SMPLException;
    
}
