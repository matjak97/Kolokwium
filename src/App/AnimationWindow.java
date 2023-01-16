package App;

import javax.swing.*;
import java.awt.*;

public class AnimationWindow extends JFrame {

    protected AnimationCanva canva;
    protected JPanel contentPane;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                AnimationWindow window = new AnimationWindow();
                window.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AnimationWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int ww = 450, wh = 300;
        setBounds((screen.width-ww)/2, (screen.height-wh)/2, ww, wh);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        canva = new AnimationCanva();
        canva.setBounds(10, 11, 412, 209);
        contentPane.add(canva);
        SwingUtilities.invokeLater(canva::initialize);
    }
}