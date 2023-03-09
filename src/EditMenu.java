import javax.swing.*;
import java.awt.event.KeyEvent;

public class EditMenu extends JMenu {
    EditMenu() {
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");
        JMenuItem selectAll = new JMenuItem("Select All");
        JMenuItem find = new JMenuItem("Find");

        this.setMnemonic(KeyEvent.VK_E);
        cut.setMnemonic(KeyEvent.VK_U);
        copy.setMnemonic(KeyEvent.VK_C);
        paste.setMnemonic(KeyEvent.VK_P);
        selectAll.setMnemonic(KeyEvent.VK_S);
        find.setMnemonic(KeyEvent.VK_F);

        this.add(cut);
        this.add(copy);
        this.add(paste);
        this.add(new JSeparator());
        this.add(selectAll);
        this.add(find);
        this.setText("Edit");
    }
}