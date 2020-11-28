package GUI;

import javax.swing.*;

public class CheckUser {

    Icon icon = new ImageIcon("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\Enter.png");
    String message = "Пожалуйста, введите ваш логин и пароль";
    JTextField userField = new JTextField();
    JPasswordField passField = new JPasswordField();


    void validation() {
        //int counterTry = 0; My thoughts about the blocking of user
        boolean flag = false;
        do {
            int result = JOptionPane.showOptionDialog(null,
                    new Object[]{message, userField, passField},
                    "TextEditorPRO", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, icon, null, null);
            char passEnter[] = passField.getPassword();

            if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
                System.exit(0);
            }
            if (result == JOptionPane.OK_OPTION) {
                if (checkPassword(passEnter)) {
                    Icon icon = new ImageIcon("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\successEnter.png");  //Пароль верный
                    JOptionPane.showMessageDialog(null,
                            new String[] {"Добро пожаловать в TextEditorPRO!" + "\n" +
                                    "Логин: " + userField.getText()},
                    "TextEditorPRO", JOptionPane.OK_OPTION, icon);
                    flag = true;
                } else {                                                           //Пароль НЕверный
                    Icon icon = new ImageIcon("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\wrongEnter.png");  //Пароль верный
                    JOptionPane.showMessageDialog(null,
                            new String[] {"Вы ввели некорректный пароль!" + "\n" +
                                    "Логин: " + userField.getText()},
                            "TextEditorPRO", JOptionPane.OK_OPTION, icon);
                    passField.setText("");
                    passField.requestFocus();
                }
            }
        } while (flag == false);
    }

    public static boolean checkPassword(char[] passEnter) {
        char[] passCorrect = new char[] {'q', 'w', 'e'};
        if (passCorrect.length != passEnter.length) {
            return false;
        }
        for (int i = 0; i < passCorrect.length; i++) {
            if (passCorrect[i] != passEnter[i]) {
                return false;
            }
        }
        return true;
    }
}













