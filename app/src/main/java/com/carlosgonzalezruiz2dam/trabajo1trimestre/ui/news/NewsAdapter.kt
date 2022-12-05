package com.carlosgonzalezruiz2dam.trabajo1trimestre.ui.news

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.carlosgonzalezruiz2dam.trabajo1trimestre.R
import com.carlosgonzalezruiz2dam.trabajo1trimestre.databinding.ViewNewBinding
import com.carlosgonzalezruiz2dam.trabajo1trimestre.inflate
import com.carlosgonzalezruiz2dam.trabajo1trimestre.loadUrl
import com.carlosgonzalezruiz2dam.trabajo1trimestre.model.New

class NewsAdapter(val listener: (New) -> Unit):
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    var news = emptyList<New>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_new, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newArticle = news[position]
        holder.bind(newArticle)

        holder.itemView.setOnClickListener {
            listener(newArticle)
        }
    }

    override fun getItemCount(): Int = news.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ViewNewBinding.bind(view)
         fun bind(newArticle: New){
             // En función de la URL de la imagen: mostrar o no la imagen.
             if (newArticle.urlImage == "") {
                binding.cardView.visibility = View.GONE
             } else {
                 binding.imagen.loadUrl(newArticle.urlImage)
             }

             binding.title.text = newArticle.title

             // En función de si hay descripción: mostrar o no la descripción.
             if (newArticle.description == "") {
                 binding.description.visibility = View.GONE
             } else {
                 binding.description.text = newArticle.description
             }

             // En función de si hay fecha: mostrar o no la fecha.
             if (newArticle.date == "") {
                 binding.date.visibility = View.GONE
             } else {
                 binding.date.text = newArticle.date
             }
         }
    }
}