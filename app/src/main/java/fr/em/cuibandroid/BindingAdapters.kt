package fr.em.cuibandroid

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import fr.em.cuibandroid.network.Article
import fr.em.cuibandroid.ui.ArticleApiStatus
import fr.em.cuibandroid.ui.ArticleListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,data:List<Article>?){
    val adapter = recyclerView.adapter as ArticleListAdapter
    adapter.submitList(data)
}

/**
 * This binding adapter displays the [ArticleApiStatuspiStatus] of the network request in an image view.
 * When the request is loading, it displays a loading_animation.  If the request has an error, it
 * displays a broken image to reflect the connection error.  When the request is finished, it
 * hides the image view.
 */
@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: ArticleApiStatus?){
    when (status){
        ArticleApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ArticleApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        ArticleApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}


