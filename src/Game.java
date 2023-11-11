import javax.swing.*;
//สร้างหน้าจอframe
public class Game extends JFrame {
    private Tick tick;
    private Player player = new Player();
    private GamePanel gamePanel = new GamePanel();

    Game() {
        gamePanel.setPlayer(player);
        player.setGamePanel(gamePanel);
        //ActionListener
        tick = new Tick(gamePanel);
        gamePanel.setTick(tick);

        add(gamePanel);
    }

    public static void main(String[] args) {
        Game frame = new Game();
        frame.setVisible(true);
        frame.setSize(Setting.WIDTH, Setting.HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}
