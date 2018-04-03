
public class GreenhouseEnvironment extends Thread{
	
	// class that most methods belong to
	// adjusts the ranges and the devices so that they work properly
	
	private double temp;
	private double humid;
	private double moist;
	
	private double changeInTemperature;
	private double changeInHumidity; 
	private double changeInMoisture; 
	
	private double tempRang;
	private double humidRangMin;
	private double humidRangMax;
	private double moistRangMin;
	private double moistRangMax;
	
	private double changeInFurnace;
	private double changeInAirCond;
	private double changeInHumidifier;
	private double changeInSprinkler;
	
	
	public GreenhouseEnvironment(double temp, 	   	   double humid, 		double moist, 
								 double tempChng,  	   double humidChng, 	double moistChng,
								 double tempRang, 
								 double humidRangMin,  double humidRangMax, 
								 double moistRangMin,  double moistRangMax,
								 double furnace,  	   double aircond, 		double humidifier,  double sprinkler
								 ) {
		
		setInitials(temp, humid, moist);
		setChanges(tempChng, humidChng, moistChng);
		setRanges(tempRang, humidRangMin, humidRangMax, moistRangMin, moistRangMax);
		setDevices(furnace, aircond, humidifier, sprinkler);
		
	}
	
	public GreenhouseEnvironment(){
		this(0, 0, 0, 5, 5, 5, 50, 40, 60, 40, 60 ,3, 7, 5, 5);
		
	}
	public void setInitials(double temp, double humid, double moist){
		this.temp  = temp;
		this.humid = humid;
		this.moist = moist;
	}
	
	public void setRanges(double temp, double humidmin, double humidmax, double moistmin, double moistmax){
		this.tempRang  = temp;
		this.humidRangMin = humidmin;
		this.humidRangMax = humidmax;
		this.moistRangMin = moistmin;
		this.moistRangMax = moistmax;
	}
	
	public void setChanges(double temp, double humid, double moist){
		setTempChange(temp);
		setHumidChange(humid);
		setMoistChange(moist);
	}
	
	public void setDevices(double furnace, double aircond, double humidifier, double sprinkler){
		this.changeInFurnace    = furnace;
		this.changeInAirCond    = aircond;
		this.changeInSprinkler  = humidifier;
		this.changeInHumidifier = sprinkler;
	}
	
	public void setTempChange(double temperature)
	{
		this.changeInTemperature = temperature;
	}
	
	public void setHumidChange(double humidity)
	{
		this.changeInHumidity = humidity;
	}
	
	public void setMoistChange(double moisture)
	{
		this.changeInMoisture = moisture;
	}
	
	public double getTempRang()
	{
		return tempRang;
	}
	
	public double getHumidRangMin()
	{
		return humidRangMin;
	}
	
	public double getHumidRangMax()
	{
		return humidRangMax;
	}
	
	public double getMoistRangMin()
	{
		return moistRangMin;
	}
	
	public double getMoistRangMax()
	{
		return moistRangMax;
	}
	
	public double getTemp()
	{
		return temp;
	}
	
	public double getHumid()
	{
		return humid;
	}
	
	public double getMoist()
	{
		return moist;
	}

	
	
	@Override
	public void run()
	{
		while (true)
		{
			// thread which runs with the logic that keeps the values within the range
			// and makes the devices turn on and off properly

			if((temp + changeInTemperature) < (tempRang - 3))
				temp  += (changeInFurnace + changeInTemperature);
			else if((temp + changeInTemperature) >= (tempRang - 3) &&
					 temp <= (tempRang + 3))
				temp  += changeInTemperature;
			else if ((temp + changeInTemperature) > (tempRang + 3) )
				temp  += (-changeInAirCond + changeInTemperature);
			


			if ((humid + changeInHumidity) > 100)
				humid = 100;
			else if ((humid + changeInHumidity) < 0)
				humid = 0;
			else if((humid + changeInHumidity) < (humidRangMin))
				humid  += (changeInHumidifier + changeInHumidity);
			else if((humid + changeInHumidity) >= (humidRangMin) &&
					 humid <= (humidRangMax))
				humid  += changeInHumidity;
			else if ((humid + (changeInHumidity)) > (humidRangMax))
				humid  += (-changeInHumidifier + changeInHumidity);
			
			
			if ((moist + changeInMoisture) > 100)
				moist = 100;
			else if ((moist + changeInMoisture) < 0)
				moist = 0;
			else if((moist + changeInMoisture) < (moistRangMin))
				moist  += (changeInSprinkler + changeInMoisture);
			else if((moist + changeInMoisture) >= (moistRangMin) &&
					 moist <= (moistRangMax))
				moist  += changeInMoisture;
			else if ((moist + (changeInMoisture)) > (moistRangMax))
				moist  += (-changeInSprinkler + changeInMoisture);
			
			

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
