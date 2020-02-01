/**
 * 
 */
package systems;

import java.util.ArrayList;
import java.util.List;

import registries.ARegistry;
import registries.IRegistryObject;
import registries.RegistryEnergy;
import registries.RegistryFluid;

/**
 * This class manages all instances of the registries in-game.  It is responsible for
 * creating all registries fed into it during startup, and handling all requests for
 * information to and from those registries.  While the default registries for the core
 * game are created and stored here, you are free to store your own registries wherever
 * you wish.  This system will link back to them when called upon, so no more trying
 * to find registries; just come here and ask for them.
 * 
 * @author don_bruce
 */
public class SystemRegistryManager{
	private static final List<ARegistry<? extends IRegistryObject>> registries = new ArrayList<ARegistry<? extends IRegistryObject>>();
	public static final RegistryEnergy energyRegistry = new RegistryEnergy();
	public static final RegistryFluid fluidRegistry = new RegistryFluid();
	
	/**Adds a registry to the RegistryManager.  This should be done before any registering operations are performed.
	 * Returns the passed-in registry for construction convenience.
	 * */
	public static <RegistryObject extends IRegistryObject> ARegistry<RegistryObject> addRegistry(ARegistry<RegistryObject> registryToAdd){
		registries.add(registryToAdd);
		return registryToAdd;
	}
	
	/**Gets the registry matching the passed-in type, or null if no such registry exists.*/
	public static ARegistry<? extends IRegistryObject> getRegistryByType(String registryType){
		for(ARegistry<? extends IRegistryObject> registry : registries){
			if(registry.getType().equals(registryType)){
				return registry;
			}
		}
		return null;
	}
}
