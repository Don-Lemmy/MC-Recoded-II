package registries;

import java.util.HashMap;
import java.util.Map;

import systems.SystemRegistryManager;

/** 
 * This class is a registry that is responsible for keeping track of things.
 * All classes that need to be displayed in-game should either call a
 * built-in registry that is constructed from this class, or should
 * extend this class and register to that.
 * Registered data is used to save/load worlds, so un-registered properties
 * on things will NOT persist.  It may also cause crashes in some systems,
 * should those systems expect something to be registered that is not.
 * Note that all registered things are fully-constructed classes, not
 * the class itself.
 *
 * @author don_bruce
 */
public abstract class ARegistry<RegistryObject extends IRegistryObject>{
	private final Map<String, RegistryObject> registryMap = new HashMap<String, RegistryObject>();

	public ARegistry(){
		SystemRegistryManager.addRegistry(this);
	}
	
	/**
	 * Gets the name for this registry.  This name is used to correlate saved data with the reigstry that
	 * data goes to, and therefore must be unique to that data type!
	 */	
	public abstract String getType();
	
	/**
	 * Registers an object with this registry.  Normally, this works just fine.  However, should an
	 * object exist with the ID already present, the object will fail to register and the object with
	 * that ID will be returned instead.  This may be used by the system attempting to register this
	 * object to handle how to proceed.  For example: The system could remove the existing object from
	 * the registry and substitute its own, or it could modify flags in itself to allow for integration
	 * with the object registered.  This does allow for potential issues, however it's a much more flexible
	 * system than just forcing the registration to halt.
	 * @throws RegistrationException 
	 */
	protected RegistryObject registerObject(RegistryObject objectToRegister){
		String objectName = objectToRegister.getName();
		if(objectName != null){
			if(!registryMap.containsKey(objectName)){
				registryMap.put(objectName, objectToRegister);
				return null;
			}else{
				return registryMap.get(objectName);
			}
		}else{
			return null;
		}
	}
	
	/**
	 * Removes an object from the registry.  Returns true if the object was removed,
	 * false if it was not (if it didn't exist in the registry to begin with).
	 * This can be used by mods to force their objects to be registered over other mods.
	 * This works, because the other mod's data will not persist as it is no longer registered.
	 * When objects are spawned into the world, they use the registry to get the correct class.
	 * This means that the removal of a registry entry will PREVENT the object from ever appearing
	 * in-world.  Use this with caution!
	 */	
	protected boolean removeObject(String objectID){
		if(registryMap.containsKey(objectID)){
			registryMap.remove(objectID);
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Gets an object from the registry, or null if it does not exist.
	 * This should be used by all code attempting to add things to the world
	 * (either by placement or loading).  Failure to use this may result in
	 * un-registred things appearing in the world, which is very bad!
	 */	
	protected RegistryObject getObject(String objectID){
		return registryMap.get(objectID);
	}
}
