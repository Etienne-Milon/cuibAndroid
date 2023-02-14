package fr.em.cuibandroid.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.em.cuibandroid.model.Article
import fr.em.cuibandroid.network.CuibApi
import kotlinx.coroutines.launch

enum class ArticleApiStatus {LOADING, ERROR, DONE}

class ArticleViewModel : ViewModel(){

    // Create properties to represent MutableLiveData and LiveData for the API status
    private val _status = MutableLiveData<ArticleApiStatus>()
    val status : LiveData<ArticleApiStatus> = _status

    private val _articles = MutableLiveData<List<Article>>()
    val articles : LiveData<List<Article>> = _articles

    private val _article = MutableLiveData<Article>()
    val article : LiveData<Article> = _article


    fun getArticleList(){
        viewModelScope.launch {
            _status.value = ArticleApiStatus.LOADING
            try {
                _articles.value = CuibApi.retrofitService.getArticles()
                _status.value = ArticleApiStatus.DONE
            }
            catch (e: java.lang.Exception){
                _status.value = ArticleApiStatus.ERROR
                _articles.value = listOf()
            }
        }
    }
    //Note : ici, c'est l'objet 'Article' qui est clickable, à voir ce que l'on souhaite réelement
    // peut-être

    fun onArticleClicked(article: Article){
        _article.value = article
    }

}