package GUI;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.*;
import static java.awt.BorderLayout.*;
import static javax.swing.BoxLayout.*;

public class FirstGui extends JPanel implements ActionListener {
  static JFrame frame = new JFrame("TextEditorPRO V1.0");
  JMenuItem create, save, saveAs, open, print, exit;
  JTabbedPane tabbedPane;
  JEditorPane editor = new JEditorPane();
  String getter;
  File file;
  JFileChooser fileChooser;
  BufferedReader bufferedReader;
  int currentVal;


  public static void main(String[] args) {
    JFrame.setDefaultLookAndFeelDecorated(true);
    FirstGui gui = new FirstGui();
    CheckUser checkUser = new CheckUser();
    //checkUser.validation();   -закомментил пока, т.к. задолбался при запуске вводить пароль
    gui.startGui();
  }

  void startGui() {
    frame.setSize(1500, 1000);
    frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\mainImage.jpg"));
    JMenuBar jmenu = new JMenuBar();
    frame.setJMenuBar(jmenu);
    // item (Файл)
    JMenu file = new JMenu("Файл");
    create = new JMenuItem("Create...");
    create.setIcon(new ImageIcon("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\create.png"));

    open = new JMenuItem("Открыть...");
    open.setIcon(new ImageIcon("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\open.jpg"));
    open.addActionListener(this);

    save = new JMenuItem("Сохранить");
    save.setIcon(new ImageIcon("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\saveIcon.png"));
    save.addActionListener(this);

    saveAs = new JMenuItem("Сохранить как...");
    saveAs.setIcon(new ImageIcon("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\saveAs.png"));
    saveAs.addActionListener(this);

    print = new JMenuItem("Печать");
    print.setIcon(new ImageIcon("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\print.jpg"));

    exit = new JMenuItem("Выход");
    exit.setIcon(new ImageIcon("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\exit.png"));

    jmenu.add(file);
    file.add(create);
    file.add(open);
    file.add(save);
    save.setEnabled(false);
    file.add(saveAs);
    file.add(new JSeparator());
    file.add(print);
    file.add(new JSeparator());
    file.add(exit);
    // item (Редактирование)
    JMenu edit = new JMenu("Редактирование");
    JMenuItem cut = new JMenuItem("Вырезать");
    JMenuItem copy = new JMenuItem("Копировать");
    JMenuItem paste = new JMenuItem("Вставить");
    jmenu.add(edit);
    cut.setIcon(new ImageIcon("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\scissors.png"));
    edit.add(cut);
    copy.setIcon(new ImageIcon("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\copy.png"));
    edit.add(copy);
    paste.setIcon(new ImageIcon("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\dobavit.png"));
    edit.add(paste);
    // item (Вид)
    JMenu view = new JMenu("Вид");
    JCheckBoxMenuItem lineika = new JCheckBoxMenuItem("Линейка");
    JCheckBoxMenuItem setka = new JCheckBoxMenuItem("Сетка");
    JCheckBoxMenuItem navigation = new JCheckBoxMenuItem("Навигация");
    JRadioButton onePage = new JRadioButton("Одна страница");
    JRadioButton twoPage = new JRadioButton("Две страницы");
    jmenu.add(view);
    view.add(lineika);
    view.add(setka);
    view.add(navigation);
    view.add(new JSeparator());
    view.add(onePage);
    view.add(twoPage);
    // item (Текст)
    JMenu text = new JMenu("Текст");
    JMenuItem fontSize = new JMenuItem("Размер");
    JMenu fontStyle = new JMenu("Стиль");
    jmenu.add(text);
    text.add(fontSize);
    text.add(fontStyle);
    //add fields to Шрифт
    JMenuItem bold = new JMenuItem("Жирный");
    JMenuItem kursiv = new JMenuItem("Курсив");
    JMenu font = new JMenu("Шрифт");
    JMenuItem arial = new JMenuItem("Arial");
    JMenuItem times = new JMenuItem("Times");
    JMenuItem calibri = new JMenuItem("Calibri");
    fontStyle.add(bold);
    fontStyle.add(kursiv);
    view.add(new JSeparator());
    fontStyle.add(font);
    font.add(arial);
    font.add(times);
    font.add(calibri);

    ToolBarFactory toolBarFactory = new ToolBarFactory(); // общий объект для создания других тулбаров
    JToolBar toolBar1 = toolBarFactory.createToolBar1();
    JToolBar toolBar2 = toolBarFactory.createToolBar2();  // ВЛАД, получается я присваиваю переменной тулбар
    JToolBar toolBar3 = toolBarFactory.createToolBar3();  // полученный из соседнего класса

    JScrollPane scroll = new JScrollPane(editor);
    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

    tabbedPane = new JTabbedPane();                   // ВЛАД , вот она панель с вкладочками
    tabbedPane.addTab("Вкладка 1", scroll);

    JPanel panel1 = new JPanel();
    panel1.setLayout(new BorderLayout());

    JPanel panel2 = new JPanel();
    panel2.setLayout(new BoxLayout(panel2, Y_AXIS));
    panel2.add(toolBar1);
    panel2.add(toolBar2);
    panel1.add(panel2, NORTH);

    JPanel panel3 = new JPanel();
    panel3.setLayout(new BoxLayout(panel3, Y_AXIS));
    panel3.setBackground(Color.lightGray);
    panel3.add(tabbedPane);
    panel3.add(toolBar3);
    panel1.add(panel3);

    frame.getContentPane().add(panel1);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void actionPerformed(ActionEvent ev) {
    if (ev.getSource() == saveAs) {
      fileChooser = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter("Текстовые файлы", "txt");
      fileChooser.setFileFilter(filter);
      int result = fileChooser.showSaveDialog(FirstGui.this);
      fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
      file = fileChooser.getSelectedFile();
      try {
        if (file != null && result == JFileChooser.APPROVE_OPTION) {
          FileWriter fileWriter = new FileWriter(file);
          getter = editor.getText();
          fileWriter.write(getter);
          fileWriter.close();
          JOptionPane.showMessageDialog(fileChooser.getParent(),
                  "Ваш файл успешно сохранен!" + "\n" + "\n"
                          + "Путь: " + fileChooser.getSelectedFile() + "\n" + "Имя файла: "
                          + file.getName());
        }
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    } else if (ev.getSource() == open) {
      JFileChooser fileChooser = new JFileChooser();
      currentVal = fileChooser.showOpenDialog(null);
      if (currentVal == JFileChooser.APPROVE_OPTION) {
        file = fileChooser.getSelectedFile();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Текстовые файлы", "txt");
        fileChooser.setFileFilter(filter);
        try {
          bufferedReader = new BufferedReader(new FileReader(file));
          editor.read(bufferedReader, null);
          bufferedReader.close();
          save.setEnabled(true);
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    } else if (ev.getSource() == save) {
      Icon icon = new ImageIcon("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\save.png");
      String message1 = "Вы уверены, что хотите перезаписать файл?" + "\n" + "Имя файла: "
              + file.getName();
      String message2 = "Ваш файл успешно перезаписан!";
      int result = JOptionPane.showOptionDialog(null,
              new Object[]{message1}, "TextEditorPRO", JOptionPane.OK_CANCEL_OPTION,
              JOptionPane.QUESTION_MESSAGE, icon, null, null);
      if (result == JOptionPane.OK_OPTION) {
        try {
          if (file != null && result == JFileChooser.APPROVE_OPTION) {
            FileWriter fileWriter = new FileWriter(file);
            getter = editor.getText();
            fileWriter.write(getter);
            fileWriter.close();
            JOptionPane.showMessageDialog(this,
                    new String[]{"Ваш файл успешно перезаписан!", "\n", "Имя файла: "
                            + file.getName()},
                    "TextEditorPRO", JOptionPane.OK_OPTION, icon);
          }
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    }
  }
}
