/**
 * 
 */
package systems;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This class allows saving and loading data stored on the disk.
 * Data is placed in their appropriate maps when loadDataFromStream is called.
 * Note that these maps are <i>static</i> variables, which means that they will change whenever
 * new data is loaded.  This means that you should NOT use this class as a data storage
 * variable like you do with NBT Types in MC.  Rather, you should copy out the data you need
 * to whatever object is calling for it.  This prevents information fragmentation between
 * class variables and saved data variables, and allows for cleaner code where all data for
 * an object is visible as a variable in that object.
 * 
 * Note that this class may be extended and replaced by mods that wish to modify the 
 * saving system.  This allows for other data types to easily be added to the system
 * class and saved/loaded from disk.  While it is not expected that this will be required,
 * it is possible nevertheless should you wish to do so.
 * 
 * @author don_bruce
 */
public class SystemDataSaveLoad{
	public static final Map<String, Boolean> booleanMap = new HashMap<String, Boolean>();
	public static final Map<String, Integer> integerMap = new HashMap<String, Integer>();
	public static final Map<String, Double> doubleMap = new HashMap<String, Double>();
	public static final Map<String, String> stringMap = new HashMap<String, String>();
	
	public static final Map<String, Boolean[]> booleanArrayMap = new HashMap<String, Boolean[]>();
	public static final Map<String, Integer[]> integerArrayMap = new HashMap<String, Integer[]>();
	public static final Map<String, Double[]> doubleArrayMap = new HashMap<String, Double[]>();
	public static final Map<String, String[]> stringArrayMap = new HashMap<String, String[]>();
	
	protected static final byte booleanDataCode = 0;
	protected static final byte integerDataCode = 1;
	protected static final byte doubleDataCode = 2;
	protected static final byte stringDataCode = 3;
	
	protected static final byte booleanArrayDataCode = 10;
	protected static final byte integerArrayDataCode = 11;
	protected static final byte doubleArrayDataCode = 12;
	protected static final byte stringArrayDataCode = 13;
	
	/**Loads data from the specified stream into the internal maps of this class.
	 * This is used to get data that was saved to disk and put it into classes.
	 * It is up to the class to copy over the data saved in the maps as they are volatile!
	 */
	public static void loadDataFromStream(DataInputStream inputStream) throws IOException{
		//Clear out maps.
		clearMaps();
		
		//Main stream iteration loop.
		try{
			for(byte dataCode = inputStream.readByte(); dataCode != -1; dataCode = inputStream.readByte()){
				//First get the String-based name for the data.
				String dataName = inputStream.readUTF();
				
				//Now we use the opcode to get the data.
				//0 is a boolean, 1 is an integer, 2 is a double, 3 is a string.
				//If the opcode is greater than 9, then that means we have an array.
				//Numbers 5-9 and 15-19 are reserved for future use or mod implementations.
				if(dataCode < 10){
					inputSingleData(dataCode, dataName, inputStream);
				}else{
					inputArrayData(dataCode, dataName, inputStream);
				}
			}
		}catch(EOFException e){
			throw new IOException("End of inputStream reached, but terminator of -1 was not detected.  Data may be corrupt!");
		}finally{
			inputStream.close();
		}
	}
	
	protected static void inputSingleData(byte dataCode, String dataName, DataInputStream inputStream) throws IOException{
		if(dataCode == booleanDataCode){
			booleanMap.put(dataName, inputStream.readBoolean());
		}else if(dataCode == integerDataCode){
			integerMap.put(dataName, inputStream.readInt());
		}else if(dataCode == doubleDataCode){
			doubleMap.put(dataName, inputStream.readDouble());
		}else if(dataCode == stringDataCode){
			stringMap.put(dataName, inputStream.readUTF());
		}else{
			throw new IOException("Invalid dataCode of " + dataCode + " detected when parsing data from storage!");
		}
	}
	
