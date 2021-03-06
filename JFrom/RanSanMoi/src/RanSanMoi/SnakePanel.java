/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RanSanMoi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author nghia
 */
public class SnakePanel extends JPanel {

    private int Matran[][];
    private int d = 1; // 1 là từ trái qua phải, 2 là từ phải qua trái, 3 là từ trên xuống dưới, 4 là từ dưới lên trên
    private Snake sn;
    private boolean thucAn = false; // true là có thức ăn, false là không có
    private boolean thucAnColor =false; // dùng để thay đổi màu sắc cho thức ăn
    public boolean DKThua = false; // true là thua
    private int diem = 0;
    public String btn = "Tạm dừng/";
    public String str = "Tiếp tục";
    MyThread th;

    public SnakePanel(int map, MyThread th) {
//        System.out.println(map);
        this.th = th;
        this.setBackground(Color.blue);
        Matran = new int[KTKhungran.Y / KTKhungran.sizeCell][KTKhungran.X / KTKhungran.sizeCell];
        for (int i = 0; i < Matran.length; i++) {
            for (int j = 0; j < Matran[i].length; j++) {
                if (i == 0) {
                    Matran[i][j] = 1;
                } else if (i == Matran.length - 1) {
                    Matran[i][j] = 1;
                } else if (j == 0) {
                    Matran[i][j] = 1;
                } else if (j == Matran[i].length - 1) {
                    Matran[i][j] = 1;
                } else {
                    Matran[i][j] = 0;
                }
            }
        }
        if (map == 1) {
            int a = 5;
            int b = Matran.length - 6;
            for (int j = 5; j < Matran[0].length - 5 ; j++) {
                Matran[a][j] = 1;
                Matran[b][j] = 1;
            }

        } else if (map == 2) {
            int a = 3;
            int b = Matran.length - 4;
            for (int j = 8; j < Matran[0].length - 8; j++) {
                Matran[a][j] = 1;
                Matran[b][j] = 1;
            }
            for (int i = a; i <= b; i++) {
                for (int j = 3; j < 6; j++) {
                    Matran[i][Matran[0].length - j] = 1;
                    Matran[i][j] = 1;
                }
            }

        }
        sn = new Snake();
        for (int i = 0; i < sn.getLength(); i++) {
            Matran[sn.getPoint(i).getX()][sn.getPoint(i).getY()] = 2;
        }
    }

    public void setMatran(int i, int j, int num) {
        Matran[i][j] = num;
    }

