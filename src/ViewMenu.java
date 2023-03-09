import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ViewMenu extends JMenu {
    JCheckBox toggleTextWrapping;
    JCheckBox toggleDarkMode;

    ViewMenu() {
        JMenuItem font = new JMenuItem("Font...");
        JMenuItem changeFontColor = new JMenuItem("Font Color");
        JMenuItem changeBackgroundColor = new JMenuItem("Background Color");
        toggleDarkMode = new JCheckBox("Dark Mode");
        toggleTextWrapping = new JCheckBox("Text Wrapping");

        font.addActionListener(e -> new FontDialog());
        changeFontColor.addActionListener(e -> setFontColor());
        changeBackgroundColor.addActionListener(e -> setBackgroundColor());
        toggleDarkMode.addActionListener(e -> setDarkMode());
        toggleTextWrapping.addActionListener(e -> setTextWrapping());

        this.setMnemonic(KeyEvent.VK_V);
        font.setMnemonic(KeyEvent.VK_F);
        changeFontColor.setMnemonic(KeyEvent.VK_C);
        changeBackgroundColor.setMnemonic(KeyEvent.VK_B);
        toggleDarkMode.setMnemonic(KeyEvent.VK_D);
        toggleTextWrapping.setMnemonic(KeyEvent.VK_T);

        this.add(font);
        this.add(new JSeparator());
        this.add(changeFontColor);
        this.add(changeBackgroundColor);
        this.add(toggleDarkMode);
        this.add(new JSeparator());
        this.add(toggleTextWrapping);
        this.setText("View");
    }

    private void setFontColor() {
        Color color = JColorChooser.showDialog(null, "Color Chooser", _MainFrame.textArea.getForeground());
        _MainFrame.textArea.setForeground(color);
    }

    private void setBackgroundColor() {
        Color color = JColorChooser.showDialog(null, "Color Chooser", _MainFrame.textArea.getBackground());
        _MainFrame.textArea.setBackground(color);
    }

    Color backgroundColor = _MainFrame.textArea.getBackground();
    Color foregroundColor = _MainFrame.textArea.getForeground();
    private void setDarkMode() {
        if(toggleDarkMode.isSelected()) {
            _MainFrame.textArea.setBackground(Color.DARK_GRAY);
            _MainFrame.textArea.setForeground(Color.WHITE);
        }
        else {
            _MainFrame.textArea.setBackground(backgroundColor);
            _MainFrame.textArea.setForeground(foregroundColor);
        }
    }

    private void setTextWrapping() {
        if(toggleTextWrapping.isSelected()) {
            _MainFrame.textArea.setLineWrap(true);
            _MainFrame.textArea.setWrapStyleWord(true);
        }
        else {
            _MainFrame.textArea.setLineWrap(false);
            _MainFrame.textArea.setWrapStyleWord(false);
        }
    }

    private static class FontDialog extends JFrame {
        JSpinner fontSizeSpinner;
        JComboBox fontFamilyIndex;
        JComboBox fontStyle;

        FontDialog() {
            this.setTitle("Font");
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setLayout(new GridLayout(6, 1));

            fontSizeSpinner = new JSpinner();
            fontSizeSpinner.setValue(_MainFrame.textArea.getFont().getSize());
            fontSizeSpinner.addChangeListener(e -> setFontSize());

            String[] index = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
            fontFamilyIndex = new JComboBox(index);
            fontFamilyIndex.setSelectedItem(_MainFrame.textArea.getFont().getFamily());
            fontFamilyIndex.addActionListener(e -> setFont());

            String[] styles = {"Plain", "Bold", "Italic"};
            fontStyle = new JComboBox(styles);
            fontStyle.setSelectedIndex(_MainFrame.textArea.getFont().getStyle());
            fontStyle.addActionListener(e -> setFontStyle());



            /*
            JPanel familyPanel = new JPanel();
            JPanel stylePanel = new JPanel();
            JPanel sizePanel = new JPanel();
            familyPanel.setLayout(new GridLayout(2, 1));
            stylePanel.setLayout(new GridLayout(2, 1));
            sizePanel.setLayout(new GridLayout(2, 1));
            familyPanel.add(new JLabel("Family"));
            stylePanel.add(new JLabel("Style"));
            sizePanel.add(new JLabel("Size"));
            familyPanel.add(fontFamilyIndex);
            stylePanel.add(fontStyle);
            sizePanel.add(fontSizeSpinner);
            this.add(familyPanel);
            this.add(stylePanel);
            this.add(sizePanel);
             */

            /*
            JPanel textPanel = new JPanel();
            JPanel actionPanel = new JPanel();
            textPanel.setLayout(new GridLayout(3, 1));
            actionPanel.setLayout(new GridLayout(3, 1));
            textPanel.add(new JLabel("Family"));
            textPanel.add(new JLabel("Style"));
            textPanel.add(new JLabel("Size"));
            actionPanel.add(fontFamilyIndex);
            actionPanel.add(fontStyle);
            actionPanel.add(fontSizeSpinner);
            this.add(textPanel);
            this.add(actionPanel);

             */

            this.add(new JLabel("Family"));
            this.add(fontFamilyIndex);
            this.add(new JLabel("Style"));
            this.add(fontStyle);
            this.add(new JLabel("Size"));
            this.add(fontSizeSpinner);

            this.pack();
            //this.setResizable(false);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        }

        private void setFontSize() {
            if((int) fontSizeSpinner.getValue() < 1) fontSizeSpinner.setValue(1);
            _MainFrame.textArea.setFont(new Font(_MainFrame.textArea.getFont().getFamily(),
                    _MainFrame.textArea.getFont().getStyle(),
                    (int) fontSizeSpinner.getValue()));
        }

        private void setFontStyle() {
            _MainFrame.textArea.setFont(new Font(_MainFrame.textArea.getFont().getFamily(),
                    fontStyle.getSelectedIndex(),
                    _MainFrame.textArea.getFont().getSize()));
        }

        private void setFont() {
            _MainFrame.textArea.setFont(new Font((String) fontFamilyIndex.getSelectedItem(),
                    _MainFrame.textArea.getFont().getStyle(),
                    _MainFrame.textArea.getFont().getSize()));
        }
    }
}