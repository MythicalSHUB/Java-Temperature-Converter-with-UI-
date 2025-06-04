import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class TemperatureConverterUI extends JFrame {
    private JTextField inputField;
    private JLabel resultLabel1;
    private JLabel resultLabel2;
    private JComboBox<String> sourceUnitComboBox;
    private DecimalFormat df = new DecimalFormat("#.##");

    public TemperatureConverterUI() {
        // Setup the frame
        setTitle("Temperature Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create the main panel with some padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create title label
        JLabel titleLabel = new JLabel("Temperature Converter", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Create input panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        
        JLabel sourceLabel = new JLabel("Source Temperature:");
        inputPanel.add(sourceLabel);
        
        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(inputField);
        
        JLabel unitLabel = new JLabel("Source Unit:");
        inputPanel.add(unitLabel);
        
        sourceUnitComboBox = new JComboBox<>(new String[]{"Celsius (°C)", "Kelvin (K)", "Fahrenheit (°F)"});
        inputPanel.add(sourceUnitComboBox);
        
        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });
        inputPanel.add(convertButton);
        
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputField.setText("");
                resultLabel1.setText("Result 1: ");
                resultLabel2.setText("Result 2: ");
            }
        });
        inputPanel.add(clearButton);
        
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        
        // Create result panel
        JPanel resultPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        resultPanel.setBorder(BorderFactory.createTitledBorder("Results"));
        
        resultLabel1 = new JLabel("Result 1: ");
        resultLabel1.setFont(new Font("Arial", Font.PLAIN, 14));
        resultPanel.add(resultLabel1);
        
        resultLabel2 = new JLabel("Result 2: ");
        resultLabel2.setFont(new Font("Arial", Font.PLAIN, 14));
        resultPanel.add(resultLabel2);
        
        mainPanel.add(resultPanel, BorderLayout.SOUTH);
        
        // Add the main panel to the frame
        add(mainPanel);
        
        // Make enter key work for conversion
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    convertTemperature();
                }
            }
        });
    }
    
    private void convertTemperature() {
        try {
            double temperature = Double.parseDouble(inputField.getText());
            int selectedUnit = sourceUnitComboBox.getSelectedIndex();
            
            switch (selectedUnit) {
                case 0: // Celsius
                    double kelvin = temperature + 273.15;
                    double fahrenheit = (temperature * 9/5) + 32;
                    resultLabel1.setText("Kelvin (K): " + df.format(kelvin));
                    resultLabel2.setText("Fahrenheit (°F): " + df.format(fahrenheit));
                    break;
                case 1: // Kelvin
                    double celsius = temperature - 273.15;
                    fahrenheit = (celsius * 9/5) + 32;
                    resultLabel1.setText("Celsius (°C): " + df.format(celsius));
                    resultLabel2.setText("Fahrenheit (°F): " + df.format(fahrenheit));
                    break;
                case 2: // Fahrenheit
                    celsius = (temperature - 32) * 5/9;
                    kelvin = celsius + 273.15;
                    resultLabel1.setText("Celsius (°C): " + df.format(celsius));
                    resultLabel2.setText("Kelvin (K): " + df.format(kelvin));
                    break;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a valid number", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Run the application
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TemperatureConverterUI().setVisible(true);
            }
        });
    }
}