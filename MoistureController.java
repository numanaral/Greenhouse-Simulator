
public class MoistureController extends Thread{
	
	// class which keeps the thread for moisture running and changes it 
	// according to greenhouse environment

		private GreenhouseEnvironment climate;
		private int interval;
		private String moist;
		
		public MoistureController(GreenhouseEnvironment climate, int interval)
		{
			this.climate  = climate;
			this.interval = interval;
		}
		
		public String getMoist(){
			return moist;
		}
		
		public void run()
		{
			while (true)
			{
				double degree = climate.getMoist();
				
				moist = ("Moist: " + degree + "%");
				
				try {
					Thread.sleep(interval);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}


}

