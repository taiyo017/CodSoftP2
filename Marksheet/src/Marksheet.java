import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Marksheet extends JFrame implements ActionListener {
    JLabel nameLabel, marksLabel[];
    JTextField nameField, marksField[];
    JButton calculateButton;
    JTextArea markSheetArea;
    JScrollPane scrollPane;
    
    public Marksheet() {
        setTitle("Mark Sheet");
        setSize(400, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        nameLabel = new JLabel("Enter Name:");
        nameLabel.setBounds(20, 20, 100, 20);
        add(nameLabel);
        
        nameField = new JTextField();
        nameField.setBounds(120, 20, 200, 20);
        add(nameField);
        
        marksLabel = new JLabel[7];
        marksField = new JTextField[7];
        String[] subjects = {"English", "Mathematics", "Science", "History", "Computer Science", "Health Education", "Economics"};
        for (int i = 0; i < 7; i++) {
            marksLabel[i] = new JLabel(subjects[i] + " Marks:");
            marksLabel[i].setBounds(20, 50 + (i * 30), 120, 20);
            add(marksLabel[i]);
            
            marksField[i] = new JTextField();
            marksField[i].setBounds(150, 50 + (i * 30), 100, 20);
            add(marksField[i]);
        }
        
        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(150, 50 + (7 * 30) + 10, 100, 30);
        calculateButton.addActionListener(this);
        add(calculateButton);
        
        markSheetArea = new JTextArea();
        markSheetArea.setEditable(false);
        markSheetArea.setBounds(20, 50 + (7 * 30) + 50, 250, 250);
        add(markSheetArea);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            String name = nameField.getText();
            int[] marks = new int[7];
            for (int i = 0; i < 7; i++) {
                try {
                    int mark = Integer.parseInt(marksField[i].getText());
                    if (mark < 0 || mark > 100) {
                        JOptionPane.showMessageDialog(this, "Marks for " + marksLabel[i].getText().split(" ")[0] + " should be between 0 and 100");
                        return;
                    }
                    marks[i] = mark;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter valid marks for " + marksLabel[i].getText().split(" ")[0]);
                    return;
                }
            }
            double percentage = calculatePercentage(marks);
            String grade = calculateGrade(percentage);
            String result = "Name: " + name + "\n";
            result += "Marks: \n";
            for (int i = 0; i < 7; i++) {
                result += marksLabel[i].getText().split(" ")[0] + ": " + marks[i] + "\n";
            }
            result += "Percentage: " + percentage + "%\n";
            result += "Grade: " + grade + "\n";
            markSheetArea.setText(result);
        }
    }
    
    private double calculatePercentage(int[] marks) {
        int totalMarks = 0;
        for (int mark : marks) {
            totalMarks += mark;
        }
        return (double) totalMarks / (marks.length * 100) * 100;
    }
    
    private String calculateGrade(double percentage) {
        if (percentage >= 90) {
            return "A";
        } else if (percentage >= 80) {
            return "B";
        } else if (percentage >= 70) {
            return "C";
        } else if (percentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
    
    public static void main(String[] args) {
        new Marksheet();
    }
}
