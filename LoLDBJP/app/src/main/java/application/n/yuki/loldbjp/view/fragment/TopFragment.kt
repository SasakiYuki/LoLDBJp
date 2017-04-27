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
import application.n.yuki.loldbjp.marker.CoordinatorMarker
import application.n.yuki.loldbjp.marker.FixedToolbarMarker
import application.n.yuki.loldbjp.rest.entity.StaticChampionEntity
import application.n.yuki.loldbjp.rest.entity.StaticChampionsData
import application.n.yuki.loldbjp.type.ChampionType
import application.n.yuki.loldbjp.view.activity.BansActivity
import application.n.yuki.loldbjp.view.activity.PickRateActivity
import application.n.yuki.loldbjp.view.adapter.ThumbnailEntity
import application.n.yuki.loldbjp.view.adapter.ThumbnailListAdapter
import application.n.yuki.loldbjp.view.activity.SearchActivity
import application.n.yuki.loldbjp.view.activity.WinRateActivity
import application.n.yuki.loldbjp.view.base.BaseActivity
import application.n.yuki.loldbjp.view.base.BaseFragment
import application.n.yuki.loldbjp.viewmodel.rest.ChampionViewModel
import application.n.yuki.loldbjp.viewmodel.rest.StaticChampionViewModel
import java.util.*

/**
 * Created by yuki.n on 2017/04/18.
 */

class TopFragment : BaseFragment(), ChampionContract.ChampionView, StaticChampionContract.StaticChampionView ,FixedToolbarMarker,CoordinatorMarker{
    override fun setContentLayout(): Int {
        return R.layout.fragment_top
    }

    val freeChampionRecyclerView: RecyclerView by Views.bind(this, R.id.free_to_play_recycler)
    val winRateView:View by Views.bind(this,R.id.fragment_top_win)
    val banRateView:View by Views.bind(this,R.id.fragment_top_ban)
    val pickRateView:View by Views.bind(this,R.id.fragment_top_pick)
    val button: View by Views.bind(this, R.id.button)
    val mageView:View by Views.bind(this,R.id.fragment_top_mage)
    val assasinView:View by Views.bind(this,R.id.fragment_top_assassin)
    val tankView:View by Views.bind(this,R.id.fragment_top_tank)
    val fighterView:View by Views.bind(this,R.id.fragment_top_fighter)
    val marksmanView:View by Views.bind(this,R.id.fragment_top_marksman)
    val supportView:View by Views.bind(this,R.id.fragment_top_support)
    private lateinit var adapter: ThumbnailListAdapter

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

        winRateView.setOnClickListener {
            activity.startActivity(Intent(activity,WinRateActivity::class.java))
        }

        banRateView.setOnClickListener {
            activity.startActivity(Intent(activity,BansActivity::class.java))
        }

        pickRateView.setOnClickListener {
            activity.startActivity(Intent(activity,PickRateActivity::class.java))
        }

        button.setOnClickListener {
            activity.startActivity(Intent(activity, SearchActivity::class.java))
        }

        val intent = Intent(activity,SearchActivity::class.java)

        mageView.setOnClickListener {
            intent.putExtra(SearchActivity.INTENT_SEARCH_TYPE,ChampionType.MAGE.string)
            activity.startActivity(intent)
        }

        assasinView.setOnClickListener {
            intent.putExtra(SearchActivity.INTENT_SEARCH_TYPE,ChampionType.ASSASSIN.string)
            activity.startActivity(intent)
        }

        tankView.setOnClickListener {
            intent.putExtra(SearchActivity.INTENT_SEARCH_TYPE,ChampionType.TANK.string)
            activity.startActivity(intent)
        }

        fighterView.setOnClickListener {
            intent.putExtra(SearchActivity.INTENT_SEARCH_TYPE,ChampionType.FIGHTER.string)
            activity.startActivity(intent)
        }

        marksmanView.setOnClickListener {
            intent.putExtra(SearchActivity.INTENT_SEARCH_TYPE,ChampionType.MARKSMAN.string)
            activity.startActivity(intent)
        }

        supportView.setOnClickListener {
            intent.putExtra(SearchActivity.INTENT_SEARCH_TYPE,ChampionType.SUPPORT.string)
            activity.startActivity(intent)
        }
    }

    override fun onSuccessChampions(staticChampionsData: StaticChampionsData) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
        fun newInstance() :TopFragment {
            val topFragment = TopFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_CONTENTS_NAME_ID,R.string.app_name)
            bundle.putString(ARG_CONTENTS_NAME,"はげくそTOp")
            topFragment.arguments = bundle
            return topFragment
        }
    }
}