package com.example.myapplication.module.textTest

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.RelativeLayout.ALIGN_TOP
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import com.example.myapplication.R
import com.example.myapplication.base.component.BaseActivity
import com.example.myapplication.databinding.ActivityFollowTagBinding
import com.scwang.smart.refresh.layout.util.SmartUtil


/**
 * 参考
 * https://juejin.cn/post/6891284263174406157?searchId=2023112710130194F12865E1177E99712D
 * https://tristanzeng.github.io/2019/05/29/TextView%E5%A4%9A%E8%A1%8C%E6%96%87%E5%AD%97%E8%B6%85%E5%87%BA%E6%97%B6%E5%A6%82%E4%BD%95%E5%9C%A8%E7%9C%81%E7%95%A5%E5%8F%B7%E5%90%8E%E6%B7%BB%E5%8A%A0%E5%9B%BE%E6%A0%87/?
 */
class FollowTagActivity : BaseActivity() {
    private lateinit var binding: ActivityFollowTagBinding
    private var tagSrc = "文字是人类用表意符号记录表达信息以传之久远的方式和工具。现代文字大多是记录语言的工具。人类往往先有口头的语言后产生书面文字，很多小语种，有语言但没有文字。文字的不同体现了国家和民族的书面表达的方式和思维不同。文字使人类进入有历史记录的文明社会。"

    override fun initData(savedInstanceState: Bundle?) {
        // 简单实现单行、两行
        followTagForDouble()
        // n行
        followTagForN()
    }

    private fun followTagForDouble() {
        binding.tvTagOne.text = tagSrc
        binding.tvTagTwo.text = tagSrc
        //判断第一行是否可以完整显示
        binding.tvTagTwo.post {
            //获取第一个 textview 显示的内容
            val lineContent = getTextLineContent(binding.tvTagOne, 0, tagSrc)
            Log.d(TAG, "lineContent = $lineContent")
            //第一行就可以显示完整
            if (TextUtils.equals(tagSrc, lineContent)) {
                binding.tvTagOne.visibility = View.GONE
                binding.tvTagTwo.text = tagSrc
            } else {
                // 需要分行
                binding.tvTagTwo.visibility = View.VISIBLE
                val srcTwoContent = tagSrc.substring(lineContent.length, tagSrc.length)
                binding.tvTagTwo.text = srcTwoContent
            }
        }
    }

    private fun followTagForN() {
        binding.tvContent.post { // 计算文字超出部分做精确截取
            var text = "$tagSrc(精)"
            binding.tvContent.text = text
            val ellipsisCount: Int =
                binding.tvContent.layout.getEllipsisCount(binding.tvContent.lineCount - 1)
            if (ellipsisCount > 0) {
                text = text.substring(0, text.length - ellipsisCount - 1) + "…(精)"
            }

            // 创建SpannableString对象
            val imageString = SpannableString(text)

            // 获取图片资源并设置绘制边界
            val image: Drawable? = AppCompatResources.getDrawable(mActivity, R.drawable.arrow_down)
            val left: Int = SmartUtil.dp2px(0F)
            val top: Int = SmartUtil.dp2px(0F)
            val right: Int = SmartUtil.dp2px(16F)
            val bottom: Int = SmartUtil.dp2px(16F)
            image?.setBounds(left, top, right, bottom)

            // 创建图片样式对象替换占位符
            val imageSpan = AlignTopImageSpan(image, ALIGN_TOP)
            imageString.setSpan(
                imageSpan,
                imageString.length - 3,
                imageString.length,
                Spanned.SPAN_INCLUSIVE_INCLUSIVE
            )
            binding.tvContent.text = imageString
        }
    }

    /**
     * 获取textview某行line内容
     */
    private fun getTextLineContent(textView: TextView?, line: Int, completeStr: String?): String {
        val result = ""
        if (textView == null || completeStr.isNullOrEmpty()) {
            return result
        }
        if (line > textView.lineCount) {
            return result
        }
        val layout = textView.layout
        val lineStart = layout.getLineStart(line)
        val lineEnd = layout.getLineEnd(line)

        return completeStr.subSequence(lineStart, lineEnd).toString()
    }

    override fun bindView(): View? {
        binding = ActivityFollowTagBinding.inflate(layoutInflater)
        return binding.root
    }
}