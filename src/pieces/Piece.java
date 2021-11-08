package src.pieces;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import src.game.Board;
import src.game.Player;

public abstract class Piece {
    public int x, y;
    public int offsetX, offsetY;

    public Player player;
    public Board board;

    private boolean hovered = false;

    public static final int TILE_SIZE = 75;
    public BufferedImage img;

    public Piece (int x, int y) {
        this.x = x;
        this.y = y;

        this.player = null;
        this.img = null;
    }
    
    public Piece(int x, int y, Player player, Board board) {
        this.x = x;
        this.y = y;
        this.player = player;
        this.board = board;

        this.img = loadImage();
    }    

    public void mouseClicked(MouseEvent e) {
        System.out.println(toString());
        System.out.println("X: " + x);
        System.out.println("Y: "+  y);
    }

    public boolean getHovered() { return hovered; }
    public void setHovered(boolean hovered) { this.hovered = hovered; }

    public abstract void render(Graphics2D g);
    public abstract BufferedImage loadImage();
    public abstract boolean canMove(Piece p);
    public abstract String toString();
}
