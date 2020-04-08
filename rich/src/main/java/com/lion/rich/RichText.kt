package com.lion.rich

import android.content.Context
import android.widget.TextView

fun TextView.rich(block: RichTextBuilder.() -> Unit): CharSequence {
    return RichTextBuilder(this).apply(block).text
}