	protected static void inputArrayData(byte dataCode, String dataName, DataInputStream inputStream) throws IOException{
		//The first two bytes here define the array length.
		//Get that information first as we need to know how many elements we need to parse.
		//Afterwards we parse out those elements in a loop.
		short arrayLength = inputStream.readShort();
		if(dataCode == booleanArrayDataCode){
			Boolean[] booleanArray = new Boolean[arrayLength];
			for(short i=0; i<arrayLength; ++i){
				booleanArray[i] = inputStream.readBoolean();
			}
			booleanArrayMap.put(dataName, booleanArray);
		}else if(dataCode == integerArrayDataCode){
			Integer[] integerArray = new Integer[arrayLength];
			for(short i=0; i<arrayLength; ++i){
				integerArray[i] = inputStream.readInt();
			}
			integerArrayMap.put(dataName, integerArray);
		}else if(dataCode == doubleArrayDataCode){
			Double[] doubleArray = new Double[arrayLength];
			for(short i=0; i<arrayLength; ++i){
				doubleArray[i] = inputStream.readDouble();
			}
			doubleArrayMap.put(dataName, doubleArray);
		}else if(dataCode == stringArrayDataCode){
			String[] stringArray = new String[arrayLength];
			for(short i=0; i<arrayLength; ++i){
				stringArray[i] = inputStream.readUTF();
			}
			stringArrayMap.put(dataName, stringArray);
		}else{
			throw new IOException("Invalid dataCode of " + dataCode + " detected when parsing data from storage!");
		}
	}
	
	/**Writes whatever data is currently in the maps to the specified stream.
	 * This is done when saving data from classes.
	 */
	public static void saveDataToStream(DataOutputStream outputStream) throws IOException{
		outputSingleData(outputStream);
		outputArrayData(outputStream);
		//End of data terminator.
		outputStream.writeByte(-1);
		outputStream.close();
		clearMaps();
	}
	
	protected static void outputSingleData(DataOutputStream outputStream) throws IOException{
		for(Entry<String, Boolean> booleanMapEntry : booleanMap.entrySet()){
			outputStream.writeByte(booleanDataCode);
			outputStream.writeUTF(booleanMapEntry.getKey());
			outputStream.writeBoolean(booleanMapEntry.getValue());
		}
		for(Entry<String, Integer> integerMapEntry : integerMap.entrySet()){
			outputStream.writeByte(integerDataCode);
			outputStream.writeUTF(integerMapEntry.getKey());
			outputStream.writeInt(integerMapEntry.getValue());
		}
		for(Entry<String, Double> doubleMapEntry : doubleMap.entrySet()){
			outputStream.writeByte(doubleDataCode);
			outputStream.writeUTF(doubleMapEntry.getKey());
			outputStream.writeDouble(doubleMapEntry.getValue());
		}
		for(Entry<String, String> stringMapEntry : stringMap.entrySet()){
			outputStream.writeByte(stringDataCode);
			outputStream.writeUTF(stringMapEntry.getKey());
			outputStream.writeUTF(stringMapEntry.getValue());
		}
	}
	
	protected static void outputArrayData(DataOutputStream outputStream) throws IOException{
		for(Entry<String, Boolean[]> booleanArrayMapEntry : booleanArrayMap.entrySet()){
			outputStream.writeByte(booleanArrayDataCode);
			outputStream.writeUTF(booleanArrayMapEntry.getKey());
			for(Boolean data : booleanArrayMapEntry.getValue()){
				outputStream.writeBoolean(data);
			}
		}
		for(Entry<String, Integer[]> integerArrayMapEntry : integerArrayMap.entrySet()){
			outputStream.writeByte(integerArrayDataCode);
			outputStream.writeUTF(integerArrayMapEntry.getKey());
			for(Integer data : integerArrayMapEntry.getValue()){
				outputStream.writeInt(data);
			}
		}
		for(Entry<String, Double[]> doubleArrayMapEntry : doubleArrayMap.entrySet()){
			outputStream.writeByte(doubleArrayDataCode);
			outputStream.writeUTF(doubleArrayMapEntry.getKey());
			for(Double data : doubleArrayMapEntry.getValue()){
				outputStream.writeDouble(data);
			}
		}
		for(Entry<String, String[]> stringArrayMapEntry : stringArrayMap.entrySet()){
			outputStream.writeByte(stringArrayDataCode);
			outputStream.writeUTF(stringArrayMapEntry.getKey());
			for(String data : stringArrayMapEntry.getValue()){
				outputStream.writeUTF(data);
			}
		}
	}
	
	protected static void clearMaps(){
		booleanMap.clear();
		integerMap.clear();
		doubleMap.clear();
		stringMap.clear();
		booleanArrayMap.clear();
		integerArrayMap.clear();
		doubleArrayMap.clear();
		stringArrayMap.clear();
	}
}
