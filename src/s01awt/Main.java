package s01awt;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        Frame frame = new Frame();
        Label label = new Label("Employee id:");
        Button button = new Button("Submit");
        TextField textField = new TextField();

        label.setBounds(20, 70, 80, 30);
        textField.setBounds(20, 100, 80, 30);
        button.setBounds(100, 100, 80, 30);

        frame.add(button);
        frame.add(label);
        frame.add(textField);

        frame.setSize(400,300);
        frame.setTitle("Employee info");

        // нужно убрать менеджер компоновки который установлен по умолчанию
        // иначе будет использован BorderLayout и компоненты будут расположены не так как задумано
        frame.setLayout(null);

        // для работы кнопки закрытие окна "крестик"
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });

        // обработка нажатия кнопки
        button.addActionListener(event -> System.out.println("Button pressed, id: " + textField.getText()));

        frame.setVisible(true);
    }
}
