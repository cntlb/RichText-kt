package com.lion.rich

import android.text.SpannableStringBuilder

class RichTextBuilder {
    val text = SpannableStringBuilder()
    operator fun CharSequence.invoke() = append(this)
    operator fun CharSequence.unaryPlus() = append(this)

    operator fun CharSequence.invoke(block: Options.() -> Unit) {
        append(Options(this).apply(block).text)
    }

    operator fun CharSequence.invoke(url: String, block: (UrlOptions.() -> Unit)? = null) {
        append(UrlOptions(this, url).apply {
            block?.invoke(this)
        }.build())
    }

    fun newline() = +"\n"

    private fun append(cs: CharSequence, opt: Options? = null) {
        text.append(cs)
    }
}



