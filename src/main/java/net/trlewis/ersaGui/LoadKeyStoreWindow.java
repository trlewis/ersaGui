package main.java.net.trlewis.ersaGui;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * Created by travisl on 3/8/17.
 */
public class LoadKeyStoreWindow {
    private JPasswordField passwordField;
    private JTextField fileLocationField;
    private JButton chooseFileButton;
    private JButton openKeystoreButton;
    private JPanel rootPanel;
    private JButton createKeystoreButton;

    private JFileChooser _fileChooser;

    public LoadKeyStoreWindow() {
        this._fileChooser = new JFileChooser();
        this._fileChooser.setFileFilter(new MasterKeyStoreFileFilter());

        // add GUI event listeners
        chooseFileButton.addActionListener(this::chooseFile_click);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(LoadKeyStoreWindow::createAndShowGUI);
    }

    private static void createAndShowGUI(){
        JFrame frame = new JFrame("ersa GUI");
//        Dimension windowSize = new Dimension(-1, 480);
//        frame.setPreferredSize(windowSize);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        JPanel panel = new JPanel();
//        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
//        panel.add(new LoadKeyStoreWindow().getRootPanel());

        frame.setContentPane(new LoadKeyStoreWindow().getRootPanel());
//        frame.setContentPane(panel);

//        Dimension size = frame.getPreferredSize();
//        frame.setPreferredSize(new Dimension(300, (int)size.getHeight()));
        frame.setPreferredSize(new Dimension(600, 96));

        frame.pack();
        frame.setVisible(true);
    }

    private void chooseFile_click(ActionEvent ae) {
        int returnVal = this._fileChooser.showOpenDialog(null);
        if(returnVal != JFileChooser.APPROVE_OPTION)
            return;

        File file = this._fileChooser.getSelectedFile();
        if(file == null || file.isDirectory())
            return;

        boolean opening = file.exists();
        this.createKeystoreButton.setVisible(!opening);
        this.openKeystoreButton.setVisible(opening);

        RsaKeyStoreManager.openKeyStoreFile(file.getAbsolutePath());
//        RsaKeyStoreManager.getInstance().openKeyStoreFile(file.getAbsolutePath());
    }

    JPanel getRootPanel() {
        return this.rootPanel;
    }

    class MasterKeyStoreFileFilter extends FileFilter {
        @Override
        public boolean accept(File file) {
            if(file.isDirectory())
                return true;
            return true;
//            String name = file.getName();
//            return name.endsWith(".eks");
        }

        @Override
        public String getDescription() {
            return "ersa keystore files (*.eks)";
        }
    }
}
