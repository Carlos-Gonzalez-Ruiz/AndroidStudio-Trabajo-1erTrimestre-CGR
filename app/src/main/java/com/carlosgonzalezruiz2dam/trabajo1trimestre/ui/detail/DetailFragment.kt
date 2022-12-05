package com.carlosgonzalezruiz2dam.trabajo1trimestre.ui.detail

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.carlosgonzalezruiz2dam.trabajo1trimestre.R
import com.carlosgonzalezruiz2dam.trabajo1trimestre.databinding.FragmentDetailBinding
import com.carlosgonzalezruiz2dam.trabajo1trimestre.model.New

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private  val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(arguments?.getParcelable<New>(EXTRA_NEW)!!)
    }
    companion object{
        const val EXTRA_NEW = "DetailActivity:New"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)

        viewModel.newArticle.observe(viewLifecycleOwner){ newArticle ->
            (requireActivity() as AppCompatActivity).supportActionBar?.title = newArticle.title

            // Crear WebView
            binding.webView.webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    // Mostrar cargando
                    binding.progress.visibility = View.VISIBLE;
                    // Esconder WebView mientras carga
                    binding.webView.visibility = View.GONE;
                }
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    // Esconder cargando
                    binding.progress.visibility = View.GONE;
                    // Mostrar WebView tras cargar
                    binding.webView.visibility = View.VISIBLE;
                }
            }
            // URL de la noticia
            binding.webView.loadUrl(newArticle.urlArticle)
            // Permitir Zoom
            binding.webView.settings.setSupportZoom(true)
            // No permitir Javascript, ya que no resultan necesarios para visualizar articulos
            binding.webView.settings.javaScriptEnabled = false
        }
    }
}

