package hpl.values;

import hpl.sys.*;
import hpl.lang.*;
import java.util.*;

/**
 * Representation for HPL Functions.  HPL+ is statically scoped, so we have
 * to maintain a closing environment for a function.
 * 
 * @author <a href="mailto:newts@uwimona.edu.jm">Daniel Coore</a>
 * @version 1.0
 */
public class SMPLFunction {

    String name;
    ArrayList<String> params;
    SMPLSequence body;
    HPLContext closingEnv;

    /**
     * Create a new function instance with a given name, parameter list, body
     * and closing environment.
     * @param id The name of the function
     * @param nParams The function's formal numerical parameters.
     * @param pParams The function's formal painter parameters.
     * @param b The body of the function
     * @param env The closing environment of the function.
     */
    public SMPLFunction(String id, ArrayList<String> params,  SMPLSequence b, SMPLContext env) {
	    name = id;
        this.params = params;
	    body = b;
        closingEnv = env;
    }

    /**
     * @return The function's name
     */
    public String getName() {
        return name;
    }

    public ArrayList<String> getParams() {
        return params;
    }

    /**
     * @return The body of this function
     */
    public SMPLSequence getBody() {
        return body;
    }

    /**
     * @return The closing environment for this function
     */
    public SMPLContext getClosingEnv() {
        return closingEnv;
    }
}
