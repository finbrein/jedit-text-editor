/*
 * 
 */
package jpad;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
// TODO: Auto-generated Javadoc
/**
 * <b>Program:</b> JEdit - Programmer's Editor<br>
 * <b>Copyright:</b> 2011 Michael Aro<br>
 * <b>License:</b> Free Software<br>
 * <b>Version:</b> 1.0<br>
 * <b>Date:</b> 24.05.2011<br>
 * <br>
 * <b>Info:</b> MainMenu is the main menu of the program to
 */
public class MainMenu extends JMenuBar {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -899601801927625991L;

/////////////////////////////////////////////////////////////////////////////
  /**
 * Constructor - Creates the menu and bind it to the ActionListener.
 *
 * @param l the l
 */
  public MainMenu( ActionListener l ) {
    JMenu menu = null;
    menu = new JMenu("File");
    // Syntax MakeMI:
    /* makeMI( String label, String name, ActionListener listener, char Shortcut ) */
    menu.add( makeMI("New...", "new", l, 'n') );
    menu.add( makeMI("Open...", "open", l, 'o') );
    menu.addSeparator();
    menu.add( makeMI("Save", "csave", l, 'v') );
    menu.add( makeMI("Save AS...", "save", l, 's') );
    menu.addSeparator();
    menu.add( makeMI("Close", "close", l, 'c') );
    menu.add( makeMI("Exit", "exit", l, 'x') );
    
    add(menu);
    
    menu = new JMenu("Edit");
    menu.add( makeMI("Cut", "cut", l, 'k') );
    menu.add( makeMI("Copy", "copy", l, 'c') );
    menu.add( makeMI("Paste", "paste", l, 'p') );
    menu.addSeparator();
    menu.add( makeMI("Select All", "select", l, 's') );
    
    add(menu);
    
    menu = new JMenu("Format");
    menu.add( makeMI("Bold", "bold", l, 'b') );
    menu.add( makeMI("Italic", "italic", l, 'i') );
    menu.add( makeMI("Regular", "regular", l, 'r') );
    menu.addSeparator();
    menu.add( makeMI("12", "twelve", l, 't') );
    menu.add( makeMI("14", "fourteen", l, 'f') );
    menu.add( makeMI("16", "sixteen", l, 'f') );
    menu.addSeparator();
    menu.add( makeMI("SansSerif", "sansserif", l, 's') );
    menu.addSeparator();
    menu.add( makeMI("Red", "red", l, 'r') );
    menu.add( makeMI("Blue", "blue", l, 'u') );
     
    add(menu);
    
    menu = new JMenu("Help");
    menu.add( makeMI("Make coffee", "coffee", l, 'c') );
    menu.add( makeMI("About", "about", l, 'a') );

    add(menu);

  }
  
  /////////////////////////////////////////////////////////////////////////////
  /**
   * Method Add a menu entry:.
   *
   * @param Label the label
   * @param name the name
   * @param listener the listener
   * @param SC the sC
   * @return the j menu item
   */
  private JMenuItem makeMI (String Label, String name, ActionListener listener, char SC) {
    JMenuItem mi;
    mi = new JMenuItem(  Label,SC);
    mi.setActionCommand( name);
    mi.addActionListener(listener);
    return mi;
  }
}