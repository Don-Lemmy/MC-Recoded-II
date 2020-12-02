package rendering.interfaces;

/**
* This class should be implemented on anything that provides a connection from a energy source to a enerrgy battery
* and/or to an object that uses energy. Be an single cable, bundle of cables, etc.
*
* @author Lemmy Regis Hellfire Von Disturbed
*/
public interface IEnergyConduit{

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
