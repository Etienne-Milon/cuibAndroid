package fr.em.cuibandroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import fr.em.cuibandroid.R
import fr.em.cuibandroid.databinding.FragmentArticleListBinding

class ArticleListFragment : Fragment() {

    private val viewModel: ArticleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentArticleListBinding.inflate(inflater)
        //call the viewModel that calls the article api
        viewModel.getArticleList()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = ArticleListAdapter(ArticleListener { article ->
            viewModel.onArticleClicked(article)
            findNavController()
                .navigate(R.id.action_articleListFragment_to_articleDetailFragment)
        }
        )
        // Inflate the layout for this fragment
        return binding.root
    }
}
