package com.example.myapplication.module.expandText

import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import com.example.myapplication.base.component.BaseActivity
import com.example.myapplication.databinding.ActivityExpandTextBinding
import com.example.myapplication.module.expandText.simpleExpandText.Callback
import com.scwang.smart.refresh.layout.util.SmartUtil


/**
 * 参考：https://github.com/MZCretin/ExpandableTextView/tree/master
 */
class ExpandTextActivity : BaseActivity() {

    private lateinit var binding: ActivityExpandTextBinding

    private var yourText = "我所认识的中国，http://www.baidu.com 强大、友好 --习大大。@奥特曼 “一带一路”经济带带动了沿线国家的经济发展，促进我国与他国的友好往来和贸易发展，可谓“双赢”，Github地址。 自古以来，中国以和平、友好的面孔示人。汉武帝派张骞出使西域，开辟丝绸之路，增进与西域各国的友好往来。http://www.baidu.com 胡麻、胡豆、香料等食材也随之传入中国，汇集于中华美食。@RNG 漠漠古道，驼铃阵阵，这条路奠定了“一带一路”的基础，让世界认识了中国。"
    override fun initData(savedInstanceState: Bundle?) {

        // 正常不带链接，不带@用户，有展开和收回功能，有切换动画
        binding.etvExpand.setContent(yourText)

        // 简单的展开折叠
        //是否支持收起功能
        binding.textViewShowMore.collapseEnable = true
        //是否给展开收起添加下划线
        binding.textViewShowMore.underlineEnable = false
        binding.textViewShowMore.marginStartPX = SmartUtil.dp2px(24f)
        //收起文案颜色
        binding.textViewShowMore.collapseTextColor = Color.parseColor("#FF0000")
        //展开文案颜色
        binding.textViewShowMore.expandTextColor = Color.parseColor("#FF0000")

        var change = false
        binding.textViewShowMore.setText(yourText, change, object : Callback {
            override fun onExpand() {
            }

            override fun onCollapse() {
            }

            override fun onLoss() {
            }

            override fun onExpandClick() {
                change = !change
                binding.textViewShowMore.setChanged(change)
            }

            override fun onCollapseClick() {
                change = !change
                binding.textViewShowMore.setChanged(change)
            }

        })


    }

    override fun bindView(): View? {
        binding = ActivityExpandTextBinding.inflate(layoutInflater)
        return binding.root
    }
}