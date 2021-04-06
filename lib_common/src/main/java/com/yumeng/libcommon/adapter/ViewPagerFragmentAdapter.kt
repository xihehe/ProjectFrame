package com.yumeng.libcommon.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.*

/**
 * Created by Chauncey on 2017/9/25 21:39.
 * ViewPager Fragment
 * 自定义封装的FragmentPagerAdapter
 * 包含一些方法
 */

class ViewPagerFragmentAdapter : FragmentPagerAdapter {
    private var fragments: ArrayList<Fragment>
    private var titles: ArrayList<String>? = null
    var destroyItemEnabled = true

    constructor(fm: FragmentManager, fragments: ArrayList<Fragment>) : super(fm) {
        this.fragments = fragments
    }

    constructor(
        fm: FragmentManager,
        fragments: ArrayList<Fragment>,
        titles: ArrayList<String>
    ) : super(fm) {
        this.fragments = fragments
        this.titles = titles
    }

    fun notifyDataSetChanged(fragments: ArrayList<Fragment>, titles: ArrayList<String>) {
        this.fragments = fragments
        this.titles = titles
        notifyDataSetChanged()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles?.get(position)
    }

    fun setFragments(fragments: ArrayList<Fragment>) {
        this.fragments = fragments
        notifyDataSetChanged()
    }

    fun addItem(fragment: Fragment) {
        fragments.add(fragment)
        notifyDataSetChanged()
    }

    fun removeItem(fragment: Fragment) {
        fragments.remove(fragment)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        fragments.removeAt(position)
        notifyDataSetChanged()
    }

    fun removeAll() {
        fragments.clear()
        notifyDataSetChanged()
    }

    fun removeItem() {
        fragments.removeAt(fragments.size - 1)
        notifyDataSetChanged()
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        if (destroyItemEnabled) {
            super.destroyItem(container, position, `object`)
        }
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}