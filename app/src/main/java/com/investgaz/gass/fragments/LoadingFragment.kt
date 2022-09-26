package com.investgaz.gass.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.investgaz.gass.R
import com.investgaz.gass.activities.PresentationActivity
import com.investgaz.gass.databinding.LoaddingFragmentBinding
import com.investgaz.gass.utils.AppsLoader
import com.investgaz.gass.utils.Checker
import com.investgaz.gass.utils.UriBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoadingFragment : Fragment() {
    private var _binding: LoaddingFragmentBinding? = null
    private val binding get() = _binding!!
    private val checker = Checker()

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
        val smth: Boolean = true
        if (checker.isDeviceSecured(requireActivity()))
        if (smth)
        {
            startPresentation()
            Log.d("customTag", "checked security")
        } else {
//            lifecycleScope.launch(Dispatchers.IO) {
            savedUrl = pref.getString("savedUrl", "").toString()
            if (savedUrl == "") {
                Log.d("customTag", "checked saved url")


                Log.d("customTag", pref.getBoolean("isStarted", false).toString())
                    lifecycleScope.launch(Dispatchers.IO) {

                        appsLoader.getApps(requireActivity()).collect {
                            Log.d("customTag", "started apps")
                            url = builder.buildUrl(it, requireActivity())
                            Log.d("customTag", "created url $url")
                            lifecycleScope.launch(Dispatchers.Main) {
                                startWeb(url)
                                Log.d("customTag", "started web from new")

                            }
                        }
                    }


            } else {
//                    lifecycleScope.launch(Dispatchers.Main) {
                startWeb(savedUrl)
                Log.d("customTag", "started web from saved $savedUrl")
//                    }
            }
//            }
        }
    }

    override fun onResume() {
        super.onResume()
        val pref = requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
        if (pref.getBoolean("isStarted", false)) {
            startWeb(savedUrl)
        }
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