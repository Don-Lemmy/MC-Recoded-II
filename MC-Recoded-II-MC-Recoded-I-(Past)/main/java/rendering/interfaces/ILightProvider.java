package rendering.interfaces;

/**
* This class should be implemented on anything that provides light.  Be it an
* item, block, entity, etc.  The rendering system looks for classes that implement 
* this when doing light calculations rather than checking everything, as it is un-needed 
* in the case of most blocks/items.
* <br><br>
* For fluid-based light, this class <i>must</i> be implemented on the block level, and may 
* <i>optionally</i> be implemented on fluid level.  The block level is required, as the 
* rendering system needs to know to check the block for light.  However, the block may use
* an internal light check for the fluid, or it may check the fluid itself to see if it
* implements this interface.  The second method is used in the {@link blocks.BlockFluidLit} class 
* that is used with lava, so use that class if you need a quick way to have light-providing fluids.  
*
* @author don_bruce
*/
public interface ILightProvider{
	
	/**Returns the texture location for the rendering system to use.**/
	public String getTextureLocation();
	
	/**If this texture should be animated, set this to true.
	 * This will cause it to go to a special render queue and section
	 * that will better optimize the texture for the animated rendering
	 * sequence.
	 **/
	public default boolean isAnimated(){
		return false;
	}
	
	/**Gets the hexadecimal color for this light.
	 * This will apply color to the light mask for colored lighting.
	 **/
	public int getLightColor();
	
	
	/**Gets the current light level for this light.
	 * 0 is no light 15 is full light.  This level
	 * is used by the rendering system to offset the
	 * lighting mask texture, hence the 4-bit range.
	 **/
	public int getLightLevel();
}
