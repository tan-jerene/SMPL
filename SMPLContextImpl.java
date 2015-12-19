package hpl.sys;

import hpl.values.HPLFunction;
import hpl.values.Painter;
import java.util.ArrayList;

/**
 *
 * @author ThreeMusketeers
 * Created on 19-Dec-2015
 */
public class SMPLContextImpl implements SMPLContext {
	private HPLEnvironment<SMPLFunction> fnEnv;
	private HPLEnvironment<Double> dEnv;
	private HPLEnvironment<String> sEnv;
    private HPLEnvironment<Boolean> bEnv;

	 public HPLContextImpl(HPLEnvironment<HPLFunction> fnEnv, HPLEnvironment<Double> dEnv){
		this.fnEnv = fnEnv;
		this.dEnv = dEnv;
		this.sEnv = sEnv;
        this.bEnv = bEnv;
	 }
	 public HPLContextImpl(){
		this.fnEnv = null;
		this.dEnv = null;
		this.sEnv = null;
        this.bEnv = null;
	 }

    /**
     * Create a new context in which the function environment is extended with
     * new bindings.
     * @param fParams The names to be bound in the new function frame.
     * @param args The corresponding values for the names
     * @return A newly created context containing the new function environment,
     * but leaving all the other components of the context unchanged.
     */
    public SMPLContext extendF(ArrayList<String> fParams, ArrayList<SMPLFunction> args){ 
		return new SMPLContextImpl(new SMPLEnvironment<SMPLFunction>(this.fnEnv, fParams, args), this.dEnv, this.sEnv, this.bEnv);
	}

    /**
     * Create a new context in which the numerical environment is extended with
     * new bindings.
     * @param nParams The names to be bound in the new numerical environment
     * frame.
     * @param vals The corresponding values for the names
     * @return A newly created context containing the new numerical environment,
     * but leaving all the other components of the context unchanged.
     */
    public SMPLContext extendN(ArrayList<String> nParams, ArrayList<Double> vals){ 
		return new SMPLContextImpl(this.fnEnv, new SMPLEnvironment<Double>(this.dEnv, nParams, vals), this.sEnv, this.bEnv);
	}

    /**
     * Create a new context in which the String environment is extended with
     * new bindings.
     * @param pParams The names to be bound in the new String environment frame.
     * @param args The corresponding values for the names
     * @return A newly created context containing the new String environment,
     * but leaving all the other components of the context unchanged.
     */
    public SMPLContext extendS(ArrayList<String> pParams, ArrayList<Sting> args){  
		return new SMPLContextImpl(this.fnEnv, this.dEnv, new SMPLEnvironment<String>(this.sEnv, pParams, args), this.bEnv);
	}

    /**
     * Create a new context in which the String environment is extended with
     * new bindings.
     * @param pParams The names to be bound in the new String environment frame.
     * @param args The corresponding values for the names
     * @return A newly created context containing the new String environment,
     * but leaving all the other components of the context unchanged.
     */
    public SMPLContext extendB(ArrayList<String> pParams, ArrayList<Sting> args){  
        return new SMPLContextImpl(this.fnEnv, this.dEnv, this.sEnv, new SMPLEnvironment<Boolean>(this.bEnv, pParams, args));
    }

    /**
     * Lookup a reference to a SMPL function.
     * @param name The identifier of the SMPL function
     * @return The SMPL function associated with the given name in this context
     * @throws SMPLException if the name is not bound to a Painter in this
     * context
     */
    public SMPLFunction getF(String name) throws SMPLException{
		return this.fnEnv.get(name);
	}

    /**
     *
     * @return The (resultant) frame associated with this context.
     */
    public PainterFrame getFrame(){
		return this.bEnv;
	}

    /**
     * Lookup a reference to a number
     * @param name The identifier of the Double
     * @return The number associated with the given name in this context
     * @throws HPLException if the name is not bound to a number in this
     * context
     */
    public Double getN(String name) throws HPLException{
		return this.dEnv.get(name);
	}

    /**
     *
     * @return The numerical environment associated with this context.
     */
    public HPLEnvironment<Double> getNumEnv(){
		return this.dEnv;
	}

    /**
     * Lookup a reference to a String
     * @param name The identifier of the String
     * @return The String associated with the given name in this context
     * @throws HPLException if the name is not bound to a String in this
     * context
     */
    public String getP(String name) throws HPLException{
		return this.pEnv.get(name);
	}

    /**
     * Store a binding for the given name to the given HPL function.
     * @param name The identifier of the binding
     * @param p The HPL function to be associated with the name
     */
    public void putF(String name, HPLFunction p){
		fnEnv.put(name, p);
	}

    /**
     * Store a binding for the given name to the given number.
     * @param name The identifier of the binding
     * @param n The numerical value to be associated with the name
     */
    public void putN(String name, Double n){
		dEnv.put(name, n);
	}

    /**
     * Store a binding for the given name to the given String.
     * @param name The identifier of the binding
     * @param p The String value to be associated with the name
     */
    public void putP(String name, String p){
		pEnv.put(name, p);
	}
    
}
