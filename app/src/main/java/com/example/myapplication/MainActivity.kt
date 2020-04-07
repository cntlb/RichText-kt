package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.lion.rich.rich
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.dip

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.text = textView.rich {
            "中国战“疫”制胜之道"{
                size = dip(40)
                color = Color.BLACK
            }
            newline()

            "[文章来源]"("https://xhpfmapi.zhongguowangshi.com/vh512/share/9015373")
            newline()

            +"""

2020-04-07 12:04:42         浏览量：1371030

来源：新华社
"""

            img("https://img-xhpfm.zhongguowangshi.com/Column/201903/de2e4d4f39c74e55a1a1716ae1e47207.jpg@120w_120h_4e_1c_80Q_2o_1x_237-237-237bgc.png")

            +"""

新冠病毒肆虐全球，中国最先遭受冲击。

在习近平总书记亲自指挥部署下，中国打响了疫情防控的人民战争。这次战“疫”事关亿万人民的生命安全和身体健康，也是人人有责、人人参与的全民行动。

中国战“疫”究竟如何开展？人民群众做出了什么贡献？疫情在全球多点暴发时，中国又是如何与国际社会一起携手遏制病毒蔓延的？

在武汉市即将解除离汉离鄂通道管控措施之际，新华社推出中英双语视频，讲述中国战“疫”制胜的人民力量。
"""

            img("https://img-xhpfm.zhongguowangshi.com/News/202004/XxjfyxC007001_20200407_CBMFN0A003.jpg@1000w_1e_1c_80Q_1x.jpg)](https://img-xhpfm.zhongguowangshi.com/News/202004/XxjfyxC007001_20200407_CBMFN0A003.jpg")
            {
                width = textView.width
                keepRatio = true
            }

            +"""长达16分40秒的视频展现了中国抗“疫”的宏大画卷，捕捉和纪录了社会各界人士在此次战“疫”中的身影。"""

            img("https://img-xhpfm.zhongguowangshi.com/News/202004/XxjfyxC007001_20200407_CBMFN0A007.jpg@1000w_1e_1c_80Q_1x.jpg)](https://img-xhpfm.zhongguowangshi.com/News/202004/XxjfyxC007001_20200407_CBMFN0A007.jpg")
            {
                width = textView.width
                keepRatio = true
            }
            +"""世卫组织赴中国考察专家组负责人布鲁斯·艾尔沃德说：“在中国，我们还听到人们反复说一句了不起的话，就是抗‘疫’不仅仅是为了自己，更是为了全世界。”"""
            img("https://img-xhpfm.zhongguowangshi.com/News/202004/XxjfyxC007001_20200407_CBMFN0A002.jpg@1000w_1e_1c_80Q_1x.jpg)](https://img-xhpfm.zhongguowangshi.com/News/202004/XxjfyxC007001_20200407_CBMFN0A002.jpg")
            {
                width = textView.width
                keepRatio = true
            }

            "\n\n多国权威专家发表了对中国抗“疫”成果的真实感受。"{
                textStyle = BOLD
            }

            +"""
英国剑桥大学高级研究员马丁·雅克：“中国政府应对疫情非常高效。中国政府能从大局和战略上着想，有能力调动人力和资源，有很强统领能力。我认为中国的治理体系理当是世界上最高效的。”
"""

            img("https://img-xhpfm.zhongguowangshi.com/News/202004/XxjfyxC007001_20200407_CBMFN0A004.jpg@1000w_1e_1c_80Q_1x.jpg)](https://img-xhpfm.zhongguowangshi.com/News/202004/XxjfyxC007001_20200407_CBMFN0A004.jpg")
            {
                width = textView.width
                keepRatio = true
            }

            +"在做好国内疫情防控的基础上，中国政府向世界各国伸出援手，出人出力。"

            img("https://img-xhpfm.zhongguowangshi.com/News/202004/XxjfyxC007001_20200407_CBMFN0A005.jpg@1000w_1e_1c_80Q_1x.jpg)](https://img-xhpfm.zhongguowangshi.com/News/202004/XxjfyxC007001_20200407_CBMFN0A005.jpg")
            {
                width = textView.width
                keepRatio = true
            }

            +"除了政府间援助，中国地方政府、企业、民间机构等还多次向相关国家提供援助。多层次、多渠道援助体现了中国勠力同心、共克时艰的大爱精神，彰显了人类命运共同体的理念和担当，赢得国际社会高度赞誉。"

            img("https://img-xhpfm.zhongguowangshi.com/News/202004/XxjfyxC007001_20200407_CBMFN0A006.jpg@1000w_1e_1c_80Q_1x.jpg)](https://img-xhpfm.zhongguowangshi.com/News/202004/XxjfyxC007001_20200407_CBMFN0A006.jpg")
            {
                width = textView.width
                keepRatio = true
            }

            +"""
策   划：倪四义

出品人：孙志平

制片人：米立公

统筹：石鹏 李琴

记者：王晖 余国庆 王斯班 杨志刚 许杨 董博涵 马原驰 郝晓江 骆慧 张愉承 蒋超 塔里克 阿里 林昊 赵宇超 田明 郭威 吉莉 杜洋 梁希之 白叶 石中玉 陈霖 韩冲 潘革平 王平平 沈忠浩 于帅帅 唐霁 王菲 晁瑾 李光正 邱冰清

编辑：高尚 刘若诗 马汝轩

配音：科林

动画：高尚 刘宇轩

音乐：尚·马龙《黎明的编钟声》

关正跃演奏《天之大》

新华社音视频部制作

新华通讯社出品
                


            """.trimIndent()
        }
        textView.movementMethod = LinkMovementMethod.getInstance()
    }
}

