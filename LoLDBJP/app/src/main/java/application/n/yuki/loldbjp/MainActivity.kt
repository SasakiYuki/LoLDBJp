package application.n.yuki.loldbjp

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import application.n.yuki.loldbjp.contract.ChampionContract
import application.n.yuki.loldbjp.contract.StaticChampionContract
import application.n.yuki.loldbjp.rest.entity.StaticChampionEntity
import application.n.yuki.loldbjp.viewmodel.ChampionViewModel
import application.n.yuki.loldbjp.viewmodel.StaticChampionViewModel
import application.n.yuki.loldbjp.view.ThumbnailListAdapter
import application.n.yuki.loldbjp.view.ThumbnailEntity
import java.util.*

class MainActivity : Activity(),ChampionContract.ChampionView,StaticChampionContract.StaticChampionView{
    private lateinit var recyclerView:RecyclerView
    private lateinit var adapter:ThumbnailListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: ChampionViewModel = ChampionViewModel(application)
        viewModel.setView(this)

        recyclerView = ((findViewById(R.id.recycler))as RecyclerView)
        val manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = manager
        adapter = ThumbnailListAdapter(this)
        recyclerView.adapter = adapter

        (findViewById(R.id.button) as Button).setOnClickListener {
            viewModel.getFreeChampions()
        }
    }

    override fun onSuccessChampion(staticChampionEntity: StaticChampionEntity) {
        adapter.addThubmnailEntity(ThumbnailEntity(staticChampionEntity.image.full,staticChampionEntity.name))
    }

    override fun onSuccess(entities: ArrayList<Int>) {
        val viewModel = StaticChampionViewModel(application)
        viewModel.setView(this)
        for(item in entities) {
            viewModel.getChampion(item)
        }
    }

    override fun onError(throwable: Throwable) {
        throwable.printStackTrace()
    }
}
