/**
 * <code>AIRExp</code> is the parent class for all arithmetic
 * expressions.  Since numbers may not be named by SMPL variables, no
 * environment is needed to evaluate arithmetic expressions.
 *
 * @author <a href="mailto:newts@uwimona.edu.jm">Daniel Coore</a>
 * @version 1.0
 */
public abstract class AIRExp extends ASTExp<AIRExp> {

    public AIRExp() {
	super();
    }
    /**************FUNCTIONALITY PROVIDED BY BinaryOpArith*************/
    public abstract <S, T> T visit(AIRVisitor<S, T> v, S arg) throws SMPLException;

    @Override
    public <S, T> T visit(ASTVisitor<AIRExp, S, T> v, S state) throws SMPLException {
        // We have to delegate to the more specific visit method from here.
        return visit((AIRVisitor<S, T>) v, state);
    }
}
