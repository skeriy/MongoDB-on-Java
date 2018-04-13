import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class MainWindow extends JFrame {

    private JTable table;
    private JTable find_table;
    private MongOperations mo;
    private JButton add_button;
    private JButton ins_button;
    private JButton upd_button;
    private JButton del_button;
    private JButton find_button;

    public MainWindow() {
        super("МОНГО");
        setBounds(300, 100, 800, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel jp = new JPanel();
        mo = new MongOperations();
        mo.createConnection();
        table = mo.getTable();
        table.setEnabled(false);
        table.setPreferredScrollableViewportSize(new Dimension(750, 165));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        jp.add(scrollPane);
        mainPanel.add(jp);

        JPanel jp_buttons = new JPanel(new FlowLayout());

        add_button = new JButton("Обновить таблицу");
        ins_button = new JButton("Добавить данные");
        upd_button = new JButton("Обновить данные");
        del_button = new JButton("Удалить данные");
        find_button = new JButton("Поиск");

        jp_buttons.add(add_button);
        jp_buttons.add(ins_button);
        jp_buttons.add(upd_button);
        jp_buttons.add(del_button);
        jp_buttons.add(find_button);
        jp_buttons.setPreferredSize(new Dimension(600, 50));
        mainPanel.add(jp_buttons);
        add(mainPanel);

        initListeners();
    }

    private void initListeners() {
        add_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table.setModel(mo.getTable().getModel());
            }
        });
        ins_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addDoc();
            }
        });
        upd_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateDoc();
            }
        });
        del_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                delDoc();
            }
        });
        find_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                find_field();
            }
        });
    }

    //Добавление нового документа
    public void addDoc(){
        JFrame jf = new JFrame("Добавление");
        jf.setBounds(500, 100, 400, 300);
        JPanel mainPanel = new JPanel();
        //mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel panel1 = new JPanel(new FlowLayout());
        JPanel panel2 = new JPanel(new FlowLayout());

        JLabel id = new JLabel("id:");
        id.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel fN = new JLabel("FirstName:");
        fN.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel lN = new JLabel("LastName:");
        JLabel gN = new JLabel("Group:");
        gN.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel age = new JLabel("Age:");
        age.setHorizontalAlignment(SwingConstants.RIGHT);

        JTextField text_id = new JTextField(20);
        JTextField text_fN = new JTextField(20);
        JTextField text_lN = new JTextField(20);
        JTextField text_gN = new JTextField(20);
        JTextField text_age = new JTextField(20);


        Box box = Box.createVerticalBox();

        Box bx0 = Box.createHorizontalBox();
        Box bx1 = Box.createHorizontalBox();
        Box bx2 = Box.createHorizontalBox();
        Box bx3 = Box.createHorizontalBox();
        Box bx4 = Box.createHorizontalBox();

        bx0.add(id);
        bx0.add(Box.createHorizontalStrut(6));
        bx0.add(text_id);
        box.add(bx0);
        box.add(Box.createVerticalStrut(10));

        bx1.add(fN);
        bx1.add(Box.createHorizontalStrut(6));
        bx1.add(text_fN);
        box.add(bx1);
        box.add(Box.createVerticalStrut(10));

        bx2.add(lN);
        bx2.add(Box.createHorizontalStrut(6));
        bx2.add(text_lN);
        box.add(bx2);
        box.add(Box.createVerticalStrut(10));

        bx3.add(gN);
        bx3.add(Box.createHorizontalStrut(6));
        bx3.add(text_gN);
        box.add(bx3);
        box.add(Box.createVerticalStrut(10));

        bx4.add(age);
        bx4.add(Box.createHorizontalStrut(6));
        bx4.add(text_age);
        box.add(bx4);
        box.add(Box.createVerticalStrut(10));

        panel1.add(box);

        id.setPreferredSize(lN.getPreferredSize());
        fN.setPreferredSize(lN.getPreferredSize());
        gN.setPreferredSize(lN.getPreferredSize());
        age.setPreferredSize(lN.getPreferredSize());
        text_fN.setPreferredSize(text_lN.getPreferredSize());
        text_gN.setPreferredSize(text_lN.getPreferredSize());
        text_age.setPreferredSize(text_lN.getPreferredSize());
        text_id.setPreferredSize(text_lN.getPreferredSize());

        JButton button_add = new JButton("Вставить");
        panel2.add(button_add);
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        jf.add(mainPanel);
        jf.setVisible(true);
        button_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mo.insertDoc(text_id.getText(),text_fN.getText(),text_lN.getText(), text_gN.getText(), text_age.getText());
                table.setModel(mo.getTable().getModel());
                jf.dispose();
            }
        });
    }
    //Обновление документа
    public void updateDoc() {
        JFrame jf = new JFrame("Обновление");
        jf.setBounds(500, 100, 400, 300);
        JPanel mainPanel = new JPanel();
        //mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel panel1 = new JPanel(new FlowLayout());
        JPanel panel2 = new JPanel(new FlowLayout());

        ButtonGroup gr = new ButtonGroup();


        JRadioButton rfN = new JRadioButton("FirstName");
        JRadioButton rlN = new JRadioButton("LastName:");
        JRadioButton rgN = new JRadioButton("Group:");
        JRadioButton rAge = new JRadioButton("Age:");

        gr.add(rfN);
        gr.add(rlN);
        gr.add(rgN);
        gr.add(rAge);

        JTextField text_field = new JTextField(20);
        JTextField text_id = new JTextField(20);
        JLabel id = new JLabel("id");


        Box box = Box.createVerticalBox();

        Box bx_1 = Box.createHorizontalBox();
        Box bx0 = Box.createHorizontalBox();
        Box bx1 = Box.createHorizontalBox();

        bx_1.add(id);
        bx_1.add(text_id);
        bx_1.add(Box.createHorizontalStrut(6));
        box.add(bx_1);
        box.add(Box.createVerticalStrut(10));

        bx0.add(rfN);
        bx0.add(rlN);
        bx0.add(rgN);
        bx0.add(rAge);
        bx0.add(Box.createHorizontalStrut(6));
        box.add(bx0);
        box.add(Box.createVerticalStrut(10));

        bx1.add(text_field);
        box.add(bx1);
        panel1.add(box);

        JButton button_add = new JButton("Обновить");
        panel2.add(button_add);
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        jf.add(mainPanel);
        jf.setVisible(true);
        button_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String col = getSelectedButtonText(gr);
                mo.updateDoc(text_id.getText(),col,text_field.getText());
                table.setModel(mo.getTable().getModel());
                jf.dispose();
            }
        });

    }

    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

    //Удаление документа
    public void delDoc(){
        JFrame jf = new JFrame("Удаление");
        jf.setBounds(500, 100, 400, 300);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel panel1 = new JPanel(new FlowLayout());
        JPanel panel2 = new JPanel(new FlowLayout());

        JTextField text_id = new JTextField(20);
        JLabel id = new JLabel("id");


        Box box = Box.createVerticalBox();

        Box bx0 = Box.createHorizontalBox();

        bx0.add(id);
        bx0.add(text_id);
        bx0.add(Box.createHorizontalStrut(6));
        box.add(bx0);
        box.add(Box.createVerticalStrut(10));

        panel1.add(box);

        JButton button_add = new JButton("Удалить");
        panel2.add(button_add);
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        jf.add(mainPanel);
        jf.setVisible(true);
        button_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mo.deleteDoc(text_id.getText());
                table.setModel(mo.getTable().getModel());
                jf.dispose();
            }
        });

    }

    //Поиск по документам
    public void find_field() {
        JFrame jf = new JFrame("Поиск");
        jf.setBounds(500, 100, 800, 400);
        JPanel mainPanel = new JPanel();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel panel1 = new JPanel(new FlowLayout());
        JPanel panel2 = new JPanel(new FlowLayout());
        JPanel jp = new JPanel(new FlowLayout());

        ButtonGroup gr = new ButtonGroup();

        JRadioButton rfN = new JRadioButton("FirstName");
        JRadioButton rlN = new JRadioButton("LastName");
        JRadioButton rgN = new JRadioButton("Group");
        JRadioButton rAge = new JRadioButton("Age");

        gr.add(rfN);
        gr.add(rlN);
        gr.add(rgN);
        gr.add(rAge);

        JTextField text_field = new JTextField(20);
        Box box = Box.createVerticalBox();

        Box bx_1 = Box.createHorizontalBox();
        Box bx0 = Box.createHorizontalBox();
        Box bx1 = Box.createHorizontalBox();

        bx0.add(rfN);
        bx0.add(rlN);
        bx0.add(rgN);
        bx0.add(rAge);
        bx0.add(Box.createHorizontalStrut(6));
        box.add(bx0);
        box.add(Box.createVerticalStrut(10));

        bx1.add(text_field);
        box.add(bx1);
        panel1.add(box);

        JButton button_add = new JButton("Найти");
        panel2.add(button_add);

        find_table = new JTable();
        find_table.setEnabled(false);
        find_table.setPreferredScrollableViewportSize(new Dimension(750, 165));
        find_table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(find_table);
        jp.add(scrollPane);


        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(jp);
        jf.add(mainPanel);
        jf.setVisible(true);
        button_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String col = getSelectedButtonText(gr);
                String value = text_field.getText();
                find_table.setModel(mo.find_f(col,value).getModel());
            }
        });

    }


    public static void main(String[] args) {
        MainWindow mw = new MainWindow();
        mw.setResizable(false);
        mw.setVisible(true);
    }
}