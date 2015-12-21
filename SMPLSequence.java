import java.util.*;

/**
 * An instance of the <code>PIRSequence</code> class is a sequence of
 * <code>PIRExp</code> objects.  Each expression representation is
 * treated as if it were a statement -- even if it yields a result.
 *
 * @author ThreeMusketeers
 * @version 1.0
 */
public class SMPLSequence extends ASTExp {

    protected ArrayList<SMPLStatement> sequence;

    /**
     * Create an empty sequence of statements
     *
     */
    public SMPLSequence() {
	sequence = new ArrayList<>();
    }

    /**
     * Creates a new <code>SMPLSequence</code> instance.
     *
     * @param seq an <code>ArrayList</code> value
     */
    public SMPLSequence(ArrayList<SMPLStatement> seq) {
	sequence = seq;
    }

    /**
     * Add an SMPL statement to the end of this sequence.
     *
     * @param stmt the statement to be added.
     */
    public void addStatement(SMPLStatement stmt) {
	   sequence.add(stmt);
    }

    public final ArrayList<SMPLStatement> getStatements() {
	   return sequence;
    }

    /**
     * Call the visitSMPLSequence method within <code>v</code> on this
     * sequence representation and the given argument.
     *
     * @param v a <code>Visitor</code> value
     * @param state the data to be passed to this sequence's components
     * @return the result of visiting this sequence
     * @throws SMPLException
     */
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
	return v.visitSMPLSequence(this, state);
    }
}

