/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * ASTNode to represent "car" function calls. 
 * @author
 */
public class ASTCarExp extends ASTExp {
    private final ASTExp pair;
    
    public ASTCarExp(ASTExp pair) {
        this.pair = pair;
    }

    /**
     *
     * @return The pair to be evaluated
     */
    public ASTExp getPair() {
        return pair;
    }
    
    @Override
    public <S, T> T visit(HPLVisitor<S, T> v, S state) throws HPLException {
        return v.visitASTCarExp(this, state);
    }
}
