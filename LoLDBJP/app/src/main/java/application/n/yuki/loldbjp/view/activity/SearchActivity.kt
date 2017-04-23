package application.n.yuki.loldbjp.view.activity

import android.os.Bundle
import application.n.yuki.loldbjp.R
import application.n.yuki.loldbjp.rest.entity.StaticChampionEntity
import application.n.yuki.loldbjp.type.ChampionType
import application.n.yuki.loldbjp.view.base.BaseActivity
import application.n.yuki.loldbjp.view.fragment.ChampionListFragment
import rx.Observable
import java.util.*

class SearchActivity : BaseActivity() {
    private val staticChampionList: ArrayList<StaticChampionEntity> = ArrayList()
    private val selectedList: ArrayList<StaticChampionEntity> = ArrayList()

    override fun getContentLayoutResId(): Int {
        return R.layout.activity_base
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setInitialFragment(ChampionListFragment.newInstance())
    }

    fun setChampionList(list: ArrayList<StaticChampionEntity>) {
        staticChampionList.addAll(list)
    }

    fun getChampionList() = staticChampionList

    fun searchFromType(type: ChampionType): ArrayList<StaticChampionEntity> {
        if (type == ChampionType.All) {
            return staticChampionList
        }

        return Observable.from(staticChampionList)
                .filter {
                    item ->
                    item.tags.contains(type.string)
                }
                .toList()
                .toBlocking()
                .first() as ArrayList<StaticChampionEntity>
    }
}
