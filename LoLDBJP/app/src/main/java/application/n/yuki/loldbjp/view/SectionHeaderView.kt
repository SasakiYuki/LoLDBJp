package application.n.yuki.loldbjp.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import application.n.yuki.loldbjp.R

/**
 * Created by yuki.n on 2017/04/18.
 */
class SectionHeaderView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
    private var mTitleView: TextView? = null

    init {
        val inflater = getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_section_header, this)

        val a = context.obtainStyledAttributes(attrs, R.styleable.SectionHeaderView)
        try {
            mTitleView = findViewById(R.id.title) as TextView
            setTitle(a.getString(R.styleable.SectionHeaderView_sectionTitle))
        } finally {
            a.recycle()
        }
    }

    fun setTitle(resId: Int) {
        mTitleView!!.setText(resId)
    }

    fun setTitle(title: String) {
        mTitleView!!.text = title
    }

    val title: CharSequence
        get() = mTitleView!!.text
}
