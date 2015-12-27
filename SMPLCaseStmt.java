import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel Coore <daniel.coore@uwimona.edu.jm>
 * Created on 25-Oct-2015
 */
public class SMPLCaseStmt extends SMPLStatement {

    ArrayList<SMPLPredConExp> lst;


    public SMPLCaseStmt(ArrayList<SMPLPredConExp> lst) {
        this.lst = lst;
    }

    public ArrayList<SMPLPredConExp> getLst() {
        return lst;
    }

    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S state) throws SMPLException {
        return v.visitSMPLCaseStmt(this, state);
    }

}
