/*
 * 
 */
package jpad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;

// TODO: Auto-generated Javadoc
/**
 * The Class SpecialTextArea.
 *
 * @author Michael
 * class to display Text on a panel
 */
public class SpecialTextArea extends JPanel {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1281725210182615412L;

	/** The area scroll pane. */
	private JScrollPane theAreaScrollPane = null;

	/** The text area. */
	private JTextArea theTextArea = null;
	
	/** The line number. */
	private JTextArea sideLineBar;
	
	/** The text field. */
	private JTextField sBar;
	
	/** The text parameter. */
	private String textParameter = "";

	/*
	 * Constructor - create a text area.
	 */
	/**
	 * Instantiates a new special text area.
	 */
	public SpecialTextArea() {		
		theTextArea = new JTextArea(textParameter);
		
		theTextArea.setFont(new Font("Serif", Font.PLAIN, 15));
		theTextArea.setLineWrap(true);
		theTextArea.setWrapStyleWord(true);
		
		sideLineBar = new JTextArea("1");
		sideLineBar.setFont(theTextArea.getFont());
		
		sideLineBar.setBackground(Color.LIGHT_GRAY);
		sideLineBar.setEditable(false);
		
		theTextArea.getDocument().addDocumentListener(new DocumentListener(){
			public String getText(){
				int cursorLocation = theTextArea.getDocument().getLength();
				Element rootElement = theTextArea.getDocument().getDefaultRootElement();
				String sideNumber = "1" + System.getProperty("line.separator");
				for(int i = 2; i < rootElement.getElementIndex( cursorLocation ) + 2; i++){
					sideNumber += i + System.getProperty("line.separator");
				}
				return sideNumber;
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				sideLineBar.setText(getText());
			}
 
			@Override
			public void insertUpdate(DocumentEvent e) {
				sideLineBar.setText(getText());
			}
 
			@Override
			public void removeUpdate(DocumentEvent e) {
				sideLineBar.setText(getText());
			}
 
		});
		
		// Create a scrollPane
		theAreaScrollPane = new JScrollPane();

		theAreaScrollPane.getViewport().add(theTextArea);
		theAreaScrollPane.setRowHeaderView(sideLineBar);

		
	
		theAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//theAreaScrollPane.setPreferredSize(new Dimension(screenSize.width - 30, screenSize.height - 185));
		theAreaScrollPane.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder("Code Area - [Note to user: Please use the Menu Item Commands, keyboard shortcuts / keys not yet implemented]"),
						BorderFactory.createEmptyBorder(6, 6, 6, 6)),
				theAreaScrollPane.getBorder()));
		
        // Add a cursor Listener to the the text area.
		theTextArea.addCaretListener(new CaretListener() {
				public void caretUpdate(CaretEvent e) {
                JTextArea dArea = (JTextArea)e.getSource();

                // initialize row and column
                int rowNum = 1;
                int colNum = 1;
                
                try {

                    int caretpos = dArea.getCaretPosition();
                    rowNum = dArea.getLineOfOffset(caretpos);

                    colNum = caretpos - dArea.getLineStartOffset(rowNum);

                    rowNum += 1;
                }
                catch(Exception ex) { }

                // Update bar with the position of cursor.
                updatesBar(rowNum, colNum);
            }
        });		
		
		// init();
		this.setLayout(new BorderLayout());
		this.add(theAreaScrollPane, BorderLayout.CENTER);
		
		sBar = new JTextField();
		this.add(sBar, BorderLayout.SOUTH);

        // Gives the sBar update value
        updatesBar(1,0);

	} // end SpecialtheTextArea constructor	  

	// ///////////////////////////////////////////////////////////////////////////
	/**
	 * Returns the data model - theTextArea.
	 *
	 * @return the model
	 */
	
	public JTextArea getModel() {
		return (theTextArea);
	}
	
	// ///////////////////////////////////////////////////////////////////////////
	/**
	 * Returns the data model - sideLineBar.
	 *
	 * @return the model
	 */
	
	public JTextArea getSideBar() {
		return (sideLineBar);
	}
	
	// ///////////////////////////////////////////////////////////////////////////
	/**
	 * Returns the data model - theTextArea.
	 *
	 * @param textParameter the new param text
	 * @return the model
	 */
	
	public void setParamText(String textParameter) {
		this.textParameter = textParameter;
	}

    // Row and column number.
    /**
     * Updates bar.
     *
     * @param rowNum the row num
     * @param colNum the col num
     */
    private void updatesBar(int rowNum, int colNum) {
        sBar.setText("Line: " + rowNum + " Column: " + colNum);
    }

} // end class SpecialtheTextArea