/*
 * 
 */
package jpad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

// TODO: Auto-generated Javadoc
/**
 * <b>Program:</b> JEdit - Programmer's Editor<br>
 * <b>Copyright:</b> 2011 Michael Aro<br>
 * <b>License:</b> Free Software<br>
 * <b>Version:</b> 1.0<br>
 * <b>Date:</b> 24.05.2011<br>
 * <br>
 * <b>Info:</b> This is the main class
 */
public class JEdit extends JFrame implements ActionListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6412431809290449779L;

	/** The main panel. */
	private JPanel mainPanel = null;

	/** The editor pane. */
	//private SpecialTextArea[] editorPane = new SpecialTextArea[5];//null; // JTextArea	
	private ArrayList<SpecialTextArea> editorPane = new ArrayList<SpecialTextArea>(); // JTextArea
	
	/** The file information. */
	Map<Object,String> fileInfoMap =new HashMap<Object, String>();

	/** The desktop. */
	private JDesktopPane theDesktop; // Desktop pane is very important for JInternalFrame 
	
	/** The tabbed pane. */
	private JTabbedPane tabbedPane;

	/** The frame. */
	//private JInternalFrame frame = null;
	
	/** The file status. */
	private boolean fileStatus = false; // Check - file exist or not
	
	/** The file is Open?. */
	private boolean fileOpen = false; // Check - file exist or not
	
	/** The file is New?. */
	private boolean fileNew = false; // Check - file exist or not
	
	/** The file name. */
	private String fileName = ""; // Name of file
	
	/** The file dir. */
	private String fileDir = ""; // File path
	
	/** The tab name. */
	private String tabName = ""; // Name on tab

	/** The COUNTER. */
	private int COUNTER = 0;

	// ///////////////////////////////////////////////////////////////////////////
	/**
	 * Constructor - Creates the Main Window.
	 */
	public JEdit() {
		super();
		makeFullScreenSize();
		setTitle("JEdit");

		addWindowListener(new WindowCallback()); // Exit the window closing

		// Add MenuBar
		setJMenuBar(new MainMenu(this));

		tabbedPane = new JTabbedPane();

		tabbedPane.setBorder(BorderFactory.createRaisedBevelBorder());

		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1, 1));
		getContentPane().add(mainPanel, BorderLayout.CENTER);

	}
	
	// Method - makes the main window near full screen
	/**
	 * Make full screen size.
	 */
	private void makeFullScreenSize()
	{
	  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	  setSize(screenSize.width, screenSize.height - 43);
	}

	// ///////////////////////////////////////////////////////////////////////////
	/**
	 * Main routine for new object and puts it on JEdit visible.
	 *
	 * @param args the arguments
	 */
	public static void main(String args[]) {
		JEdit x = new JEdit();
		x.setVisible(true);
	}

	// ///////////////////////////////////////////////////////////////////////////
	/**
	 * Event Handler - Catch the Menu events.
	 *
	 * @param e the e
	 */
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd == "open") {
			dataiOpen();
		}
		if (cmd == "csave") {
			dataSave();
		}
		if (cmd == "save") {
			dataiSave();
		}
		if (cmd == "close") {
			dataiClose();			
		}
		if (cmd == "new") {
			dataNew();
		}
		if (cmd == "about") {
			about();
		}
		if (cmd == "exit") {
			System.exit(0);
		}
		if (cmd == "coffee") {
			coffee();
		}
		if (cmd == "cut") {
			cut();
		}
		if (cmd == "copy") {
			copy();
		}
		if (cmd == "paste") {
			paste();
		}
		if (cmd == "select") {
			selectAll();
		}
		if (cmd == "bold") {
			bold();
		}
		if (cmd == "italic") {
			italic();
		}
		if (cmd == "regular") {
			regular();
		}
		if (cmd == "twelve") {
			twelve();
		}
		if (cmd == "fourteen") {
			fourteen();
		}
		if (cmd == "sixteen") {
			sixteen();
		}
		if (cmd == "sansserif") {
			sansserif();
		}
		if (cmd == "red") {
			red();
		}
		if (cmd == "blue") {
			blue();
		}
	}

	// ///////////////////////////////////////////////////////////////////////////
	/**
	 * Loads text document from a file.
	 */
	private void dataiOpen() {
		JFileChooser chooser = new JFileChooser("."); // in the current directory
														// Start
		int returnVal = chooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File f = chooser.getSelectedFile();
			fileName = chooser.getSelectedFile().getName(); // Get file name
			setFileDir(chooser.getSelectedFile().getPath()); // Read path
			try {
				// create internal frame
				FileReader reader = new FileReader(f);
				
				BufferedReader br = new BufferedReader(reader);
				
				String s=""; int c=0;
				while((c=br.read())!=-1) 
					s+=(char)c; 

				JInternalFrame frame = new JInternalFrame("", true, true, true, true);

				SpecialTextArea specialArea = new SpecialTextArea(); // create new panel
				editorPane.add(specialArea);
				
				// add or set elements in Map put method key and value pair
				fileInfoMap.put(new Integer(COUNTER), fileDir);
				
				editorPane.get(COUNTER).getModel().setText(s); // Use TextComponent 
				//editorPane[COUNTER].getModel().read(reader, "");
				frame.add(editorPane.get(COUNTER), BorderLayout.CENTER); // add panel

				frame.pack(); // set internal frame to size of contents
				
				theDesktop = new JDesktopPane();

				theDesktop.add(frame, BorderLayout.CENTER); // attach internal frame
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				
				frame.setSize(screenSize.width - 20, screenSize.height - 130);

				frame.setVisible(true); // show internal frame
				
				setTabName(fileName);

				tabbedPane.addTab(getTabName(), theDesktop);

				int count = tabbedPane.getTabCount();
				tabbedPane.setSelectedIndex(count - 1);

				mainPanel.add(tabbedPane);
				
				COUNTER++;
				
				br.close();
				reader.close();

			} catch (Exception e) {
				new Error("Opening " + fileName + " failed.");
				return;
			}
		}
		this.setFileOpen(true);
		this.setFileNew(false);	

	}


	// ///////////////////////////////////////////////////////////////////////////
	/**
	 * Save a File method: void dataSave().
	 */
	private void dataSave() {
		int selectedIndex = tabbedPane.getSelectedIndex();
		String filePath = fileInfoMap.get(new Integer(selectedIndex));
		if (editorPane.size() == 0) {
			new Error("No Data loaded");
			return;
		}
		
		if (editorPane.get(selectedIndex) == null) {
			new Error("No Data loaded");
			return;
		}	
		
		if (!filePath.equals(""))
			this.setFileNew(false);
		
		if (isFileNew())
		{		
			dataiSave();			
		}
		else {
			writeFile(filePath);
		}

	}
	
    /**
     * Saves data to file.
     *
     * @param currfileName the currfile name
     */
    public void writeFile(String currfileName) {
    	int selectedIndex = tabbedPane.getSelectedIndex();
        BufferedWriter bufferedWriter = null;
        
        try {
            
            //Create the BufferedWriter 
            bufferedWriter = new BufferedWriter(new FileWriter(currfileName, false));

            bufferedWriter.write(editorPane.get(selectedIndex).getModel().getText());
            
        } catch (FileNotFoundException ex) {
        	ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        } finally {
            // Close
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }
            } catch (IOException ex) {
                ex.getMessage();
            }
        }
    }
	
	
	// ///////////////////////////////////////////////////////////////////////////
	/*
	 * Save Function
	 */
	/**
	 * Datai save.
	 */
	private void dataiSave() {
		int selectedIndex = tabbedPane.getSelectedIndex();
		if (editorPane.size() == 0) {
			new Error("No Data loaded");
			return;
		}
		
		if (editorPane.get(selectedIndex) == null) {
			new Error("No Data loaded");
			return;
		}
		JFileChooser chooser = new JFileChooser("."); // in the current directory
														// start
		int returnVal = chooser.showSaveDialog(JEdit.this);
		if (returnVal == JFileChooser.CANCEL_OPTION)
			return;
		else if (returnVal == JFileChooser.APPROVE_OPTION) {
			File f = chooser.getSelectedFile();
			fileName = chooser.getSelectedFile().getName(); // Get file name
			setFileDir(chooser.getSelectedFile().getPath()); // Read path
			try {
				FileWriter writer = new FileWriter(f);
				editorPane.get(selectedIndex).getModel().write(writer); // Use TextComponent write
				writer.close();
			} catch (Exception e) {
				new Error("Saving failed");
				return;
			}
		}
		
		
		/* String to split. */
		String newName = fileName;
		
		String[] splitName;
		
		/* delimiter - escape special character "." */
		String delimiter = "\\.";
		
		splitName = newName.split(delimiter);				
		
		// Rename tab title		
		tabbedPane.setTitleAt(selectedIndex, splitName[0]);
		
		// updating elements in Map put method key and value pair
		fileInfoMap.put(new Integer(selectedIndex), getFileDir());
		
		this.setFileOpen(true);		
		this.setFileNew(false);	
		
	}

	// ///////////////////////////////////////////////////////////////////////////
	/**
	 * Method - creates a new text document with the special
	 * desired size.
	 */
	private void dataNew() {

		this.setFileNew(true);	
		this.setFileDir("");
		// create internal frame
		JInternalFrame frame = new JInternalFrame("", true, true, true, true);
		
		SpecialTextArea specialArea = new SpecialTextArea(); // create new panel
		editorPane.add(specialArea);
		
		// add or set elements in Map put method key and value pair
		fileInfoMap.put(new Integer(COUNTER), fileDir);
		
		//editorPane.setParamText(""); // Use TextComponent
		frame.add(editorPane.get(COUNTER), BorderLayout.CENTER); // add panel

		frame.pack(); // set internal frame to size of contents
		theDesktop = new JDesktopPane();

		theDesktop.add(frame, BorderLayout.CENTER); // attach internal frame
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		frame.setSize(screenSize.width - 20, screenSize.height - 130);
		frame.setVisible(true); // show internal frame		
		
		setTabName("new " + COUNTER);
		tabbedPane.addTab(getTabName(), theDesktop);
		int count = tabbedPane.getTabCount();
		tabbedPane.setSelectedIndex(count - 1);		
		
		mainPanel.add(tabbedPane);		

		COUNTER++;	
		
		this.setFileOpen(false);

	} // end method actionPerformed
	
	/*
	 *  Method to get the status of the file
	 */		
	/**
	 * Checks if is open.
	 *
	 * @return true, if is open
	 */
	public boolean isOpen()
	{
		return fileStatus;
	}
	
	/*
	 * Method - sets the file status
	 */
	/**
	 * Sets the status.
	 *
	 * @param fileStatus the new status
	 */
	public void setStatus(boolean fileStatus)
	{
		this.fileStatus = fileStatus;
	}
	
	/**
	 * Close the file.
	 *
	 */
	public void dataiClose()
	{
		int selectedIndex = tabbedPane.getSelectedIndex();
		if (editorPane.size() == 0) {
			new Error("No Data loaded");
			return;
		}
		
		if (editorPane.get(selectedIndex) == null) {
			new Error("No Data loaded");
			return;
		}
		
		// Remove the current tab		
		tabbedPane.remove(selectedIndex);
		editorPane.remove(selectedIndex);
		
		// Remove file info
		fileInfoMap.remove(new Integer(selectedIndex));
		
		this.setStatus(false);
		this.setFileNew(false);
		this.setFileOpen(false);
		this.setFileDir("");
		
		// Reduce the number of tabs
		COUNTER--;
	}
	
	// ///////////////////////////////////////////////////////////////////////////
	/*
	 * Method - Cut selected text
	 */
	/**
	 * Cut.
	 */
	private void cut() {
		int selectedIndex = tabbedPane.getSelectedIndex();
		if (editorPane.get(selectedIndex) == null) {
			return;
		}

		//int selectedIndex = tabbedPane.getSelectedIndex();
		//tabbedPane.get
		//System.out.println("Print component " + tabbedPane.getSelectedComponent().getName().toString());
		editorPane.get(selectedIndex).getModel().cut();
	}

	// ///////////////////////////////////////////////////////////////////////////
	/*
	 * Method - Copy selected text to ClipBoard
	 */
	/**
	 * Copy.
	 */
	private void copy() {
		int selectedIndex = tabbedPane.getSelectedIndex();
		if (editorPane.get(selectedIndex) == null) {
			return;
		}
		
		editorPane.get(selectedIndex).getModel().copy(); 
	}

	// ///////////////////////////////////////////////////////////////////////////
	/*
	 * Method - Past text in ClipBoard to new cursor location
	 */
	/**
	 * Paste.
	 */
	private void paste() {
		int selectedIndex = tabbedPane.getSelectedIndex();
		if (editorPane.get(selectedIndex) == null) {
			return;
		}
		
		editorPane.get(selectedIndex).getModel().paste();
	}

	// ///////////////////////////////////////////////////////////////////////////
	/*
	 * Method - Select the all text in the pad
	 */
	/**
	 * Select all.
	 */
	private void selectAll() {
		int selectedIndex = tabbedPane.getSelectedIndex();
		if (editorPane.get(selectedIndex) == null) {
			return;
		}
		editorPane.get(selectedIndex).getModel().selectAll();
	}

	// ///////////////////////////////////////////////////////////////////////////
	/*
	 * Method - bold selected text
	 */
	/**
	 * Bold.
	 */
	private void bold() {
		int selectedIndex = tabbedPane.getSelectedIndex();
		Font a = new Font("Serif", Font.BOLD, 15);
		if (editorPane.get(selectedIndex) == null) {
			return;
		}
		editorPane.get(selectedIndex).getModel().setFont(a);
	}

	// ///////////////////////////////////////////////////////////////////////////
	/*
	 * Method - indent or italicize selected text
	 */
	/**
	 * Italic.
	 */
	private void italic() {
		int selectedIndex = tabbedPane.getSelectedIndex();
		Font a = new Font("Serif", Font.ITALIC, 15);
		if (editorPane.get(selectedIndex) == null) {
			return;
		}
		editorPane.get(selectedIndex).getModel().setFont(a);
	}

	// ///////////////////////////////////////////////////////////////////////////
	/*
	 * Method - change text document to normal
	 */
	/**
	 * Regular.
	 */
	private void regular() {
		int selectedIndex = tabbedPane.getSelectedIndex();
		Font a = new Font("Serif", Font.PLAIN, 15);
		if (editorPane.get(selectedIndex) == null) {
			return;
		}
		editorPane.get(selectedIndex).getModel().setFont(a);
		editorPane.get(selectedIndex).getSideBar().setFont(a);
		editorPane.get(selectedIndex).getModel().setForeground(Color.BLACK);
	}

	// ///////////////////////////////////////////////////////////////////////////
	/*
	 * Method - change font size to 12
	 */
	/**
	 * Twelve.
	 */
	private void twelve() {
		int selectedIndex = tabbedPane.getSelectedIndex();
		Font a = new Font("Serif", Font.PLAIN, 12);
		if (editorPane.get(selectedIndex) == null) {
			return;
		}
		editorPane.get(selectedIndex).getModel().setFont(a);
		editorPane.get(selectedIndex).getSideBar().setFont(a);
	}

	// ///////////////////////////////////////////////////////////////////////////
	/*
	 * Method - change font size to 14
	 */
	/**
	 * Fourteen.
	 */
	private void fourteen() {
		int selectedIndex = tabbedPane.getSelectedIndex();
		Font a = new Font("Serif", Font.PLAIN, 14);
		if (editorPane.get(selectedIndex) == null) {
			return;
		}
		editorPane.get(selectedIndex).getModel().setFont(a);
		editorPane.get(selectedIndex).getSideBar().setFont(a);
	}

	// ///////////////////////////////////////////////////////////////////////////
	/*
	 * Method - change font size to 16
	 */
	/**
	 * Sixteen.
	 */
	private void sixteen() {
		int selectedIndex = tabbedPane.getSelectedIndex();
		Font a = new Font("Serif", Font.PLAIN, 16);
		if (editorPane.get(selectedIndex) == null) {
			return;
		}
		editorPane.get(selectedIndex).getModel().setFont(a);
		editorPane.get(selectedIndex).getSideBar().setFont(a);
	}

	// ///////////////////////////////////////////////////////////////////////////
	/*
	 * @Method - change font type to SansSerif
	 */
	/**
	 * Sansserif.
	 */
	private void sansserif() {
		int selectedIndex = tabbedPane.getSelectedIndex();
		Font a = new Font("SansSerif", Font.PLAIN, 15);
		if (editorPane.get(selectedIndex) == null) {
			return;
		}
		editorPane.get(selectedIndex).getModel().setFont(a);
	}

	// ///////////////////////////////////////////////////////////////////////////
	/*
	 * Method - change font color to Red
	 */
	/**
	 * Red.
	 */
	private void red() {
		int selectedIndex = tabbedPane.getSelectedIndex();
		Font a = new Font("Serif", Font.PLAIN, 15);
		if (editorPane.get(selectedIndex) == null) {
			return;
		}
		editorPane.get(selectedIndex).getModel().setFont(a);
		editorPane.get(selectedIndex).getModel().setForeground(Color.RED);
	}

	// ///////////////////////////////////////////////////////////////////////////
	/*
	 * Method - change font color to Blue
	 */
	/**
	 * Blue.
	 */
	private void blue() {
		int selectedIndex = tabbedPane.getSelectedIndex();
		Font a = new Font("Serif", Font.PLAIN, 15);
		if (editorPane.get(selectedIndex) == null) {
			return;
		}
		editorPane.get(selectedIndex).getModel().setFont(a);
		editorPane.get(selectedIndex).getModel().setForeground(Color.BLUE);
	}

	// ////////////////////////////////////////////////////////////////////////////
	/**
	 * Coffee.
	 *
	 * @Make Java Coffee :-D
	 */
	private void coffee() {
		JOptionPane.showMessageDialog(this, "Coffee is ready :-)", "JEditor",
				JOptionPane.INFORMATION_MESSAGE);
	}

	// ////////////////////////////////////////////////////////////////////////////
	/**
	 * About-Window.
	 */
	private void about() {
		JOptionPane.showMessageDialog(this, "2011 (c) Michael Aro\n\n"
				+ "This Software is licensed under the terms of\n"
				+ "the Free Software License", "JEditor",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Sets the file dir.
	 *
	 * @param fileDir the fileDir to set
	 */
	public void setFileDir(String fileDir) {
		this.fileDir = fileDir;
	}

	/**
	 * Gets the file dir.
	 *
	 * @return the fileDir
	 */
	public String getFileDir() {
		return fileDir;
	}

	/**
	 * Sets the tab name.
	 *
	 * @param tabName the tabName to set
	 */
	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	/**
	 * Gets the tab name.
	 *
	 * @return the tabName
	 */
	public String getTabName() {
		return tabName;
	}

	/**
	 * Sets the file open.
	 *
	 * @param fileOpen the fileOpen to set
	 */
	public void setFileOpen(boolean fileOpen) {
		this.fileOpen = fileOpen;
	}

	/**
	 * Checks if is file open.
	 *
	 * @return the fileOpen
	 */
	public boolean isFileOpen() {
		return fileOpen;
	}

	/**
	 * Sets the file new.
	 *
	 * @param fileNew the fileNew to set
	 */
	public void setFileNew(boolean fileNew) {
		this.fileNew = fileNew;
	}

	/**
	 * Checks if is file new.
	 *
	 * @return the fileNew
	 */
	public boolean isFileNew() {
		return fileNew;
	}
}

// /////////////////////////////////////////////////////////////////////////////
/**
 * @Provides for the end of the program when closing the window
 */
class WindowCallback extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}

/*
 * Justification of the program structure: JEdit contains
 * classes JEdit, SpecialTextArea, Error and MainMenu, and it contains various utility classes for
 * specific tasks.
 * 
 * All data and functions that will act on it are stored in the SpecialTextArea class. In
 * SpecialTextArea, the graphic output of the text area structure is created. JEdit
 * itself then provides the framework for the entire program ready with the main
 * menu, dialog boxes.
 * 
 */
