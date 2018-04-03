
public class ControlDevices extends Thread{
	
	// This class runs a thread which checks if the devices are on or off 
	// and returns their state
	
	private GreenhouseEnvironment climate;
	private String updatedDeviceFurnace;
	private String updatedDeviceAirCond;
	private String updatedDeviceHumidifier;
	private String updatedDeviceSprinkler;
	
	private int interval; 
	
	public ControlDevices(GreenhouseEnvironment climate,  int interval){ 
		
		this.climate = climate;
		this.interval = interval;		
	}
	
	public String getFurnaceState(){
		return updatedDeviceFurnace;
	}
	
	public String getAirCondState(){
		return updatedDeviceAirCond;
	}
	
	public String getHumidifierState(){
		return updatedDeviceHumidifier;
	}
	
	public String getSprinklerState(){
		return updatedDeviceSprinkler;
	}

	public void run(){
		
		while (true){
		
		// the logic of this formula is to keep the things within the use specified range
			
			double degree = climate.getTemp();
			double humid  = climate.getHumid();
			double moist  = climate.getMoist();
			
			
			if (degree < (climate.getTempRang() - 3))
				updatedDeviceFurnace = "Furnace: ON";
			else if (degree > (climate.getTempRang() + 3))
				updatedDeviceFurnace = "Furnace: OFF";
			else
				updatedDeviceFurnace = "Furnace: OFF";
			
			
			if (degree < (climate.getTempRang() - 3))
				updatedDeviceAirCond = "AirCond: OFF";
			else if (degree > (climate.getTempRang() + 3))
				updatedDeviceAirCond = "AirCond: ON";
			else
				updatedDeviceAirCond = "AirCond: OFF";
		
			
			if (humid < climate.getHumidRangMin())
				updatedDeviceHumidifier = "Humidifier: ON";
			else if (humid > climate.getHumidRangMax())
				updatedDeviceHumidifier = "Humidifier: ON";
			else
				updatedDeviceHumidifier = "Humidifier: OFF";
					
			
			if (moist < climate.getMoistRangMin())
				updatedDeviceSprinkler = "Sprinkler: ON";
			else if (moist > climate.getMoistRangMax())
				updatedDeviceSprinkler = "Sprinkler: ON"; 
			else
				updatedDeviceSprinkler = "Sprinkler: OFF";
			
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
