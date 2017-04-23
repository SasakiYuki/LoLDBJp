package application.n.yuki.loldbjp.view.base

import android.content.Context
import android.support.annotation.Nullable
import android.view.View
import application.n.yuki.loldbjp.marker.CoordinatorMarker

/**
 * Created by yuki.n on 2017/04/20.
 */
interface OverlayContentProvider :CoordinatorMarker{
    @Nullable
    fun getOverlayContent(context:Context):View
}