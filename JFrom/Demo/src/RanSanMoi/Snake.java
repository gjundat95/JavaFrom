/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RanSanMoi;

/**
 *
 * @author nghia
 */
public class Snake {
    private int length;
    private Point[] point;
    public Snake(){
        length = 3;
        point = new Point[200];
        for(int i = 0; i < point.length; i++){
            point[i] = new Point(0, 0);
        }
        
        point[0].setX(KTKhungran.Y/KTKhungran.sizeCell/2);
        point[0].setY(KTKhungran.X/KTKhungran.sizeCell/2 + 1);
        
        point[1].setX(KTKhungran.Y/KTKhungran.sizeCell/2);
        point[1].setY(KTKhungran.X/KTKhungran.sizeCell/2);
        
        point[2].setX(KTKhungran.Y/KTKhungran.sizeCell/2);
        point[2].setY(KTKhungran.X/KTKhungran.sizeCell/2 - 1);
    }
    
    public int getLength(){
        return length;
    }
    public void setLength(int length){
        this.length = length;
    }
    public Point getPoint(int i){
        return point[i];
    }
    public void setPoint(int i, Point p){
        this.point[i].setX(p.getX());
        this.point[i].setY(p.getY());
    }
}
