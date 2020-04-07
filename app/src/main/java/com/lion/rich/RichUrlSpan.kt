package com.lion.rich
import android.text.TextPaint
import android.text.style.URLSpan

class RichUrlSpan(val opt: UrlOptions) : URLSpan(opt.url) {
    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        opt.linkColor?.run {
            ds.color = this
        }
        opt.underline?.run {
            ds.isUnderlineText = this
        }
    }
}