    public int getMatran(int i, int j) {
        return Matran[i][j];
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getD() {
        return this.d;
    }

    @Override
    @SuppressWarnings("empty-statement")
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        if (!DKThua) {
            Point p = new Point(0, 0);

            for (int i = sn.getLength(); i > 0; i--) {
                // point thứ i bằng point thứ i-1
                p = sn.getPoint(i - 1); // khi rắn di chuyển phần sau di chuyển theo
                sn.setPoint(i, p);
                Matran[sn.getPoint(i).getX()][sn.getPoint(i).getY()] = 2;
            }
            // hướng di chuyển của rắn
            if (d == 1) {
                p.setX(sn.getPoint(0).getX());
                p.setY(sn.getPoint(0).getY() + 1);
                sn.setPoint(0, p);
            } else if (d == 2) {
                p.setX(sn.getPoint(0).getX());
                p.setY(sn.getPoint(0).getY() - 1);
                sn.setPoint(0, p);
            } else if (d == 3) {
                p.setX(sn.getPoint(0).getX() + 1);
                p.setY(sn.getPoint(0).getY());
                sn.setPoint(0, p);
            } else if (d == 4) {
                p.setX(sn.getPoint(0).getX() - 1);
                p.setY(sn.getPoint(0).getY());
                sn.setPoint(0, p);
            }
            if (Matran[sn.getPoint(0).getX()][sn.getPoint(0).getY()] == 3) {

                // nếu vị trí đầu của snake là thức ăn
                sn.setLength(sn.getLength() + 1); // tăng chiều dài snake lên 1
                Matran[sn.getPoint(0).getX()][sn.getPoint(0).getY()] = 2;
                thucAn = false;
                diem++;
            } else if (Matran[sn.getPoint(0).getX()][sn.getPoint(0).getY()] == 0) {
                // nếu là 0 thì cho phép di,  xóa vị trí cuối của snake
                Matran[sn.getPoint(sn.getLength()).getX()][sn.getPoint(sn.getLength()).getY()] = 0;
            } else if (Matran[sn.getPoint(0).getX()][sn.getPoint(0).getY()] == 2 || Matran[sn.getPoint(0).getX()][sn.getPoint(0).getY()] == 1) {
                DKThua = true;
                // cho nay la no thua ha hình như thế :)
                // Them duong dan
                
            }

            Matran[sn.getPoint(0).getX()][sn.getPoint(0).getY()] = 2; // vị trí đầu tiên của snake

            if (!thucAn) {
                // tạo vị trí ngẫu nhiên của thức ăn
                Random rd = new Random();
                int randomX = rd.nextInt(Matran.length);
                int randomY = rd.nextInt(Matran.length);
                if (Matran[randomX][randomY] != 0) {
                    outerloop:
                    for (int i = 0; i < Matran.length; i++) {
                        for (int j = 0; j < Matran[i].length; j++) {
                            if (Matran[i][j] == 0) {
                                Matran[i][j] = 3;
                                break outerloop;
                            }
                        }
                    }
                } else {
                Matran[randomX][randomY] = 3;
                }
                thucAn = true;
            }

            // vẽ trên panel dựa trên ma trận
            for (int i = 0; i < Matran.length; i++) {
                for (int j = 0; j < Matran[i].length; j++) {
                    if (Matran[i][j] == 1) { // vẽ khung
                        g.setColor(Color.CYAN);
                        g.fill3DRect(j * KTKhungran.sizeCell, i * KTKhungran.sizeCell, KTKhungran.sizeCell, KTKhungran.sizeCell, true);
                    } else if (Matran[i][j] == 2) { // vẽ rắn
                        g.setColor(Color.yellow);
                        g.fill3DRect(j * KTKhungran.sizeCell, i * KTKhungran.sizeCell, KTKhungran.sizeCell, KTKhungran.sizeCell, true);
                    } else if (Matran[i][j] == 3) { // vẽ thức ăn
                        if (thucAnColor) {
                            g.setColor(Color.RED);
                        } else {
                            g.setColor(Color.lightGray);
                        }
                        thucAnColor = !thucAnColor;
                        g.fill3DRect(j * KTKhungran.sizeCell, i * KTKhungran.sizeCell, KTKhungran.sizeCell, KTKhungran.sizeCell, true);
                    }
                }
            }

            g.setColor(Color.LIGHT_GRAY);
            Font font = new Font("Arial", Font.PLAIN, 35);
            g.setFont(font);
            String ss = "Điểm: " +diem;
            g.drawString(ss, 430, 40);
        } else {
            g.setColor(Color.red);
            Font font = new Font("Arial", Font.PLAIN, 40);
            g.setFont(font);
            String str = "Kết Thúc";
            g.drawString(str, 250, 100);
            g.setColor(Color.LIGHT_GRAY);
            String s = "Điểm Của Bạn: \n"+diem;
            g.drawString(s, 180, 180);
        }
        addbutton(g);
        if(DKThua){
            this.setEnabled(false);
            BestCore bestCore = new BestCore("C:\\new.txt");
//                String name = JOptionPane.showInputDialog("Nhap Ten Vao Di Thang Kia: ");
                NguoiChoi nc = new NguoiChoi(5, "tinh");
                bestCore.LuuDiemCao(nc);
                this.setEnabled(true);
        }
    }

    public void addbutton(Graphics g) {
        if (!DKThua) {
            g.setColor(Color.red);
            g.fillRect(435, 100, 100, 60);
            g.setColor(Color.BLACK);
            Font font = new Font("Arial", 20, 18);
            g.setFont(font);
            g.drawString(btn, 440, 125);
            g.drawString(str, 440, 145);
        }
    }
}
