import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.*;

	
public class UserInterface extends JFrame{
	
	private JTextField temp;
	private JTextField humid;
	private JTextField moist;
	private JTextField changeInTemperature;
	private JTextField changeInHumidity; 
	private JTextField changeInMoisture; 
	private JTextField tempRang;
	private JTextField humidRangMin;
	private JTextField humidRangMax;
	private JTextField moistRangMin;
	private JTextField moistRangMax;
	private JTextField changeInFurnace;
	private JTextField changeInAirCond;
	private JTextField changeInHumidifier;
	private JTextField changeInSprinkler;
	
	
	private JButton defaultB;
	private JButton start;
	private JButton stop;
	private JButton save;
	private JButton load;
	
	private JLabel output;
	private JLabel output2;
	private JLabel output3;
	private JLabel output4;
	private JPanel outPanel;
	private JPanel outPanel2;
	private JPanel outPanel3;
	private JPanel outPanel4;
	
	private double c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15;
	
	GreenhouseEnvironment weatherEnv;
	
	TemperatureController checkTemp;
	HumidityController 	  checkHumid;
	MoistureController    checkMoist;
	ControlDevices        contDevice;
	
	public double getParsedText(JTextField text)
	// method to parse string to double
	{
		double textToNum = 0;
		try
		{
			textToNum = Parser.ParseStringToDouble(text.getText());
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage());

		}
		
