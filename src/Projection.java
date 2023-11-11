public class Projection {
    private int x;
    private int y;
    private int size = 10;
    private double speed = 20;
    private double direction;

    public Projection(int x, int y, double direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public double getDirection() {
        return direction;
    }

    public double getSpeed() {
        return speed;
    }
    //การเคลื่อนที่ของกระสุน
    public void move() {
        x += speed * Math.cos(direction);
        y -= speed * Math.sin(direction);
    }
}
