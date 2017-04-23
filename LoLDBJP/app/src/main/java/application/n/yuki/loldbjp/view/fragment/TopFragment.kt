package application.n.yuki.loldbjp.view.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import application.n.yuki.loldbjp.R
import application.n.yuki.loldbjp.Views
import application.n.yuki.loldbjp.contract.ChampionContract
import application.n.yuki.loldbjp.contract.StaticChampionContract
import application.n.yuki.loldbjp.rest.entity.StaticChampionEntity
import application.n.yuki.loldbjp.rest.entity.StaticChampionsData
import application.n.yuki.loldbjp.view.ThumbnailEntity
import application.n.yuki.loldbjp.view.ThumbnailListAdapter
import application.n.yuki.loldbjp.view.activity.SearchActivity
import application.n.yuki.loldbjp.viewmodel.ChampionViewModel
import application.n.yuki.loldbjp.viewmodel.StaticChampionViewModel
import java.util.*

/**
 * Created by yuki.n on 2017/04/18.
 */

class TopFragment : Fragment(), ChampionContract.ChampionView, StaticChampionContract.StaticChampionView {
    override fun onSuccessChampions(staticChampionsData: StaticChampionsData) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val freeChampionRecyclerView:RecyclerView by Views.bind(this,R.id.free_to_play_recycler)
    val button:Button by Views.bind(this,R.id.button)
    private lateinit var adapter: ThumbnailListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_top, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    fun setUpViews() {
        val viewModel: ChampionViewModel = ChampionViewModel(context.applicationContext)
        viewModel.setView(this)

        val manager = LinearLayoutManager(context)
        manager.orientation = LinearLayoutManager.HORIZONTAL
        freeChampionRecyclerView.layoutManager = manager
        adapter = ThumbnailListAdapter(context)
        freeChampionRecyclerView.adapter = adapter

        viewModel.getFreeChampions()
        button.setOnClickListener {
            activity.startActivity(Intent(activity,SearchActivity::class.java))
        }
    }

    override fun onSuccessChampion(staticChampionEntity: StaticChampionEntity) {
        adapter.addThubmnailEntity(ThumbnailEntity(staticChampionEntity.image.full, staticChampionEntity.name))
    }

    override fun onSuccess(entities: ArrayList<Int>) {
        val viewModel = StaticChampionViewModel(context.applicationContext)
        viewModel.setView(this)
        for (item in entities) {
            viewModel.getChampion(item)
        }
    }

    override fun onError(throwable: Throwable) {
        throwable.printStackTrace()
    }

    companion object {
        fun newInstance() = TopFragment()
    }
}