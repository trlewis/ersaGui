package main.java.net.trlewis.ersaGui;

import net.trlewis.ersa.RsaKeyStore;

import java.security.KeyPair;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by travisl on 3/8/17.
 */
public final class RsaKeyStoreManager {
    private static List<KeyChangeListener> _otherKeyListeners = new ArrayList<>();
    private static List<KeyChangeListener> _myKeyListeners = new ArrayList<>();
    private RsaKeyStore _keyStore = new RsaKeyStore();

    private static RsaKeyStoreManager _instance;

    private String _filePath;

    private RsaKeyStoreManager(){}

    private RsaKeyStoreManager(String filePath) {
        this._filePath=  filePath;

        //TODO: check to see if file exists or not.
    }

    public static RsaKeyStoreManager getInstance(){
        if(_instance == null)
            _instance = new RsaKeyStoreManager();
        return _instance;
    }

    public void addMyKeyListener(KeyChangeListener listener) {
        _myKeyListeners.add(listener);
    }

    public void addOtherKeyListener(KeyChangeListener listener) {
        _otherKeyListeners.add(listener);
    }

    public static void openKeyStoreFile(String filePath) {
        _instance = new RsaKeyStoreManager(filePath);
    }

    public void createNewMyKeyPair(String name) {
        boolean success = _keyStore.createNewMyKeyPair(name);
        if(!success)
            return;

        this._keyStore.getMyKeyPair(name);
        for(KeyChangeListener kcl : _myKeyListeners)
            kcl.onKeyChanged(new KeyChangeEvent(this, name, KeyChangeEventType.ADDED));
    }

    public void removeMyKeyPair(String name) {
        this._keyStore.removeMyKey(name);
        for(KeyChangeListener kcl : _myKeyListeners)
            kcl.onKeyChanged(new KeyChangeEvent(this, name, KeyChangeEventType.REMOVED));
    }

    public void addOtherKey(String name, PublicKey key) {
        boolean result = this._keyStore.addOtherKey(name, key);
        if(!result)
            return;
        for(KeyChangeListener kcl : _otherKeyListeners)
            kcl.onKeyChanged(new KeyChangeEvent(this, name, KeyChangeEventType.ADDED));
    }

    public void removeOtherKey(String name) {
        this._keyStore.removeOtherKey(name);
        for(KeyChangeListener kcl : _otherKeyListeners)
            kcl.onKeyChanged(new KeyChangeEvent(this, name, KeyChangeEventType.REMOVED));
    }

    //region wrapper methods

    public PublicKey getOtherKey(String keyName) {
        return _keyStore.getOtherKey(keyName);
    }

    public KeyPair getMyKeyPair(String name) {
        return _keyStore.getMyKeyPair(name);
    }

    public Set<String> getMyKeyNames() {
        return _keyStore.getMyKeyNames();
    }

    public Set<String> getOtherKeyNames() {
        return _keyStore.getOtherKeyNames();
    }

    //endregion wrapper methods
}
