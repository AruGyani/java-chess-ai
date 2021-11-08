package src.game;

import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.event.MouseEvent;

import src.pieces.*;

public class Board {
    public int width, height;
    public static int offsetX, offsetY;

    public Piece[][] board;
    public Game game;

    public Color white = new Color(239, 210, 186);
    public Color black = new Color(158, 110, 91);

    public Board(int width, int height, Game game) {
        this.width = width;
        this.height = height;
        this.game = game;

        board = new Piece[height][width];

        offsetX = App.WIDTH / 2 - (width * Piece.TILE_SIZE) / 2;
        offsetY = App.HEIGHT / 2 - (height * Piece.TILE_SIZE) / 2;

        initialize();
    }

    public Board(Game game) {
        this.width = 8;
        this.height = 8;
        this.game = game;

        board = new Piece[height][width];

        offsetX = App.WIDTH / 2 - (width * Piece.TILE_SIZE) / 2;
        offsetY = App.HEIGHT / 2 - (height * Piece.TILE_SIZE) / 2;

        initialize();
    }

    public void initialize() {
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(i, 1, game.players[1], this);
            board[6][i] = new Pawn(i, 6, game.players[0], this);
        }

        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Empty(j, i);
            }
        }
        
        board[0][0] = new Rook(0, 0, game.players[1], this);
        board[0][1] = new Knight(1, 0, game.players[1], this);
        board[0][2] = new Bishop(2, 0, game.players[1], this);
        board[0][3] = new Queen(3, 0, game.players[1], this);
        board[0][4] = new King(4, 0, game.players[1], this);
        board[0][5] = new Bishop(5, 0, game.players[1], this);
        board[0][6] = new Knight(6, 0, game.players[1], this);
        board[0][7] = new Rook(7, 0, game.players[1], this);

        board[7][0] = new Rook(0, 7, game.players[0], this);
        board[7][1] = new Knight(1, 7, game.players[0], this);
        board[7][2] = new Bishop(2, 7, game.players[0], this);
        board[7][3] = new Queen(3, 7, game.players[0], this);
        board[7][4] = new King(4, 7, game.players[0], this);
        board[7][5] = new Bishop(5, 7, game.players[0], this);
        board[7][6] = new Knight(6, 7, game.players[0], this);
        board[7][7] = new Rook(7, 7, game.players[0], this);
    }

    public void render(Graphics2D g) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                boolean isLightSquare = (i + j) % 2 == 0;

                int x = j * Piece.TILE_SIZE + offsetX;
                int y = i * Piece.TILE_SIZE + offsetY;

                if (board[i][j].getHovered()) g.setColor((isLightSquare) ? white.darker() : black.darker());
                else g.setColor((isLightSquare) ? white : black);
                g.fillRect(x, y, Piece.TILE_SIZE, Piece.TILE_SIZE);

                if (board[i][j] != null) board[i][j].render(g);
            }
        }
    }
    
    public void mouseClicked(MouseEvent e) {
        if (e.getX() - offsetX < 0) return;
        if (e.getY() - offsetY < 0) return;
        if (e.getX() > (Piece.TILE_SIZE * width + offsetX)) return;
        if (e.getY() > (Piece.TILE_SIZE * height + offsetY)) return;
     
        int x = (e.getX() - offsetX) / Piece.TILE_SIZE;
        int y = (e.getY() - offsetY) / Piece.TILE_SIZE;

        // board[y][x].mouseClicked(e);
        
        if (game.selection == null && !(board[y][x] instanceof Empty) && board[y][x].player == game.currentPlayer) game.selection = board[y][x];
        else if (game.selection != null) game.makeMove(board[y][x]);
    }

    public void mouseHover(MouseEvent e) {
        if (e.getX() - offsetX < 0) return;
        if (e.getY() - offsetY < 0) return;
        if (e.getX() > (Piece.TILE_SIZE * width + offsetX)) return;
        if (e.getY() > (Piece.TILE_SIZE * height + offsetY)) return;
     
        int x = (e.getX() - offsetX) / Piece.TILE_SIZE;
        int y = (e.getY() - offsetY) / Piece.TILE_SIZE;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (i == y && j == x) board[i][j].setHovered(true);
                else board[i][j].setHovered(false);
            }
        }
    }
}
