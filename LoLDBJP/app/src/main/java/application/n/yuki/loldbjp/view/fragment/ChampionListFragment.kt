package application.n.yuki.loldbjp.view.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import application.n.yuki.loldbjp.R
import application.n.yuki.loldbjp.Views
import application.n.yuki.loldbjp.contract.StaticChampionContract
import application.n.yuki.loldbjp.rest.entity.StaticChampionEntity
import application.n.yuki.loldbjp.rest.entity.StaticChampionsData
import application.n.yuki.loldbjp.view.ThumbnailEntity
import application.n.yuki.loldbjp.view.ThumbnailListAdapter
import application.n.yuki.loldbjp.view.activity.SearchActivity
import application.n.yuki.loldbjp.view.base.BaseFragment
import application.n.yuki.loldbjp.view.base.OverlayContentProvider
import application.n.yuki.loldbjp.viewmodel.StaticChampionViewModel
import rx.Observable
import java.util.*

/**
 * Created by yuki.n on 2017/04/18.
 */
class ChampionListFragment : BaseFragment(), StaticChampionContract.StaticChampionView, OverlayContentProvider {
    override fun setContentLayout() = R.layout.fragment_champion_list

    val recyclerView: RecyclerView by Views.bind(this, R.id.recycler)
    private lateinit var adapter: ThumbnailListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        val v = StaticChampionViewModel(activity.applicationContext)
        v.setView(this)
        setUpViews()

        // SearchActivity でかつ　既にデータが入っている場合は非同期処理を行わなずListにアイテムを渡す
        if (isOnSearchActivity() && !getSearchActivity().getChampionList().isEmpty()) {
            setListFromActivity()
        } else {
            v.getChampions()
        }
    }

    fun setUpViews() {
        val manager = GridLayoutManager(context, 3)
        manager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = manager
        adapter = ThumbnailListAdapter(context)
        recyclerView.adapter = adapter
    }

    private fun setListFromActivity() {
        val list = getSearchActivity().getChampionList()
        if (isSearched()) {
            val item = (Observable.from(list)
                    .filter { item -> if (isSearchedKey()) item.tags.contains(getSearchedKey()) else true }
                    .filter { item -> if (isSearchedPartype()) getSearchedPartype().equals(item.partype) else true }
                    .filter { item -> if (isSearchedName()) getSearchedName().contains(getSearchedName()) else true }
                    .map {
                        item ->
                        ThumbnailEntity(item.image.full, item.name)
                    }
                    .toList()
                    .toBlocking()
                    .first() as ArrayList<ThumbnailEntity>)
            adapter.addThumbnailEntity(item)
        } else {
            adapter.addThumbnailEntity(Observable.from(list).map {
                item ->
                ThumbnailEntity(item.image.full, item.name)
            }
                    .toList()
                    .toBlocking()
                    .first() as ArrayList<ThumbnailEntity>)
        }
    }

    override fun onSuccessChampion(staticChampionEntity: StaticChampionEntity) {
    }

    override fun onSuccessChampions(staticChampionsData: StaticChampionsData) {
        val arrayList = ArrayList<StaticChampionEntity>()
        for (e in staticChampionsData.data.entries) {
            val entity = ThumbnailEntity(e.value.image.full, e.value.name)
            adapter.addThubmnailEntity(entity)
            arrayList.add(e.value)
        }

        if (isOnSearchActivity()) {
            (activity as SearchActivity).setChampionList(arrayList)
        }
    }

    override fun onError(throwable: Throwable) {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                SearchFragment.requestCode -> {
                    arguments.putString(ARG_SEARCH_KEY, data.getStringExtra(ARG_SEARCH_KEY))
                    arguments.putString(ARG_SEARCH_PARTYPE, data.getStringExtra(ARG_SEARCH_PARTYPE))
                    arguments.putString(ARG_SEARCH_NAME, data.getStringExtra(ARG_SEARCH_NAME))
                    setListFromActivity()
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun isSearchedKey() = !TextUtils.isEmpty(getSearchedKey()) && !getSearchedKey().equals("all")

    private fun getSearchedKey() = arguments.getString(ARG_SEARCH_KEY)

    private fun isSearchedPartype() = !TextUtils.isEmpty(getSearchedPartype()) && !getSearchedPartype().equals("すべて")

    private fun getSearchedPartype() = arguments.getString(ARG_SEARCH_PARTYPE)

    private fun isSearchedName() = !TextUtils.isEmpty(getSearchedName())

    private fun getSearchedName() = arguments.getString(ARG_SEARCH_NAME)

    private fun isSearched() =
            !TextUtils.isEmpty(getSearchedKey()) || !TextUtils.isEmpty(getSearchedPartype()) || !TextUtils.isEmpty(getSearchedName())

    private fun isOnSearchActivity() = (context is SearchActivity)

    private fun getSearchActivity() = context as SearchActivity

    override fun getOverlayContent(context: Context): View {
        val view = View.inflate(context, R.layout.overlay_fab, null)
        (view.findViewById(R.id.fab)).setOnClickListener {
            replaceFragment(SearchFragment.newInstance(this))
        }
        return view
    }

    companion object {
        fun newInstance(key: String = "", partype: String = "", name: String = ""): ChampionListFragment {
            val fragment = ChampionListFragment()
            val bundle = Bundle()
            bundle.putString(ARG_SEARCH_KEY, key)
            bundle.putString(ARG_SEARCH_PARTYPE, partype)
            bundle.putString(ARG_SEARCH_NAME, name)
            bundle.putInt(ARG_CONTENTS_NAME_ID, R.string.fragment_champion_list_title)
            fragment.arguments = bundle
            return fragment
        }

        val ARG_SEARCH_KEY = "arg_search_key"
        val ARG_SEARCH_PARTYPE = "arg_search_partype"
        val ARG_SEARCH_NAME = "arg_search_name"
    }
}