import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {
    private GamePanel gamePanel;

    private int score = 0;
    private double direction = 2*Math.PI;

    private Image image = null;
    private int imageHeight;
    private int imageWidth;

    public Player() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/plane.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageHeight = (int) (image.getHeight(null) * 0.1);
        imageWidth = (int) (image.getWidth(null) * 0.1);
    }

    public void shoot() {
        // System.out.println("Shoot");
        gamePanel.addProjection(new Projection(
                (Setting.WIDTH / 2),
                Setting.HEIGHT - imageHeight/2,
                Math.PI / 2 - direction));
    }

    public int getScore() {
        return score;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public Image getImage() {
        return image;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
