package com.example.myapplication

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.sdk27.coroutines.onClick

abstract class ContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UI(true) {
            scrollView {
                textView {
                    text = content(this)
                    movementMethod = LinkMovementMethod.getInstance()
                }.lparams(width = matchParent, height = wrapContent)
            }
        }
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    abstract fun content(textView: TextView): CharSequence
}
