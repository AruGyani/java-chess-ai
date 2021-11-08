package src.game;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
// import java.util.List;

import src.pieces.*;

public class Game {
    public Board board;
    
    public Player[] players;
    public Player currentPlayer;

    public Piece selection;

    // public List<Move> moves;

    public Game() {
        players = new Player[2];

        players[0] = new Player(Team.WHITE);
        players[1] = new Player(Team.BLACK);

        currentPlayer = players[0];

        selection = null;

        board = new Board(this);
    }

    public void render(Graphics2D g)  {
        board.render(g);
    }

    public void makeMove(Piece selection2) {
        System.out.println("Selection 1: " + selection.toString());
        System.out.println("Selection 2: " + selection2.toString());

        Piece p = null;

        if (selection instanceof King) p = new King(selection2.x, selection2.y, selection.player, board);
        else if (selection instanceof Bishop) p = new Bishop(selection2.x, selection2.y, selection.player, board);
        else if (selection instanceof Pawn) p = new Pawn(selection2.x, selection2.y, selection.player, board);
        else if (selection instanceof Knight) p = new Knight(selection2.x, selection2.y, selection.player, board);
        else if (selection instanceof Queen) p = new Queen(selection2.x, selection2.y, selection.player, board);
        else if (selection instanceof Rook) p = new Rook(selection2.x, selection2.y, selection.player, board);
        else p = new Empty(selection2.x, selection2.y);

        if (selection.player != selection2.player && selection.canMove(selection2)) {
            board.board[selection2.y][selection2.x] = p;
            board.board[selection.y][selection.x] = new Empty(selection.x, selection.y);

            currentPlayer = (currentPlayer == players[0]) ? players[1] : players[0];
        }

        selection = null;
    }

    public void mouseClicked(MouseEvent e) {
        board.mouseClicked(e);
    }

    public void mouseMoved(MouseEvent e) {
        board.mouseHover(e);
    }
}
