import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class GamePanel extends JPanel {
    private Tick tick;
    private Timer timer;
    private Player player;
    private KeyHandler keyHandler = new KeyHandler();

    private Image imagebackground = null;
    private Image imageCoin = null;

    private ArrayList<Projection> projections = new ArrayList<Projection>();
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    //รูปของเอเลี่ยน
    public Image[] alienImages = {
            loadImage("/resources/alien1.png"),
            loadImage("/resources/alien2.png"),
            loadImage("/resources/alien3.png"),
    };
    //ใส่รูปพื้นหลังกับรูปเหรียญ
    GamePanel() {
        setSize(Setting.WIDTH, Setting.HEIGHT);

        imagebackground = loadImage("/resources/bg001.png");
        imageCoin = loadImage("/resources/coin.png");

        setFocusable(true);
        addKeyListener(keyHandler);
    }
    //วาด
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        //randomเอเลี่ยน ให้ขึ้น4ตัวในเกม
        if (Math.random() < 0.5 && enemies.size() < 4) {
            enemies.add(
                    new Enemy(
                            (int) (Math.random() * Setting.WIDTH),
                            (int) (Math.random() * Setting.HEIGHT),
                            randomAlien()));
        }
        //วาดพื้นหลัง คะแนน 
        drawBackground(g2d);
        drawScore(g2d);
        
        //วาดกระสุน เอเลี่ยน ยานอวกาศ
        drawProjection(g2d);
        drawEnemy(g2d);
        drawPlayer(g2d);
    }
    //รูปพื้นหลัง
    public void drawBackground(Graphics2D g) {
        g.drawImage(imagebackground, 0, 0, Setting.WIDTH, Setting.HEIGHT, null);
        g.setColor(new Color(0, 0, 0, 50));
        g.fillRect(0, 0, Setting.WIDTH, Setting.HEIGHT);
    }
    //คะแนน ใส่รูปเหรียญกับตัวอักษรสีขาว
    public void drawScore(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));

        g.drawImage(imageCoin, Setting.WIDTH - 100, 5, 35, 35, this);
        g.drawString(String.valueOf(player.getScore()), Setting.WIDTH - 60, 30);
    }
    //ยานอวกาศ
    public void drawPlayer(Graphics2D g) {
        drawRotateImage(g,
                // (Setting.WIDTH / 2) - (player.getImageWidth() / 2),
                // Setting.HEIGHT - player.getImageHeight(),
                (Setting.WIDTH / 2) + (player.getImageWidth() / 4),
                Setting.HEIGHT ,
                player.getImage(),
                player.getDirection(),
                player.getImageWidth(),
                player.getImageHeight());
    }
    
    public Image randomAlien() {
        return alienImages[(int) (Math.random() * alienImages.length)];
    }
    //วาดกระสุน กลมเหลือง
    public void drawProjection(Graphics2D g) {
        g.setColor(Color.yellow);
        //loop ถ้ากระสุนเลยขอบจอกระสุนจะหายไปและถูกลบ
        for (Iterator<Projection> it = projections.iterator(); it.hasNext();) {
            Projection pj = it.next();
            if (pj.getX() < 0 || pj.getX() > Setting.WIDTH || pj.getY() < 0 || pj.getY() > Setting.HEIGHT) {
                it.remove();
                continue;
            }
            //loop ถ้ากระสุนโดนตัวเอเลี่ยนกระสุนจะหายไป
            for (Iterator<Enemy> it2 = enemies.iterator(); it2.hasNext();) {
                Enemy enemy = it2.next();
                if (enemy.isHit(pj.getX(), pj.getY())) {
                    it.remove();
                    it2.remove();
                    player.addScore(1);
                    continue;
                }
            }

            pj.move();
            //วาดกระสุน
            g.fillOval(
                    pj.getX() - pj.getSize() / 2,
                    pj.getY() - pj.getSize() / 2,
                    pj.getSize(), pj.getSize());
           
        }
    }

    public void drawEnemy(Graphics2D g) {
        for (Iterator<Enemy> it = enemies.iterator(); it.hasNext();) {
            Enemy enemy = it.next();
            if (enemy.getX() < 0 || enemy.getX() > Setting.WIDTH || enemy.getY() < 0 || enemy.getY() > Setting.HEIGHT) {
                it.remove();
                continue;
            }
            g.drawImage(
                    enemy.getImage(),
                    enemy.getX() - enemy.getSize() / 2,
                    enemy.getY() - enemy.getSize() / 2,
                    enemy.getSize(), enemy.getSize(),
                    null);
        }
    }

    public void addProjection(Projection pj) {
        projections.add(pj);
    }

    public void setTick(Tick tick) {
        this.tick = tick;
        timer = new Timer(1000 / 60, tick);
        timer.start();
    }

    public Tick getTick() {
        return tick;
    }

    public void setPlayer(Player player) {
        this.player = player;
        this.keyHandler.setPlayer(player);
    }

    public Player getPlayer() {
        return player;
    }
    //วาดยาน
    public void drawRotateImage(Graphics2D g2d, int x, int y, Image img, double angle, int width, int height) {
        AffineTransform backup = g2d.getTransform();
        AffineTransform a = AffineTransform.getRotateInstance(angle, x + width / 2, y + height / 2);
        g2d.setTransform(a);
        g2d.drawImage(img, x, y, width, height, null);
        g2d.setTransform(backup);
    }

    public Image loadImage(String path) {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}