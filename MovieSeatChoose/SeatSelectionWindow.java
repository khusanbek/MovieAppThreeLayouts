
package movie.seat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeatSelectionWindow extends JFrame {
	
    private static final int ROWS = 4;
    private static final int COLUMNS = 7;
    private JButton[][] seatButtons = new JButton[ROWS][COLUMNS];

    public SeatSelectionWindow() {
		
        super("Select Your Seats");
        
        JPanel seatPanel = new JPanel(new GridLayout(ROWS, COLUMNS, 10, 10));
		
        for (int row = 0; row < ROWS; row++) {
			
            for (int col = 0; col < COLUMNS; col++) {
				
                JButton seat = new JButton((char) ('A' + row) + String.valueOf(col + 1));
                seat.setBackground(Color.LIGHT_GRAY);
                seat.setFocusPainted(false);
                
                seat.addActionListener(new ActionListener() {
					
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        toggleSeatSelection(seat);
                    }
                });

                seatButtons[row][col] = seat;
                seatPanel.add(seat);
            }
        }

        add(seatPanel, BorderLayout.CENTER);

        // Add a dummy bottom button to proceed if needed
        JButton confirmButton = new JButton("Confirm Selection");
        confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				handleConfirm();
			}
		});
		
        add(confirmButton, BorderLayout.SOUTH);
    }

    private void toggleSeatSelection(JButton seat) {
		
        if (seat.getBackground() == Color.LIGHT_GRAY) {
			
            seat.setBackground(Color.GREEN);
			
        } else {
			
            seat.setBackground(Color.LIGHT_GRAY);
        }
    }

    private void handleConfirm() {
		
        StringBuilder selectedSeats = new StringBuilder("Selected seats:\n");
        
		for (int row = 0; row < ROWS; row++) {
			
            for (int col = 0; col < COLUMNS; col++) {
				
                if (seatButtons[row][col].getBackground() == Color.GREEN) {
					
                    selectedSeats.append(seatButtons[row][col].getText()).append(" ");
                }
            }
        }
		
        JOptionPane.showMessageDialog(this, selectedSeats.toString());
    }

    public static void main(String[] args) {
		
		SeatSelectionWindow seatSelectionWindow = new SeatSelectionWindow();
		seatSelectionWindow.setSize(500, 300);
        seatSelectionWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        seatSelectionWindow.setLocationRelativeTo(null);
		seatSelectionWindow.setVisible(true);
    }
}