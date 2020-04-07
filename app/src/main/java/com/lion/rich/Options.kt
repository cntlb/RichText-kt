package com.lion.rich
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.style.*
import androidx.annotation.IntDef

class Options(text: SpannableString) : BaseOption(text) {
    val BOLD = Typeface.BOLD
    val ITALIC = Typeface.ITALIC
    val BOLD_ITALIC = Typeface.BOLD_ITALIC

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

    var textStyle:Int?=null
        set(v) {
            field = v?.apply {
                +StyleSpan(this)
            }
        }
}