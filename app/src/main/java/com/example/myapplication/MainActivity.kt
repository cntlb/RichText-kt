package com.example.myapplication

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

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
            }
            +"ccc"
        }
        textView.movementMethod = LinkMovementMethod.getInstance()
    }
}

class RichTextBuilder {
    val text = SpannableStringBuilder()
    operator fun CharSequence.invoke() = append(this)
    operator fun CharSequence.unaryPlus() = append(this)

    operator fun CharSequence.invoke(block: Options.() -> Unit) {
        append(this, Options(this).apply(block))
    }

    private fun append(cs: CharSequence, opt: Options? = null) {
        val start = text.length
        text.append(cs)
        opt ?: return
        opt.size?.run {
            text.setSpan(AbsoluteSizeSpan(this), start)
        }
        opt.color?.run {
            text.setSpan(ForegroundColorSpan(this), start)
        }
        opt.backgroundColor?.run {
            text.setSpan(BackgroundColorSpan(this), start)
        }
        opt.img?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            text.setSpan(ImageSpan(this), start)
        }
        opt.onClick?.run {
            text.setSpan(object : ClickableSpan() {
                override fun onClick(widget: View) = this@run()
            }, start)
        }
    }

    private fun Spannable.setSpan(what: Any, start: Int) {
        setSpan(what, start, length, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)
    }

}

class Options(var text: CharSequence = "") {
    var size: Int? = null
    var color: Int? = null
    var backgroundColor: Int? = null
    var img: Drawable? = null
    var onClick: (() -> Unit)? = null

    fun onClick(block: () -> Unit) {
        onClick = block
    }
}

fun rich(block: RichTextBuilder.() -> Unit): CharSequence {
    return RichTextBuilder().apply(block).text
}
