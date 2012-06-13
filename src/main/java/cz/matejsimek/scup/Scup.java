package cz.matejsimek.scup;

import java.awt.AWTException;
import java.awt.CheckboxMenuItem;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.FlavorEvent;
import java.awt.datatransfer.FlavorListener;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.List;
import java.util.Properties;
import javax.imageio.ImageIO;

public class Scup {

    private static Clipboard clipboard;
    private static FileUpload fileupload;
    private static SystemTray tray;
    private static TrayIcon trayIcon;
    // User configuration
    private static String FTP_SERVER, FTP_USERNAME, FTP_PASSWORD, FTP_DIRECTORY, URL;
    // Runtime configuration
    private static boolean isUploadEnabled = true;

    public static void main(String[] args) throws InterruptedException, IOException {
	File configFile = new File("config.properties");
	if (configFile.exists()) {
	    Properties config = new Properties();
	    config.load(new FileInputStream(configFile));
	    readConfiguration(config);
	} else {
	    System.err.println("Configuration file config.properties doesn't exist, please create one.");
	    System.exit(1);
	}

	initTray();

	clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	clipboard.addFlavorListener(new ClipboardChangeListener());

	fileupload = new FileUpload(FTP_SERVER, FTP_USERNAME, FTP_PASSWORD, FTP_DIRECTORY);

	// Endless program run
	while (true) {
	    Thread.sleep(100);
	}
    }

