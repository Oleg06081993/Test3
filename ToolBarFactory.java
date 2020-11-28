package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBarFactory {

    static JButton addTabb;

        JToolBar createToolBar1() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.addSeparator();
        JButton one = new JButton("Get the code");
        JButton two = new JButton("Open the page");

        toolBar.add(one);
        toolBar.add(two);
        JTextField urlField = new JTextField(10);
        toolBar.add(new JLabel("Link:"));
        toolBar.add(urlField);
        return toolBar;

    }

        JToolBar createToolBar2() {
        JToolBar toolBar = new JToolBar();
        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        toolBar.addSeparator();
        toolBar.setFloatable(false);

        Icon icon = new ImageIcon("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\addTabb.png");
        JButton buttonAddTabb = new JButton(icon);       // создание кнопки для добавления новой вкладочки
        buttonAddTabb.addActionListener(new AddNewPageListener());  //делаем кнопку слушателем события

        JButton simpleFont = new JButton("Обычный");
        JButton kursivFont = new JButton("Курсив");
        JButton boldFont = new JButton("Жирный");

        toolBar.add(buttonAddTabb);
        toolBar.add(simpleFont);
        toolBar.add(kursivFont);
        toolBar.add(boldFont);
        return toolBar;
        }

        JToolBar createToolBar3() {
        JToolBar toolBar = new JToolBar();
        toolBar.addSeparator();
        return toolBar;
    }

    class AddNewPageListener implements ActionListener {   // реализуем логику при нажатии
        public void actionPerformed(ActionEvent e) {
            JEditorPane editor = new JEditorPane();
            JScrollPane scroll = new JScrollPane(editor);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            tabbedPane.addTab("Вкладка N", scroll);  // здесь ОШИБКА, у меня нет доступа в этом классе к самому tabbedPane
        }
    };
}
// Что я хочу: чтобы при нажатии юзером на кнопку buttonAddTabb добавлялась новая вкладочка
// Что я сделал: написал шаблон для создания ТулБара, добавил туда кнопку, реализовал логику при нажатии
// Моя проблема: во внутреннем классе AddNewPageListener (где реализована логика при нажатии на кнопку)
// у меня нет доступа к переменной tabbedPane (то есть к самому объекту JTabbedPane),
// так как панель tabbedPane создана в классе FirstGui

//Класс FirstGui :
// 119 строка  - создается ТулБар (где и находится кнопка buttonAddTabb)
// 126 строка  - создается объект tabbedPane

// Короче, надо чтобы именно в этот tabbedPane просто добавлялись новые вкладки, и все



