import java.awt.Image;
//setตำแหน่ง ขนาด setกรณีชนเอเลี่ยน
public class Enemy {
    private int x;
    private int y;
    private int size = 50;

    private Image image = null;

    public Enemy(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public int getSize() {
        return size;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }
    public boolean isHit(int x, int y) {
        //ระยะห่างเอเลี่ยนกับกระสุน
        return (x - this.x) * (x - this.x) + (y - this.y) * (y - this.y) < 2*size * size / 4;
    }
}
