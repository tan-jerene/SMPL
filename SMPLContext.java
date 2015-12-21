/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 *
 * @author ThreeMusketeers
 */
public interface SMPLContext {
	/**
	 * Constructor?
	 */

    /**
     * Create a new context in which the function environment is extended with
     * new bindings.
     * @param fParams The names to be bound in the new function frame.
     * @param args The corresponding values for the names
     * @return A newly created context containing the new function environment,
     * but leaving all the other components of the context unchanged.
     */
    public SMPLContext extendF(ArrayList<String> fParams, ArrayList<SMPLFunction> args);

    /**
     * Create a new context in which the numerical environment is extended with
     * new bindings.
     * @param nParams The names to be bound in the new numerical environment
     * frame.
     * @param vals The corresponding values for the names
     * @return A newly created context containing the new numerical environment,
     * but leaving all the other components of the context unchanged.
     */
    public SMPLContext extendN(ArrayList<String> nParams, ArrayList<Double> vals);

    /**
     * Create a new context in which the painter environment is extended with
     * new bindings.
     * @param pParams The names to be bound in the new painter environment frame.
     * @param args The corresponding values for the names
     * @return A newly created context containing the new painter environment,
     * but leaving all the other components of the context unchanged.
     */
    public SMPLContext extendS(ArrayList<String> pParams, ArrayList<String> args);
    public SMPLContext extendB(ArrayList<String> pParams, ArrayList<Boolean> args);

    /**
     * Lookup a reference to a SMPL function.
     * @param name The identifier of the SMPL function
     * @return The SMPL function associated with the given name in this context
     * @throws SMPLException if the name is not bound to a painter in this
     * context
     */
    public SMPLFunction getF(String name) throws SMPLException;

    /**
     *
     * @return The (resultant) frame associated with this context.
     */
    //public PainterFrame getFrame();

    /**
     * Lookup a reference to a number
     * @param name The identifier of the Double
     * @return The number associated with the given name in this context
     * @throws SMPLException if the name is not bound to a number in this
     * context
     */
    public Double getN(String name) throws SMPLException;

    /**
     *
     * @return The numerical environment associated with this context.
     */
    public SMPLEnvironment<Double> getNumEnv();

    /**
     * Lookup a reference to a painter
     * @param name The identifier of the painter
     * @return The painter associated with the given name in this context
     * @throws SMPLException if the name is not bound to a painter in this
     * context
     */
    public String getP(String name) throws SMPLException;

    /**
     * Store a binding for the given name to the given SMPL function.
     * @param name The identifier of the binding
     * @param p The SMPL function to be associated with the name
     */
    public void putF(String name, SMPLFunction p);

    /**
     * Store a binding for the given name to the given number.
     * @param name The identifier of the binding
     * @param n The numerical value to be associated with the name
     */
    public void putN(String name, Double n);

    /**
     * Store a binding for the given name to the given painter.
     * @param name The identifier of the binding
     * @param p The painter value to be associated with the name
     */
    public void putP(String name, Painter p);
    
}
