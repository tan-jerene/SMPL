/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 import java.util.HashMap;

/**
 * An evaluator for arithmetic subexpressions of SMPL programs.
 * @author Daniel Coore <daniel.coore@uwimona.edu.jm>
 * Created on 24-Oct-2015
 */
public class CmpEvaluator implements AIRVisitor<SMPLEnvironment<Boolean>, Boolean> {
    
    HashMap<String, BinCmpArith> binCmpsMap;
    
    public CmpEvaluator() {
        init();
    }
    
    private void init() {
        binCmpsMap = new HashMap<>();
        for (BinCmpArith cmp: BinCmpArith.values()) {
            binCmpsMap.put(cmp.getSymbol(), cmp);
        }
    }

    @Override
    public Boolean visitBinaryCmpExp(ASTBinaryExp<AIRExp> exp, SMPLEnvironment<Double> env) throws SMPLException {
        String cmpName = exp.getOperator();
        BinCmpArith cmp = binCmpsMap.get(cmpName);
        ASTExp<AIRExp> leftExp = exp.getExp1();
        ASTExp<AIRExp> rightExp = exp.getExp2();
        double leftArg = leftExp.visit(this, env);
        double rightArg = rightExp.visit(this, env);
        return cmp.apply(leftArg, rightArg);
    }
    
}
