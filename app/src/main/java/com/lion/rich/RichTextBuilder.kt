package com.lion.rich

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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

    fun newline() = +"\n"

    fun img(url: String, block: (ImgOption.() -> Unit)? = null) {
        val si = SpanInfo(text.length, text.length + LEN)
        +IMG
        GlobalScope.launch(Dispatchers.Main) {
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

            text.setSpan(
                ImageSpan(context, bitmap),
                si.start,
                si.end,
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            textView.text = text
        }
    }


    private fun append(cs: CharSequence, opt: Options? = null) {
        text.append(cs)
    }
}



