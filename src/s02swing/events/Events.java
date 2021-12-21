package s02swing.events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Обработка событий
 */
public class Events {
    public static void main(String[] args) {
        JFrame myWindow = new JFrame("Пробное окно");
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setSize(400, 300);

        myWindow.setLayout(new FlowLayout());

        JButton jButtonHi = new JButton("Привет!");
        JButton jButtonBye = new JButton("Пока!");

        jButtonHi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(null,"Привет","Нажато",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        myWindow.add(jButtonHi);
        myWindow.add(jButtonBye);

        myWindow.setVisible(true);
    }
}
