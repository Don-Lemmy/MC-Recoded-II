package registries;

import components.Fluid;

public class RegistryFluid extends ARegistry<Fluid>{

	@Override
	public String getType(){
		return "fluid";
	}
	
	public Fluid registerFluid(Fluid fluidToRegister){
		return super.registerObject(fluidToRegister);
	}
	
	public boolean removeFluid(String fluidName){
		return super.removeObject(fluidName);
	}
	
	public Fluid getFluid(String fluidName){
		return super.getObject(fluidName);
	}
}
