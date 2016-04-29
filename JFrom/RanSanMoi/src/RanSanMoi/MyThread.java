
package RanSanMoi;


public class MyThread implements Runnable {

    private int bando;
    private int tocdo;
    public void setBando(int bando){
        this.bando = bando;
    }
    public void setTocdo(int tocdo){
        this.tocdo = tocdo;
    }
    @Override
    public void run() {
        SnakeFrame sf = new SnakeFrame(bando, tocdo, this);
    }
    
}
