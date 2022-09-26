package com.investgaz.gass.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.investgaz.gass.databinding.PresentationActivityBinding
import com.investgaz.gass.presentationadapter.PresentationAdapter

class PresentationActivity : AppCompatActivity() {
    private var _binding: PresentationActivityBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = PresentationActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager = binding.pager
        viewPager.adapter = PresentationAdapter(this)

    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            // to prevent exit from app
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}