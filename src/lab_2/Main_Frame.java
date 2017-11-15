package lab_2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.*;
import static java.lang.Math.*;

public class Main_Frame extends JFrame {
    /*
    Параметры рамки
     */
    private static final int width = 1300;
    private static final int height = 500;
    /*
    Текстовые поля. JTextField позволяет вводить однострочный текст
    Если надо ввести закрытую информацию, то можно использовать класс
    наследник JPasswordField
     */
    private JTextField result_field;
    private JTextField x_field;
    private JTextField y_field;
    private JTextField z_field;
    /*
    Если нужен какой-то компонент, чтобы отобразить какое-то сообщение
    пользователю или сделать для поля ввода текстовую метку, или показать
    иконку — используем JLabel. Текст, который показывает JLabel выделять нельзя, можно только смотреть.
     */
    private JLabel image;

    /*
    легко реализовать группы переключателей. Для того, чтобы реализовать группы переключателей
    нужно создать для каждой группы по одному объекту ButtonGroup.
    Затем в группе переключателей необходимо добавить объекты типа JRadioButton.
    Объект класса ButtonGroup предназначен для того, чтобы отключать ранее выбранный переключатель,
    если пользователь щелкнул на новой кнопке.
     */
    private ButtonGroup radioButtons = new ButtonGroup();
    /*
    Класс Box используется для работы с менеджером блочного размещения BoxLayout
    Менеджер BoxLayout размещает элементы на панели в строку или в столбец
    В классе Box есть методы, которые помогают красиво размещать необходимые блоки
     */
    private Box formula_type = Box.createHorizontalBox();
    private ButtonGroup radioMemoryButtons = new ButtonGroup();
    private Box hBoxMemoryType = Box.createHorizontalBox();
    private JTextField memoryTextField;
    int formula_number = 1;
    private int memoryId= 1;

    /*
    Переменные, для выделения памяти
     */
    private Double mem1 = new Double(0);
    private Double mem2 = new Double(0);
    private Double mem3 = new Double(0);

