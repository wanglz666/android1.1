package com.example.myapplication.module.textTest

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.myapplication.base.component.BaseActivity
import com.example.myapplication.databinding.ActivityFollowTagBinding

/**
 * 参考https://juejin.cn/post/6891284263174406157?searchId=2023112710130194F12865E1177E99712D
 */
class FollowTagActivity : BaseActivity() {
    private lateinit var binding: ActivityFollowTagBinding
    var tagSrc = "文字是人类用表意符号记录表达信息以传之久远的方式和工具。现代文字大多是记录语言的工具。人类往往先有口头的语言后产生书面文字，很多小语种，有语言但没有文字。文字的不同体现了国家和民族的书面表达的方式和思维不同。文字使人类进入有历史记录的文明社会。"

    override fun initData(savedInstanceState: Bundle?) {
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

    /**
     * 获取textview某行line内容
     */
    fun getTextLineContent(textView: TextView?, line: Int, completeStr: String?): String {
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