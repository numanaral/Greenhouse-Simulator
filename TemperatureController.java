
public class TemperatureController extends Thread{
		
	// class which keeps the thread for temperature running and changes it 
	// according to greenhouse environment
	
		private GreenhouseEnvironment climate;
		private int interval;
		private String temp;
		
		public TemperatureController(GreenhouseEnvironment climate, int interval)
		{
			this.climate     = climate;
			this.interval    = interval;
		}
		
		public String getTemp(){
			return temp;
		}
		
		public void run()
		{
			while (true)
			{
				double degree = climate.getTemp();					
				
				temp = ("Temp: " + degree + "Degree");
				
				try {
					Thread.sleep(interval);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}


}
