package main.java.net.trlewis.ersaGui;

import net.trlewis.ersa.RsaHelper;
import net.trlewis.ersa.RsaKeyStore;

import javax.swing.*;
import java.awt.*;
import java.security.KeyPair;
import java.security.PublicKey;

/**
 * Created by travisl on 3/8/17.
 */
public final class MainWindow {
    //final modifier on class prevents extension of class, making it function like static

    // for making MainWindow function as static
    private MainWindow() { }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("ersa GUI");
        Dimension windowSize = new Dimension(640, 480);
        frame.setPreferredSize(windowSize);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setContentPane(new LoadKeyStoreWindow().getRootPanel());


        JTabbedPane tabbedPane = new JTabbedPane();
        EncryptMessageTab etab = new EncryptMessageTab();
        tabbedPane.addTab("Encrypt", null, etab.getRootPanel());

        DecryptMessageTab dtab = new DecryptMessageTab();
        tabbedPane.addTab("Decrypt", null, dtab.getRootPanel());

        frame.setContentPane(tabbedPane);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(MainWindow::createAndShowGUI);
    }
}
