/**Author Name: Blake Rollins
*Date 11/05/2020
*Program Name: Rollins_module10_word_occurrence_with_javadoc
*Purpose: Adding JavaDoc to existing application
*/

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.TextArea;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;

public class Rollins_TextAnalyzer extends JDialog {

	/**
	 * Launches the the application which calls the Rollins_TextAnalyzer constructor.
	 * @param IOException throws an input/output error if file is not file
	 * @throws IOException is handled by generic input/output which prints to StackTrace
	 * @param args, Exception IOException throws IOException
	 */
	public static void main(String[] args, Exception IOException) throws IOException {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rollins_TextAnalyzer dialog = new Rollins_TextAnalyzer();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});	
		
	}

	/**
	 * Creates the dialog which contains the text area to store output and button to execute the program. Once the button is pressed, the program will scan a file titled
	 * shakespeare.txt identifying and counting repeating words. Special characters will be removed and all text will be changed to lower case. The output will be sorted in
	 * descending order and not alphabetically. 
	 */
	public Rollins_TextAnalyzer() {
		
		setForeground(Color.BLACK);
		setTitle("Rollins_TextAnalyzer");
		setBounds(100, 100, 566, 416);
		getContentPane().setLayout(null);
		
		//creating text area to store output

		TextArea textArea = new TextArea();
		textArea.setBackground(Color.WHITE);
		textArea.setForeground(Color.BLACK);
		textArea.setEditable(false);
		textArea.setBounds(10, 10, 530, 327);
		getContentPane().add(textArea);
		
		//creating button to execute program
		 
		JButton btnExecute = new JButton("Execute");
		btnExecute.setBounds(289, 343, 89, 23);
		getContentPane().add(btnExecute);
		
		JLabel lblPressButtonTo = new JLabel("Press Button To See \"Word Occurrences\" ---->");
		lblPressButtonTo.setForeground(Color.RED);
		lblPressButtonTo.setBounds(10, 347, 333, 14);
		getContentPane().add(lblPressButtonTo);

		//button event handler
		btnExecute.addActionListener(new ActionListener() {
						
			public void actionPerformed(ActionEvent arg0) {
				
				// Name of file that's being scanned. File stored within workspace
				File file = new File("shakespeare.txt");

				// Input
				Scanner keyboard = null;
				try {
					keyboard = new Scanner(file);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Map<String, Integer> word = new HashMap<String, Integer>();

				while (keyboard.hasNext()) {
					
					// scan each word
					String frequency = keyboard.next();
					
					// removing special characters and setting all words to lower case letters
					String newFrequency = frequency.replaceAll("[^a-zA-Z0-9]", " ").toLowerCase();
									
					//insert by setting the newFrequency as +1
					if (word.containsKey(newFrequency) == false)
													
						word.put(newFrequency, 1);
					
					// remove entry from map and add 1 in the newFrequency
					else 
					{
						//find word count
						int count = (int) (word.get(newFrequency));

						// adding word and increase newFrequency by 1 if it doesn't exist
						word.put(newFrequency, count + 1);
					}
				}

				// retrieving data set
				Set<Map.Entry<String, Integer>> dataset = word.entrySet();

				// creating an array to house data set
				List<Map.Entry<String, Integer>> descendingSort = new ArrayList<Map.Entry<String, Integer>>(dataset); 

				// sorting array data set
				Collections.sort(descendingSort, new Comparator<Map.Entry<String, Integer>>() 

				{
					// compare for sorting

					public int compare(Map.Entry<String, Integer> first, Map.Entry<String, Integer> second) {
						
						// descending sorting
						return (second.getValue()).compareTo(first.getValue()); 
					}
				});
								
				StringBuilder results = new StringBuilder();
				
				//add values to results
				for (Map.Entry<String, Integer> entry : descendingSort){
					results.append(entry.getKey());
					results.append(" ---------- ");
					results.append(entry.getValue());
					results.append("\n");
				}
				
				//display results to text area
				textArea.setText(results.toString());

				keyboard.close();
			}
		});
		
	}
}
