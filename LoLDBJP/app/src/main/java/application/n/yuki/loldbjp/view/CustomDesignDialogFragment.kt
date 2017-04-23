package application.n.yuki.loldbjp.view

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import application.n.yuki.loldbjp.R

abstract class CustomDesignDialogFragment : DialogFragment() {

    private var mOnDismissListener: DialogInterface.OnDismissListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomDesignDialog_Dark)
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        if (mOnDismissListener != null) {
            mOnDismissListener!!.onDismiss(dialog)
        }
    }

    fun setOnDismissListener(listener: DialogInterface.OnDismissListener) {
        mOnDismissListener = listener
    }
}
