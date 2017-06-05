package com.chokopan.eqinput;

import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

public class EqIME extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    static final int KEYCODE_SWITCH_LANG = -101;
    static final int KEYCODE_KB_ABC = -102;
    static final int KEYCODE_KB_NUM = -103;
    static final int KEYCODE_KB_GRK = -104;
    static final int KEYCODE_KB_SYM = -105;

    MyKeyboardView kv;
    int activeKbType;
    MyKeyboard kb_abc;
    MyKeyboard kb_num;
    MyKeyboard kb_grk;
    MyKeyboard kb_sym;

    private boolean caps = false;

    @Override
    public View onCreateInputView() {
        kv = (MyKeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        kb_abc = new MyKeyboard(this, R.xml.qwerty);
        kb_grk = new MyKeyboard(this, R.xml.greek);
        kb_num = new MyKeyboard(this, R.xml.numbers);
        kb_sym = new MyKeyboard(this, R.xml.symbols);
        kv.setKeyboard(kb_abc);
        kv.setOnKeyboardActionListener(this);
        kv.setPreviewEnabled(false);
        return kv;
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();
        if (primaryCode < -101 && primaryCode > -106) {
            changeKeyboard(primaryCode);
            return;
        }
        switch(primaryCode){
            case KEYCODE_SWITCH_LANG:
                InputMethodManager in = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                in.switchToNextInputMethod(getCurrentInputBinding().getConnectionToken(), false);
                break;
            case Keyboard.KEYCODE_DELETE :
                ic.deleteSurroundingText(1, 0);
                break;
            case Keyboard.KEYCODE_SHIFT:
                caps = !caps;
                kb_abc.setShifted(caps);
                kb_grk.setShifted(caps);
                kv.invalidateAllKeys();
                break;
            case Keyboard.KEYCODE_DONE:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            default:
                char code = (char)primaryCode;
                if(Character.isLetter(code) && caps){
                    code = Character.toUpperCase(code);
                }
                ic.commitText(String.valueOf(code),1);
        }
    }

    @Override
    public void onPress(int primaryCode) {
    }

    @Override
    public void onRelease(int primaryCode) {
    }

    @Override
    public void onText(CharSequence text) {
    }

    @Override
    public void swipeDown() {
    }

    @Override
    public void swipeLeft() {
    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeUp() {

    }

    void changeKeyboard (int a) {
        activeKbType = a;
        switch (activeKbType) {
            case -102:
                kv.setKeyboard(kb_abc);
                break;
            case -103:
                kv.setKeyboard(kb_num);
                break;
            case -104:
                kv.setKeyboard(kb_grk);
                break;
            case -105:
                kv.setKeyboard(kb_sym);
                break;
        }
    }
}