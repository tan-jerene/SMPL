import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * ASTNode to represent "substr" function which defines the data to be processed in order to return the substring of the original.
 * @author ThreeMusketeers
 */
public class SMPLVecList extends SMPLExp {
    protected ArrayList<ASTExp> vlist;
    
    public SMPLVecList(ArrayList<ASTExp> vlist) {
        this.vlist = vlist;
    }

    public SMPLVecList() {
        this.vlist = new ArrayList<ASTExp>();
    }

    public void addEntry(ASTExp val) {
        vlist.add(val);
    }

    /**
     *
     * @return The beginning index of the substring.
     */
    public ArrayList<ASTExp> getVList() {
        return vlist;
    }
    
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
        return v.visitSMPLVecList(this, state);
    }
}
