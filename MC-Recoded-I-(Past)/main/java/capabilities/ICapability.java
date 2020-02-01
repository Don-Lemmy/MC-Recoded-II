/**
 * 
 */
package capabilities;

import systems.SystemDataSaveLoad;

/**
 * Capabilities are classes that can be added to other classes to extend their functionality.
 * They essentially make that class <i>capable</i> of doing a specific thing.
 * All core classes have the ability to have any number of capabilities, which may be added or removed
 * whenever you see fit.  What each capability does is up to the creator, but the one thing
 * they all must do is implement this interface.  This is done to allow a save/load call
 * to the capability class to save the data present in it.  Note that this means the data
 * for the capability is stored <i>inside</i> the capability itself, and not the class that
 * implements that capability.  This is important to note as any capability that needs to load data
 * from disk must be present <i>before</i> the class is loaded from disk or it will miss out on the
 * saved data retrieved from disk!
 *
 * @author don_bruce
 */
public interface ICapability{
	/**Gets the name of this capability.  Must be unique!  Also should represent what this capability does.*/
	public String getName();
	
	/**Called during saving operations to allow this capability to save its data.
	 *Data should be copied into the maps of {@link SystemDataSaveLoad} during this method.
	 *Note that data from the object that has this capability will be present in the maps
	 *at this point, and modifications to that data will be reflected in the data that is saved. 
	 **/
	public void saveData();
	
	/**Called whenever the object that has this capability is loaded from save data.
	 * Data should be copied from the maps of {@link SystemDataSaveLoad} into local variables of this
	 * capability.  Note that while the data of the object that has this capability is present in the
	 * maps at this time, it has already been copied over to the object, so modification of that data
	 * will NOT affect the object.
	 */
	public void loadData(SystemDataSaveLoad data);
}
