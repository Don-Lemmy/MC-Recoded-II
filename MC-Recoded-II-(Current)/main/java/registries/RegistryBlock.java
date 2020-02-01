package registries;

import Blocks.Block;

public class RegistryBlock extends ARegistry<Block>{
	
	public Block registerBlock(Block BlockToRegister){
		return super.registerObject(BlockToRegister, BlockToRegister.blockName);
	}
	
	public boolean removeBlock(String blockName){
		return super.removeObject(blockNameName);
	}
	
	public Block getBlock(String BlockName){
		return super.getObject(blockName);
	}
}
