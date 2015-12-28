/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * ASTNode to represent pair type definitions.
 * @author ThreeMusketeers
 */
public class SMPLPredConExp {
    private final ASTLogBinaryExp<AIRExp> predicate;
    private final SMPLSequence consequent;
    
    public SMPLPredConExp(ASTLogBinaryExp<AIRExp> predicate, SMPLSequence consequent) {
        this.predicate = predicate;
        this.consequent = consequent;
    }

    /**
     *
     * @return The first expression in the pair to be created.
     */
    public ASTLogBinaryExp<AIRExp> getPredicate() {
        return predicate;
    }

    /**
     *
     * @return The second expression in the pair to be created.
     */
    public SMPLSequence getConsequent() {
        return consequent;
    }
}
