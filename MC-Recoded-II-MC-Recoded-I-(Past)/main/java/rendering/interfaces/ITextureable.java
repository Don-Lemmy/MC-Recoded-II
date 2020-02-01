package rendering.interfaces;

/**
* This class should be implemented on anything that has a texture that needs
* to be bound into the GPU.  Actual texture binding happens later, and only
* references this class for registered objects.
*
* @author don_bruce
*/
public interface ITextureable{
	
	/**Returns the texture location for the rendering system to use.**/
	public String getTextureLocation();
	
	/**If this texture should be animated, set this to true.
	 * This will cause it to go to a special render queue and section
	 * that will better optimize the texture for the animated rendering
	 * sequence.
	 * **/
	public default boolean isAnimated(){
		return false;
	}
}