    static private void initTray() {
	if (SystemTray.isSupported()) {
	    tray = SystemTray.getSystemTray();
	    trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage("icon.png"), "Scup");
	    //trayIcon.setImageAutoSize(true);

	    try {
		tray.add(trayIcon);
	    } catch (AWTException e) {
		System.out.println("TrayIcon could not be added.");
		System.exit(1);
	    }

	    PopupMenu popup = new PopupMenu();
	    final CheckboxMenuItem uploadEnabledCheckBox = new CheckboxMenuItem("Upload enabled");
	    MenuItem exitItem = new MenuItem("Exit");
	    popup.add(uploadEnabledCheckBox);
	    popup.addSeparator();
	    popup.add(exitItem);

	    trayIcon.setPopupMenu(popup);

	    uploadEnabledCheckBox.setState(isUploadEnabled);

	    // Add listener to uploadEnabledCheckBox.
	    uploadEnabledCheckBox.addItemListener(new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
		    int chxbx = e.getStateChange();
		    if (chxbx == ItemEvent.SELECTED) {
			isUploadEnabled = true;
			uploadEnabledCheckBox.setLabel("Upload enabled");
		    } else {
			isUploadEnabled = false;
			uploadEnabledCheckBox.setLabel("Upload disabled");
		    }
		}
	    });

	    // Add listener to exitItem.
	    exitItem.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    tray.remove(trayIcon);
		    System.exit(0);
		}
	    });

	    trayIcon.displayMessage("Scup", "I am here to serve", TrayIcon.MessageType.NONE);
	} else {
	    System.err.println("SystemTray is not supported");
	}
    }

    static private void readConfiguration(Properties config) {
	FTP_SERVER = config.getProperty("FTP_SERVER");
	FTP_USERNAME = config.getProperty("FTP_USERNAME", "anonymous");
	FTP_PASSWORD = config.getProperty("FTP_PASSWORD", "");
	FTP_DIRECTORY = config.getProperty("FTP_DIRECTORY", "");
	URL = config.getProperty("URL");
    }

    static void processImage(BufferedImage img) {
	System.out.println("Processing image...");
	System.out.println("Image: " + img.getWidth() + "x" + img.getHeight());

	// TODO crop image

	File imageFile = saveImageToFile(img);

	if (isUploadEnabled) {
	    // Transer image to FTP
	    System.out.println("Uploading image to FTP server...");
	    if (fileupload.uploadFile(imageFile, imageFile.getName())) {
		System.out.println("Upload done");
		// Generate URL
		String url = (URL.endsWith("/") ? URL : URL + "/") + imageFile.getName();
		System.out.println(url);
		// Copy URL to clipboard
		try {
		    clipboard.setContents(new StringSelection(url), null);
		} catch (IllegalStateException e) {
		    System.err.println("Can't set clipboard, sorry!");
		}
		// Notify me about it
		trayIcon.displayMessage("Image uploaded", url, TrayIcon.MessageType.INFO);
	    } else {
		System.err.println("Upload failed");
		trayIcon.displayMessage("Upload failed", "I can not serve, sorry", TrayIcon.MessageType.ERROR);
	    }
	} else {
	    // Copy URL to clipboard
	    try {
		clipboard.setContents(new StringSelection(imageFile.getAbsolutePath()), null);
	    } catch (IllegalStateException e) {
		System.err.println("Can't set clipboard, sorry!");
	    }
	    // Notify me about it
	    trayIcon.displayMessage("Image saved", imageFile.getAbsolutePath(), TrayIcon.MessageType.INFO);
	}

    }

    static void processFiles(List<File> files) {
	System.out.println("Processing files...");
	for (int i = 0; i < files.size(); i++) {
	    System.out.println("File " + i + ": " + files.get(i).getName());
	}
	System.err.println("Not supported feature yet, stay tuned!");


    }

    static class ClipboardChangeListener implements FlavorListener {

	@Override
	public void flavorsChanged(FlavorEvent e) {
	    System.out.println("Clipboard changed " + e.getSource() + " " + e.toString());

	    try {
		if (clipboard.isDataFlavorAvailable(DataFlavor.imageFlavor)) {
		    BufferedImage image = (BufferedImage) clipboard.getData(DataFlavor.imageFlavor);
		    clearClipboard();
		    Scup.processImage(image);
		    // Clear all possible references to save memory
		    image.flush();
		    image = null;
		}
		if (clipboard.isDataFlavorAvailable(DataFlavor.javaFileListFlavor)) {
		    List<File> files = (List<File>) clipboard.getData(DataFlavor.javaFileListFlavor);
		    clearClipboard();
		    Scup.processFiles(files);
		    // Clear all possible references to save memory
		    files = null;
		}
	    } catch (NullPointerException npe) {
		// Clipboard content is null
	    } catch (IllegalStateException ise) {
		// Clipboard is unavailable
	    } catch (UnsupportedFlavorException ufe) {
		// Cliboard content is unsupported
	    } catch (IOException ioe) {
		// Clipboard content is unreadable
		ioe.printStackTrace();
	    }

	    // Try to remove unused resources from memory
	    System.gc();
	}

	static void clearClipboard() {
	    System.out.println("Clearing clipboard...");
	    try {
		clipboard.setContents(new Transferable() {
		    @Override
		    public DataFlavor[] getTransferDataFlavors() {
			return new DataFlavor[0];
		    }

		    @Override
		    public boolean isDataFlavorSupported(DataFlavor df) {
			return false;
		    }

		    @Override
		    public Object getTransferData(DataFlavor df) throws UnsupportedFlavorException, IOException {
			throw new UnsupportedOperationException("Not supported yet.");
		    }
		}, null);
	    } catch (IllegalStateException e) {
		System.err.println("Can't clear clipboard!");
	    }
	}
    }

    static File saveImageToFile(BufferedImage img) {
	try {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ImageIO.write(img, "png", baos);
	    byte[] data = baos.toByteArray();
	    baos.close();
	    baos = null;

	    String hash = generateHash(data);
	    data = null;

	    System.out.println("Saving image: " + hash + ".png");
	    File outputfile = new File(hash + ".png");
	    ImageIO.write(img, "png", outputfile);

	    return outputfile;

	} catch (IOException ex) {
	    System.err.println("Can't write image to file!");
	}
	return null;
    }

    static String generateHash(byte[] data) {
	try {
	    MessageDigest md = MessageDigest.getInstance("SHA");
	    md.update(data);

	    Formatter formatter = new Formatter();
	    for (byte b : md.digest()) {
		formatter.format("%02x", b);
	    }

	    return formatter.toString();

	} catch (NoSuchAlgorithmException ex) {
	    System.err.println("Can't load digest algorithm!");
	}

	return Long.toString(System.currentTimeMillis());
    }
//    public void takeScreenshot() {
//	Graphics2D imageGraphics = null;
//	try {
//	    Robot robot = new Robot();
//	    GraphicsDevice currentDevice = MouseInfo.getPointerInfo().getDevice();
//	    BufferedImage exportImage = robot.createScreenCapture(currentDevice.getDefaultConfiguration().getBounds());
//
//	    imageGraphics = (Graphics2D) exportImage.getGraphics();
//	    File screenshotFile = new File("./CurrentMonitorScreenshot-" + System.currentTimeMillis() + ".png");
//	    ImageIO.write(exportImage, "png", screenshotFile);
//	    System.out.println("Screenshot successfully captured to '" + screenshotFile.getCanonicalPath() + "'!");
//	} catch (Exception exp) {
//	    exp.printStackTrace();
//	} finally {
//	    imageGraphics.dispose();
//	}
//    }
}