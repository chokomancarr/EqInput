package com.chokopan.eqinput;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import java.util.List;

public class MyKeyboardView extends KeyboardView {
    Paint paint;
    float dpi;
    public MyKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        dpi = getResources().getDisplayMetrics().densityDpi / 160f;
        paint = new Paint();
        paint.setTextAlign(Paint.Align.RIGHT);
        paint.setTextSize(dpi * 13f);
        paint.setStrokeWidth(2);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorKey2));
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        List<Keyboard.Key> keys = getKeyboard().getKeys();
        for(Keyboard.Key key: keys) {
            MyKeyboard.MyKey k = (MyKeyboard.MyKey)key;
            if(k.secondaryCode > 0)
                canvas.drawText(k.secondaryKey, key.x + key.width - dpi*4f, key.y + dpi*12f, paint);
        }
    }
    /*
    @Override
    protected boolean onLongPress(Keyboard.Key key) {
        if (key.codes[0] == Keyboard.KEYCODE_CANCEL) {
            getOnKeyboardActionListener().onKey(KEYCODE_OPTIONS, null);
            return true;
        } else {
            return super.onLongPress(key);
        }
    }
    void setSubtypeOnSpaceKey(final InputMethodSubtype subtype) {
        final LatinKeyboard keyboard = (LatinKeyboard)getKeyboard();
        keyboard.setSpaceIcon(getResources().getDrawable(subtype.getIconResId()));
        invalidateAllKeys();
    }
    */


}
