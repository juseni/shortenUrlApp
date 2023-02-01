package com.example.shortlyapphijfqp.view.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.domain.model.Shorten
import com.example.shortlyapphijfqp.databinding.FragmentShortenUrlsBinding
import com.example.shortlyapphijfqp.view.MainActivity
import com.example.shortlyapphijfqp.view.adapters.ShortenUrlAdapter
import com.example.shortlyapphijfqp.viewmodel.ShortenViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class ShortenUrlsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: ShortenViewModel by lazy {
        ViewModelProvider(
            this,
            viewModelFactory
        )[ShortenViewModel::class.java]
    }

    private var _binding: FragmentShortenUrlsBinding? = null
    private val binding get() = _binding!!

    lateinit var adapter: ShortenUrlAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShortenUrlsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundleArgs = arguments?.let { ShortenUrlsFragmentArgs.fromBundle(it) }
        setupObservers()
        setupViews()
        bundleArgs?.let { shortenUrl(it.urlParam) }
    }


    private fun setupViews() {
        adapter = ShortenUrlAdapter(
            onDelete = { viewModel.deleteShortenUrl(it) },
            onCopy =  { onCopyShortenUrl(it) }
        )
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = this@ShortenUrlsFragment.adapter
        }
    }

    fun shortenUrl(url: String) {
        viewModel.getShortenUrls(url)
    }

    private fun updateShortenUrls(shortenUrls: List<Shorten>) {
        adapter.updateList(shortenUrls)
    }

    private fun onCopyShortenUrl(textToCopy: String) {
        val clipboard = getSystemService(requireContext(), ClipboardManager::class.java) as ClipboardManager
        val clip = ClipData.newPlainText("label", textToCopy)
        clipboard.setPrimaryClip(clip)
    }

    private fun setupObservers() {
        viewModel.shortenUrls.observe(viewLifecycleOwner, Observer {
            updateShortenUrls(it)
        })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            (requireActivity() as MainActivity).onLoadingVisible(it)
        })
        viewModel.noDataLoaded.observe(viewLifecycleOwner, Observer {
            // When there's no data to load, com back to the first screen
            val directions = ShortenUrlsFragmentDirections.actionShortenUrlsFragmentToFirstScreenFragment()
            findNavController().navigate(directions)
        })
    }
}