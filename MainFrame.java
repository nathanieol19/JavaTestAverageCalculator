import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private JLabel titleLabel, inputLabel, outputLabel;
    private JTextField gradeField;
    private JButton enterButton;
    private JTextArea outputArea;
    private int[] quizGrades = new int[10];
    private int gradeCount = 0;

    public MainFrame() {
        setTitle("Quiz Grade Calculator");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleLabel = new JLabel("Quiz Grade Calculator");
        inputLabel = new JLabel("Enter quiz grade (999 to quit): ");
        outputLabel = new JLabel("Grades entered:");
        gradeField = new JTextField(5);
        enterButton = new JButton("Enter");
        outputArea = new JTextArea(10, 20);
        outputArea.setEditable(false);

        add(titleLabel);
        add(inputLabel);
        add(gradeField);
        add(enterButton);
        add(outputLabel);
        add(outputArea);

        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int grade = Integer.parseInt(gradeField.getText());
                    if (grade == 999 || gradeCount >= 10) {
                        calculateAndDisplayResults();
                    } else {
                        quizGrades[gradeCount] = grade;
                        gradeCount++;
                        gradeField.setText("");
                        outputArea.append(grade + "\n");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
                }
            }
        });
    }

    private void calculateAndDisplayResults() {
        double total = 0;
        for (int i = 0; i < gradeCount; i++) {
            total += quizGrades[i];
        }

        double average = total / gradeCount;
        String letterGrade = getLetterGrade(average);

        outputArea.append("Average Grade: " + average + "\n");
        outputArea.append("Letter Grade: " + letterGrade + "\n");

        gradeField.setEditable(false);
        enterButton.setEnabled(false);
    }

    private String getLetterGrade(double average) {
        if (average >= 90) {
            return "A";
        } else if (average >= 80) {
            return "B";
        } else if (average >= 70) {
            return "C";
        } else if (average >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        MainFrame calculator = new MainFrame();
        calculator.setSize(300, 400);
        calculator.setVisible(true);
    }
}
