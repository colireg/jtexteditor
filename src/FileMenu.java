import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileMenu extends JMenu {
    static File currentFile = new File("");

    FileMenu() {
        JMenuItem newFile = new JMenuItem("New");
        JMenuItem openFile = new JMenuItem("Open");
        JMenuItem saveFile = new JMenuItem("Save");
        JMenuItem saveFileAs = new JMenuItem("Save as");
        JMenuItem properties = new JMenuItem("Properties");
        JMenuItem exit = new JMenuItem("Exit");

        newFile.addActionListener(e -> newFile());
        openFile.addActionListener(e -> openFile());
        saveFile.addActionListener(e -> saveFile());
        saveFileAs.addActionListener(e -> saveFileAs());
        exit.addActionListener(e -> System.exit(0));

        this.setMnemonic(KeyEvent.VK_F);
        newFile.setMnemonic(KeyEvent.VK_N);
        openFile.setMnemonic(KeyEvent.VK_O);
        saveFile.setMnemonic(KeyEvent.VK_S);
        saveFileAs.setMnemonic(KeyEvent.VK_S);
        properties.setMnemonic(KeyEvent.VK_P);
        exit.setMnemonic(KeyEvent.VK_E);

        this.add(newFile);
        this.add(openFile);
        this.add(saveFile);
        this.add(saveFileAs);
        this.add(new JSeparator());
        this.add(properties);
        this.add(exit);
        this.setText("File");
    }

    private void newFile() {
        _MainFrame.textArea.setText(null);
        currentFile = new File("");
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        int r = fileChooser.showOpenDialog(null);
        if(r == JFileChooser.APPROVE_OPTION) {
            currentFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
            Scanner fileIn = null;
            try {
                fileIn = new Scanner(currentFile);
                if(currentFile.isFile()) {
                    _MainFrame.textArea.setText(null);
                    while(fileIn.hasNextLine()) {
                        String line = fileIn.nextLine() + "\n";
                        _MainFrame.textArea.append(line);
                    }
                }
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null,
                        "File \"" + fileChooser.getSelectedFile().getAbsolutePath() + "\" not found.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            finally {
                fileIn.close();
            }
        }
    }

    private void saveFile() {
        if(currentFile.exists()) {
            PrintWriter fileOut = null;
            try {
                fileOut = new PrintWriter(currentFile);
                fileOut.println(_MainFrame.textArea.getText());
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null,
                        "File \"" + currentFile.getAbsolutePath() + "\" was not found.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            finally {
                fileOut.close();
            }
        }
        else {
            saveFileAs();
        }
    }

    private void saveFileAs() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        int r = fileChooser.showSaveDialog(null);

        if(r == JFileChooser.APPROVE_OPTION) {
            currentFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
            PrintWriter fileOut = null;

            try {
                fileOut = new PrintWriter(currentFile);
                fileOut.println(_MainFrame.textArea.getText());
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null,
                        "File \"" + fileChooser.getSelectedFile().getAbsolutePath() + "\" was not found.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            finally {
                fileOut.close();
            }
        }
    }
}