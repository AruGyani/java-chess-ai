package src.pieces;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class Empty extends Piece {
    public Empty(int x, int y) {
        super(x, y);
    }

    public void render(Graphics2D g) {}
    public String toString() { return "EMPTY"; }

    public boolean canMove(Piece p) { return false; }
    public BufferedImage loadImage() { return null; }
}
