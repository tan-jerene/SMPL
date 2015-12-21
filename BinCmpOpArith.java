/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel Coore <daniel.coore@uwimona.edu.jm>
 */
public enum BinCmpOpArith implements BinaryOp<Double, Boolean> {
    LTHAN("<") {
        @Override
        public Boolean apply(Double leftArg, Double rightArg) {
            return leftArg < rightArg;
        }
    },
    GTHAN(">") {
        @Override
        public Boolean apply(Double leftArg, Double rightArg) {
            return leftArg > rightArg;
        }
    },
    LTHANE("<=") {
        @Override
        public Boolean apply(Double leftArg, Double rightArg) {
            return leftArg <= rightArg;
        }
    },
    GTHANE(">=") {
        @Override
        public Boolean apply(Double leftArg, Double rightArg) {
            return leftArg >= rightArg;
        }
    },
    EQUAL("==") {
        @Override
        public Boolean apply(Double leftArg, Double rightArg) {
            return leftArg == rightArg;
        }
    },
    NEQUAL("!=") {
        @Override
        public Boolean apply(Double leftArg, Double rightArg) {
            return leftArg != rightArg;
        }
    },
    ;
    
    String symbol;

    public BinOpArith(String symbol) {
        this.symbol = symbol;
    }

    /**
     *
     * @return The symbol for this operator
     */
    @Override
    public String getSymbol() {
        return symbol;
    }
}
