package registries;

import items.Item;

public class RegistryItem extends ARegistry<Item>{
	
	public Item registerItem(Item itemToRegister){
		return super.registerObject(itemToRegister, itemToRegister.itemName);
	}
	
	public boolean removeItem(String itemName){
		return super.removeObject(itemName);
	}
	
	public Item getItem(String itemName){
		return super.getObject(itemName);
	}
}
