package theory;

import javax.swing.*;
import javax.swing.border.TitledBorder; 
import java.awt.*;
import java.awt.event.*;

public class Assignment1 extends JFrame implements ActionListener {

    JTextField topicField, locationField;
    JComboBox<Integer> hourBox, dayBox, monthBox, yearBox;
    JComboBox<String> minuteBox;
    JButton addButton, saveButton;

    String[] meetingArray = new String[100];
    int count = 0;

    public Assignment1() {
        setTitle("Meeting Manager Application");
        setSize(450, 320); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        Color beigeColor = new Color(245, 245, 220); 
        Color brownColor = new Color(139, 69, 19);   

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 15));
        mainPanel.setBackground(beigeColor); 

        TitledBorder myBorder = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(brownColor, 2), 
            "Meeting Manager", 
            TitledBorder.LEFT, 
            TitledBorder.TOP,  
            new Font("Serif", Font.BOLD, 24), 
            brownColor 
        );
        
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10), 
            myBorder
        ));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 2, 5, 10)); 
        centerPanel.setBackground(beigeColor); 

        JLabel topicLabel = new JLabel("Topic:");
        topicLabel.setForeground(brownColor); 
        centerPanel.add(topicLabel);
        topicField = new JTextField(18); 
        centerPanel.add(topicField);

        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setForeground(brownColor); 
        centerPanel.add(locationLabel);
        locationField = new JTextField(18); 
        centerPanel.add(locationField);

        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setForeground(brownColor); 
        centerPanel.add(timeLabel);
        
        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0)); 
        timePanel.setBackground(beigeColor);
        
        Integer[] hours = {8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        hourBox = new JComboBox<>(hours);
        String[] minutes = {"00", "15", "30", "45"};
        minuteBox = new JComboBox<>(minutes);
        
        timePanel.add(hourBox);
        JLabel colonLabel = new JLabel(" : ");
        colonLabel.setForeground(brownColor);
        timePanel.add(colonLabel);
        timePanel.add(minuteBox);
        centerPanel.add(timePanel);

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setForeground(brownColor); 
        centerPanel.add(dateLabel);
        
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        datePanel.setBackground(beigeColor);
        
        Integer[] days = new Integer[31];
        for (int i = 0; i < 31; i++) days[i] = i + 1;
        dayBox = new JComboBox<>(days);

        Integer[] months = new Integer[12];
        for (int i = 0; i < 12; i++) months[i] = i + 1;
        monthBox = new JComboBox<>(months);

        Integer[] years = {2019, 2020, 2021, 2022};
        yearBox = new JComboBox<>(years);

        JLabel dash1 = new JLabel(" - ");
        dash1.setForeground(brownColor);
        JLabel dash2 = new JLabel(" - ");
        dash2.setForeground(brownColor);

        datePanel.add(dayBox);
        datePanel.add(dash1);
        datePanel.add(monthBox);
        datePanel.add(dash2);
        datePanel.add(yearBox);
        centerPanel.add(datePanel);

        JPanel leftAlignWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        leftAlignWrapper.setBackground(beigeColor);
        leftAlignWrapper.add(centerPanel);
        
        mainPanel.add(leftAlignWrapper, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 0)); 
        bottomPanel.setBackground(beigeColor);

        addButton = new JButton("ADD");
        addButton.setPreferredSize(new Dimension(100, 25)); 
        addButton.setForeground(brownColor); 
        
        saveButton = new JButton("Save (0 Meetings)");
        saveButton.setForeground(brownColor); 
        saveButton.setPreferredSize(new Dimension(150, 25));

        addButton.addActionListener(this);
        saveButton.addActionListener(this);

        bottomPanel.add(addButton);
        bottomPanel.add(saveButton);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        getContentPane().setBackground(beigeColor);
        add(mainPanel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String t = topicField.getText();
            String l = locationField.getText();
            String time = hourBox.getSelectedItem() + ":" + minuteBox.getSelectedItem();
            String date = dayBox.getSelectedItem() + "-" + monthBox.getSelectedItem() + "-" + yearBox.getSelectedItem();

            meetingArray[count] = "Topic: " + t + "\nLocation: " + l + "\nTime: " + time + "\nDate: " + date;
            count++; 

            topicField.setText("");
            locationField.setText("");
            hourBox.setSelectedIndex(0);
            minuteBox.setSelectedIndex(0);
            dayBox.setSelectedIndex(0);
            monthBox.setSelectedIndex(0);
            yearBox.setSelectedIndex(0);
            
            saveButton.setText("Save (" + count + " Meetings)");
        } 
        else if (e.getSource() == saveButton) {
            String printAll = ""; 
            for (int i = 0; i < count; i++) {
                printAll = printAll + "--- Meeting " + (i + 1) + " ---\n" + meetingArray[i] + "\n\n";
            }
            
            if (count == 0) {
                JOptionPane.showMessageDialog(null, "No meetings yet!");
            } else {
                JOptionPane.showMessageDialog(null, printAll);
            }
        }
    }

    public static void main(String[] args) {
        Assignment1 myFrame = new Assignment1();
        myFrame.setVisible(true);
    }
}