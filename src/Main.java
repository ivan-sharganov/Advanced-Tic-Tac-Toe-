import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyPanel panel = new MyPanel();
        JFrame frame = new JFrame("window");
        JLabel text= new JLabel(panel.button.statusOfGame);
        int q = 0;
        for (int i = 0; i < panel.button.countX; i++) {
            for (int j = 0; j < panel.button.countY; j++) {
                q++;
                int finalQ = q;
                panel.buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    }
                });
            }
        }
        frame.setSize(640, 480);
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        panel.add(text);

        while (true) {
            text.setText(panel.button.statusOfGame);
            frame.repaint();
            Thread.sleep(20);
        }
    }
}

