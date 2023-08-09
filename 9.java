import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import javax.swing.*;

public class Filter_Datatype_Sort {
    private static final int size = 100000;

    public static void main(String args[]) {
        JFrame frame = new JFrame("Sorter");
        frame.setSize(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        String dataTypes[] = { "Integers", "Double", "Long", "Strings" };
        final JComboBox<String> selector = new JComboBox<>(dataTypes);
        JButton sortButton = new JButton("Sort");
        mainPanel.add(selector);
        mainPanel.add(sortButton);
        frame.add(mainPanel, BorderLayout.NORTH);

        JPanel timePanel = new JPanel();
        JLabel label1 = new JLabel("Time Taken:");
        final JTextField timeField = new JTextField(10);
        timeField.setEditable(false);
        timePanel.add(label1);
        timePanel.add(timeField);
        frame.add(timePanel, BorderLayout.CENTER);

        frame.setVisible(true);

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                String dataTypeSelected = (String) selector.getSelectedItem();
                long end, start, time;

                switch (dataTypeSelected) {
                    case "Integers":
                        int[] randomIntegers = new int[size];
                        for (int i = 0; i < randomIntegers.length; i++) {
                            randomIntegers[i] = random.nextInt();
                        }
                        start = System.currentTimeMillis();
                        Arrays.sort(randomIntegers);
                        end = System.currentTimeMillis();
                        time = end - start;
                        timeField.setText(time + " ms");
                        break;

                    case "Double":
                        double[] randomDoubles = new double[size];
                        for (int i = 0; i < randomDoubles.length; i++) {
                            randomDoubles[i] = random.nextDouble();
                        }
                        start = System.currentTimeMillis();
                        Arrays.sort(randomDoubles);
                        end = System.currentTimeMillis();
                        time = end - start;
                        timeField.setText(time + " ms");
                        break;

                    case "Long":
                        long[] randomLong = new long[size];
                        for (int i = 0; i < randomLong.length; i++) {
                            randomLong[i] = random.nextLong();
                        }
                        start = System.currentTimeMillis();
                        Arrays.sort(randomLong);
                        end = System.currentTimeMillis();
                        time = end - start;
                        timeField.setText(time + " ms");
                        break;

                    case "Strings":
                        String[] randomStrings = new String[size];
                        for (int i = 0; i < randomStrings.length; i++) {
                            randomStrings[i] = UUID.randomUUID().toString();
                        }
                        start = System.currentTimeMillis();
                        Arrays.sort(randomStrings);
                        end = System.currentTimeMillis();
                        time = end - start;
                        timeField.setText(time + " ms");
                        break;

                    default:
                        break;
                }
            }
        });
    }
}
