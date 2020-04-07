package com.lion.rich

fun rich(block: RichTextBuilder.() -> Unit): CharSequence {
    return RichTextBuilder().apply(block).text
}
