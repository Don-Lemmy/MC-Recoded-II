package items;

import rendering.interfaces.ITextureable;

/**
 * This class is used to add new items to the game.
 * All items should use or extend this class, and should be registered in
 * {@link registries.Registryitem}.  The class is structured to allow
 * for constructor-based instantiation to prevent the need to have
 * individual item classes for new items.
 * <br><br>
 * Note that this item class is designed to be generic and work with
 * all items,
 *
 * @author Lemmy Regis Hellfire Von Disturbed
 */
public class Item implements ITextureable{
	/**Name of this item.  Used for registration and texture location.**/
	public final String itemName;
	/**Texture location for this item.**/
	public final String textureLocation;
	/**Full Block for culling operations**/
	public final int defaultUseAction;
	/**HasHealth for breaking**/
	public final int defaultHasHealth;
	/**ExtraProp for lighting**/
	public final float defaultExtraProp;
	/**Need Tool used if a tool is needed to break a item**/
	public final float defaultGetHealth;
	
	public item(String itemName, String textureLocation, int defaultUseAction, int defaultHasHealth, float defaultExtraProp, float defaultGetHealth){
		this.itemName = itemName;
		this.textureLocation = textureLocation;
		this.defaultUseAction = defaultUseAction;
		this.defaultHasHealth = defaultHasHealth;
		this.defaultExtraProp = defaultExtraProp;
		this.defaultGetHealth = defaultGetHealth;
	}
	
	@Override
	public String getTextureLocation(){
		return textureLocation;
	}
	
	@Override
	public boolean isAnimated(){
		//We override this here as it is assumed all items will not be animated.
		return false;
	}
}