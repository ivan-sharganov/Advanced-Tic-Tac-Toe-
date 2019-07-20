import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class CButton {
    String statusOfGame="play!";
    int x;
    int y;
    int width;
    int height;
    int countX=7;
    int countY=7;
    int [] dxs={1,1,0,1};
    int [] dys={1,-1,1,0};
    boolean endGame = false;
    ActionListener listener;
    int howManyMoves = 0;
    int[][] moves = new int[countX][countY];

    public CButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    void paintComponent(Graphics g) {
        g.drawRect(x, y, width, height);
    }

    void addActionListener(ActionListener listener) {
        this.listener = listener;
    }

    boolean checkEndOfTheGame() {
        for (int i = 0; i < countX; i++) {
            for (int j = 0; j < countY; j++) {
                for (int t = 0; t < 4; t++) {
                    int dx = dxs[t];
                    int dy = dys[t];
                    int start = moves[i][j];
                    boolean OKAY = true;
                    if (start == 0) {
                        OKAY = false;
                    } else {
                        for (int q = 0; q < 5; q++) {
                            if (j + q * dy < 0 || j + q * dy >= countY || i + q * dx < 0 || i + q * dx >= countX || (moves[i+q*dx][j+q*dy] != start)) {
                                OKAY = false;
                            }
                        }
                        if (OKAY) {
                            System.out.println("WIN");
                            endGame = true;
                            statusOfGame="tap to restart";
                            return true;
                        }
                    }
                }
            }
        }
        statusOfGame="play!";
        return false;
    }
    void clearMoves() {
        for (int i = 0; i < countX; i++) {
            for (int j = 0; j < countY; j++) {
                moves[i][j] = 0;
            }
        }
        statusOfGame="play!";
        endGame = false;
    }
    void putSign(MouseEvent e, CButton[][] buttons1) {
        for (int i = 0; i < countX; i++) {
            for (int j = 0; j < countY; j++) {
                if ((e.getX() >= buttons1[i][j].x && e.getX() < buttons1[i][j].x + buttons1[i][j].width) && (e.getY() >= buttons1[i][j].y && e.getY() < buttons1[i][j].y + buttons1[i][j].height)) {
                    if (howManyMoves % 2 == 0 && moves[i][j] == 0 & !checkEndOfTheGame()) { //кресты
                        moves[i][j] = 2;
                        howManyMoves += 1;
                    }
                    if (howManyMoves % 2 == 1 && moves[i][j] == 0) {      // круги
                        moves[i][j] = 1;
                        howManyMoves += 1;
                    }
                }
            }
        }
    }
    void mousePressed(MouseEvent e) {
        if ((e.getX() >= x && e.getX() < x + width) && (e.getY() >= y && e.getY() < y + height)) {
            listener.actionPerformed(null);
        }
    }
}
