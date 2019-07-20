import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyPanel extends JPanel {
    CButton button = new CButton(10, 10, 50, 50);
    CButton[][] buttons = new CButton[button.countX][button.countY];
    MouseEvent mouseEvent;

    public MyPanel() {
        for (int i = 0; i < button.countX; i++) {
            for (int j = 0; j < button.countY; j++) {
                buttons[i][j] = new CButton(10 + i * 50, 10 + j * 50, 50, 50);
            }
        }
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int q=0;
                if (!button.endGame) {
                    button.putSign(e, buttons);
                    for (int i = 0; i < button.countX; i++) {
                        for (int j = 0; j < button.countY; j++) {
                            buttons[i][j].mousePressed(e);
                        }
                    }
                    button.checkEndOfTheGame();
                    for (int i = 0; i < button.countX; i++) {
                        for (int j = 0; j < button.countY; j++) {
                            if (button.moves[i][j] != 0) {
                                q+=1;
                            }
                        }
                    }
                    if(q==(button.countX*button.countY)){
                        System.out.println("LOSE");
                        button.endGame = true;
                        button.statusOfGame="tap to restart";
//                        button.clearMoves();
                    }
                } else {
                    button.clearMoves();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        for (int i = 0; i < button.countX; i++) {
            for (int j = 0; j < button.countY; j++) {
                buttons[i][j].paintComponent(g);
                if (button.moves[i][j] == 1) {
                    g.drawOval(10 + 10 + (i) * 50, 10 + 10 + (j) * 50, 30, 30);
                }
                if (button.moves[i][j] == 2) {
                    g.drawLine(10 + 10 + i * 50, 10 + 10 + j * 50, 10 + 50 - 10 + i * 50, 10 + 50 - 10 + j * 50);
                    g.drawLine(10 + 10 + i * 50, 10 + 50 - 10 + j * 50, 10 + 50 - 10 + i * 50, 10 + 10 + j * 50);
                }
            }
        }
    }
}



