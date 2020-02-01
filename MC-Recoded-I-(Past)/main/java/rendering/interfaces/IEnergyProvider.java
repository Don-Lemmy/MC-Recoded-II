package rendering.interfaces;

/**
* This class should be implemented on anything that provides energy.  Be it an
* generator, nuclear reactor, , etc.
*
* @author don_bruce
*/
public interface IEnergyProvider{

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

    /**Gets the rate of energy transfer
     * This dictates how much energy can safely pass a wire without causing a fire and a blackout
     **/
    public int getMaxRate();


    /**Gets how strong an energy type is
     * is it likely to harm you or not if in contact of
     * some uninsulated wire(s)
     **/
    public int getPotency();
}
