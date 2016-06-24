package Control;

import UI.MainFrame;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class Configuration {
	// XML configuration file to use:
	private static final String CONFIG_FILE = "Configuration.xml";

	private final Properties prop;
	private final MainFrame mainFrame;

	// Constructor:
	public Configuration(MainFrame mainFrame){
		this.mainFrame = mainFrame;

		prop = new Properties();
		loadConfiguration();
	}

	/**
	 * Save program configuration.
	 */
	public void saveConfiguration(){
		try {
			FileOutputStream fos = new FileOutputStream(CONFIG_FILE);

			// Proxy settings:
			prop.setProperty("Timeout", "10000");
			prop.setProperty("WaitForInitTime", "1500");
			prop.setProperty("ProxyHost", "tipl01.swg.usma.ibm.com");
			prop.setProperty("ProxyUser", "perfadmin");
			prop.setProperty("ProxyPassword", "tipadmin");
			prop.setProperty("ProxyPort", "7890");
			// Main window view toolbars:
			prop.setProperty("ViewUtilitiesBar", String.valueOf(mainFrame.utilitiesBarMenuItem.getSelection()));
			prop.setProperty("ViewConnectionBar", String.valueOf(mainFrame.connectionBarMenuItem.getSelection()));

			prop.storeToXML(fos, "SmartPutty configuration file");
			fos.close();
		} catch (FileNotFoundException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Load program configuration.
	 */
	private void loadConfiguration(){
		try {
			FileInputStream fis = new FileInputStream(CONFIG_FILE);
			prop.loadFromXML(fis);
		} catch (InvalidPropertiesFormatException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		prop.list(System.out); //DEBUG
	}

	public String getTimeout(){
		return (String) prop.get("Timeout");
	}

	public String getWaitForInitTime(){
		return (String) prop.get("WaitForInitTime");
	}

	public String getProxyHost(){
		return (String) prop.get("ProxyHost");
	}

	public String getProxyUser(){
		return (String) prop.get("ProxyUser");
	}

	public String getProxyPort(){
		return (String) prop.get("ProxyPort");
	}

	public String getProxyPassword(){
		return (String) prop.get("ProxyPassword");
	}

	/**
	 * Utilities bar must be visible?
	 * @return 
	 */
	public Boolean getUtilitiesVisible(){
		return Boolean.valueOf((String) prop.get("ViewUtilitiesBar"));
	}

	/**
	 * Connection bar must be visible?
	 * @return 
	 */
	public Boolean getConnectionVisible(){
		return Boolean.valueOf((String) prop.get("ViewConnectionBar"));
	}
}
