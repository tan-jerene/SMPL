/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * ASTNode to represent pair type definitions.
 * @author ThreeMusketeers
 */
public class SMPLLetExp extends SMPLExp {
    private final ArrayList<SMPLBindExp> bList;
    private final SMPLSequence stmtList;
    
    public SMPLLetExp(ArrayList<SMPLBindExp> bList, SMPLSequence stmtList) {
        this.bList = bList;
        this.stmtList = stmtList;
    }

    /**
     *
     * @return The bind List.
     */
    public ArrayList<SMPLBindExp> getOriginal() {
        return bList;
    }

    /**
     *
     * @return The Statement List.
     */
    public SMPLSequence getAddition() {
        return stmtList;
    }
    
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
        return v.visitSMPLLetExp(this, state);
    }
}
