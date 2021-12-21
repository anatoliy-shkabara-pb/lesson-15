package s02swing.layouts;

import javax.swing.*;
import java.awt.*;

/**
 * По умолчанию в Swing используется менеджер BorderLayout, в нем определены следующие константы для установки компонентов.
 * BorderLayout.NORTH (верх)
 * BorderLayout.SOUTH (низ)
 * BorderLayout.EAST (справа)
 * BorderLayout.WEST (слева)
 * BorderLayout.CENTER (заполнить середину до других компонент или до краев)
 * По умолчанию принимается константа Center.
 */
public class S01BorderLayoutDemo {
    public static void main(String[] args) {

        // создаем фрейм и устанавливаем его размер.
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(400, 300);
        jf.setVisible(true);

        // создаем панель.
        JPanel p = new JPanel();
        jf.add(p);

        // к панели добавляем менеджер BorderLayout.
        p.setLayout(new BorderLayout());

        // к панели добавляем кнопку и устанавливаем для нее менеджер в верхнее расположение.
        p.add(new JButton("Okay"), BorderLayout.NORTH);
    }
}
