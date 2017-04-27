package application.n.yuki.loldbjp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import application.n.yuki.loldbjp.R
import application.n.yuki.loldbjp.databinding.ListRoleRankingBinding
import application.n.yuki.loldbjp.view.base.recycler.BaseRecyclerAdapter
import application.n.yuki.loldbjp.view.base.recycler.BindingViewHolder
import application.n.yuki.loldbjp.view.base.recycler.ViewHolder
import application.n.yuki.loldbjp.viewmodel.list.RoleRankingViewModel
import java.util.*

/**
 * Created by yuki.n on 2017/04/24.
 */
class RoleRankingAdapter(val context: Context) : BaseRecyclerAdapter<ViewHolder>() {
    private val arrayList: ArrayList<RoleRankingViewEntity> = ArrayList()
    override val sectionCount: Int
        get() = if(arrayList.size > 0) 1 else 0

    fun addEntity(entity: RoleRankingViewEntity) {
        arrayList.add(entity)
        resetDataSource()
        notifyDataSetChanged()
    }

    fun clearList() {
        arrayList.clear()
        resetDataSource()
        notifyDataSetChanged()
    }

    private fun getItemAt(position:IndexPath) = arrayList[position.row]

    override fun getRowCount(section: Int) = arrayList.size

    override fun onBindViewHolder(holder: ViewHolder, indexPath: IndexPath?) {
        @ViewType val viewType = getItemViewType(indexPath!!)
        onBindRow(holder,indexPath)
    }

    private fun onBindRow(holder: ViewHolder, indexPath: IndexPath) {
        val binding = (holder as RowViewHolder).viewDataBinding
        val entity = getItemAt(indexPath)
        val viewModel = RoleRankingViewModel(entity.ranking,entity.imageUrl,entity.name,entity.key,entity.mainPercent,entity.subPercent,entity.subPlaceholder)
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        return onCreateRowViewHolder(parent)
    }

    private fun onCreateRowViewHolder(parent: ViewGroup): RowViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_role_ranking, parent, false)
        return RowViewHolder(view)
    }

    internal class RowViewHolder(itemView: View) : BindingViewHolder<ListRoleRankingBinding>(itemView)
}

data class RoleRankingViewEntity(
        val ranking:Int,
        val imageUrl:String,
        val name:String,
        val key:String,
        val mainPercent:Float,
        val subPercent:Float,
        val subPlaceholder:String
)
