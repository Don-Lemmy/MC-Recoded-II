package registries;

/** 
 * This interface must be implemented on the base type of anything that is to
 * be registered in-game.  This allows the save/load system to identify which
 * object a specific name is registered to, and allows it to properly assign
 * data types to that object.  It also ensures that non-registerable things
 * do not end up in the registries by mistake.
 *
 * @author don_bruce
 */
public interface IRegistryObject{	
	
	/**Gets the name of this registered object.  Must be unique!*/
	public String getName();
}
