
package movie.reserve;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MovieReservationApp {
	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MovieReservationApp::createAndShowGUI);
    }

    private static void createAndShowGUI() {
		
        JFrame frame = new JFrame("Movie Reservation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1110, 600);

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30)); // horizontal & vertical gaps

        // Example data
        String[] movieTitles = {
            "Inception", "Interstellar", "The Matrix", "The Dark Knight", "Pink Panther"
        };
        String[] showtimes = {
            "12:00, 15:00, 18:00", "13:00, 16:00", "14:00, 19:00", "11:00, 17:00, 20:00", "10:30, 14:30, 18:30"
        };
		String[] movieImages = {
            "inception.jpg", "interstellar.jpg", "matrix.jpg", "dark-knight.jpg", "pink-panther.jpg"
        };
		
        for (int i = 0; i < movieTitles.length; i++) {
            containerPanel.add(createMovieBox(movieTitles[i], showtimes[i], movieImages[i]));
        }

        frame.add(containerPanel);
        frame.setVisible(true);
    }

    private static JPanel createMovieBox(String title, String times, String imageName) {
        
		JPanel box = new JPanel();
        box.setPreferredSize(new Dimension(180, 220));
        box.setLayout(new BorderLayout());
        box.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        box.setBackground(Color.WHITE);

        // Placeholder image
        // ImageIcon imageIcon = new ImageIcon(new BufferedImage(180, 120, BufferedImage.TYPE_INT_RGB));
        // JLabel imageLabel = new JLabel(imageIcon);
        // imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

		// Real image
		ImageIcon icon = new ImageIcon("images/" + imageName);
		Image scaledImage = icon.getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH);
		JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));


        // Movie title
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Showtimes
        JLabel timesLabel = new JLabel("<html><center>" + times.replaceAll(",", "<br>") + "</center></html>", SwingConstants.CENTER);
        timesLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		timesLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); // top, left, bottom, right

        // Put components together
        box.add(imageLabel, BorderLayout.NORTH);
        box.add(titleLabel, BorderLayout.CENTER);
        box.add(timesLabel, BorderLayout.SOUTH);

        return box;
    }
}
