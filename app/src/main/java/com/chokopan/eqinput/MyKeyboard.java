package com.chokopan.eqinput;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;

import java.util.List;

public class MyKeyboard extends Keyboard {
    private Key mEnterKey;
    private Key mSpaceKey;
    private Key mModeChangeKey;
    private Key mLanguageSwitchKey;
    private Key mSavedModeChangeKey;
    private Key mSavedLanguageSwitchKey;

    public MyKeyboard(Context context, int xmlLayoutResId) {
        super(context, xmlLayoutResId);
    }
    public MyKeyboard(Context context, int layoutTemplateResId,
                         CharSequence characters, int columns, int horizontalPadding) {
        super(context, layoutTemplateResId, characters, columns, horizontalPadding);
    }

    @Override
    protected Key createKeyFromXml(Resources res, Row parent, int x, int y,
                                   XmlResourceParser parser) {
        /*
        Key key = new MyKey(res, parent, x, y, parser);
        if (key.codes[0] == 10) {
            mEnterKey = key;
        } else if (key.codes[0] == ' ') {
            mSpaceKey = key;
        } else if (key.codes[0] == Keyboard.KEYCODE_MODE_CHANGE) {
            mModeChangeKey = key;
            mSavedModeChangeKey = new MyKey(res, parent, x, y, parser);
        } else if (key.codes[0] == EqIME.KEYCODE_SWITCH_LANG) {
            mLanguageSwitchKey = key;
            mSavedLanguageSwitchKey = new MyKey(res, parent, x, y, parser);
        }
        */
        return new MyKey(res, parent, x, y, parser);
    }
    /**
     * Dynamically change the visibility of the language switch key (a.k.a. globe key).
     * @param visible True if the language switch key should be visible.
     */
    /*
    void setLanguageSwitchKeyVisibility(boolean visible) {
        if (visible) {
            // The language switch key should be visible. Restore the size of the mode change key
            // and language switch key using the saved layout.
            mModeChangeKey.width = mSavedModeChangeKey.width;
            mModeChangeKey.x = mSavedModeChangeKey.x;
            mLanguageSwitchKey.width = mSavedLanguageSwitchKey.width;
            mLanguageSwitchKey.icon = mSavedLanguageSwitchKey.icon;
            mLanguageSwitchKey.iconPreview = mSavedLanguageSwitchKey.iconPreview;
        } else {
            // The language switch key should be hidden. Change the width of the mode change key
            // to fill the space of the language key so that the user will not see any strange gap.
            mModeChangeKey.width = mSavedModeChangeKey.width + mSavedLanguageSwitchKey.width;
            mLanguageSwitchKey.width = 0;
            mLanguageSwitchKey.icon = null;
            mLanguageSwitchKey.iconPreview = null;
        }
    }
    */
    /**
     * This looks at the ime options given by the current editor, to set the
     * appropriate label on the keyboard's enter key (if it has one).
     */
    /*
    void setImeOptions(Resources res, int options) {
        if (mEnterKey == null) {
            return;
        }
        switch (options&(EditorInfo.IME_MASK_ACTION|EditorInfo.IME_FLAG_NO_ENTER_ACTION)) {
            case EditorInfo.IME_ACTION_GO:
                mEnterKey.iconPreview = null;
                mEnterKey.icon = null;
                mEnterKey.label = res.getText(R.string.label_go_key);
                break;
            case EditorInfo.IME_ACTION_NEXT:
                mEnterKey.iconPreview = null;
                mEnterKey.icon = null;
                mEnterKey.label = res.getText(R.string.label_next_key);
                break;
            case EditorInfo.IME_ACTION_SEARCH:
                mEnterKey.icon = res.getDrawable(R.drawable.sym_keyboard_search);
                mEnterKey.label = null;
                break;
            case EditorInfo.IME_ACTION_SEND:
                mEnterKey.iconPreview = null;
                mEnterKey.icon = null;
                mEnterKey.label = res.getText(R.string.label_send_key);
                break;
            default:
                mEnterKey.icon = res.getDrawable(R.drawable.sym_keyboard_return);
                mEnterKey.label = null;
                break;
        }
    }
    */
    /*
    void setSpaceIcon(final Drawable icon) {
        if (mSpaceKey != null) {
            mSpaceKey.icon = icon;
        }
    }
    */
    static class MyKey extends Keyboard.Key {
        public String secondaryKey = "";
        public int secondaryCode = 0;

        public MyKey(Resources res, Keyboard.Row parent, int x, int y,
                        XmlResourceParser parser) {
            super(res, parent, x, y, parser);
            secondaryCode = parser.getAttributeIntValue("http://schemas.android.com/apk/lib/com.chokopan.eqinput.MyKeyboard.MyKey", "secondaryCode", 0);
            if (secondaryCode > 0)
                secondaryKey = parser.getAttributeValue("http://schemas.android.com/apk/lib/com.chokopan.eqinput.MyKeyboard.MyKey", "secondaryKey");
        }
    }
}