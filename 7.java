import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator extends JFrame implements ActionListener {

    private JTextField numField1, numField2, resultField;
    private JButton addButton, subtractButton, multiplyButton, divideButton, clearButton;
    private JLabel title;

    public SimpleCalculator() {

        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(320, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(Color.YELLOW);

        getContentPane().setBackground(Color.DARK_GRAY);

        title = new JLabel("Arithmetic Calculator.");
        title.setBounds(30, 20, 280, 30);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        numField1 = new JTextField();
        numField2 = new JTextField();
        resultField = new JTextField();
        resultField.setEditable(false);

        addButton = new JButton("Add");
        subtractButton = new JButton("Subtract");
        multiplyButton = new JButton("Multiply");
        divideButton = new JButton("Divide");
        clearButton = new JButton("CE");

        numField1.setBounds(20, 120, 100, 25);
        numField2.setBounds(20, 150, 100, 25);
        resultField.setBounds(90, 60, 150, 25);
        addButton.setBounds(20, 200, 90, 30);
        subtractButton.setBounds(20, 240, 90, 30);
        multiplyButton.setBounds(20, 280, 90, 30);
        divideButton.setBounds(20, 320, 90, 30);
        clearButton.setBounds(20, 60, 60, 24);


        addButton.addActionListener(this);
        subtractButton.addActionListener(this);
        multiplyButton.addActionListener(this);
        divideButton.addActionListener(this);
        clearButton.addActionListener(this);

        addButton.setFocusable(false);
        subtractButton.setFocusable(false);
        multiplyButton.setFocusable(false);
        divideButton.setFocusable(false);
        clearButton.setFocusable(false);

        addButton.setBackground(Color.BLACK);
        subtractButton.setBackground(Color.BLACK);
        multiplyButton.setBackground(Color.BLACK);
        divideButton.setBackground(Color.BLACK);
        clearButton.setBackground(Color.BLACK);
        resultField.setBackground(Color.LIGHT_GRAY);

        addButton.setForeground(Color.WHITE);
        subtractButton.setForeground(Color.WHITE);
        multiplyButton.setForeground(Color.WHITE);
        divideButton.setForeground(Color.WHITE);
        clearButton.setForeground(Color.WHITE);

        add(numField1);
        add(numField2);
        add(resultField);
        add(addButton);
        add(subtractButton);
        add(multiplyButton);
        add(divideButton);
        add(title);
        add(clearButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

            try {
                double num1 = Double.parseDouble(numField1.getText());
                double num2 = Double.parseDouble(numField2.getText());
                double result = 0.0;


                if (e.getSource() == addButton) {
                    result = num1 + num2;
                } else if (e.getSource() == subtractButton) {
                    result = num1 - num2;
                } else if (e.getSource() == multiplyButton) {
                    result = num1 * num2;
                } else if (e.getSource() == divideButton) {
                    if (num2 == 0) {
                        JOptionPane.showMessageDialog(this, "Error: Cannot divide by zero!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    result = num1 / num2;
                }

                resultField.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(this, "Error: Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
               }

            if (e.getSource() == clearButton) {
                resultField.setText(null);
                numField1.setText(null);
                numField2.setText(null);
            }

        }


    public static void main(String[] args) {
        new SimpleCalculator();
    }
}
