package fr.em.cuibandroid.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fr.em.cuibandroid.databinding.ListViewItemBinding
import fr.em.cuibandroid.network.Article


/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */
class ArticleListAdapter(val clickListener: ArticleListener) :
    ListAdapter<Article, ArticleListAdapter.ArticleViewHolder>(DiffCallBack) {


    class ArticleViewHolder(var binding: ListViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: ArticleListener, article: Article) {
            binding.article = article
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.ean13 == newItem.ean13
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.titre == newItem.titre && oldItem.genre == newItem.genre
                // && oldItem.format == newItem.format
                // etc...
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ArticleViewHolder(ListViewItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(clickListener, article)
    }
}

class ArticleListener (val clickListener: (article : Article) -> Unit){
    fun onClick(article: Article) = clickListener(article)
}
