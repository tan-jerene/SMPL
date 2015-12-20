/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * ASTNode to represent "eqv" function which checks to expressions to determine whether equivalent.
 * @author
 */
public class ASTEqvExp extends ASTExp {
    private final ASTExp exp1;
    private final ASTExp exp2;
    
    public ASTEqvExp(ASTExp exp1, ASTExp exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    /**
     *
     * @return The first expression to be evaluated.
     */
    public ASTExp getExp1() {
        return exp1;
    }

    /**
     *
     * @return The second expression to be evaluated.
     */
    public ASTExp getExp2() {
        return fArgExps;
    }
    
    @Override
    public <S, T> T visit(HPLVisitor<S, T> v, S state) throws HPLException {
        return v.visitASTEqvExp(this, state);
    }
}
