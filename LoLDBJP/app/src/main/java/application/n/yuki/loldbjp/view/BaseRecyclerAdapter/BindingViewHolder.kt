package application.n.yuki.loldbjp.view.BaseRecyclerAdapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.View
import application.n.yuki.loldbjp.view.BaseRecyclerAdapter.ViewHolder

open class BindingViewHolder<T : ViewDataBinding>(itemView: View) : ViewHolder(itemView){
    val viewDataBinding: T

    init {
        viewDataBinding = DataBindingUtil.bind<T>(itemView)
    }
}