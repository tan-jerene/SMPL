/**
 * Abstract Representation for Statements.  This class is actually
 * empty, but its presence allows us to add functionality specific to
 * statements at a later time without breaking the rest of the
 * language processor (interpreter or compiler).
 *
 * @author ThreeMusketeers
 * @version 1.0
 */
public abstract class SMPLStatement extends ASTNode {

    // abstract class placeholder for statements

    /**
     * Call the <code>visitSMPLStatement</code> method in the given visitor.
     * @param <S> The type of the state used by the visitor
     * @param <T> The return type of the visitor
     * @param v The visitor visiting this node.
     * @param state The context available to the visitor at the time it 
     * encountered this statement.
     * @return The result of calling the relevant statement visiting method of the given 
     * visitor.
     * @throws SMPLException     
     */
        public abstract <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException;
}
