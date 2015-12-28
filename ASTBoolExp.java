public class ASTBoolExp<E extends ASTExp<E>> extends ASTExp<E>{

	boolean b;

	public ASTBoolExp(String b){
		if (b.equals("true")) {
			this.b = true;
		} 
		else{

			this.b = false;
		}
	}

	public boolean getb(){
		return b;


	}
}