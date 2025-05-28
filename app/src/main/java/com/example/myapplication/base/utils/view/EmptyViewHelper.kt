package com.example.myapplication.base.utils.view
import com.example.myapplication.base.utils.view.Type.Companion.NO
import com.example.myapplication.base.utils.view.Type.Companion.PAGE_STATUS_DATA
import com.example.myapplication.base.utils.view.Type.Companion.PAGE_STATUS_INFO
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.IntDef
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.base.component.BaseApplication

@IntDef(
    NO, PAGE_STATUS_INFO, PAGE_STATUS_DATA
)
@Retention(AnnotationRetention.SOURCE)
annotation class Type {
    companion object {
        const val NO = 0x0
        const val PAGE_STATUS_INFO = 0x1
        const val PAGE_STATUS_DATA = 0x2
    }
}

/**
 * 使用：adapter.setEmptyView(EmptyViewHelper.getView((ViewGroup) recyclerView.getParent(), EmptyViewHelper.Type.PAGE_STATUS_DATA));
 * adapter.setUseEmpty(boolean)
 */
object EmptyViewHelper {

    // 获取一个默认显示的空布局
    fun getView(root: ViewGroup, onClickListener: View.OnClickListener?, @Type type: Int): View {
        return getView(root, onClickListener, type, 0)
    }

    fun getView(
        root: ViewGroup,
        onClickListener: View.OnClickListener?,
        @Type type: Int,
        imageRes: Int
    ): View {
        return getView(root, onClickListener, type, imageRes, null)
    }

    fun getView(
        root: ViewGroup,
        onClickListener: View.OnClickListener?,
        @Type type: Int,
        text: String?
    ): View {
        return getView(root, onClickListener, type, 0, text)
    }

    fun getView(
        root: ViewGroup, onClickListener: View.OnClickListener?, @Type type: Int,
        imageRes: Int, text: String?
    ): View {
        val emptyView =
            LayoutInflater.from(root.context).inflate(R.layout.include_empty_view, root, false)
        emptyView.setOnClickListener(onClickListener)

        val imgEmpty = emptyView.findViewById<AppCompatImageView>(R.id.include_empty_view_img)
        val txvTitle = emptyView.findViewById<AppCompatTextView>(R.id.include_empty_view_title)

        if (imageRes != 0) {
            imgEmpty.setImageResource(imageRes)
            txvTitle.visibility = View.GONE
        } else {
            when (type) {
                PAGE_STATUS_INFO -> {
                    imgEmpty.setImageResource(R.mipmap.ic_page_status_error)
                    txvTitle.text = root.context.getString(R.string.no_message)
                }

                PAGE_STATUS_DATA -> {
                    imgEmpty.setImageDrawable(
                        ContextCompat.getDrawable(
                            BaseApplication.getContext(),
                            R.mipmap.ic_page_status_data
                        )
                    )
                    txvTitle.text = text ?: root.context.getString(R.string.no_data)
                    val layoutParams = txvTitle.layoutParams as LinearLayout.LayoutParams
                    layoutParams.setMargins(50, 0, 50, 0)
                    txvTitle.layoutParams = layoutParams
                }

                else -> {}
            }
        }
        return emptyView
    }

    // 获取一个默认显示的空布局
    fun getView(root: ViewGroup, @Type type: Int): View {
        return getView(root, null, type)
    }

    // 获取一个默认显示的空布局
    fun getView(recyclerView: RecyclerView, imageRes: Int): View {
        return getView(recyclerView.parent as ViewGroup, null, NO, imageRes)
    }
}
