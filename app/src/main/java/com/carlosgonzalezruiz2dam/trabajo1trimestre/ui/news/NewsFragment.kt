package com.carlosgonzalezruiz2dam.trabajo1trimestre.ui.news

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.carlosgonzalezruiz2dam.trabajo1trimestre.R
import com.carlosgonzalezruiz2dam.trabajo1trimestre.databinding.FragmentNewsBinding
import com.carlosgonzalezruiz2dam.trabajo1trimestre.model.NewsData
import com.carlosgonzalezruiz2dam.trabajo1trimestre.ui.detail.DetailFragment
import java.util.*

/**
 * Función para obtener la fecha actual en el formate AÑO-MES-DÍA.
 *
 * @return String
 */
fun getCurrentDate() : String {
    val c = Calendar.getInstance()

    return  c.get(Calendar.YEAR).toString() + "-" +
            c.get(Calendar.MONTH).toString() + "-" +
            c.get(Calendar.DAY_OF_MONTH).toString()
}

class NewsFragment : Fragment(R.layout.fragment_news) {
    private val viewModel: NewsViewModel by viewModels {
        NewsViewModelFactory(
            getString(R.string.country_code),
            arguments?.getParcelable<NewsData>(EXTRA_NEWSDATA)!!,
            getCurrentDate(),
            getString(R.string.api_key))
    }
    companion object{
        const val EXTRA_NEWSDATA = "NewsActivity:NewsData"
    }
    private lateinit var binding: FragmentNewsBinding
    private val adapter = NewsAdapter(){ newArticle -> viewModel.navigateTo(newArticle)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view).apply {
            recycler.adapter = adapter
        }

        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.latest_news_about) + " " + viewModel.newsData.category
        // Volver a mostrar el Action Bar
        (requireActivity() as AppCompatActivity).supportActionBar?.show();

        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.progress.visibility = if (state.loading) VISIBLE else GONE
            state.news?.let {
                adapter.news = state.news
                adapter.notifyDataSetChanged()
            }

            state.navigateTo?.let {
                findNavController().navigate(
                    R.id.action_newsFragment_to_detailFragment,
                    bundleOf(DetailFragment.EXTRA_NEW to it)
                )
                viewModel.onNavigateDone()
            }

        }
    }
}
