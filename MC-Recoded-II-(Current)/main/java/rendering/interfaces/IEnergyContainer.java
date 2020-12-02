package rendering.interfaces;

/**
* This class should be implemented on anything that stores energy.  Be it an
* battery, capacitor , energy cell, etc.
*
* @author Lemmy Von Disturbed
*/
public interface IEnergyContainer{

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

    /**This tells someone how full it is compared to the max allowed energy level,
     * it's affected by the max energy amount
     **/
    public int getCurrentEnergyLevel();


    /**Gets the max safe energy level thus
     * affecting how the current energy level is displayed
     **/
    public int getMaxEnergyLevel();
}
