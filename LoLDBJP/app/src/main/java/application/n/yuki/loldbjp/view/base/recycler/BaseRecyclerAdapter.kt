package application.n.yuki.loldbjp.view.base.recycler

import android.support.annotation.IntDef
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import application.n.yuki.loldbjp.view.base.recycler.BaseViewHolder
import java.util.*

abstract class BaseRecyclerAdapter<T : BaseViewHolder> : RecyclerView.Adapter<T>() {
    /**
     * 区切りの定義。
     * Enumより固くなく、受け取り側でもタイプを指定しなければintを渡すだけになる。
     */
    @IntDef(VIEW_TYPE_SECTION_HEADER.toLong(), VIEW_TYPE_ROW.toLong(), VIEW_TYPE_FOOTER.toLong(), VIEW_TYPE_HEADER.toLong(), VIEW_TYPE_SECTION_FOOTER.toLong(), VIEW_TYPE_SKIP.toLong())
    annotation class ViewType

    private var mIndexPaths: MutableList<IndexPath>? = null
    private var mViewTypes: MutableList<Int>? = null
    var isShowHeader: Boolean = false
        set(showHeader) {
            field = showHeader
            resetDataSource()
            notifyDataSetChanged()
        }
    var isShowSectionHeader: Boolean = false
        set(showSectionHeader) {
            field = showSectionHeader
            resetDataSource()
            notifyDataSetChanged()
        }
    var isShowSectionFooter: Boolean = false
        set(showSectionFooter) {
            field = showSectionFooter
            resetDataSource()
            notifyDataSetChanged()
        }
    var isShowFooter: Boolean = false
        set(showFooter) {
            field = showFooter
            resetDataSource()
            notifyDataSetChanged()
        }

    fun resetDataSource() {
        mIndexPaths = ArrayList<IndexPath>()
        mViewTypes = ArrayList<Int>()
        if (isShowHeader) {
            mViewTypes!!.add(VIEW_TYPE_HEADER)
        }
        val sections = sectionCount
        for (i in 0..sections - 1) {
            if (isShowSectionHeader) {
                mViewTypes!!.add(VIEW_TYPE_SECTION_HEADER)
                mIndexPaths!!.add(SectionHeaderIndexPath(i))
            }
            val rowCount = getRowCount(i)
            for (j in 0..rowCount - 1) {
                val indexPath = IndexPath(i, j)
                mIndexPaths!!.add(indexPath)
                mViewTypes!!.add(VIEW_TYPE_ROW)
            }
            if (isShowSectionFooter) {
                mViewTypes!!.add(VIEW_TYPE_SECTION_FOOTER)
                mIndexPaths!!.add(SectionFooterIndexPath(i))
            }
        }
        if (isShowFooter) {
            mViewTypes!!.add(VIEW_TYPE_FOOTER)
        }
    }

    fun positionToIndexPath(position: Int): IndexPath? {
        if (mIndexPaths == null) {
            return null
        }
        val headerCount = if (isShowHeader) 1 else 0
        val fixedPosition = position - headerCount
        try {
            return mIndexPaths!![fixedPosition]
        } catch (e: ArrayIndexOutOfBoundsException) {
            return null
        } catch (e: IndexOutOfBoundsException) {
            return null
        }

    }

    fun indexPathToPosition(indexPath: IndexPath): Int {
        val headerCount = if (isShowHeader) 1 else 0
        val position = mIndexPaths!!.indexOf(indexPath)
        return headerCount + position
    }

    @ViewType
    fun getItemViewType(indexPath: IndexPath): Int {
        return getItemViewType(indexPathToPosition(indexPath))
    }

    /**
     * RecyclerView の中で表示するセクションの数を返してください。
     */
    abstract val sectionCount: Int

    /**
     * パラメータに指定されたセクションにおける行の数を返してください。
     * @param section 0から始まるセクション
     * *
     */
    abstract fun getRowCount(section: Int): Int

    /**
     * パラメータに指定された IndexPath におけるViewHolderがバインドされたときに呼ばれます。
     * ヘッダーやフッターがバインドされたときには呼ばれません。
     * @param indexPath バインドされた ViewHolder のインデックスパス
     * *
     */
    abstract fun onBindViewHolder(holder: T, indexPath: IndexPath?)

    val isEmpty: Boolean
        get() = sectionCount == 0

    override fun getItemCount(): Int {
        if (mViewTypes == null) {
            return 0
        }
        return mViewTypes!!.size
    }

    @ViewType
    override fun getItemViewType(position: Int): Int {
        val viewType = mViewTypes!![position]
        if (viewType == VIEW_TYPE_HEADER) {
            return VIEW_TYPE_HEADER
        }
        if (viewType == VIEW_TYPE_SECTION_HEADER) {
            return VIEW_TYPE_SECTION_HEADER
        }
        if (viewType == VIEW_TYPE_ROW) {
            return VIEW_TYPE_ROW
        }
        if (viewType == VIEW_TYPE_SECTION_FOOTER) {
            return VIEW_TYPE_SECTION_FOOTER
        }
        if (viewType == VIEW_TYPE_FOOTER) {
            return VIEW_TYPE_FOOTER
        }
        throw IllegalStateException("ViewType of " + viewType + " is in mViewType at " + position + "\n" +
                "This is invalid ViewType. Please use @ViewType."
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, @ViewType viewType: Int): T? {
        return null
    }

    /**
     * Overrideして通す要素を選択すること。
     */
    override fun onBindViewHolder(holder: T, position: Int) {
        val viewType = getItemViewType(position)
        if (viewType == VIEW_TYPE_SECTION_HEADER || viewType == VIEW_TYPE_ROW || viewType == VIEW_TYPE_SECTION_FOOTER) {
            onBindViewHolder(holder, positionToIndexPath(position))
        }
    }

    /**
     * 0から始まるセクションと行の番号を管理するクラスです。
     */
    open class IndexPath(val section: Int, val row: Int) {

        override fun equals(o: Any?): Boolean {
            if (this === o) return true
            if (o == null || javaClass != o.javaClass) return false

            val indexPath = o as IndexPath?

            if (section != indexPath!!.section) return false
            return row == indexPath.row

        }

        override fun hashCode(): Int {
            var result = section
            result = 31 * result + row
            return result
        }
    }

    class SectionHeaderIndexPath(section: Int) : IndexPath(section, ROW_FOR_SECTION_HEADER)

    class SectionFooterIndexPath(section: Int) : IndexPath(section, ROW_FOR_SECTION_FOOTER)

    companion object {
        const val VIEW_TYPE_SECTION_HEADER = 0
        const val VIEW_TYPE_ROW = 1
        const val VIEW_TYPE_FOOTER = 2
        const val VIEW_TYPE_HEADER = 3
        const val VIEW_TYPE_SECTION_FOOTER = 4
        const val VIEW_TYPE_SKIP = 5

        private val ROW_FOR_SECTION_HEADER = -1
        private val ROW_FOR_SECTION_FOOTER = -2
    }
}