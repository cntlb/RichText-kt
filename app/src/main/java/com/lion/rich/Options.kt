package com.lion.rich
import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan

class Options(text: SpannableString) : BaseOption(text) {
    constructor(source: CharSequence) : this(SpannableString(source))

    var size: Int? = null
        set(v) {
            field = v ?: return
            +AbsoluteSizeSpan(v)
        }

    var color: Int? = null
        set(v) {
            field = v ?: return
            +ForegroundColorSpan(v)
        }

    var backgroundColor: Int? = null
        set(v) {
            field = v ?: return
            +BackgroundColorSpan(v)
        }
    var img: Drawable? = null
        set(v) {
            field = v?.apply {
                if (bounds.isEmpty)
                    setBounds(0, 0, intrinsicHeight, intrinsicHeight)
                +ImageSpan(this)
            }
        }
}