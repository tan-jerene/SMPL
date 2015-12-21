/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ThreeMusketeers
 */
public enum BinLogOpArith implements BinaryOp<Boolean, Boolean> {
    OR("or") {
        @Override
        public Boolean apply(Boolean leftArg, Boolean rightArg) {
            return leftArg || rightArg;
        }
    },
    AND("and") {
        @Override
        public Boolean apply(Boolean leftArg, Boolean rightArg) {
            return leftArg && rightArg;
        }
    },
    ;
    
    String symbol;

    BinLogOpArith(String symbol) {
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
