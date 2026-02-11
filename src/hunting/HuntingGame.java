/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hunting;

/**
 *
 * @author wolfd
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * Main class for the Hunting Game application.
 * Manages the game window, navigation between screens, and game flow.
 */
public class HuntingGame {
    private JFrame frame;
    private BoardGUI boardGUI;
    private final int[] BOARD_SIZES = new int[]{3, 5, 7};
    public String message; 
    /**
     * Constructs the Hunting Game and displays the initial size selection screen.
     */
    public HuntingGame() {
        frame = new JFrame("Hunting Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showSizeSelection();
    }

    /**
     * Displays the board size selection screen with options for 3x3, 5x5, and 7x7 boards.
     */
    private void showSizeSelection() {
        frame.getContentPane().removeAll();
        
        SizeSelectionGUI sizeSelection = new SizeSelectionGUI(this);
        frame.getContentPane().add(sizeSelection.getPanel(), BorderLayout.CENTER);

        JMenuBar menuBar = createMenuBar();
        frame.setJMenuBar(menuBar);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Starts a new game with the specified board size.
     * 
     * @param boardSize the dimension of the square game board (3, 5, or 7)
     */
    public void startGame(int boardSize) {
        frame.getContentPane().removeAll();

        boardGUI = new BoardGUI(boardSize, this);
        frame.getContentPane().add(boardGUI.getBoardPanel(), BorderLayout.CENTER);
        frame.getContentPane().add(boardGUI.getInfoPanel(), BorderLayout.NORTH);

        JMenuBar menuBar = createMenuBar();
        frame.setJMenuBar(menuBar);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Creates and returns the application menu bar with Game menu options.
     * 
     * @return the configured JMenuBar for the application
     */
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);
        
        JMenuItem newGameMenuItem = new JMenuItem("New Game");
        newGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSizeSelection();
            }
        });
        gameMenu.add(newGameMenuItem);
        
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        gameMenu.add(exitMenuItem);
        
        return menuBar;
    }

    /**
     * Handles game end conditions and displays appropriate message.
     * Automatically returns to size selection screen after game completion.
     * 
     * @param huntersWin true if hunters won the game, false if fugitive survived (draw)
     */
    public void gameEnded(boolean huntersWin) {
        message = huntersWin ? "Hunters win! The fugitive is trapped!" : "Fugitive survived! Draw!";
        JOptionPane.showMessageDialog(frame, message);
        showSizeSelection();
    }
}