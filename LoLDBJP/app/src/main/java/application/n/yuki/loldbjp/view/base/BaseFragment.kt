package application.n.yuki.loldbjp.view.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import application.n.yuki.loldbjp.utils.FragmentReplacer


abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(setContentLayout(), container, false)
    }

    /**
     * ツールバーに表示するタイトル文字列を返す
     */
    val title: String
        get() = getTitle(activity)

    /**
     * ツールバーに表示するタイトル文字列を返す
     */
    fun getTitle(context: Context): String {
        if (arguments != null) {
            val contentsNameId = arguments.getInt(ARG_CONTENTS_NAME_ID, 0)
            if (contentsNameId != 0) {
                return context.getString(contentsNameId)
            } else {
                return arguments.getString(ARG_CONTENTS_NAME, "")
            }
        }
        return ""
    }

    fun setNavigationTitle(title:String) {
        (context as BaseActivity).setNavigationTitle(title)
    }

    protected fun replaceFragment(fragment: BaseFragment, animated: Boolean) {
        val activity = activity
        if (activity is FragmentReplacer) {
            activity.replaceFragment(fragment, animated)
        }
    }

    fun replaceFragment(fragment: BaseFragment) {
        replaceFragment(fragment, true)
    }

    abstract fun setContentLayout(): Int

    protected fun popBackStack() {
        fragmentManager.popBackStack()
    }

    @JvmOverloads protected fun finish(fragment: Fragment = this) {
        fragmentManager.beginTransaction().remove(fragment).commit()
    }

    protected fun finish(klazz: Class<*>) {
        val fragments = fragmentManager.fragments
        for (fragment in fragments) {
            if (klazz.isInstance(fragment)) {
                finish(fragment)
            }
        }
    }

    fun showProgress() {
        (activity as BaseActivity).showProgress()
    }

    fun hideProgress() {
        (activity as BaseActivity).hideProgress()
    }

    companion object {
        const val ARG_CONTENTS_NAME_ID = "contents_name_id"
        const val ARG_CONTENTS_NAME = "contents_name"
    }
}
