package emums;

/**
* This enum is used to reference direction a block faces.
* This is done to allow for better representation of direction internally, rather
* than using integers or strings.  Convenience methods here can be used to get 
* direction given a direction.  Note that this is different from {@link EnumSide}.
* That enum is used for the side of something, not the direction it is facing.
*
* @author don_bruce
*/
public enum EnumDirection{
	UP, DOWN, SOUTH, NORTH, EAST, WEST;
	
	private EnumDirection(){}

	public EnumDirection getOpposite(){
		switch(this){
			case UP: return DOWN;
			case DOWN: return UP;
			case SOUTH: return NORTH;
			case NORTH: return SOUTH;
			case EAST: return WEST;
			case WEST: return EAST;
			default: return null;
		}
	}
}
