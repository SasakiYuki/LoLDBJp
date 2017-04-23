package application.n.yuki.loldbjp.view

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import application.n.yuki.loldbjp.R
import application.n.yuki.loldbjp.view.base.recycler.BaseRecyclerAdapter

/**
 * Created by yuki.n on 2017/04/20.
 */
class RecyclerDialogFragment : CustomDesignDialogFragment() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_select_list, container, false)
        recyclerView = view.findViewById(R.id.dialog_select_list_recycler) as RecyclerView
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    fun setRecyclerAdapter(adapter: BaseRecyclerAdapter<*>) {
        val manager = LinearLayoutManager(context)
        manager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter
    }

    companion object {
        fun newInstance() = RecyclerDialogFragment()
    }
}