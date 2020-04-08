/**
 * @author Linbing Tang
 * @since 20-4-8 13:45
 */
package com.example.myapplication

import android.graphics.Color
import android.widget.TextView
import com.lion.rich.Options
import com.lion.rich.rich
import org.jetbrains.anko.dip

class DocumentStructureActivity : ContentActivity() {
    override fun content(textView: TextView): CharSequence {
        //定义一堆配置
        val part: Options.() -> Unit = {
            size = dip(60)
            color = Color.RED
            textStyle = ITALIC
        }
        val chapter: Options.() -> Unit = {
            size = dip(40)
            textStyle = BOLD
        }
        val section: Options.() -> Unit = {
            size = dip(30)
            textStyle = BOLD
        }
        val codeBlock: Options.() -> Unit = { lineBackgroundColor = Color.GRAY }
        val code: Options.() -> Unit = { backgroundColor = Color.LTGRAY }
        val italic: Options.() -> Unit = { textStyle = ITALIC }
        return textView.rich {
            "第一部分\n"(part)
            "第一章 协程\n"(chapter)
            "1.1 你的第一个协程程序\n"(section)

            +"  运行以下代码:\n"
            """
            import kotlinx.coroutines.*
            fun main() {
                GlobalScope.launch { // 在后台启动一个新的协程并继续
                delay(1000L) // 非阻塞的等待 1 秒钟(默认时间单位是毫秒)
                println("World!") // 在延迟后打印输出
            }
            println("Hello,") // 协程已在等待时主线程还在继续
                Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
            }
            """.trimIndent()(codeBlock)

            +"\n可以在"
            "这里"("https://github.com/hltj/kotlinx.coroutines-cn/blob/master/kotlinx-coroutines-core/jvm/test/guide/example-basic-01.kt")
            +"获取完整代码。\n"

            +"代码运行的结果:\n"
            """
                Hello,
                World!
            """.trimIndent()(codeBlock)
            newline(2)

            +"  本质上,协程是轻量级的线程。 它们在某些"
            "CoroutineScope"("https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/coroutine-scope.html")
            +"上下文中与 "
            "launch"("https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/launch.html")
            +" 协程构建器 一起启动。 这里我们在"
            "GlobalScope"("https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-global-scope/index.html")
            +"中启动了一个新的协程,这意味着新协程的生命周期只受整个应用程序的生命周期限制。"

            +"可以将"
            " GlobalScope.launch { ...... } "(code)
            +"替换为"
            " thread { ...... } "(code)
            +",将"
            " delay(......) "(code)
            +"替换为"
            "Thread.sleep(......) "(code)
            +"达到同样目的。 试试看(不要忘记导入"
            " kotlin.concurrent.thread "(code)
            +")。如果你首先将"
            " GlobalScope.launch "(code)
            +"替换为"
            " thread "(code)
            +",编译器会报以下错误\n"

            ("Error: Kotlin: Suspend functions are only allowed to be " +
                    "called from a coroutine or another suspend function")(codeBlock)
            newline()
            +"这是因为"
            " delay "("https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/delay.html")
            +"是一个特殊的 "
            "挂起函数"(italic)
            +" ,它不会造成线程阻塞,但是会 "
            "挂起"(italic)
            +" 协程,并且只能在协程中使用。\n\n"

            "1.2 桥接阻塞与非阻塞的世界\n"(section)

            +"  第一个示例在同一段代码中混用了 "
            "非阻塞的"(italic)
            " delay(......) "(code)
            +"与 "
            "阻塞的"(italic)
            "Thread.sleep(......) "(code)
            +"。 这容易让我们记混哪个是阻塞的、哪个是非阻塞的。 让我们显式使用 "
            "runBlocking"("https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/run-blocking.html")
            +"协程构建器来阻塞:\n"

            """
            import kotlinx.coroutines.*
            fun main() {
                GlobalScope.launch { // 在后台启动一个新的协程并继续
                    delay(1000L)
                    println("World!")
                }
                println("Hello,") // 主线程中的代码会立即执行
                runBlocking {
                    // 但是这个表达式阻塞了主线程
                    delay(2000L) // ......我们延迟 2 秒来保证 JVM 的存活
                }
            }
            """.trimIndent()(codeBlock)
            newline()
            +"可以在"
            "这里"("https://github.com/hltj/kotlinx.coroutines-cn/blob/master/kotlinx-coroutines-core/jvm/test/guide/example-basic-02.kt")
            +"获取完整代码。\n"

            "   结果是相似的,但是这些代码只使用了非阻塞的函数 "()
            "delay"("https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/delay.html")
            "。 调用了 "()
            "runBlocking"(code)
            "的主线程会一直 "()
            "阻塞"(italic)
            " 直到 "()
            "runBlocking"(code)
            " 内部的协程执行完毕。"()

            newline(2)
            "这个示例可以使用更合乎惯用法的方式重写,使用 "()
            "runBlocking"(code)
            "来包装 main 函数的执行:\n"()

            """
            import kotlinx.coroutines.*
            fun main() = runBlocking<Unit> { // 开始执行主协程
                GlobalScope.launch { // 在后台启动一个新的协程并继续
                    delay(1000L)
                    println("World!")
                }
                println("Hello,") // 主协程在这里会立即执行
                delay(2000L)
                // 延迟 2 秒来保证 JVM 存活
            }
            """.trimIndent()(codeBlock)
            newline()
            +"可以在"
            "这里"("https://github.com/hltj/kotlinx.coroutines-cn/blob/master/kotlinx-coroutines-core/jvm/test/guide/example-basic-03.kt")
            +"获取完整代码。\n"

            +("这里的 runBlocking<Unit> { ...... } 作为用来启动顶层主协程的适配器。 我们显式指定了其返回\n" +
                    "类型 Unit ,因为在 Kotlin 中 main 函数必须返回 Unit 类型。\n" +
                    "这也是为挂起函数编写单元测试的一种方式:\n")

            """
            class MyTest {
                @Test
                fun testMySuspendingFunction() = runBlocking<Unit> {
                // 这里我们可以使用任何喜欢的断言⻛格来使用挂起函数
                }
            }
            """.trimIndent()(codeBlock)
            newline(5)
        }
    }
}
