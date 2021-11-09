package src.pieces;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import src.game.Board;
import src.game.Player;
import src.game.Team;

public class Pawn extends Piece {
    private boolean hasMoved = false;

    public Pawn(int x, int y, Player player, Board board) {
        super(x, y, player, board);

        this.offsetX = Board.offsetX + TILE_SIZE / 2 - img.getWidth() / 2;
        this.offsetY = Board.offsetY + TILE_SIZE / 2 - img.getHeight() / 2;
    }

    public void render(Graphics2D g) {
        g.drawImage(img, x * TILE_SIZE + offsetX, y * TILE_SIZE + offsetY, null);
    }

    public BufferedImage loadImage() {
        BufferedImage img = null;
        String url = "";

        if (player.getTeam() == Team.WHITE) url = "res/wp.png";
        else url = "res/bp.png";

        try {
            img = ImageIO.read(new File(url));
        } catch (IOException e) { e.printStackTrace(); }

        return img;
    }

    //TODO: Implement enPassant
    public boolean canMove(Piece p) {
        if (board.board[p.y][p.x] instanceof Empty && p.x == x) {
            if (player.getTeam() == Team.WHITE) {
                if (p.y - y == -1) return true;
                if (p.y - y == -2 && board.board[y - 1][x] instanceof Empty && y == 6) return true;
            }

            if (player.getTeam() == Team.BLACK) {
                if (p.y - y == 1) return true;
                if (p.y - y == 2 && board.board[y + 1][x] instanceof Empty && y == 1) return true;
            }
        }

        if (!(p instanceof Empty)) {
            if (player.getTeam() == Team.WHITE) {
                if (p.y - y == -1 && Math.abs(p.x - x) == 1) return true;
            }

            if (player.getTeam() == Team.BLACK) {
                if (p.y -y == 1 && Math.abs(p.x - x) == 1) return true;
            }
        }
    
        return false; 
    }

    public String toString() {
        return player.getTeam() + " PAWN";
    }
}
