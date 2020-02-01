package registries;

import components.Energy;

public class RegistryEnergy extends ARegistry<Energy>{

	@Override
	public String getType(){
		return "energy";
	}
	
	public Energy registerEnergy(Energy energyToRegister){
		return super.registerObject(energyToRegister);
	}
	
	public boolean removeEnergy(String energyName){
		return super.removeObject(energyName);
	}
	
	public Energy getEnergy(String energyName){
		return super.getObject(energyName);
	}
}
