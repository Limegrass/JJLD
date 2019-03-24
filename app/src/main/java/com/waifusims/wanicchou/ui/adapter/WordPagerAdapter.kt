package com.waifusims.wanicchou.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.waifusims.wanicchou.ui.fragments.DefinitionFragment
import com.waifusims.wanicchou.ui.fragments.RelatedFragment
import com.waifusims.wanicchou.ui.fragments.TagFragment

class WordPagerAdapter(fragmentManager: FragmentManager)
    : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> DefinitionFragment()
            1 -> RelatedFragment()
            2 -> TagFragment()
            else -> throw IndexOutOfBoundsException("Invalid position for Word Pager.")
        }
    }

    override fun getCount(): Int {
        return 3
    }

}