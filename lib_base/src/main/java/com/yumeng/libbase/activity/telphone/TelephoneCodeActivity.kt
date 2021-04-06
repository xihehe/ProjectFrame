package com.yumeng.libbase.activity.telphone

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.yumeng.libbase.R
import com.yumeng.libcommon.helper.DensityHelper
import com.yumeng.libcommon.helper.JsonHelper
import com.yumeng.libcommonview.activity.CommonToolbarActivity
import com.yumeng.libcommonview.view.ItemDecoration.FloatingDecoration
import kotlinx.android.synthetic.main.activity_telephone_code.*

class TelephoneCodeActivity :CommonToolbarActivity() {


    private val mAdapter by lazy { TelephoneCodeAdapter() }

    companion object {
        const val TEL_CODE = "tel_code"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNormalConfig()
        title = getString(R.string.region_country)
        setContentView(R.layout.activity_telephone_code)
        mAdapter.run {
            setOnItemClickListener { _, _, position ->
                val model = data[position]
                setResult(Activity.RESULT_OK, Intent().apply {
                    putExtra(TEL_CODE, model.tel)
                })
                finish()
            }
            indexBar.wordsNavigation.run {
                setOnWordsChangeListener { keyword ->
                    val item = data.firstOrNull { model ->
                        model.getHeadWord() == keyword
                    }
                    val from = data.indexOf(item)
                    if (from != -1) {
                        (recyclerView.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
                            from,
                            0
                        )
                    }
                }
            }
        }
        recyclerView.run {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                FloatingDecoration(
                    context,
                    FloatingDecoration.DecorationCallback { position ->
                        mAdapter.data[position].getHeadWord()
                    }).apply {
                    this.setFloatingBarHeight(DensityHelper.dp2px(context, 33.33F))
                    this.setLabelTextSize(DensityHelper.sp2px(context, 16F))
                    this.setTitleMarginStart(DensityHelper.dp2px(context, 16F))
                    this.setDividingLine(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.divider_vertical_ddd
                        )
                    )
                    this.setDividerMarginStart(DensityHelper.dp2px(context, 16F))
                    this.setLabelTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.module_login_account_title_color
                        )
                    )
                    this.setFloatingBarBackgroundColorRes(R.color.news_detail_comment)
                })

            adapter = mAdapter
        }

        loadData()
    }

    private fun loadData() {
        val list = JsonHelper.getObjectFromJsonArrayInAssets(
            this, "tel_code.json",
            TelephoneCodeModel::class.java
        )
        list.sortBy {
            it.en
        }
        mAdapter.suggestions=list
        mAdapter.replaceData(list)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search_tel, menu)
        val searchView = menu.findItem(R.id.item_search).actionView as SearchView
        searchView.apply {
            initSearchView(this)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    mAdapter.filter.filter(newText)
                    return false
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }
}