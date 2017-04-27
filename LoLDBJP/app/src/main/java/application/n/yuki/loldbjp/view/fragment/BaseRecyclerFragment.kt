package application.n.yuki.loldbjp.view.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import application.n.yuki.loldbjp.R
import application.n.yuki.loldbjp.Views
import application.n.yuki.loldbjp.view.base.BaseFragment
import application.n.yuki.loldbjp.view.base.recycler.BaseRecyclerAdapter

/**
 * Created by yuki.n on 2017/04/25.
 */
abstract class BaseRecyclerFragment :BaseFragment(){
    val recyclerView:RecyclerView by Views.bind(this,R.id.recycler)

    override fun setContentLayout(): Int {
        return R.layout.fragment_base_recycler
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerManager()
    }

    fun setupRecyclerAdapter(adapter:BaseRecyclerAdapter<*>) {
        recyclerView.adapter = adapter
    }

    fun setupRecyclerManager() {
        val manager = LinearLayoutManager(context)
        manager.orientation =getRecyclerManager()
        recyclerView.layoutManager = manager
    }

    abstract fun getRecyclerManager() :Int
}