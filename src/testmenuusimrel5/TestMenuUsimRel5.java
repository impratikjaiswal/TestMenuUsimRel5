package testmenuusimrel5;

/*
 * Imported packages
 */
import sim.toolkit.*;
import sim.access.*;
import javacard.framework.*;

public class TestMenuUsimRel5 extends javacard.framework.Applet implements
		ToolkitInterface, ToolkitConstants {
	// Mandatory variables
	private SIMView gsmFile;
	private ToolkitRegistry reg;

	// Main Menu
	private byte idMenu1;
	private byte[] Menu1;
	private byte idMenu2;
	private byte[] Menu2;

	/**
	 * Constructor of the applet
	 */
	public TestMenuUsimRel5() {
		// Get the GSM application reference
		gsmFile = SIMSystem.getTheSIMView();
		// Get the reference of the applet ToolkitRegistry object
		reg = ToolkitRegistry.getEntry();

		/**@todo: Customize your menu titles here*/
		Menu1 = new byte[] { (byte) '1', (byte) ' ', (byte) 'M', (byte) 'e',
				(byte) 'n', (byte) 'u', (byte) '1' };
		Menu2 = new byte[] { (byte) '2', (byte) ' ', (byte) 'M', (byte) 'e',
				(byte) 'n', (byte) 'u', (byte) '2' };
		// Define the applet Menu Entry
		idMenu1 = reg.initMenuEntry(Menu1, (short) 0, (short) Menu1.length,
				PRO_CMD_SELECT_ITEM, false, (byte) 0, (short) 0);
		idMenu2 = reg.initMenuEntry(Menu2, (short) 0, (short) Menu2.length,
				(byte) 0, false, (byte) 0, (short) 0);
	}

	/**
	 * Method called by the JCRE at the installation of the applet
	 * @param bArray the byte array containing the AID bytes
	 * @param bOffset the start of AID bytes in bArray
	 * @param bLength the length of the AID bytes in bArray
	 */
	public static void install(byte[] bArray, short bOffset, byte bLength) {
		// Create the Java SIM toolkit applet
		TestMenuUsimRel5 StkCommandsExampleApplet = new TestMenuUsimRel5();
		// Register this applet
		StkCommandsExampleApplet.register(bArray, (short) (bOffset + 1),
				(byte) bArray[bOffset]);
	}

	/**
	 * Method called by the SIM Toolkit Framework
	 * @param event the byte representation of the event triggered
	 */
	public void processToolkit(byte event) {
		// Manage the request following the MENU SELECTION event type
		if (event == EVENT_MENU_SELECTION) {
			// Get the selected item
			EnvelopeHandler envHdlr = EnvelopeHandler.getTheHandler();
			byte selectedItemId = envHdlr.getItemIdentifier();

			// Perform the required service following the Menu1 selected item
			if (selectedItemId == idMenu1) {
				menu1Action();
			}

			// Perform the required service following the Menu2 selected item
			if (selectedItemId == idMenu2) {
				menu2Action();
			}
		}
	}

	/**
	 * Method called by the JCRE, once selected
	 * @param apdu the incoming APDU object
	 */
	public void process(APDU apdu) {
		// ignore the applet select command dispached to the process
		if (selectingApplet()) {
			return;
		}
	}

	/**
	 * Manage the Menu1 selection
	 */
	private void menu1Action() {
		/**@todo: Replace following sample code with your implementation*/
		// Get the received envelope
		ProactiveHandler proHdlr = ProactiveHandler.getTheHandler();

		// Display the "Menu1" message text
		// Initialize the display text command
		proHdlr.initDisplayText((byte) 0x00, DCS_8_BIT_DATA, Menu1, (short) 0,
				(short) (Menu1.length));
		proHdlr.send();

		return;
	}

	/**
	 * Manage the Menu2 selection
	 */
	private void menu2Action() {
		/**@todo: Replace following sample code with your implementation*/
		// Get the received envelope
		ProactiveHandler proHdlr = ProactiveHandler.getTheHandler();

		// Display the "Menu2" message text
		// Initialize the display text command
		proHdlr.initDisplayText((byte) 0x00, DCS_8_BIT_DATA, Menu2, (short) 0,
				(short) (Menu2.length));
		proHdlr.send();

		return;
	}
}
