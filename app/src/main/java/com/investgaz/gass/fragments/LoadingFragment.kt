package com.investgaz.gass.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.investgaz.gass.InvestApplication
import com.investgaz.gass.R
import com.investgaz.gass.activities.PresentationActivity
import com.investgaz.gass.databinding.LoaddingFragmentBinding
import com.investgaz.gass.utils.AppsLoader
import com.investgaz.gass.utils.UriBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoadingFragment : Fragment() {
    private var _binding: LoaddingFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var savedUrl: String
    lateinit var url: String
    private val builder = UriBuilder()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoaddingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val appsLoader = AppsLoader(requireActivity())
        if (checkADB(requireActivity()) != "1") {
            startPresentation()
        } else {
            savedUrl = pref.getString("savedUrl", "").toString()
            if (savedUrl == "") {

                lifecycleScope.launch(Dispatchers.IO) {
                    appsLoader.getApps(requireActivity()).collect {
                        url = builder.buildUrl(it, requireActivity(), InvestApplication.gadId)
                        lifecycleScope.launch(Dispatchers.Main) {
                            startWeb(url)
                        }
                    }
                }
            } else {
                startWeb(savedUrl)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val pref = requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
        if (pref.getBoolean("isStarted", false)) {
            startWeb(savedUrl)
        }
    }

    private fun checkADB(activity: Activity): String {
        return Settings.Global.getString(activity.contentResolver, Settings.Global.ADB_ENABLED)
    }

    private fun startPresentation() {
        with(Intent(requireActivity(), PresentationActivity::class.java)) {
            startActivity(this)
            requireActivity().finish()
        }
    }

    private fun startWeb(url: String) {
        val bundle = bundleOf("url" to url)
        findNavController().navigate(R.id.webFragment, bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}