package application.n.yuki.loldbjp.marker

import android.content.Context
import android.view.View

/**
 * Created by yuki.n on 2017/04/27.
 */
interface HeaderContentProvider :CoordinatorMarker{
    fun getAppBarContent(context: Context):View
}