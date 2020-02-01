package components;

import registries.IRegistryObject;

/**
 * This class is used to add new energy types to the game.
 * All energy types should use or extend this class, and should be registered in
 * {@link registries.RegistryEnergy}.  The class is structured to allow
 * for constructor-based instantiation to prevent the need to have
 * individual energy classes for new energy types.
 * <br><br>
 * Note that this energy class is quite basic, as energy types don't really have
 * much difference between them except potency and what containers they can go into.
 * The latter being the responsibility of Capabilities to store and transmit the energy
 * and define properties like transmission speed and capacity.
 * 
 * @author don_bruce
 */
public class Energy implements IRegistryObject{
	/**Name of this energy type, Used for registration.**/
	public final String energyName;
	/**How strong a energy type is**/
	public final int defaultPotency;
	
	public Energy(String energyName, int defaultMaxRate, int defaultPotency){
		this.energyName = energyName;
		this.defaultPotency = defaultPotency;
	}
	
	@Override
	public String getName(){
		return energyName;
	}
}
