import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private Player player;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) {
            player.setDirection(player.getDirection() - 0.05);
            // System.out.println("Left");
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            player.setDirection(player.getDirection() + 0.05);
            // System.out.println("Right");
        } else if (keyCode == KeyEvent.VK_UP) {
            player.shoot();
            // System.out.println("Shoot");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
