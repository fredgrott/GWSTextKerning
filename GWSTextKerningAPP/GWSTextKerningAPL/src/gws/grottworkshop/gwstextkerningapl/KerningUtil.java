package gws.grottworkshop.gwstextkerningapl;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ScaleXSpan;

/**
 * KerningUtil,  applyKerning is a helper method to adjust  text spacing, kerning, when you are
 * not able to use the KerningTextView in this library. For example, if you modified the TextView
 * for something else for example.
 *
 *
 * @author Fred Grott
 *
 */
public class KerningUtil {

	public static Spannable applyKerning(CharSequence src, float kerning, int start, int end)
	{
	    if (src == null) return null;
	    final int srcLength = src.length();
	    if (srcLength < 2) return src instanceof Spannable
	                              ? (Spannable)src
	                              : new SpannableString(src);
	    if (start < 0) start = 0;
	    if (end > srcLength) end = srcLength;

	    final String nonBreakingSpace = "\u00A0";
	    final SpannableStringBuilder builder = src instanceof SpannableStringBuilder
	                                           ? (SpannableStringBuilder)src
	                                           : new SpannableStringBuilder(src);
	    for (int i = src.length(); i >= 1; i--)
	    {
	        builder.insert(i, nonBreakingSpace);
	        builder.setSpan(new ScaleXSpan(kerning), i, i + 1,
	                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	    }

	    return builder;
	}
}
