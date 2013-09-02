import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: st1chpro
 * Date: 31.05.13
 * Time: 0:43
 * Package name: PACKAGE_NAME
 * Project name: Clock
 */
public class DigitalClock
{
    JFrame digitalClockFrame;
    JPanel digitalClockPanel;
    JPanel controlPanel;
    JLabel time;
    JButton setSystemTime;
    JButton start;
    JComboBox<Integer> hoursBox;
    JComboBox<Integer> minutsBox;
    JComboBox<Integer> secondsBox;
    Integer[] hoursItems = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
    Integer[] minSecItems = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59};
    int hours;
    int minuts;
    int seconds;
    Timer t;

    DigitalClock()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        digitalClockFrame = new JFrame();
        digitalClockFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        digitalClockFrame.setTitle("Цифровые часы");
        digitalClockFrame.setSize(400, 200);
        digitalClockFrame.setLocation((int) screenSize.getWidth() / 2 - digitalClockFrame.getWidth() / 2, (int) screenSize.getHeight() / 2 - digitalClockFrame.getHeight() / 2);

        digitalClockPanel = new JPanel(new BorderLayout());
        time = new JLabel("Установите время");
        time.setHorizontalAlignment(SwingConstants.CENTER);
        time.setFont(new Font("Times New Roman", Font.BOLD, 18));
        digitalClockPanel.add(time, BorderLayout.CENTER);

        controlPanel = new JPanel();
        hoursBox = new JComboBox<Integer>(hoursItems);
        minutsBox = new JComboBox<Integer>(minSecItems);
        secondsBox = new JComboBox<Integer>(minSecItems);
        setSystemTime = new JButton("Set system time");
        start = new JButton("Start");

        hoursBox.addActionListener(new ComboListener());
        minutsBox.addActionListener(new ComboListener());
        secondsBox.addActionListener(new ComboListener());
        setSystemTime.addActionListener(new SystemTimeListener());
        start.addActionListener(new StartListener());

        controlPanel.add(hoursBox);
        controlPanel.add(minutsBox);
        controlPanel.add(secondsBox);
        controlPanel.add(setSystemTime);
        controlPanel.add(start);

        digitalClockPanel.add(controlPanel, BorderLayout.SOUTH);
        digitalClockFrame.add(digitalClockPanel);
        digitalClockFrame.setVisible(true);
    }

    class ComboListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource().equals(hoursBox))
            {
                hours = (Integer) hoursBox.getSelectedItem();
            }
            if(e.getSource().equals(minutsBox))
            {
                minuts = (Integer) minutsBox.getSelectedItem();
            }
            if(e.getSource().equals(secondsBox))
            {
                seconds = (Integer) secondsBox.getSelectedItem();
            }
        }
    }

    class SystemTimeListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Date date = new Date();
            hours = date.getHours();
            minuts = date.getMinutes();
            seconds = date.getSeconds();
            if(start.getText().equals("Start"))
            {
                start.doClick();
            }
        }
    }

    class StartListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(start.getText().equals("Start"))
            {
                time.setFont(new Font("Times New Roman", Font.BOLD, 72));
                time.setText(String.format("%02d:%02d:%02d", hours, minuts, seconds));
                time.repaint();
                t = new Timer(1000, al);
                t.start();
                start.setText("Stop");
                start.repaint();
            } else
            {
                start.setText("Start");
                t.stop();
                start.repaint();
            }
        }
    }

    ActionListener al = new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            if(seconds == 59)
            {
                seconds = 0;
                if(minuts == 59)
                {
                    minuts = 0;
                    if(hours == 23)
                    {
                        hours = 0;
                    } else
                    {
                        hours++;
                    }
                } else
                {
                    minuts++;
                }
            } else
            {
                seconds++;
            }
            time.setText(String.format("%02d:%02d:%02d", hours, minuts, seconds));
            time.repaint();
        }
    };
}