    /*
    Методы получения значения выражения по заданным формулам
     */
    public Double formula1(Double x, Double y, Double z)
    {
        if (y <= 0)	{
            /*
            JOptionPane нужен для вывода сообщений или выбора простых данных
            showMessageDialog - открывает диалоговое окно с заданным сообщением
            WARNING_MESSAGE - помечает, что вывод данного окна означает ошибку
             */
            JOptionPane.showMessageDialog(Main_Frame.this,
                    "y должен быть положительным", "" +
                            "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
            return 0.0;
        }
        if ((pow(x,2)+ sin(z)+exp(cos(z))) < 0)	{
            JOptionPane.showMessageDialog(Main_Frame.this,
                    "выражение под корнем должно быть положительным", "" +
                            "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
            return 0.0;
        }
        return sin(log(y)+ sin(Math.PI*pow(y,2)))* pow(pow(x,2)+ sin(z)+exp(cos(z)),1/4);
    }
    public Double formula2(Double x, Double y, Double z)
    {
        if (y == -1)	{
            JOptionPane.showMessageDialog(Main_Frame.this,
                    "y должен не ровняться -1", "" +
                            "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
            return 0.0;
        }
        if (x <= 0)	{
            JOptionPane.showMessageDialog(Main_Frame.this,
                    "x должен быть положительным", "" +
                            "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
            return 0.0;
        }
        if ((exp(cos(x))+pow(sin(Math.PI*z),2)) < 0)	{
            JOptionPane.showMessageDialog(Main_Frame.this,
                    "выражение под корнем должно быть положительным", "" +
                            "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
            return 0.0;
        }
        return pow(cos(exp(x))+log(pow(1+y,2))+pow(exp(cos(x))+pow(sin(Math.PI*z),2),1/2)+pow(1/x,1/2)+cos(pow(y,2)),sin(z));
    }

    /*
    Метод добавления кнопок для выделения памяти
     */
    private void addMemoryRadioButton (String buttonName, final int memoryId)	{
        JRadioButton button = new JRadioButton(buttonName);

        /*
        ActionListener необходим, чтобы мы могли прописать дейсвтие, которое будет выполняться
        после нажатия на кнопку.
        addActionListener - добавления "слушателя" на нашу кнопку. В данном интерфейсе нам
        нужно переопределить метод actionPerformed, в котором мы прописываем само дейсвтие
         */
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event)	{
                Main_Frame.this.memoryId = memoryId;
                if (memoryId == 1)	memoryTextField.setText(mem1.toString());
                if (memoryId == 2)	memoryTextField.setText(mem2.toString());
                if (memoryId == 3)	memoryTextField.setText(mem3.toString());
            }
        });

        radioMemoryButtons.add(button);
        hBoxMemoryType.add(button);
    }
    /*
    Метод добавления кнопки для выбора активной формулы.
     */
    private void addRadioButton(String name, final int formula_number)
    {
        JRadioButton button = new JRadioButton(name);
        //создаем необходимое действие
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                Main_Frame.this.formula_number = formula_number;
                /*
                Условия ниже помогают определить какая кнопка нажата и вывести в панель
                соответствующую картинку с формулой. Там используется меод createIcon, он описан в конце класса.
                метод setIcon позволяет поставить картинку на нужную панель
                Этот способ подходит только для случая, когда файлы с картинками хранятся в одном пакете с классом
                 */
                if (formula_number == 1) {
                    ImageIcon icon1 = createIcon("formula_1.jpg");
                    image.setIcon(icon1);
                }
                if (formula_number == 2) {
                    ImageIcon icon2 = createIcon("formula_2.jpg");
                    image.setIcon(icon2);
                }
            }
        });
        radioButtons.add(button);
        formula_type.add(button);
    }

    /*
    Конструктор класса, который выполнит всю программу, при создании его объекта.
     */
    public Main_Frame() {
        super("Вычисление формулы");
        setSize(width,height);
        /*
        Toolkit используется, чтобы связать различные компоненты с определенными
        собственными реализациями инструментария
        getDefaultToolkit получает инструментарий по умолчанию.
         */
        Toolkit kit = Toolkit.getDefaultToolkit();
        /*
        setLocation устонавливает компонент на конкретное заданное место
        getScreenSize
         */
        setLocation((kit.getScreenSize().width-width)/2,
                (kit.getScreenSize().height-height)/2);



        /*
        создание горизонтального блока, в котором будет находиться картинка
         */
        Box picture = Box.createHorizontalBox();
        /*
        Создание "пружин". Они необходимы для красивого расставления всех компонентов.
        Если будет мало или много свободного места, то эти пружнки либо растянутся либо наоборот сожмутся
         */
        picture.add(Box.createVerticalGlue());
        picture.add(Box.createHorizontalGlue());
        image = new JLabel(new ImageIcon(Main_Frame.class.getResource("formula_1.jpg")));
        // Добавление картинки на панель
        picture.add(image);
        picture.add(Box.createHorizontalGlue());
        /*
        Также можно установить цвет рамки методом setBorder:
        picture.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        Но лично мне так не нравится :)
         */

        formula_type.add(Box.createHorizontalGlue());
        /*
        Создание области для выбора формул.
         */
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
        formula_type.add(Box.createHorizontalGlue());

        //Создание области для ввода значений
        Box data=Box.createHorizontalBox();
        data.add(Box.createHorizontalGlue());
        x_field = new JTextField("",10);
        x_field.setMaximumSize(x_field.getPreferredSize());
        y_field = new JTextField("",10);
        y_field.setMaximumSize(x_field.getPreferredSize());
        z_field = new JTextField("",10);
        z_field.setMaximumSize(x_field.getPreferredSize());
        JLabel x_label=new JLabel("X:");
        JLabel y_label=new JLabel("Y:");
        JLabel z_label=new JLabel("Z:");
        data.add(Box.createHorizontalGlue());
        data.add(x_label);
        data.add(Box.createHorizontalStrut(10));
        data.add(x_field);
        data.add(Box.createHorizontalStrut(100));
        data.add(y_label);
        data.add(Box.createHorizontalStrut(10));
        data.add(y_field);
        data.add(Box.createHorizontalStrut(100));
        data.add(z_label);
        data.add(Box.createHorizontalStrut(10));
        data.add(z_field);
        data.add(Box.createHorizontalGlue());

        // Создание области для итогового результата
        Box result_area = Box.createHorizontalBox();
        result_area.add(Box.createHorizontalGlue());
        JLabel resultlabel=new JLabel("Результат:");
        result_field = new JTextField("",15);
        result_field.setMaximumSize(result_field.getPreferredSize());
        result_area.add(resultlabel);
        result_area.add(Box.createHorizontalStrut(10));
        result_area.add(result_field);
        result_area.add(Box.createHorizontalGlue());

        Box actions=Box.createHorizontalBox();                        // область для действий
        JButton calc_button=new JButton("Вычислить");
        calc_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                //Преобразование введенных строк в числа с плавающей точкой может
                //спровоцировать исключительную ситуацию при неправильном формате чисел,
                //поэтому необходим блок try-catch
                try {
                    //Получить значение X
                    Double x = Double.parseDouble(x_field.getText());
                    //Получить значение Y
                    Double y = Double.parseDouble(y_field.getText());
                    //Получить значение Z
                    Double z = Double.parseDouble(z_field.getText());
                    // Результат
                    Double result;

                    //Вычислить результат
                    if (formula_number == 1)
                        result = formula1(x, y, z);
                    else
                        result = formula2(x, y, z);
                    //Установить текст надписи равным результату
                    result_field.setText(result.toString());
                } catch (NumberFormatException ex) {
                    //В случае исключительной ситуации показать сообщение
                    JOptionPane.showMessageDialog(Main_Frame.this, "Ошибка в" +
                                    "формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton clean_button=new JButton("Очистить");
        clean_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                x_field.setText("");
                y_field.setText("");
                z_field.setText("");
                result_field.setText("");
            }
        });
        actions.add(Box.createHorizontalGlue());
        actions.add(calc_button);
        actions.add(Box.createHorizontalStrut(10));
        actions.add(clean_button);
        actions.add(Box.createHorizontalGlue());

        /*
        Добавление области для работы с памятью и действий при выборе разных кнопок
        После получения результата мы можем выбрать память и добавить данный результат
        нажатием на М+ или очистить МС.
        Это необходимо, например, для сравнения результатов при разных данных.
         */
        hBoxMemoryType.add(Box.createHorizontalGlue());
        addMemoryRadioButton("Память 1",1);
        addMemoryRadioButton("Память 2",2);
        addMemoryRadioButton("Память 3",3);
        radioMemoryButtons.setSelected(radioMemoryButtons.getElements().nextElement().getModel(), true);
        hBoxMemoryType.add(Box.createHorizontalGlue());



        Box memory_result=Box.createHorizontalBox();
        memory_result.add(Box.createHorizontalGlue());
        JButton MC=new JButton("MC");

        MC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (memoryId == 1)	mem1 = (double) 0;
                if (memoryId == 2)	mem2 = (double) 0;
                if (memoryId == 3)	mem3 = (double) 0;
                memoryTextField.setText("0.0");
            }
        });

        JButton M_plus=new JButton("M+");
        M_plus.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try{
                    Double result = Double.parseDouble(result_field.getText());

                    /*
                    Накопление результата в зависимости от выбранной памяти
                     */
                    if (memoryId == 1) 	{mem1 += result;memoryTextField.setText(mem1.toString());}
                    if (memoryId == 2)	{mem2 += result;memoryTextField.setText(mem2.toString());}
                    if (memoryId == 3)	{mem3 += result;memoryTextField.setText(mem3.toString());}

                }catch (NumberFormatException ex)
                { JOptionPane.showMessageDialog(Main_Frame.this,
                        "Ошибка в формате записи числа с плавающей точкой", "" +
                                "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        memoryTextField = new JTextField("0.0", 15);
        memoryTextField.setMaximumSize(memoryTextField.getPreferredSize());

        memory_result.add(MC);
        memory_result.add(Box.createHorizontalStrut(10));
        memory_result.add(memoryTextField);
        memory_result.add(Box.createHorizontalStrut(10));
        memory_result.add(M_plus);
        memory_result.add(Box.createHorizontalGlue());



        /*
        Создание итогового вида нашего фрейма (контент окна)
        Просто добавляем все созданные выше области
         */
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(picture);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(formula_type);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(data);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(result_area);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(actions);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hBoxMemoryType);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(memory_result);
        //getContentPane возвращает контейнер верхнего уровня
        getContentPane().add(contentBox, BorderLayout.CENTER);




    }

    /*
    Метод для создание иконки используя вложенный файл с картинкой
    передаем параметром название файла с картинкой
     */
    protected static ImageIcon createIcon(String path) {
        /*
        Класс ImageIcon не может параметром принимать название файла,
        поэтому необходимо создать "буфферный" объект, который примет
        название класса и затем уже объект этого класса передать параметром
        в ImageIcon. Если такого файла нет, то выведет сообщение об ошибке
         */
        URL imgURL = Main_Frame.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("File not found " + path);
            return null;
        }
    }
}
