/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hunting;

/**
 *
 * @author wolfd
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Manages the graphical user interface for the game board.
 * Handles button creation, visual updates, and user interaction.
 */
public class BoardGUI {
    private JButton[][] buttons;
    private Board board;
    private JPanel boardPanel;
    private JPanel infoPanel;
    private JLabel infoLabel;
    private Timer timer;
    private long startTime;
    private HuntingGame parentGame;
    
    private boolean fugitiveTurn;
    private int hunterMoves;
    private final int maxHunterMoves;

    /**
     * Constructs the board GUI with specified size and parent game reference.
     * 
     * @param boardSize the dimension of the game board
     * @param parentGame the main game instance for callback communication
     */
    public BoardGUI(int boardSize, HuntingGame parentGame) {
        this.parentGame = parentGame;
        board = new Board(boardSize);
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(board.getSize(), board.getSize()));
        buttons = new JButton[board.getSize()][board.getSize()];
        
        this.maxHunterMoves = 4 * boardSize;
        this.hunterMoves = 0;
        this.fugitiveTurn = true;
        
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(60, 60));
                button.addActionListener(new ButtonListener(i, j));
                buttons[i][j] = button;
                boardPanel.add(button);
            }
        }
        
        infoPanel = new JPanel();
        infoLabel = new JLabel();
        infoLabel.setHorizontalAlignment(JLabel.CENTER);
        infoPanel.add(infoLabel);
        
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateInfoLabel();
            }
        });
        
        startTime = System.currentTimeMillis();
        timer.start();
        
        refresh();
    }


    /**
     * Refreshes the entire game board display.
     * Updates button colors, text, and enabled states based on current game state.
     */
    public void refresh() {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                Field field = board.getField(i, j);
                JButton button = buttons[i][j];
                
                button.setBackground(Color.WHITE);
                button.setText("");
                button.setEnabled(true);
                
                if (field.hasFugitive()) {
                    button.setText("F");
                    button.setBackground(Color.RED);
                } else if (field.hasHunter()) {
                    button.setText("H" + field.getHunterId());
                    button.setBackground(Color.GREEN);
                }
                
                if (!board.isValidMove(i, j, fugitiveTurn)) {
                    button.setEnabled(false);
                }
            }
        }
        updateInfoLabel();
    }

    /**
     * Updates the information label with current game status.
     */
    private void updateInfoLabel() {
        String turn = fugitiveTurn ? "Fugitive" : "Hunters";
        long elapsedTime = System.currentTimeMillis() - startTime;
        infoLabel.setText("Turn: " + turn + " | Hunter moves: " + hunterMoves + "/" + maxHunterMoves + " | Time: " + (elapsedTime / 1000) + "s");
    }

    /**
     * Returns the main board panel containing all game buttons.
     * 
     * @return the JPanel representing the game board
     */
    public JPanel getBoardPanel() {
        return boardPanel;
    }

    /**
     * Returns the information panel displaying game status.
     * 
     * @return the JPanel containing game information
     */
    public JPanel getInfoPanel() {
        return infoPanel;
    }

    /**
     * Action listener for game board buttons.
     * Handles move validation and game state updates.
     */
    class ButtonListener implements ActionListener {
        private int x, y;

        /**
         * Constructs a button listener for the specified board coordinates.
         * 
         * @param x the x-coordinate of the button
         * @param y the y-coordinate of the button
         */
        public ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Handles button click events, validating and executing moves.
         * 
         * @param e the action event triggered by button click
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (board.isValidMove(x, y, fugitiveTurn)) {
                if (fugitiveTurn) {
                    board.moveFugitive(x, y);
                    fugitiveTurn = false;
                } else {
                    board.moveHunter(x, y);
                    hunterMoves++;
                    fugitiveTurn = true;
                }

                refresh();

                if (board.isFugitiveTrapped()) {
                    timer.stop();
                    parentGame.gameEnded(true);
                } else if (hunterMoves >= maxHunterMoves) {
                    timer.stop();
                    parentGame.gameEnded(false);
                }
            }
        }
    }
}