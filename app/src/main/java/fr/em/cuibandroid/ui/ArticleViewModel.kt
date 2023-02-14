package fr.em.cuibandroid.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.em.cuibandroid.network.Article
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
                println(articles.value.toString())
                _status.value = ArticleApiStatus.DONE
            }
            catch (e: java.lang.Exception){
                _status.value = ArticleApiStatus.ERROR
                _articles.value = listOf()
            }
        }
    }
    // TODO Note : ici, c'est l'objet 'Article' qui est rendu clickable, à voir ce que l'on souhaite réellement
    //  peut-être que ça doit être l'item c-à-d un composant personnalisé, un bouton de l'item ?? peu clair :/
    fun onArticleClicked(article: Article){
        _article.value = article
    }

}