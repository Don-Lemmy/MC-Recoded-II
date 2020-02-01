package emums;

/**
* This enum is used to reference texture sizes for the game.
* To ensure optimal GPU performance, texture sizes are restricted
* to powers of 2 on the length and width, as well as a 1:1 aspect ratio.
* Additionally, they are limited to a max size of 1024x1024, to ensure
* optimal performance.  These enums are generally used when creating the
* texture maps in the GPU to help with stitching, but they may be used
* in other locations if desired.
*
* @author don_bruce
*/
public enum EnumTextureSize{
	SMALL(8), REGULAR(16), LARGE(32), XLARGE(64), DOUBLE_XLARGE(128), HD(512), SUPER_HD(1024); 

	public final int size;
	
	private EnumTextureSize(int size){
		this.size = size;
	}
}
