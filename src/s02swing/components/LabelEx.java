package s02swing.components;

import javax.swing.*;
import java.awt.*;

public class LabelEx  extends JFrame {

    public LabelEx() {

        initUI();
    }

    private void initUI() {

        String lyrics =  "<html>It's way too late to think of<br>" +
                "Someone I would call now<br>" +
                "And neon signs got tired<br>" +
                "Red eye flights help the stars out<br>" +
                "I'm safe in a corner<br>" +
                "Just hours before me<br>" +
                "<br>" +
                "I'm waking with the roaches<br>" +
                "The world has surrendered<br>" +
                "I'm dating ancient ghosts<br>" +
                "The ones I made friends with<br>" +
                "The comfort of fireflies<br>" +
                "Long gone before daylight<br>" +
                "<br>" +
                "And if I had one wishful field tonight<br>" +
                "I'd ask for the sun to never rise<br>" +
                "If God leant his voice for me to speak<br>" +
                "I'd say go to bed, world<br>" +
                "<br>" +
                "I've always been too late<br>" +
                "To see what's before me<br>" +
                "And I know nothing sweeter than<br>" +
                "Champaign from last New Years<br>" +
                "Sweet music in my ears<br>" +
                "And a night full of no fears<br>" +
                "<br>" +
                "But if I had one wishful field tonight<br>" +
                "I'd ask for the sun to never rise<br>" +
                "If God passed a mic to me to speak<br>" +
                "I'd say stay in bed, world<br>" +
                "Sleep in peace</html>";

        JLabel label = new JLabel(lyrics);
        label.setFont(new Font("Serif", Font.PLAIN, 14));
        label.setForeground(new Color(50, 50, 25));


        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(label)
        );

        gl.setVerticalGroup(gl.createParallelGroup()
                .addComponent(label)
        );

        pack();

        setTitle("No Sleep");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            new LabelEx().setVisible(true);
        });
    }
}