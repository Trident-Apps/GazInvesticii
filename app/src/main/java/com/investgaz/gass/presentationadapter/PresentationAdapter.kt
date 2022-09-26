package com.investgaz.gass.presentationadapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.investgaz.gass.fragments.PresentationFragment
import com.investgaz.gass.fragments.PresentationFragment2
import com.investgaz.gass.fragments.PresentationFragment3


class PresentationAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private val COUNT = 3

    override fun getItemCount(): Int {
        return COUNT
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PresentationFragment()
            1 -> PresentationFragment2()
            2 -> PresentationFragment3()
            else -> PresentationFragment()
        }
    }
}