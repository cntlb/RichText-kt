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

    }
}

