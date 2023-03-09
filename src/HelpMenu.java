import javax.swing.*;
import java.awt.event.KeyEvent;

public class HelpMenu extends JMenu {
    HelpMenu() {
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,
                    "A simple text editor written entirely in java.",
                    "About",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        this.setMnemonic(KeyEvent.VK_H);
        about.setMnemonic(KeyEvent.VK_A);

        this.add(about);
        this.setText("Help");
    }


}