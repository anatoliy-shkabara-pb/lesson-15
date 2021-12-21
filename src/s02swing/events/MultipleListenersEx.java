package s02swing.events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;
import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;

public class MultipleListenersEx extends JFrame {

    private JLabel statusBar;
    private JSpinner spinner;
    private int count = 0;

    public MultipleListenersEx() {

        initUI();
    }

    private void initUI() {

        statusBar = new JLabel("0");
        statusBar.setBorder(BorderFactory.createEtchedBorder());

        JButton addBtn = new JButton("+");
        addBtn.addActionListener(new ButtonListener1());
        addBtn.addActionListener(new ButtonListener2());

        int currentYear = Year.now().getValue();

        SpinnerNumberModel yearModel = new SpinnerNumberModel(currentYear,
                currentYear - 100,
                currentYear + 100,
                1);

        spinner = new JSpinner(yearModel);
        spinner.setEditor(new JSpinner.NumberEditor(spinner, "#"));

        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createParallelGroup()
                .addGroup(gl.createSequentialGroup()
                        .addComponent(addBtn)
                        .addGap(20)
                        .addComponent(spinner, DEFAULT_SIZE,
                                DEFAULT_SIZE, PREFERRED_SIZE))
                .addComponent(statusBar, DEFAULT_SIZE,
                        DEFAULT_SIZE, Short.MAX_VALUE)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(addBtn)
                        .addGap(20)
                        .addComponent(spinner, DEFAULT_SIZE,
                                DEFAULT_SIZE, PREFERRED_SIZE))
                .addPreferredGap(RELATED,
                        DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(statusBar)
        );

        pack();

        setTitle("Multiple Listeners");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private class ButtonListener1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Integer val = (Integer) spinner.getValue();
            spinner.setValue(++val);
        }
    }

    private class ButtonListener2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            statusBar.setText(Integer.toString(++count));
        }
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            new MultipleListenersEx().setVisible(true);
        });
    }
}
