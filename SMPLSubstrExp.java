/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * ASTNode to represent "substr" function which defines the data to be processed in order to return the substring of the original.
 * @author ThreeMusketeers
 */
public class SMPLSubstrExp extends SMPLExp {
    private final String original;
    private final Integer begIndex;
    private final Integer endIndex;
    
    public SMPLSubstrExp(String original, Integer begIndex, Integer endIndex) {
        this.original = original;
        this.begIndex = begIndex;
        this.endIndex = endIndex;
    }

    /**
     *
     * @return The original string.
     */
    public String getOriginal() {
        return original;
    }

    /**
     *
     * @return The beginning index of the substring.
     */
    public Integer getBegIndex() {
        return begIndex;
    }

    /**
     *
     * @return The end index of the substring.
     */
    public Integer getEndIndex() {
        return endIndex;
    }
    
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
        return v.visitSMPLSubstrExp(this, state);
    }
}
