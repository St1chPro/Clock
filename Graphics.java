import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created with IntelliJ IDEA.
 * User: st1chpro
 * Date: 30.05.13
 * Time: 23:36
 * Package name: PACKAGE_NAME
 * Project name: Clock
 */
public class Graphics
{
    JFrame startFrame;
    DigitalClock digitFrame;
    JPanel panel;
    JPanel radioPanel;
    JLabel label;
    JRadioButton analogButton;
    JRadioButton digitalButton;

    Graphics()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        startFrame = new JFrame();
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setSize(320, 100);
        startFrame.setTitle("Clock");
        startFrame.setLocation((int) screenSize.getWidth() / 2 - startFrame.getWidth() / 2,
                (int) screenSize.getHeight() / 2 - startFrame.getHeight() / 2);

        panel = new JPanel(new BorderLayout());
        label = new JLabel("Выберите тип часов: ");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Times New Roman", Font.BOLD, 18));
        panel.add(label, BorderLayout.NORTH);

        analogButton = new JRadioButton("Аналоговые часы");
        digitalButton = new JRadioButton("Цифровые часы");
        radioPanel = new JPanel();
        radioPanel.add(analogButton);
        radioPanel.add(digitalButton);
        digitalButton.addActionListener(new DigitalButtonListener());
        panel.add(radioPanel, BorderLayout.SOUTH);

        startFrame.add(panel);
        startFrame.setVisible(true);
    }
    class DigitalButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            startFrame.dispose();
            digitFrame = new DigitalClock();
        }
    }

}

