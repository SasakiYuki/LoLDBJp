package application.n.yuki.loldbjp.view.base.recycler

import android.support.v7.widget.RecyclerView
import android.view.View

open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    interface OnItemClickListener {
        fun onItemClick(viewHolder: BaseViewHolder, position: Int, id: Long)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(viewHolder: BaseViewHolder, position: Int, id: Long): Boolean
    }

    protected var mOnItemClickListener: OnItemClickListener? = null
    private var mOnItemLongClickListener: OnItemLongClickListener? = null


    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        mOnItemClickListener = onItemClickListener
    }

    fun setOnItemLongClickListener(onItemLongClickListener: OnItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener
    }

    init {
        itemView.setOnClickListener { v ->
            if (mOnItemClickListener == null) {
                return@setOnClickListener
            }
            mOnItemClickListener!!.onItemClick(this, adapterPosition, itemId)
        }
        itemView.setOnLongClickListener { v ->
            if (mOnItemLongClickListener == null) {
                return@setOnLongClickListener false
            }
            mOnItemLongClickListener!!.onItemLongClick(this, adapterPosition, itemId)
        }
    }
}