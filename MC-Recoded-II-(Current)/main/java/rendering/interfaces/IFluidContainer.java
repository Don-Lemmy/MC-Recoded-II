package rendering.interfaces;

/**
* This class should be implemented on anything that stores fluid.  Be it an
* barrel, silo, tank, etc.
*
* @author Lemmy Von Disturbed
*/
public interface IFluidContainer{

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

    /**This tells someone how full it is compared to the max fluid level,
     * it's affected by the max fluid amount
     **/
    public int getCurrentFluidLevel();


    /**Gets the max fluid level thus
     * affecting how the current fluid level is displayed
     **/
    public int getMaxFluidLevel();
}
