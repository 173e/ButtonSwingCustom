package swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author Gomez
 */
public class SwitchButton extends Component {

    private Timer timer;
    private float location;
    private boolean selected;
    private boolean mouseOver;
    private float speed = 0.10f;

    public SwitchButton() {
        setBackground(new Color(0, 174, 255));
        setPreferredSize(new Dimension(50, 25));
        setForeground(Color.white);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        location =2;

        timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (selected) {
                    int endLocation = getWidth() - getHeight() + 2;
                    System.out.println(endLocation);
                    if (location < endLocation) {
                        location += speed;
                        repaint();
                    } else {

                        timer.stop();
                        location = endLocation;
                        System.out.println("posicion ctual No.1: " + location);
                        repaint();
                    }
                } else {
                    int endLocation = 2;
                    if (location > endLocation) {
                        location -= speed;
                        repaint();
                    } else {
                        timer.stop();
                        location = endLocation;
                        System.out.println("posicion ctual No.2: " + location);
                        repaint();
                    }

                }

            }
        });
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                mouseOver= false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mouseOver= true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (mouseOver) {
                        selected=!selected;
                        timer.start();
                    }
                    
                }
            }
            
            
        });

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width, height, 25, 25);
        g2.setColor(getForeground());
        g2.fillOval((int)location, 2, height - 4, height - 4);

        super.paint(g);
    }

}
