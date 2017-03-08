package main.java.net.trlewis.ersaGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Vector;

/**
 * Created by travisl on 3/8/17.
 */
public class EncryptMessageTab implements KeyChangeListener {
    private JPanel rootPanel;
    private JTextField toEncryptBox;
    private JButton encryptButton;
    private JTextField textField2;
    private JButton copyToClipboardButton;
    private JButton newPrivateKeyButton;
    private JList myKeyList;
//    private JList<String> myKeyList;

    private DefaultListModel _keyList = new DefaultListModel();
//    private DefaultListModel<String> _keyList = new DefaultListModel<>();

    EncryptMessageTab() {
        this.encryptButton.addActionListener(this::encrypt_click);
        this.newPrivateKeyButton.addActionListener(ae -> this.newPrivateKey_click(ae));

        RsaKeyStoreManager.getInstance().addMyKeyListener(this);
    }

    JPanel getRootPanel() {
        return this.rootPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    //region GUI event handlers

    private void encrypt_click(ActionEvent actionEvent) {
    }

    private void newPrivateKey_click(ActionEvent actionEvent) {
        String name = JOptionPane.showInputDialog(null, "Enter name for new key");
        if(name == null || name.isEmpty())
            return;
        RsaKeyStoreManager.getInstance().createNewMyKeyPair(name);
    }

    @Override
    public void onKeyChanged(KeyChangeEvent e) {
        //add or remove key name from list
        this._keyList.addElement(e.getKeyName());
        Vector keyList = new Vector(Arrays.asList(this._keyList.toArray()));
        this.myKeyList.setListData(keyList);
    }

    //endregion GUI event handlers
}