		return textToNum;
	}
	
	public double getParsedTextMinMax(JTextField text, double i, double j)
	// method to parse string to double with min and max values
	{
		double textToNum = 0;
		try
		{
			textToNum = Parser.ParseStringToDoubleMinMax(text.getText(), i, j);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage());

		}
		
		return textToNum;
	}
	
	public void setListenerToButton(ActionListener l, JButton button)
	// action listener method
	{
		button.addActionListener(l);
	}
	
	
	public  UserInterface()
	// main GUI method / constructor
	{

		JFrame container = new JFrame("Greenhouse Simulator");
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                           	        
        
        output    = new JLabel("<html>Output Screen <br> <br> <br> [   Devices   ] </html>");
        outPanel  = new JPanel();
        output2   = new JLabel("<html>Output Screen <br> <br> <br> [   Devices   ] </html>");
        outPanel2 = new JPanel();
        output3   = new JLabel("<html>Load Screen <br> <br> <br> [   Devices   ] </html>");
        outPanel3 = new JPanel();
        output4   = new JLabel("<html>Load Screen <br> <br> <br> [   Weather   ] </html>");
        outPanel4 = new JPanel();
		
		temp 				= new JTextField(4);
		humid 				= new JTextField(4);
		moist 				= new JTextField(4);
		changeInTemperature = new JTextField(4);
		changeInHumidity  	= new JTextField(4);
		changeInMoisture  	= new JTextField(4);
		tempRang 			= new JTextField(4);
		humidRangMin		= new JTextField(4);
		humidRangMax		= new JTextField(4);
		moistRangMin		= new JTextField(4);
		moistRangMax		= new JTextField(4);
		changeInFurnace 	= new JTextField(4);
		changeInAirCond 	= new JTextField(4);
		changeInHumidifier	= new JTextField(4);
		changeInSprinkler	= new JTextField(4);
		
		defaultB = new JButton("===DEFAULT===");
		start	 = new JButton(" ===START=== ");
		stop	 = new JButton("====STOP====");
		save 	 = new JButton("====SAVE====");
		load 	 = new JButton("====LOAD====");
		
		
		defaultB.addActionListener(new ActionListener( )
		
		// creates an object with defaults settings and starts the program
		
			{
				public void actionPerformed(ActionEvent e) {
					weatherEnv = new GreenhouseEnvironment();
			    	
			    	checkTemp  = new TemperatureController(weatherEnv,1000);
			    	checkHumid = new HumidityController(weatherEnv,1000);
			    	checkMoist = new MoistureController(weatherEnv,1000);
			    	contDevice = new ControlDevices(weatherEnv,1000);
										
					contDevice.start();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					checkTemp.start();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					checkHumid.start();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					checkMoist.start();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					weatherEnv.start();
					
					// threads are run after a little of sleep so that they
					// can keep their order
					
					Timer SimpleTimer = new Timer(1000, new ActionListener(){
						// to update the text output
					    @Override
					    public void actionPerformed(ActionEvent e) {
					    	String s1 = String.format("<html>%s<br>%s<br>%s<br>%s</html>",
					    								contDevice.getFurnaceState(),
					    								contDevice.getAirCondState(), 
					    								contDevice.getHumidifierState(),
					    								contDevice.getSprinklerState());
					    	
					    	String s2 = String.format("<html>%s<br>%s<br>%s</html>",
					    								checkTemp.getTemp(),
					    								checkHumid.getHumid(), 
					    								checkMoist.getMoist());
							output.setText(s1);
							output2.setText(s2);
					    
					    }
					});
					SimpleTimer.start();
					
					
				}
			}
		);
		
		
		start.addActionListener(new ActionListener( )
			{
				public void actionPerformed(ActionEvent e) {
					c1  = getParsedText(temp);
			    	c2  = getParsedTextMinMax(humid, 0, 100);
			    	c3  = getParsedTextMinMax(moist, 0, 100);
			    	c4  = getParsedText(changeInTemperature);
			    	c5  = getParsedText(changeInHumidity);
			    	c6  = getParsedText(changeInMoisture);
			    	c7  = getParsedText(tempRang);
			    	c8  = getParsedTextMinMax(humidRangMin, 0, 100);
			    	c9  = getParsedTextMinMax(humidRangMax, 0, 100);
			    	c10 = getParsedTextMinMax(moistRangMin, 0, 100);
			    	c11 = getParsedTextMinMax(moistRangMax, 0, 100);
			    	c12 = getParsedText(changeInFurnace);
			    	c13 = getParsedText(changeInAirCond);
			    	c14 = getParsedText(changeInHumidifier);
			    	c15 = getParsedText(changeInSprinkler);
					
			    	// user inputted numbers are taken and sent to the constructor, the idea is 
			    	// the same with the default one, this includes user inputs
			    	
			    	weatherEnv = new GreenhouseEnvironment(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15);
			    	
			    	checkTemp  = new TemperatureController(weatherEnv,1000);
			    	checkHumid = new HumidityController(weatherEnv,1000);
			    	checkMoist = new MoistureController(weatherEnv,1000);
			    	contDevice = new ControlDevices(weatherEnv,1000);
			    	
					contDevice.start();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					checkTemp.start();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					checkHumid.start();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					checkMoist.start();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					weatherEnv.start();
					
					Timer SimpleTimer = new Timer(1000, new ActionListener(){
					    @Override
					    public void actionPerformed(ActionEvent e) {
					    	String s1 = String.format("<html>%s<br>%s<br>%s<br>%s</html>",
					    								contDevice.getFurnaceState(),
					    								contDevice.getAirCondState(), 
					    								contDevice.getHumidifierState(),
					    								contDevice.getSprinklerState());
					    	
					    	String s2 = String.format("<html>%s<br>%s<br>%s</html>",
					    								checkTemp.getTemp(),
					    								checkHumid.getHumid(), 
					    								checkMoist.getMoist());
							output.setText(s1);
							output2.setText(s2);
					    
					    }
					});
					SimpleTimer.start();
				}
			}
		);
		
		stop.addActionListener(new ActionListener( )
			{
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
					
					contDevice.suspend();
					checkTemp.suspend();
					checkHumid.suspend();
					checkMoist.suspend();
					weatherEnv.suspend();
					
					// stop the threads by suspending them
				}
			}
		);

		save.addActionListener(new ActionListener( )
		
		// save the lines/outputs to a specific file named savefile.txt
				
			{
				public void actionPerformed(ActionEvent e) {
					try(  PrintWriter out = new PrintWriter( "savefile.txt" )  ){
					    out.printf("%s\n%s\n%s\n%s\n",
								contDevice.getFurnaceState(),
								contDevice.getAirCondState(), 
								contDevice.getHumidifierState(),
								contDevice.getSprinklerState());
					    out.printf("%s\n%s\n%s",
								checkTemp.getTemp(),
								checkHumid.getHumid(), 
								checkMoist.getMoist());
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					
					
							
					}
				}
			}
		);
		
		load.addActionListener(new ActionListener( )
		
		// load the savefile.txt lines
		
			{
				public void actionPerformed(ActionEvent e) {
					BufferedReader in = null;
					try {
						in = new BufferedReader(new FileReader("savefile.txt"));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}

					String line = null;
					try {
						String line1 = Files.readAllLines(Paths.get("savefile.txt")).get(0);
						String line2 = Files.readAllLines(Paths.get("savefile.txt")).get(1);
						String line3 = Files.readAllLines(Paths.get("savefile.txt")).get(2);
						String line4 = Files.readAllLines(Paths.get("savefile.txt")).get(3);
						String line5 = Files.readAllLines(Paths.get("savefile.txt")).get(4);
						String line6 = Files.readAllLines(Paths.get("savefile.txt")).get(5);
						String line7 = Files.readAllLines(Paths.get("savefile.txt")).get(6);
						
						// this is to show each line on a proper position
						
						String devices= String.format("<html>%s<br>%s<br>%s<br>%s</html>",
														line1, line2, line3, line4);
	
						String temps = String.format("<html>%s<br>%s<br>%s</html>",
														line5, line6, line7);
		
						output3.setText(devices);
						output4.setText(temps);
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					try {
						in.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		);


		JLabel la1 = new JLabel("Initial Temperature");
		JLabel la2 = new JLabel("Change in Temp");
        JLabel la3 = new JLabel("Desired Range");
        JLabel la4 = new JLabel("Furnace Rate");
		
		JLabel lb1 = new JLabel("Initial Humidity");
        JLabel lb2 = new JLabel("Change in Humid");
		JLabel lb3 = new JLabel("Humidity Range(Min)");
		JLabel lb4 = new JLabel("Air Cond. Rate");
		
        JLabel lc1 = new JLabel("Initial Moisture");
        JLabel lc2 = new JLabel("Change in Moisture");
		JLabel lc3 = new JLabel("Humidity Range(Max)");
        JLabel lc4 = new JLabel("Humidifier Rate");
		
        JLabel ld1 = new JLabel("Start with inputs");
		JLabel ld2 = new JLabel("Stop the process");
		JLabel ld3 = new JLabel("Moisture Range(Min)");
		JLabel ld4 = new JLabel("Sprinkler Rate");
		
		JLabel le1 = new JLabel("Save to a file");
		JLabel le2 = new JLabel("Display load file");
        JLabel le3 = new JLabel("Moisture Range(Max)");
        JLabel le4 = new JLabel("Default Start");
            
        	
        	// this following is a grouplayout with horizontal and vertical lines/orders
        	
	        GroupLayout layout = new GroupLayout(container.getContentPane());    	        
	        container.getContentPane().setLayout(layout);
	        layout.setAutoCreateGaps(true);
	        layout.setAutoCreateContainerGaps(true);
	        layout.setHorizontalGroup(layout.createSequentialGroup()
	        		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
    	                .addComponent(la1)
    	                .addComponent(temp)
    	                .addComponent(la2)
    					.addComponent(changeInTemperature)
    					.addComponent(la3)
    	                .addComponent(tempRang)
    					.addComponent(la4)
    	            	.addComponent(changeInFurnace)  
	        			.addComponent(outPanel)) 
    	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
    	                .addComponent(lb1)
    	                .addComponent(humid)
    	                .addComponent(lb2)
    					.addComponent(changeInHumidity)
    					.addComponent(lb3)
    	                .addComponent(humidRangMin)
    					.addComponent(lb4)
    	            	.addComponent(changeInAirCond)
    	            	.addComponent(outPanel2))
    	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
    	                .addComponent(lc1)
    	                .addComponent(moist)
    	                .addComponent(lc2)
    					.addComponent(changeInMoisture)
    					.addComponent(lc3)
    	                .addComponent(humidRangMax)
    					.addComponent(lc4)
    	            	.addComponent(changeInHumidifier)
    	            	.addComponent(outPanel3))
    				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(ld1)
						.addComponent(start)
						.addComponent(ld2)
    					.addComponent(save)
						.addComponent(ld3)
    	                .addComponent(moistRangMin)
    					.addComponent(ld4)
    	            	.addComponent(changeInSprinkler)
	            		.addComponent(outPanel4))
    				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(le1)
    					.addComponent(stop)
    					.addComponent(le2)
    					.addComponent(load)
						.addComponent(le3)
    	                .addComponent(moistRangMax)
    					.addComponent(le4)
    					.addComponent(defaultB))
	        );
	        layout.setVerticalGroup(layout.createSequentialGroup()
	        		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
    	                .addComponent(la1)
    	                .addComponent(lb1)
    	                .addComponent(lc1)
    	                .addComponent(ld1)
    	                .addComponent(le1))
		        	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
    	                .addComponent(temp)
    	                .addComponent(humid)
    	            	.addComponent(moist)
		        		.addComponent(start)
		        		.addComponent(stop))
    	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
    	                .addComponent(la2)
    	                .addComponent(lb2)
    	                .addComponent(lc2)
    	                .addComponent(ld2)
    	                .addComponent(le2))
    	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
    	                .addComponent(changeInTemperature)
    	                .addComponent(changeInHumidity)
    	            	.addComponent(changeInMoisture)
    	            	.addComponent(save)
    	            	.addComponent(load))
    	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
    		            .addComponent(la3)
    	                .addComponent(lb3)
    	                .addComponent(lc3)
    					.addComponent(ld3)
    	                .addComponent(le3))
    	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
    	                .addComponent(tempRang)
    	                .addComponent(humidRangMin)
    	            	.addComponent(humidRangMax)
    					.addComponent(moistRangMin)
    	            	.addComponent(moistRangMax))
    				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
    	                .addComponent(la4)
    	                .addComponent(lb4)
    					.addComponent(lc4)
    	                .addComponent(ld4)
    					.addComponent(le4))
    				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
    	                .addComponent(changeInFurnace)
    	                .addComponent(changeInAirCond)
    	            	.addComponent(changeInHumidifier)
    					.addComponent(changeInSprinkler)
    					.addComponent(defaultB))
    				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
    					.addComponent(outPanel)
    					.addComponent(outPanel2)
    					.addComponent(outPanel3)
	            		.addComponent(outPanel4))

	        );
	        
	        
	        outPanel.add(output);
			outPanel.setBackground(Color.MAGENTA);
			output.setFont(new Font("Serif", Font.BOLD + Font.CENTER_BASELINE, 14));
	        
			outPanel2.add(output2);
			outPanel2.setBackground(Color.GREEN);
			output2.setFont(new Font("Serif", Font.BOLD + Font.CENTER_BASELINE, 14));
	        
			outPanel3.add(output3);
			outPanel3.setBackground(Color.ORANGE);
			output3.setFont(new Font("Serif", Font.BOLD + Font.CENTER_BASELINE, 14));
			
			outPanel4.add(output4);
			outPanel4.setBackground(Color.CYAN);
			output4.setFont(new Font("Serif", Font.BOLD + Font.CENTER_BASELINE, 14));
			
			// setting bground colors for the output panels
	
	        //container.setLayout(layout);
	        container.setLocationRelativeTo(null);
	        container.pack();
	        // keep the screen packed
	        container.setVisible(true);

	    
		
	
}
}