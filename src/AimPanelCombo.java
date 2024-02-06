import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.sql.Array;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class AimPanelCombo extends JPanel implements ActionListener,MouseListener  {

    HashMap<Point, Integer> map = new HashMap<>();
    private JFrame jFrame;
    private Timer timer;
    private int delay;
    private int time;
    private int drawedCount;
    private int maxCircles;
    private int points;
    private long wholeTime;
    private long lastUnixTime;

    public AimPanelCombo(JFrame jFrame, int time, int delay, int maxCircles){
        this.jFrame = jFrame;
        this.time = time;
        this.maxCircles = maxCircles;
        this.delay = delay;
        this.points = 0;
        this.drawedCount = 0;
        Date date = new Date();
        this.lastUnixTime = date.getTime();
        this.wholeTime = date.getTime() + (time*1000);
        this.jFrame.getContentPane().addMouseListener(this);
        this.jFrame.setFocusable(true);
        timer = new Timer(50, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Date date = new Date();
        long unixTime = date.getTime();

        if(this.lastUnixTime <= unixTime && (((this.maxCircles != 0) && this.drawedCount < this.maxCircles) || this.maxCircles == 0) && (((this.time != 0) && this.wholeTime > unixTime) || this.time == 0) ) {
            int x = ThreadLocalRandom.current().nextInt(40, 1900 + 1);
            int y = ThreadLocalRandom.current().nextInt(40, 1000 + 1);
            g.fillOval(x - (10/2), y - (10/2), 10, 10);
            Point p = new Point(x, y);
            map.put(p, 15);
            this.lastUnixTime = date.getTime() + this.delay;
            this.drawedCount++;
            if (this.maxCircles != 0 && this.drawedCount >= this.maxCircles){
                this.time = (this.delay/1000);
                this.wholeTime = date.getTime() + this.delay;
            }
        }

        for (Map.Entry<Point, Integer> element : map.entrySet()){
            Point p2 = element.getKey();
            map.put(p2, element.getValue()+1);
            int x = (int) p2.getX();
            int y = (int) p2.getY();
            g.fillOval(x - ((element.getValue()+1)/2), y - ((element.getValue()+1)/2), element.getValue()+1, element.getValue()+1);
        }

        if(this.time != 0 && unixTime > this.wholeTime){
            this.wholeTime = unixTime+999999;
            JOptionPane.showMessageDialog(this.jFrame, "Uzyskany wynik to: "+this.points+"/"+this.drawedCount);
            jFrame.setVisible(false);
            jFrame.dispose();
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        repaint();
    }

    public double calculateDistanceBetweenPointsWithPoint2D(
            double x1,
            double y1,
            double x2,
            double y2) {

        return Point2D.distance(x1, y1, x2, y2);
    }

    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        Point p = e.getPoint();
        Vector<Point> toRemove = new Vector();
        boolean hit = false;
        for (Map.Entry<Point, Integer> element : map.entrySet()){
            Point p2 = element.getKey();
            int radius = element.getValue();
            double dist = calculateDistanceBetweenPointsWithPoint2D(p.getX(), p.getY(), p2.getX(), p2.getY());
            if (dist < radius){
                this.points++;
                hit = true;
            }
                toRemove.add(p2);
        }



        for (Point pointToRemove : toRemove) {
            map.remove(pointToRemove);
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
