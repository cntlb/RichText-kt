package com.example.myapplication


import android.app.Activity
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.attr
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {
    private val array = arrayOf<Pair<String, KClass<out Activity>>>(
        "中国战“疫”制胜之道" to NewsActivity::class,
        "文档结构示例" to DocumentStructureActivity::class
    )

    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        val content = RecyclerView(this)
        setContentView(content)

        content.layoutManager = LinearLayoutManager(this)
        content.adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): RecyclerView.ViewHolder {
                val view =
                    layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false)
                view.background = getDrawable(attr(android.R.attr.selectableItemBackground).resourceId)
                return object : RecyclerView.ViewHolder(view) {}
            }

            override fun getItemCount(): Int = array.size

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                val (title, clz) = array[position]
                holder.run {
                    itemView.onClick {
                        startActivity(Intent(this@MainActivity, clz.java))
                    }
                    itemView.find<TextView>(android.R.id.text1).text = title
                }
            }
        }
    }
}

