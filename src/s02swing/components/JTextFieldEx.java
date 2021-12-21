package s02swing.components;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;

public class JTextFieldEx extends JFrame {

    private JLabel jLabel;

    public JTextFieldEx() {

        initUI();
    }

    private void initUI() {

        JTextField jTextField = new JTextField(15);
        jLabel = new JLabel();

        jTextField.getDocument().addDocumentListener(new MyDocumentListener());


        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(jTextField)
                .addComponent(jLabel)
                .addGap(250)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(jTextField, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel)
                .addGap(150)
        );

        pack();

        setTitle("JTextField");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private class MyDocumentListener implements DocumentListener {

        private String text;

        @Override
        public void insertUpdate(DocumentEvent e) {
            updateLabel(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateLabel(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
        }

        private void updateLabel(DocumentEvent e) {

            Document doc = e.getDocument();
            int len = doc.getLength();

            try {
                text = doc.getText(0, len);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }

            jLabel.setText(text);

        }
    }


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JTextFieldEx ex = new JTextFieldEx();
            ex.setVisible(true);
        });
    }
}