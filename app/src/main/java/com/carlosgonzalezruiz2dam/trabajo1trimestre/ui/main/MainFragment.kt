package com.carlosgonzalezruiz2dam.trabajo1trimestre.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.carlosgonzalezruiz2dam.trabajo1trimestre.R
import com.carlosgonzalezruiz2dam.trabajo1trimestre.databinding.FragmentMainBinding
import com.carlosgonzalezruiz2dam.trabajo1trimestre.model.NewsData
import com.carlosgonzalezruiz2dam.trabajo1trimestre.ui.news.NewsFragment

class MainFragment : Fragment(R.layout.fragment_main) {
    private  val viewModel: MainViewModel by viewModels {
        MainViewModelFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view)

        (requireActivity() as AppCompatActivity).supportActionBar?.title = "test"
        // No mostrar el Action bar aquí
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        binding.cardViewTech.setOnClickListener {
            val newsData = NewsData("technology")
            findNavController().navigate(
                R.id.action_mainFragment_to_newsFragment,
                bundleOf(NewsFragment.EXTRA_NEWSDATA to newsData)
            )
        }
        binding.cardViewSci.setOnClickListener {
            val newsData = NewsData("science")
            findNavController().navigate(
                R.id.action_mainFragment_to_newsFragment,
                bundleOf(NewsFragment.EXTRA_NEWSDATA to newsData)
            )
        }
        binding.cardViewSports.setOnClickListener {
            val newsData = NewsData("sports")
            findNavController().navigate(
                R.id.action_mainFragment_to_newsFragment,
                bundleOf(NewsFragment.EXTRA_NEWSDATA to newsData)
            )
        }
        binding.cardViewHealth.setOnClickListener {
            val newsData = NewsData("health")
            findNavController().navigate(
                R.id.action_mainFragment_to_newsFragment,
                bundleOf(NewsFragment.EXTRA_NEWSDATA to newsData)
            )
        }
        binding.cardViewEntertainment.setOnClickListener {
            val newsData = NewsData("entertainment")
            findNavController().navigate(
                R.id.action_mainFragment_to_newsFragment,
                bundleOf(NewsFragment.EXTRA_NEWSDATA to newsData)
            )
        }
        binding.cardViewPolitics.setOnClickListener {
            val newsData = NewsData("politics")
            findNavController().navigate(
                R.id.action_mainFragment_to_newsFragment,
                bundleOf(NewsFragment.EXTRA_NEWSDATA to newsData)
            )
        }
    }
}