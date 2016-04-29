/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RanSanMoi;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author nghia
 */
public class SnakeFrame implements KeyListener {

    private JFrame jf;
    private SnakePanel sp;
    public boolean check = false;
    public SnakeFrame(int bando, int tocdo, MyThread th) {
        sp = new SnakePanel(bando, th);
        jf = new JFrame("Rắn săn mồi");
        jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jf.setSize(KTKhungran.X + 300, KTKhungran.Y + 150);
        jf.setLocation(250, 100);
        jf.add(sp);
        jf.setVisible(true);
        jf.addKeyListener(this);
        sp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                isaction(e.getPoint());
            }
        });
        while (true) {
            while (check) {                
                try {
                    Thread.sleep(tocdo);
                } catch (InterruptedException ex) {
                }
            }
            sp.repaint();
            try {
                Thread.currentThread().sleep(tocdo);
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            sp.setD(1);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            sp.setD(2);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            sp.setD(3);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            sp.setD(4);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    public void tamdung(){
        check = true;
    }
    
    public void tieptuc(){
        check = false;
    }
    
    public void isaction(java.awt.Point p){
        if(p.x > 435 && p.x < 535 && p.y > 100 && p.y < 160 && !sp.DKThua){
            if(check){
                tieptuc();
            }else{
                tamdung();
            }
        }
    }
}
