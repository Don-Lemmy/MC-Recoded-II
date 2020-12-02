package rendering.interfaces;

/**
* This class should be implemented on anything that is a block.  Be it an
* decorative blocks, crafting blocks, entity spawning blocks, storage blocks, etc.
*
* @author Lemmy Von Disturbed
*/
public interface IBlockProperty{

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
}
