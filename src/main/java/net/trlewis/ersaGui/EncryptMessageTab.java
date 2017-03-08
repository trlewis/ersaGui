package main.java.net.trlewis.ersaGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.*;

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
    private JList<String> myKeyList;
    private JButton removeKeyButton;

    private DefaultListModel<String> _keyList = new DefaultListModel<>();

    EncryptMessageTab() {
        this.encryptButton.addActionListener(this::encrypt_click);
//        this.newPrivateKeyButton.addActionListener(ae -> this.newPrivateKey_click(ae));
        this.newPrivateKeyButton.addActionListener(this::newPrivateKey_click);
        this.removeKeyButton.addActionListener(this::deleteKey_click);

        RsaKeyStoreManager.getInstance().addMyKeyListener(this);
    }

    JPanel getRootPanel() {
        return this.rootPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    //region Inherited methods

    @Override
    public void onKeyChanged(KeyChangeEvent e) {
        //add or remove key name from list
        if(e.getKeyChangeEventType() == KeyChangeEventType.ADDED)
            this._keyList.addElement(e.getKeyName());
        else if(e.getKeyChangeEventType() == KeyChangeEventType.REMOVED)
            this._keyList.removeElement(e.getKeyName());
        else
            return;

        Object[] keyObjectArray = this._keyList.toArray();
        String[] keyStringArray = Arrays.copyOf(keyObjectArray, keyObjectArray.length, String[].class);
        Vector<String> keyList = new Vector<>(Arrays.asList(keyStringArray));
        this.myKeyList.setListData(keyList);
    }

    //endregion

    //region GUI event handlers

    private void encrypt_click(ActionEvent actionEvent) {
    }

    private void deleteKey_click(ActionEvent actionEvent) {
        String name = this.myKeyList.getSelectedValue();
        if (name == null || name.isEmpty())
            return;

        String cMsg = "Are you sure you want to delete the key '" + name + "'?";
        int result = JOptionPane.showConfirmDialog(null, cMsg, "Delete Key", JOptionPane.YES_NO_OPTION);
        if(result == 0) //apparently 0 is yes
            RsaKeyStoreManager.getInstance().removeMyKeyPair(name);
    }

    private void newPrivateKey_click(ActionEvent actionEvent) {
        String name = JOptionPane.showInputDialog(null, "Enter name for new key");
        if (name != null && !name.isEmpty())
            RsaKeyStoreManager.getInstance().createNewMyKeyPair(name);
    }

    //endregion GUI event handlers
}
