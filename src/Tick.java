import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tick implements ActionListener {
    private GamePanel gamePanel;

    Tick(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gamePanel.repaint();
    }
}
