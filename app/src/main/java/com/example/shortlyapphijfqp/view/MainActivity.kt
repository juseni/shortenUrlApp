package com.example.shortlyapphijfqp.view

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.shortlyapphijfqp.R
import com.example.shortlyapphijfqp.databinding.ActivityMainBinding
import com.example.shortlyapphijfqp.view.fragments.FirstScreenFragment
import com.example.shortlyapphijfqp.view.fragments.ShortenUrlsFragment
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var navController: NavController

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }

    private fun setupViews() {
        binding.bottomSheetOptions.apply {
            shortenButton.setOnClickListener {
                when(val fragment = getCurrentVisibleFragment()) {
                    is FirstScreenFragment ->
                        fragment.gotToShortenUrlsScreen(enterTextEditText.text.toString())
                    is ShortenUrlsFragment ->
                        fragment.shortenUrl(enterTextEditText.text.toString())
                }
                enterTextEditText.text?.clear()
            }
        }
    }

    fun onLoadingVisible(isVisible: Boolean) {
        binding.loading.isVisible = isVisible
    }

    private fun getCurrentVisibleFragment(): Fragment? {
        val navHostFragment: NavHostFragment? =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment?
       return navHostFragment?.childFragmentManager?.primaryNavigationFragment
    }

    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp()
    }


}