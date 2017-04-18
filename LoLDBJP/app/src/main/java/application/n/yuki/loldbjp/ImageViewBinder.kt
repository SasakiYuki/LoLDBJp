package application.n.yuki.loldbjp

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by yuki.n on 2017/04/17.
 */

@BindingAdapter("android:imageUrl")
fun ImageView.imageUrl(url: String?) {
    Picasso.with(context)
            .load(url)
            .into(this)
}
