package com.example.myapplication

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.View
import android.view.textclassifier.TextLinks
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.intellij.lang.annotations.Language

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.text = rich {
            +"aaaaa"
            "bbb"{
                size = 80
                color = Color.RED
                backgroundColor = Color.YELLOW
                onClick {
                    Toast.makeText(this@MainActivity, text, Toast.LENGTH_LONG).show()
                }
            }
            "icon"{
                img = getDrawable(R.mipmap.ic_launcher)
                onClick {
                    Toast.makeText(this@MainActivity, text, Toast.LENGTH_LONG).show()
                }
            }
            "ccc"("http://www.baidu.com") {
                linkColor = Color.RED
//                underline = false
            }
        }
        textView.movementMethod = LinkMovementMethod.getInstance()
    }
}

class RichTextBuilder {
    val text = SpannableStringBuilder()
    operator fun CharSequence.invoke() = append(this)
    operator fun CharSequence.unaryPlus() = append(this)

    operator fun CharSequence.invoke(block: Options.() -> Unit) {
        append(Options(this).apply(block).text)
    }

    operator fun CharSequence.invoke(url: String, block: UrlOptions.() -> Unit) {
        append(UrlOptions(this, url).apply(block).build())
    }

    private fun append(cs: CharSequence, opt: Options? = null) {
        text.append(cs)
    }
}

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

class UrlOptions(source: CharSequence, val url: String) : BaseOption(source) {
    var linkColor: Int? = null
    var underline: Boolean? = null

    fun build(): CharSequence {
        +RichUrlSpan(this)
        return text
    }
}

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

fun rich(block: RichTextBuilder.() -> Unit): CharSequence {
    return RichTextBuilder().apply(block).text
}
