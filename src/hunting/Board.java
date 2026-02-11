/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hunting;

/**
 *
 * @author wolfd
 */

import java.awt.Point;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents the game board and manages game state logic.
 * Handles piece movement, validation, and win condition checking.
 */
public class Board {
    private Field[][] fields;
    private final int size;
    private Point fugitivePos;
    private List<Point> hunterPositions;

    /**
     * Constructs a new game board with the specified size.
     * Initializes all fields and places pieces in starting positions.
     * 
     * @param size the dimension of the square board
     */
    public Board(int size) {
        this.size = size;
        this.fields = new Field[size][size];
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                fields[i][j] = new Field();
            }
        }
        
        hunterPositions = new ArrayList<>();
        hunterPositions.add(new Point(0,0));
        hunterPositions.add(new Point(0,size-1));
        hunterPositions.add(new Point(size-1,0));
        hunterPositions.add(new Point(size-1,size-1));
        
        fugitivePos = new Point(size / 2, size / 2);
        
        updateFields();
    }

    /**
     * Updates all field states based on current piece positions.
     */
    private void updateFields() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                fields[i][j].clear();
            }
        }
        
        fields[fugitivePos.x][fugitivePos.y].setFugitive(true);
        
        int id = 1;
        for(Point position : hunterPositions){
            fields[position.x][position.y].setHunter(true);
            fields[position.x][position.y].setHunterId(id);
            id++;
        }
    }

    /**
     * Validates whether a move to the specified coordinates is legal.
     * 
     * @param x the target x-coordinate
     * @param y the target y-coordinate
     * @param isFugitiveTurn true if it's the fugitive's turn, false for hunters
     * @return true if the move is valid, false otherwise
     */
    public boolean isValidMove(int x, int y, boolean isFugitiveTurn) {
        if (x < 0 || y < 0 || x >= size || y >= size) return false;
        if (fields[x][y].isOccupied()) return false;

        if (isFugitiveTurn) {
            return isAdjacent(fugitivePos.x, fugitivePos.y, x, y);
        } else {
            for (Point hunter : hunterPositions) {
                if (isAdjacent(hunter.x, hunter.y, x, y)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Moves the fugitive to the specified coordinates.
     * 
     * @param x the target x-coordinate for the fugitive
     * @param y the target y-coordinate for the fugitive
     */
    public void moveFugitive(int x, int y) {
        fugitivePos.move(x, y);
        updateFields();
    }

    /**
     * Moves a hunter to the specified coordinates.
     * Automatically selects which hunter can make the move.
     * 
     * @param x the target x-coordinate for the hunter
     * @param y the target y-coordinate for the hunter
     */
    public void moveHunter(int x, int y) {
        for (Point hunter : hunterPositions) {
            if (isAdjacent(hunter.x, hunter.y, x, y)) {
                hunter.move(x, y);
                updateFields();
                return;
            }
        }
    }

    /**
     * Checks if the fugitive is completely surrounded and cannot move.
     * 
     * @return true if fugitive is trapped, false otherwise
     */
    public boolean isFugitiveTrapped() {
        int x = fugitivePos.x;
        int y = fugitivePos.y;
        
        List<Point> directions = new ArrayList<>();
        directions.add(new Point(1,0));
        directions.add(new Point(-1,0));
        directions.add(new Point(0,1));
        directions.add(new Point(0,-1));
        
        for (Point dir : directions) {
            int xx = x + dir.x;
            int yy = y + dir.y;
            if (xx >= 0 && yy >= 0 && xx < size && yy < size && !fields[xx][yy].isOccupied()) return false;
        }
        return true;
    }

    /**
     * Checks if two positions are adjacent on the board.
     * 
     * @param x1 the x-coordinate of the first position
     * @param y1 the y-coordinate of the first position
     * @param x2 the x-coordinate of the second position
     * @param y2 the y-coordinate of the second position
     * @return true if positions are horizontally or vertically adjacent
     */
    private boolean isAdjacent(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2) == 1;
    }

    /**
     * Returns the field at the specified coordinates.
     * 
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return the Field object at the specified position
     */
    public Field getField(int x, int y) {
        return fields[x][y];
    }

    /**
     * Returns the size (dimension) of the game board.
     * 
     * @return the board size
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the current position of the fugitive.
     * 
     * @return a copy of the fugitive's position
     */
    public Point getFugitivePosition() {
        return fugitivePos;
    }

    /**
     * Returns the current positions of all hunters.
     * 
     * @return an array containing copies of all hunter positions
     */
    public List<Point> getHunterPositions() {
        return hunterPositions;
    }
}