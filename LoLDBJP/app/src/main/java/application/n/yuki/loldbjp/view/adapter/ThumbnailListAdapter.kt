package application.n.yuki.loldbjp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import application.n.yuki.loldbjp.R
import application.n.yuki.loldbjp.databinding.ListThumbnailBinding
import application.n.yuki.loldbjp.view.base.recycler.BaseRecyclerAdapter
import application.n.yuki.loldbjp.view.base.recycler.BindingViewHolder
import application.n.yuki.loldbjp.view.base.recycler.ViewHolder
import application.n.yuki.loldbjp.viewmodel.list.ThumbnailViewModel
import java.util.*

/**
 * Created by yuki.n on 2017/04/16.
 */
open class ThumbnailListAdapter(val context: Context) : BaseRecyclerAdapter<ViewHolder>() {
    private val arrayList: ArrayList<ThumbnailEntity> = ArrayList()

    override val sectionCount: Int
        get() = if(arrayList.size > 0)  1 else 0

    override fun getRowCount(section: Int) = arrayList.size

    override fun onBindViewHolder(holder: ViewHolder, indexPath: IndexPath?) {
        @ViewType val viewType = getItemViewType(indexPath!!)
        onBindRowViewHolder(holder,indexPath)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        return onCreateRowViewHolder(parent)
    }

    fun addThubmnailEntity(entity: ThumbnailEntity) {
        arrayList.add(entity)
        resetDataSource()
        notifyDataSetChanged()
    }

    fun clearEntities(){
        arrayList.clear()
        resetDataSource()
        notifyDataSetChanged()
    }

    fun addThumbnailEntity(list: ArrayList<ThumbnailEntity>) {
        arrayList.addAll(list)
        resetDataSource()
        notifyDataSetChanged()
    }

    private fun onBindRowViewHolder(holder: ViewHolder, indexPath: IndexPath?) {
        val binding = (holder as RowViewHolder).viewDataBinding
        val entity = getItemAt(indexPath)
        val viewModel = ThumbnailViewModel(entity.name,entity.imageUrl)
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }

    private fun getItemAt(position: IndexPath?) = arrayList[position!!.row]



    private fun onCreateRowViewHolder(parent: ViewGroup) : RowViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_thumbnail,parent,false)
        return RowViewHolder(view)
    }

    internal class RowViewHolder(itemView: View) : BindingViewHolder<ListThumbnailBinding>(itemView)

}

data class ThumbnailEntity(
        val imageUrl:String,
        val name:String
)