import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;

public class MainFrame {
    private JLabel modeLabel;
    private JList modeList;
    private JPanel mainPanel;
    private JLabel delayText;
    private JTextArea delayArea;
    private JTextArea timeArea;
    private JLabel timeText;
    private JButton startButton;
    private JTextArea circlesCountArea;
    private JLabel circlesCount;
    private JList mode2List;
    private JLabel mode2Select;

    private JFrame jFrame;

    private DefaultListModel model2;

    public MainFrame() {
        DefaultListModel model = new DefaultListModel();
        String[] modes = {"Normal Aim Training", "Combo Aim Training", "Reflex" };
        for (int i = 0; i < modes.length; i++) {
            model.add(i, modes[i]);
        }
        modeList.setModel(model);


        DefaultListModel model2 = new DefaultListModel();
        String[] modes2 = {"Czas", "Ilość celów" };
        for (int i = 0; i < modes2.length; i++) {
            model2.add(i, modes2[i]);
        }
        mode2List.setModel(model2);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int time = 0;
                if (timeArea.isVisible() == true){
                    time = Integer.parseInt(timeArea.getText());
                }
                int maxCircles = 0;
                if (circlesCountArea.isVisible() == true){
                    maxCircles = Integer.parseInt(circlesCountArea.getText());
                }
                int delay = Integer.parseInt(delayArea.getText());
                int index = modeList.getSelectedIndex();

                JFrame frame = new JFrame("Game");
                if (index == 0) {
                    AimPanel aimPanel = new AimPanel(frame, time, delay, maxCircles);
                    frame.getContentPane().add(aimPanel);
                    frame.setVisible(true);
                    frame.setSize(1920, 1080);
                    frame.setResizable(false);
                } else if (index == 1) {
                    AimPanelCombo aimPanel = new AimPanelCombo(frame, time, delay, maxCircles);
                    frame.getContentPane().add(aimPanel);
                    frame.setVisible(true);
                    frame.setSize(1920, 1080);
                    frame.setResizable(false);
                } else if (index == 2){
                    ReflexPanel reflexPanel = new ReflexPanel(frame);
                    frame.getContentPane().add(reflexPanel);
                    frame.setVisible(true);
                    frame.setSize(1920, 1080);
                    frame.setResizable(false);
                }
            }
        });


        mode2List.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (mode2List.getSelectedIndex() == 0){
                    timeArea.setVisible(true);
                    timeText.setVisible(true);
                    circlesCountArea.setVisible(false);
                    circlesCount.setVisible(false);
                }
                else if(mode2List.getSelectedIndex() == 1){
                    circlesCountArea.setVisible(true);
                    circlesCount.setVisible(true);
                    timeArea.setVisible(false);
                    timeText.setVisible(false);
                }
            }
        });
        modeList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (modeList.getSelectedIndex() == 0){
                    timeArea.setVisible(false);
                    timeText.setVisible(false);
                    circlesCountArea.setVisible(true);
                    circlesCount.setVisible(true);
                    delayArea.setVisible(false);
                    delayText.setVisible(false);
                    mode2List.setVisible(true);
                    mode2Select.setVisible(true);

                }
                else if(modeList.getSelectedIndex() == 1){
                    circlesCountArea.setVisible(false);
                    circlesCount.setVisible(false);
                    circlesCountArea.setVisible(true);
                    circlesCount.setVisible(true);
                    delayArea.setVisible(true);
                    delayText.setVisible(true);
                    mode2List.setVisible(true);
                    mode2Select.setVisible(true);

                }
                else if(modeList.getSelectedIndex() == 2){
                    timeArea.setVisible(false);
                    timeText.setVisible(false);
                    circlesCountArea.setVisible(false);
                    circlesCount.setVisible(false);
                    delayArea.setVisible(false);
                    delayText.setVisible(false);
                    mode2List.setVisible(false);
                    mode2Select.setVisible(false);

                }

            }
        });
    }

    public void setMainFrame(){
        JFrame frame = new JFrame("Game");
        circlesCountArea.setVisible(false);
        circlesCount.setVisible(false);
        timeArea.setVisible(false);
        timeText.setVisible(false);

        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }
}
