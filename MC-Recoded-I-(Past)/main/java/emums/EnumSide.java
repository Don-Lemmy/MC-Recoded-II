package emums;

/**
* This enum is used to reference sides of blocks.
* This is done to allow for better representation of sides internally, rather
* than using integers or strings.  Convenience methods here can be used to get 
* sides given a side.  Note that this is different from {@link EnumDirection}.
* That enum is used for the direction something is facing, not the side of the 
* thing.
*
* @author don_bruce
*/
public enum EnumSide{
	TOP, BOTTOM, FRONT, BACK, LEFT, RIGHT;
	
	private EnumSide(){}

	public EnumSide getOpposite(){
		switch(this){
			case TOP: return BOTTOM;
			case BOTTOM: return TOP;
			case FRONT: return BACK;
			case BACK: return FRONT;
			case LEFT: return RIGHT;
			case RIGHT: return LEFT;
			default: return null;
		}
	}
}
