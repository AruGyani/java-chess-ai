package src.pieces;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import src.game.Board;
import src.game.Player;
import src.game.Team;

public class Knight extends Piece {
    public Knight(int x, int y, Player player, Board board) {
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

        if (player.getTeam() == Team.WHITE) url = "res/wn.png";
        else url = "res/bn.png";

        try {
            img = ImageIO.read(new File(url));
        } catch (IOException e) { e.printStackTrace(); }

        return img;
    }

    public boolean canMove(Piece p) {
        return true;
    }

    public String toString() {
        return player.getTeam() + " KNIGHT";
    }
}
