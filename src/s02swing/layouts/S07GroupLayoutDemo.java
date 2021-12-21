package s02swing.layouts;

import javax.swing.*;
import java.awt.*;

/**
 * GroupLayout менеджер имеет возможность независимо устанавливать горизонтальное и вертикальное расположение компонентов на форме.
 * Он использует два типа добавления компонентов параллельный и последовательный объединенный с иерархическим составом.
 *
 * 1. Последовательным добавляет компоненты просто помещая один за другим,
 * точно так же как BoxLayout или FlowLayout вдоль одной оси. Положение каждого компонента
 * определяется относительно предыдущего компонента.
 *
 * 2. Помещает компоненты параллельно относительно друг друга в то же самом месте.
 * Они добавляются к верху формы или выравниваются к основанию вдоль вертикальной оси.
 * Вдоль горизонтальной оси они устанавливаются влево или по центру, если у компонентов разный размер.
 */
public class S07GroupLayoutDemo extends JFrame {

    public S07GroupLayoutDemo() {
        JLabel label = new JLabel("Find What:");
        JTextField textField = new JTextField();
        JCheckBox caseCheckBox = new JCheckBox("Match Case");
        JCheckBox wrapCheckBox = new JCheckBox("Wrap Around");
        JCheckBox wholeCheckBox = new JCheckBox("Whole Words");
        JCheckBox backCheckBox = new JCheckBox("Search Backwards");
        JButton findButton = new JButton("Find");
        JButton cancelButton = new JButton("Cancel");

        caseCheckBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        wrapCheckBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        wholeCheckBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        backCheckBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(label)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(textField)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(caseCheckBox)
                                        .addComponent(wholeCheckBox))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(wrapCheckBox)
                                        .addComponent(backCheckBox))))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(findButton)
                        .addComponent(cancelButton))
        );

        layout.linkSize(SwingConstants.HORIZONTAL, findButton, cancelButton);

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label)
                        .addComponent(textField)
                        .addComponent(findButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(caseCheckBox)
                                        .addComponent(wrapCheckBox))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(wholeCheckBox)
                                        .addComponent(backCheckBox)))
                        .addComponent(cancelButton))
        );

        setTitle("Find");
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new S07GroupLayoutDemo().setVisible(true));

    }
}
