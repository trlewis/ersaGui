package main.java.net.trlewis.ersaGui;

import java.util.EventObject;

/**
 * Contains information about whether a key was added/removed and what the name of it was. The source
 * is just the object that fired the event.
 * Created by travisl on 3/8/17.
 */
class KeyChangeEvent extends EventObject {
    private KeyChangeEventType _changeType;
    private String _keyName;

    public KeyChangeEvent(Object o, String keyName, KeyChangeEventType changeType) {
        super(o);
        this._keyName = keyName;
        this._changeType = changeType;
    }

//    public KeyChangeEvent(Object o, String keyName, KeyChangeEventType changeType) {
//        super(o);
//        this._keyName = keyName;
//        this._changeType = changeType;
//    }

    public String getKeyName() {
        return this._keyName;
    }

    public KeyChangeEventType getKeyChangeEventType() {
        return this._changeType;
    }
}
