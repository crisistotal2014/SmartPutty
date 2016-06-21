package Utils;

import com.ice.jni.registry.RegDWordValue;
import com.ice.jni.registry.RegStringValue;
import com.ice.jni.registry.Registry;
import com.ice.jni.registry.RegistryKey;
import com.ice.jni.registry.RegistryValue;
import java.util.Enumeration;

/**
 * Utilities to deal with Windows registry.
 * @author Carlos SS
 */
public class RegistryUtils {
	// Putty session to be used on SmartPutty:
	public static String SMARTPUTTY_SESSION = "SmartPutty_Session";

	/**
	 * Create all needed Putty registry keys.
	 */
	public static void createPuttyKeys(){
		try {
			RegistryKey subkey = Registry.HKEY_CURRENT_USER.createSubKey(
				"Software\\SimonTatham\\PuTTY\\Sessions\\" + SMARTPUTTY_SESSION, "");
			subkey.setValue(new RegDWordValue(subkey, "WarnOnClose", RegistryValue.REG_DWORD, 0));
			subkey.setValue(new RegStringValue(subkey, "LineCodePage", "UTF-8"));
			subkey.setValue(new RegDWordValue(subkey, "ScrollbackLines", RegistryValue.REG_DWORD, 5000));
			subkey.setValue(new RegStringValue(subkey, "WinTitle", ""));
			subkey.setValue(new RegDWordValue(subkey, "NoRemoteWinTitle", RegistryValue.REG_DWORD, 1));
			subkey.setValue(new RegDWordValue(subkey, "BlinkCur", RegistryValue.REG_DWORD, 1));
			subkey.closeKey();
		} catch (Exception ex){
			System.err.println(ex.getMessage());
		}
	}

	/**
	 * Get all Putty sessions.
	 * @return 
	 */
	public static Enumeration getAllPuttySessions(){
		Enumeration sessions = null;

		try {
			RegistryKey subkey = Registry.HKEY_CURRENT_USER.openSubKey("Software\\SimonTatham\\PuTTY\\Sessions");
			sessions = subkey.keyElements();
		} catch (Exception ex){
			System.err.println(ex.getMessage());
		}

		return sessions;
	}
}
