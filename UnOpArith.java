/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * An enumeration of all unary arithmetic operations.
 * @author ThreeMusketeers
 * Created on 28-Oct-2015
 */
public enum UnOpArith implements UnaryOp<Double, Double>{
    INC("++") {
        @Override
        public Double apply(Double arg) {
            return arg + 1;
        }//experiment
    },
    DEC("--") {
        @Override
        public Double apply(Double arg) {
            return arg - 1;
        }//experiment
    },
    NEG("-") {
        @Override
        public Double apply(Double arg) {
            return -arg;
        }//experiment
    },
    ;
    
     String symbol;

    public UnOpArith(String symbol) {
        this.symbol = symbol;
    }

    
       @Override
    public String getSymbol() {
        return symbol;
    }
}
