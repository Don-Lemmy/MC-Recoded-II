package components;

import registries.IRegistryObject;
import rendering.interfaces.ITextureable;

/**
 * This class is used to add new fluids to the game.
 * All fluids should use or extend this class, and should be registered in
 * {@link registries.RegistryFluid}.  The class is structured to allow
 * for constructor-based instantiation to prevent the need to have
 * individual fluid classes for new fluids.
 * <br><br>
 * Note that this fluid class is designed to be generic and work with
 * both liquids and gases.  It also has an internal temp variable,
 *  which is designed to the the average temp of this fluid.  These two variables
 * can be used in conjunction with container logic to decide what this fluid
 * can and cannot go into.
 * <br><br>
 * Also note that the name given to this fluid is what is used during
 * construction.  This means that this fluid should NOT have a specific name
 * as it could allow for duplicate fluids to be registered which could create
 * an incompatibility.  For example, registering "oil" and "crude_oil" is a
 * bad idea as both are likely the same with respect to mod functions.
 * <br><br>
 * Finally, note that the values given here are DEFAULTS.  This is done to provide
 * a baseline stat for mod compatibility, with the realization that the fluid may
 * change properties when in block/item/contained form.  In this case, the the fluid
 * properties will be stored by the other object to affect its behavior, as the state
 * of the fluid is based on the interaction with the object, not the fluid itself.
 *
 * @author don_bruce
 */
public class Fluid implements IRegistryObject, ITextureable{
	/**Name of this fluid.  Used for registration and texture location.**/
	public final String fluidName;
	/**Texture location for this fluid.**/
	public final String textureLocation;
	/**Temp of this fluid, in degrees celsius.**/
	public final int defaultTemp;
	/**How many ticks it takes for one unit of this fluid to evaporate.  -1 means it cannot do so.**/
	public final int defaultEvaporationRate;
	/**How thick the fluid is.  A value of 0.0 will treat this fluid as water, 1.0 will treat it as solid rock.**/
	public final float defaultViscosity;
	/**How buoyant the fluid is.  A value of 0.0 will make players sink as if the fluid wasn't there, 1.0 will make them float on the top.**/
	public final float defaultBuoyancy;
	
	public Fluid(String fluidName, String textureLocation, int defaultTemp, int defaultEvaporationRate, float defaultViscosity, float defaultBuoyancy){
		this.fluidName = fluidName;
		this.textureLocation = textureLocation;
		this.defaultTemp = defaultTemp;
		this.defaultEvaporationRate = defaultEvaporationRate;
		this.defaultViscosity = defaultViscosity;
		this.defaultBuoyancy = defaultBuoyancy;
	}
	
	@Override
	public String getName(){
		return fluidName;
	}
	
	@Override
	public String getTextureLocation(){
		return textureLocation;
	}
	
	@Override
	public boolean isAnimated(){
		//We override this here as it is assumed all fluids will be animated.
		return true;
	}
}
