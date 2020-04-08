package com.lion.rich

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class RichTextBuilder(private val textView: TextView) {
    companion object {
        private const val IMG = "[img]"
        private const val LEN = IMG.length
    }

    val context: Context = textView.context
    private val imgSpans = hashMapOf<String, SpanInfo>()

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

    fun CharSequence.span(span: Any) {
        val start = text.length
        +this
        text.setSpan(
            span,
            start,
            start + length,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }

    fun newline(num: Int = 1) = repeat(num) { +"\n" }

    fun img(url: String, block: (ImgOption.() -> Unit)? = null) = img {
        createImageSpanByUrl(url, block)
    }

    fun img(bitmap: Bitmap) = img {
        ImageSpan(context, bitmap)
    }

    fun img(drawable: Drawable) = img {
        check(drawable)
        ImageSpan(drawable)
    }

    fun img(resId: Int) = img {
        val drawable = context.getDrawable(resId)!!
        check(drawable)
        ImageSpan(drawable)
    }

    private fun img(creator: suspend () -> ImageSpan) {
        val start = text.length
        +IMG
        val si = SpanInfo(start, start + LEN)
        GlobalScope.launch(Dispatchers.Main) {
            text.setSpan(
                creator(),
                si.start,
                si.end,
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            textView.text = text
        }
    }

    private suspend fun createImageSpanByUrl(
        url: String,
        block: (ImgOption.() -> Unit)? = null
    ): ImageSpan {
        val bitmap = withContext(Dispatchers.IO) {
            val b = BitmapFactory.decodeStream(URL(url).openStream())
            block ?: return@withContext b
            return@withContext ImgOption().apply(block).run {
                if (width <= 0 && height <= 0) return@run b
                if (keepRatio) {
                    if (width > 0) {
                        height = width * b.height / b.width
                    } else {
                        width = height * b.width / b.height
                    }
                }
                return@run Bitmap.createScaledBitmap(b, width, height, false)
            }
        }
        return ImageSpan(context, bitmap)
    }

    private fun check(drawable: Drawable) {
        val b = drawable.bounds
        if (b.bottom <= b.top || b.right <= b.left)
            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    }

    private fun append(cs: CharSequence, opt: Options? = null) {
        text.append(cs)
    }
}



