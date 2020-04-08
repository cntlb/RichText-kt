package com.lion.rich

class UrlOptions(source: CharSequence, val url: String) : BaseOption(source) {
    var linkColor: Int? = null
    var underline: Boolean? = null

    fun build(): CharSequence {
        +RichUrlSpan(this)
        return text
    }
}