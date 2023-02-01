package com.example.shortlyapphijfqp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.shortlyapphijfqp.R
import dagger.android.support.DaggerFragment

class FirstScreenFragment : DaggerFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun gotToShortenUrlsScreen(url: String) {
        val directions = FirstScreenFragmentDirections.actionFirstScreenFragmentToShortenUrlsFragment(url)
        findNavController().navigate(directions)
    }

}