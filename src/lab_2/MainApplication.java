package lab_2;

import javax.swing.JFrame;
public class MainApplication {
    public static void main(String[]args)
    {
        Main_Frame frame = new Main_Frame();
        //После закрытия окна фрэйма программа завершится
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Делаем фрейм видимым
        frame.setVisible(true);
    }
}
