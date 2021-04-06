package com.yumeng.libbase.activity.telphone

import android.widget.Filter
import android.widget.Filterable
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

import com.yumeng.libbase.R
import com.yumeng.libbase.helper.DataHelper
import java.util.*

class TelephoneCodeAdapter(layoutResId: Int = R.layout.item_telephone_code) :
    BaseQuickAdapter<TelephoneCodeModel, BaseViewHolder>(layoutResId), Filterable {
    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if(!constraint.isNullOrEmpty()){
                    val content = constraint.toString().toLowerCase(Locale.getDefault())
                    val list = ArrayList<TelephoneCodeModel>()
                    suggestions?.filter {
                        val code = it.tel
                        val name = it.en.toLowerCase(Locale.getDefault())
                        val cnName = it.name
                        DataHelper.isMatch(code, content) || DataHelper.isMatch(name, content) || DataHelper.isMatch(cnName, content)
                    }?.forEach {
                        list.add(it)
                    }
                    filterResults.count = list.size
                    filterResults.values = list
                }else{
                    filterResults.count = suggestions?.size ?: 0
                    filterResults.values = suggestions
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                results?.let {
                    replaceData(it.values as ArrayList<TelephoneCodeModel>)
                }
            }

        }
    }

    var suggestions: List<TelephoneCodeModel>? = null


    override fun convert(helper: BaseViewHolder, item: TelephoneCodeModel) {
        helper.setText(R.id.tvNationName, item.name)
            .setText(R.id.tvNationCode, "+${item.tel}")
    }
}