package com.lion.rich

import android.text.SpannableString
import android.text.style.ClickableSpan
import android.view.View

open class BaseOption(var text: SpannableString) {
    constructor(source: CharSequence) : this(SpannableString(source))

    protected val start = 0
    protected val end = text.length

    protected operator fun Any.unaryPlus() {
        text.setSpan(this, start, end, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)
    }

    var onClick: (() -> Unit)? = null
        set(run) {
            field = run?.apply {
                +object : ClickableSpan() {
                    override fun onClick(widget: View) = run()
                }
            }
        }

    fun onClick(block: () -> Unit) {
        onClick = block
    }
}
