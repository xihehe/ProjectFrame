package com.yumeng.libcommon.adapter

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

/**
 * Created by Chauncey on 2018/7/10 15:39.
 */
class PagerViewAdapter(private val viewList: List<View>//数据源
) : PagerAdapter() {

    //数据源的数目
    override fun getCount(): Int {
        return viewList.size
    }

    //view是否由对象产生，官方写arg0==arg1即可
    override fun isViewFromObject(arg0: View, arg1: Any) = arg0 === arg1

    //销毁一个页卡(即ViewPager的一个item)
    override fun destroyItem(container: ViewGroup, position: Int, any: Any) {
        container.removeView(viewList[position])
    }

    //对应页卡添加上数据
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(viewList[position])//千万别忘记添加到container
        return viewList[position]
    }
}
