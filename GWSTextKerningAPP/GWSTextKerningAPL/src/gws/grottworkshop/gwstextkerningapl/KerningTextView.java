package gws.grottworkshop.gwstextkerningapl;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ScaleXSpan;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 *
 * KerningTextView, Pedro Barros is the original author. I modified it  so that
 * multiple lines dose not break the managed kerning and gave it a better
 * class name that refers to what its actual function is.
 *
 * Usage:
 *
 * <code>
 * KerningTextView textView = new KerningTextView(context);
 * textView.setLetterSpacing(10); //Or any float. To reset to normal, use 0 or LetterSpacingTextView.LetterSpacing.NORMAL
 * textView.setText("My text");
 * //Add the textView in a layout, for instance:
 * ((LinearLayout) findViewById(R.id.myLinearLayout)).addView(textView);
* </code>
 *
 * @author Fred Grott
 *
 */
public class KerningTextView extends TextView {

    private float letterSpacing = LetterSpacing.NORMAL;
    private CharSequence originalText = "";


    public KerningTextView(Context context) {
        super(context);
    }

    public KerningTextView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public KerningTextView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
    }

    public float getLetterSpacing() {
        return letterSpacing;
    }

    public void setLetterSpacing(float letterSpacing) {
        this.letterSpacing = letterSpacing;
        applyLetterSpacing();
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        originalText = text;
        applyLetterSpacing();
    }

    @Override
    public CharSequence getText() {
        return originalText;
    }

    private void applyLetterSpacing() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < originalText.length(); i++) {
            builder.append(originalText.charAt(i));
            if(i+1 < originalText.length()) {
                builder.append("\u00A0");
            }
        }
        SpannableString finalText = new SpannableString(builder.toString());
        if(builder.toString().length() > 1) {
            for(int i = 1; i < builder.toString().length(); i+=2) {
                finalText.setSpan(new ScaleXSpan((letterSpacing+1)/10), i, i+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        super.setText(finalText, BufferType.SPANNABLE);
    }

    public class LetterSpacing {
        public final static float NORMAL = 0;
    }
}
