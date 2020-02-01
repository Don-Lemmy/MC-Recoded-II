/**
 * 
 */
package capabilities;

import components.Fluid;
import emums.EnumSide;

/**
 * This class should be applied to any objects needing to contain fluids.
 * They can be blocks, items, entities, whatever.  They all use the same interface.
 * Important things to note is that the fluid contained in this capability is saved
 * within the object that is implementing it, so changes to that object can affect
 * or remove the fluid in said object.  Note that this capability is for objects 
 * <i>containing<i/> fluids, not objects that <i>are</i> fluids.  That
 * distinction belongs to {@link BlockFluid}, which ironically
 * does not have this capability.
 *
 * @author don_bruce
 */
public class CapabilityFluidContainer{
	private Fluid fluid;
	private int fluidLevel;
	private final int maxFluidLevel;
	private final int inputRate;
	private final int outputRate;
	/**An array of valid fluids for this container, or null if it can hold any fluids**/
	private final Fluid[] validFluids;
	
	public CapabilityFluidContainer(int maxFluidLevel, int inputRate, int outputRate, Fluid[] validFluids){
		this.maxFluidLevel = maxFluidLevel;
		this.inputRate = inputRate;
		this.outputRate = outputRate;
		this.validFluids = validFluids;
	}
	
	/**Gets the current fluid in the container.*/
	public Fluid getFluid(){
		return fluid;
	}
	
	/**Sets the fluid in the container to the passed-in fluid.
	 * Only works if this container is empty and this container
	 * can hold the passed-in fluid.
	 */
	public void setFluid(Fluid newFluid){
		if(newFluid == null){
			fluid = null;
		}else{
			if(fluidLevel == 0 || fluid == null){
				if(validFluids != null){
					for(Fluid validFluid : validFluids){
						if(validFluid.equals(newFluid)){
							fluid = newFluid;
						}
					}
				}else{
					fluid = newFluid;
				}
			}
		}
	}
	
	/**Gets the current fluid temp.  Defaults to the data provided by the fluid itself,
	 * but can be changed in other container implementations if so desired.*/
	public int getFluidTemp(){
		return getFluid().defaultTemp;
	}
	
	/**Gets the current fluid level of the container.*/
	public int getCurrentFluidLevel(){
		return fluidLevel;
	}
	
	/**Gets the max possible fluid level of the container.*/
	public int getMaxFluidLevel(){
		return maxFluidLevel;
	}
	
	/**
	 * Rate of fluid input, in mb/tick.
	 * The default implementation of this is to allow
	 * any fluid to input on any side, however this method is
	 * designed to allow for side-sensitive responses.
	 **/
	public int getFluidInputRate(EnumSide side){
		return inputRate;
	}
	
	/**
	 * Rate of fluid output, in mb/tick.
	 * The default implementation of this is to allow
	 * any fluid to output on any side, however this method is
	 * designed to allow for side-sensitive responses.
	 **/
	public int getFluidOutputRate(EnumSide side){
		return outputRate;
	}
	
	/**
	 * Fills container with fluid by the amount.  Default
	 * implementation is to add fluid to container and return
	 * the total amount added.  This method should check
	 * to make sure the fluid given can go in the container.
	 **/	
	public int fillContainer(Fluid fluidToFill, int amountToFill){
		if(getFluid() == null){
			setFluid(fluidToFill);
			if(getFluid() == null){
				//We can't store this fluid here;
				return 0;
			}
		}
		if(fluidToFill.equals(getFluid())){
			if(getCurrentFluidLevel() + amountToFill > getMaxFluidLevel()){
				amountToFill = getMaxFluidLevel() - getCurrentFluidLevel();
			}
			this.fluidLevel += amountToFill;
			return amountToFill;
		}else{
			//Fluid is not what we are storing.
			return 0;
		}
	}
	
	/**
	 * Drains the container of the fluid by the amount.  Default
	 * implementation is to drain fluid and return the total amount
	 * drained.  This method should check to make sure the fluid
	 * requested to be drained is present and can be drained before
	 * attempting to drain it.  It should also set the current fluid
	 * to null if the level hits 0 during this operation.
	 **/	
	public int drainContainer(Fluid fluidToDrain, int amountToDrain){
		if(fluidToDrain.equals(getFluid())){
			if(amountToDrain > getCurrentFluidLevel()){
				amountToDrain = getCurrentFluidLevel();
			}
			fluidLevel -= amountToDrain;
			if(fluidLevel == 0){
				setFluid(null);
			}
			return amountToDrain;
		}else{
			//Fluid requested to drain is not what we have.
			return 0;
		}
	}
}
