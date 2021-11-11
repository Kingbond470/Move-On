package com.example.liquidswipedemo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import dev.kingbond.moveon.Swipe.Swipe
import dev.kingbond.moveon.backgroundColorArray
import dev.kingbond.moveon.resourceArray
import dev.kingbond.moveon.titleArray

class CustomFragmentPagerAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return Swipe.newInstance(
            backgroundColorArray[(position % titleArray.count())],
            resourceArray[(position % titleArray.count())],
            titleArray[(position % titleArray.count())]
        )
    }

    override fun getCount(): Int {
        return titleArray.count() * 20
    }
}