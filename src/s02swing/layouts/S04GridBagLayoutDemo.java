package s02swing.layouts;

import javax.swing.*;
import java.awt.*;

/**
 * Этот менеджер подобно GridLayout менеджеру устанавливает компоненты в таблицу,
 * но он более гибок, так как предоставляет возможность определять для компонентов
 * разную ширину и высоту колонок и строк таблицы.
 * По существу, GridBagLayout помещает компоненты в ячейки, и затем использует привилегированные размеры компонентов,
 * чтобы определить, насколько большой ячейка должна быть.
 */
public class S04GridBagLayoutDemo {

    static final boolean shouldFill = true;
    static final boolean shouldWeightX = true;
    static final boolean RIGHT_TO_LEFT = false;

    public static void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        JButton button;
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        if (shouldFill) {
            // натуральная высота, максимальная ширина
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        button = new JButton("Button 1");

        if (shouldWeightX) {
            c.weightx = 0.5;
        }

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(button, c);

        button = new JButton("Button 2");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        pane.add(button, c);

        button = new JButton("Button 3");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        pane.add(button, c);

        button = new JButton("Long-Named Button 4");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;      // сделать эту кнопку высокой
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(button, c);

        button = new JButton("5");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       // установить первоначальный размер кнопки
        c.weighty = 1.0;   // установить отступ
        c.anchor = GridBagConstraints.PAGE_END; // установить кнопку в конец окна
        c.insets = new Insets(10, 0, 0, 0);  // поставить заглушку
        c.gridx = 1;       // выравнять компонент по Button 2
        c.gridwidth = 2;   // установить в 2 колонку
        c.gridy = 2;       // и 3 столбец
        pane.add(button, c);

    }

    private static void createAndShowGUI() {
        // Создание окна
        JFrame frame = new JFrame("GridBagLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Установить панель содержания
        addComponentsToPane(frame.getContentPane());

        // Показать окно
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
