
public class HumidityController extends Thread{
	
	// class which keeps the thread for humidity running and changes it 
	// according to greenhouse environment

		private GreenhouseEnvironment climate;
		private int interval;
		private String humid;
		
		public HumidityController(GreenhouseEnvironment climate, int interval)
		{
			this.climate  = climate;
			this.interval = interval;
		}
		
		public String getHumid(){
			return humid;
		}
		
		public void run()
		{
			while (true)
			{
				double degree = climate.getHumid();
				
				humid = ("Humid: " + degree + "%");
				
				try {
					Thread.sleep(interval);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}


}
