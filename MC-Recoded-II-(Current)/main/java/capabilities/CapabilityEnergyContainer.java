/**
 * 
 */
package capabilities;

import components.Energy;
import emums.EnumSide;
import systems.SystemDataSaveLoad;
import systems.SystemRegistryManager;

/**
 * This class should be applied to any objects needing to contain energy.
 * They can be blocks, items, whatever.  They all use the same interface.
 * Important things to note is that the energy contained in this interface is saved
 * within the object that is implementing it, so changes to that object can affect
 * or remove the energy in said object.  Note that this container's energy type
 * is pre-determined at compile time as it is expected that containers will be able
 * to only hold one type of energy.  This may be modified if so desired by extending
 * this class.
 *
 * @author don_bruce
 */
public class CapabilityEnergyContainer implements ICapability{
	private Energy energy;
	private int energyLevel;
	private final int maxEnergyLevel;
	private final int inputRate;
	private final int outputRate;
	
	public CapabilityEnergyContainer(int maxEnergyLevel, int inputRate, int outputRate, Energy energy){
		this.maxEnergyLevel = maxEnergyLevel;
		this.inputRate = inputRate;
		this.outputRate = outputRate;
		this.energy = energy;
	}
	
	/**Gets the energy type this container can store.*/
	public Energy getEnergy(){
		return energy;
	}
	
	/**Gets the current energy level of the container.*/
	public int getCurrentEnergyLevel(){
		return energyLevel;
	}
	
	/**Gets the max possible energy level of the container.*/
	public int getMaxEnergyLevel(){
		return maxEnergyLevel;
	}
	
	/**
	 * Rate of energy input, in mj/tick.
	 * The default implementation of this is to allow
	 * any energy to input on any side, however this method is
	 * designed to allow for side-sensitive responses.
	 **/
	public int getEnergyInputRate(EnumSide side){
		return inputRate;
	}
	
	/**
	 * Rate of energy output, in mj/tick.
	 * The default implementation of this is to allow
	 * any energy to output on any side, however this method is
	 * designed to allow for side-sensitive responses.
	 **/
	public int getEnergyOutputRate(EnumSide side){
		return outputRate;
	}
	
	/**
	 * Fills container with energy by the amount.  Default
	 * implementation is to add energy to container and return
	 * the total amount added.  This method should check
	 * to make sure the energy given can go in the container.
	 **/	
	public int fillContainer(Energy energyToFill, int amountToFill){
		if(energyToFill.equals(getEnergy())){
			if(getCurrentEnergyLevel() + amountToFill > getMaxEnergyLevel()){
				amountToFill = getMaxEnergyLevel() - getCurrentEnergyLevel();
			}
			this.energyLevel += amountToFill;
			return amountToFill;
		}else{
			//Energy is not what we are storing.
			return 0;
		}
	}
	
	/**
	 * Drains the container of the energy by the amount.  Default
	 * implementation is to drain energy and return the total amount
	 * drained.  This method should check to make sure the energy
	 * requested to be drained is present and can be drained before
	 * attempting to drain it.
	 **/	
	public int drainContainer(Energy energyToDrain, int amountToDrain){
		if(energyToDrain.equals(getEnergy())){
			if(amountToDrain > getCurrentEnergyLevel()){
				amountToDrain = getCurrentEnergyLevel();
			}
			energyLevel -= amountToDrain;
			return amountToDrain;
		}else{
			//Energy requested to drain is not what we have.
			return 0;
		}
	}

	@Override
	public String getName(){
		return "ENERGY_CONTAINER";
	}

	@Override
	public void saveData(){
		SystemDataSaveLoad.stringMap.put("energy", energy.energyName);
		SystemDataSaveLoad.integerMap.put("energyLevel", energyLevel);
	}

	@Override
	public void loadData(SystemDataSaveLoad data){
		energy = SystemRegistryManager.energyRegistry.getEnergy(SystemDataSaveLoad.stringMap.get("energy"));
		energyLevel = SystemDataSaveLoad.integerMap.get("energyLevel");
	}
}
