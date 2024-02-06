import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class ReflexPanel extends JPanel implements ActionListener, MouseListener {
    private JFrame jFrame;
    private Timer timer;
    private int delay;
    private boolean clickable = false;

    private long clickTime;

    private long lastUnixTime;

    public ReflexPanel(JFrame jFrame) {
        this.jFrame = jFrame;
        this.jFrame.getContentPane().addMouseListener(this);
        this.jFrame.setFocusable(true);
        this.jFrame = jFrame;
        Date date = new Date();
        int rand = ThreadLocalRandom.current().nextInt(4, 7 + 1);
        this.lastUnixTime = date.getTime();
        this.clickTime = this.lastUnixTime + (rand*1000);
        timer = new Timer(1, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int x = 900;
        int y = 500;
        Date date = new Date();
        this.lastUnixTime = date.getTime();
        if (this.clickTime < this.lastUnixTime){
            g.setColor(Color.RED);
            this.clickable = true;
        }
        g.fillOval(x - (300/2), y - (300/2), 300, 300);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (this.clickable == true){
            long diff = this.lastUnixTime - this.clickTime;
            JOptionPane.showMessageDialog(this.jFrame, "Uzyskany wynik to: "+diff+"ms");
            jFrame.setVisible(false);
            jFrame.dispose();
            timer.stop();
        }
        else{
            JOptionPane.showMessageDialog(this.jFrame, "Nie udało się");
            jFrame.setVisible(false);
            jFrame.dispose();
            timer.stop();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
