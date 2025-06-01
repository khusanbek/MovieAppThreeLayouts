
package screen.basic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieScreen {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createMovieSelectionWindow(
                "Inception",
                "images/inception.jpg",
                new String[]{"10:00", "12:40", "15:20"}
        ));
    }

    public static void createMovieSelectionWindow(String movieTitle, String imagePath, String[] screens) {
        
		JFrame frame = new JFrame("Select Screen - " + movieTitle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Poster Image
        ImageIcon posterIcon = getScaledIconByWidth(imagePath, 300);
        JLabel posterLabel = new JLabel(posterIcon);
        posterLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(posterLabel, BorderLayout.NORTH);

        // Center Panel for screens
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel screensLabel = new JLabel("Available Screens:");
        screensLabel.setFont(new Font("Arial", Font.BOLD, 14));
        centerPanel.add(screensLabel);
        centerPanel.add(Box.createVerticalStrut(10));

        ButtonGroup screenGroup = new ButtonGroup();
        JRadioButton[] screenButtons = new JRadioButton[screens.length];

        for (int i = 0; i < screens.length; i++) {
            screenButtons[i] = new JRadioButton(screens[i]);
            screenButtons[i].setFont(new Font("Arial", Font.PLAIN, 13));
            screenGroup.add(screenButtons[i]);
            centerPanel.add(screenButtons[i]);
            centerPanel.add(Box.createVerticalStrut(5));
        }

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Next Button
        JButton nextButton = new JButton("Next >");
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextButton.setPreferredSize(new Dimension(100, 40));
        nextButton.addActionListener(e -> {
            for (JRadioButton btn : screenButtons) {
                if (btn.isSelected()) {
                    JOptionPane.showMessageDialog(frame, "Selected: " + btn.getText());
                    // Proceed to next screen
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Please select a screen.");
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(nextButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    // Scale image by width, preserving aspect ratio
    private static ImageIcon getScaledIconByWidth(String imagePath, int targetWidth) {
        ImageIcon icon = new ImageIcon(imagePath);
        int originalWidth = icon.getIconWidth();
        int originalHeight = icon.getIconHeight();

        int targetHeight = (int) ((double) targetWidth / originalWidth * originalHeight);
        Image scaled = icon.getImage().getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }
}
