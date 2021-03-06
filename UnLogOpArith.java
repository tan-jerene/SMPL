/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hpl.lang;

/**
 * An enumeration of all unary arithmetic operations.
 * @author Daniel Coore <daniel.coore@uwimona.edu.jm>
 * Created on 28-Oct-2015
 */
public enum UnLogOpArith implements UnaryOp<Boolean, Boolean>{
    NOT("not") {
        @Override
        public Boolean apply(Boolean arg) {
            return !arg;
        }//experimental examples
    };
    
    String symbol;
    private UnLogOpArith(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }
}
