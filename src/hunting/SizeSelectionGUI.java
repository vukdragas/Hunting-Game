/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hunting;

/**
 *
 * @author wolfd
 */


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Handles the board size selection user interface.
 * Provides buttons for selecting between 3x3, 5x5, and 7x7 game boards.
 */
public class SizeSelectionGUI {
    private JPanel panel;
    private HuntingGame game;
    private final int[] BOARD_SIZES = new int[]{3, 5, 7};

    /**
     * Constructs the size selection GUI with buttons for available board sizes.
     * 
     * @param game the main HuntingGame instance for callback communication
     */
    public SizeSelectionGUI(HuntingGame game) {
        this.game = game;
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        
        JLabel titleLabel = new JLabel("Select Board Size", JLabel.CENTER);
        panel.add(titleLabel);
        
        for (final int boardSize : BOARD_SIZES) {
            JButton sizeButton = new JButton(boardSize + " x " + boardSize);
            panel.add(sizeButton);
            sizeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    game.startGame(boardSize);
                }
            });
        }
    }

    /**
     * Returns the main panel containing the size selection interface.
     * 
     * @return the JPanel with size selection components
     */
    public JPanel getPanel() {
        return panel;
    }
}