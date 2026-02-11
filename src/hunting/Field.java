/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hunting;
/**
 * Represents a single field on the game board.
 * Tracks whether the field contains a fugitive or hunter and associated data.
 */
public class Field {
    private boolean hasFugitive;
    private boolean hasHunter;
    private int hunterId;

    /**
     * Constructs an empty field with no pieces.
     */
    public Field() {
        this.hasFugitive = false;
        this.hasHunter = false;
        this.hunterId = -1;
    }

    /**
     * Clears the field, removing any piece information.
     */
    public void clear() {
        this.hasFugitive = false;
        this.hasHunter = false;
        this.hunterId = -1;
    }

    /**
     * Checks if the field contains the fugitive.
     * 
     * @return true if fugitive is on this field, false otherwise
     */
    public boolean hasFugitive() {
        return hasFugitive;
    }

    /**
     * Sets or clears the fugitive presence on this field.
     * 
     * @param hasFugitive true to place fugitive, false to remove
     */
    public void setFugitive(boolean f) {
        this.hasFugitive = f;
        if (f) {
            this.hasHunter = false;
            this.hunterId = -1;
        }
    }

    /**
     * Checks if the field contains a hunter.
     * 
     * @return true if a hunter is on this field, false otherwise
     */
    public boolean hasHunter() {
        return hasHunter;
    }

    /**
     * Sets or clears hunter presence on this field.
     * 
     * @param hasHunter true to place hunter, false to remove
     */
    public void setHunter(boolean h) {
        this.hasHunter = h;
        if (h) this.hasFugitive = false;
    }

    /**
     * Returns the ID of the hunter on this field.
     * 
     * @return the hunter ID (1-4), or -1 if no hunter present
     */
    public int getHunterId() {
        return hunterId;
    }

    /**
     * Sets the ID for the hunter on this field.
     * 
     * @param hunterId the hunter ID to set (1-4)
     */
    public void setHunterId(int hId) {
        this.hunterId = hId;
    }

    /**
     * Checks if the field is occupied by any piece.
     * 
     * @return true if field contains fugitive or hunter, false otherwise
     */
    public boolean isOccupied() {
        return hasFugitive || hasHunter;
    }
}