 package blocks;

import rendering.interfaces.ITextureable;

/**
 * This class is used to add new blocks to the game.
 * All blocks should use or extend this class, and should be registered in
 * {@link registries.RegistryBlock}. The class is structured to allow
 * for constructor-based instantiation to prevent the need to have
 * individual block classes for new blocks.
 * <br><br>
 * Note that this block class is designed to be generic and work with
 * all blocks,
 *
 * @author Lemmy Regis Hellfire Von Disturbed
 */
public class Block implements ITextureable{
	/**Name of this block. Used for registration and texture location.**/
	public final String blockName;
	/**Texture location for this block.**/
	public final String textureLocation;
	/**Full Block for culling operations**/
	public final int defaultFullBlock;
	/**Hardness for breaking**/
	public final int defaultHardness;
	/**Opacity for lighting**/
	public final float defaultOpacity;
	/**Need Tool used if a tool is needed to break a block**/
	public final float defaultNeedTool;
	
	public block(String blockName, String textureLocation, int defaultFullBlock, int defaultHardness, float defaultOpacity, float defaultNeedTool){
		this.blockName = blockName;
		this.textureLocation = textureLocation;
		this.defaultFullBlock = defaultFullBlock;
		this.defaultHardness = defaultHardness;
		this.defaultOpacity = defaultOpacity;
		this.defaultNeedTool = defaultNeedTool;
	}
	
	@Override
	public String getTextureLocation(){
		return textureLocation;
	}
	
	@Override
	public boolean isAnimated(){
		//We override this here as it is assumed all blocks will be animated.
		return true;
	}
}