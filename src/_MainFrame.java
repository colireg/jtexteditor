import javax.swing.*;
import java.awt.*;

public class _MainFrame extends JFrame {
    static JTextArea textArea;

    _MainFrame() {
        this.setTitle("JTextEditor");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(512, 512);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout());

        textArea = new JTextArea();
        textArea.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JMenuBar mainMenuBar = new JMenuBar();
        mainMenuBar.add(new FileMenu());
        mainMenuBar.add(new EditMenu());
        mainMenuBar.add(new ViewMenu());
        mainMenuBar.add(new HelpMenu());

        this.setJMenuBar(mainMenuBar);
        this.add(scrollPane);
        this.setVisible(true);
    }

    public static void main(String[] args) {
            new _MainFrame();
    }
}
