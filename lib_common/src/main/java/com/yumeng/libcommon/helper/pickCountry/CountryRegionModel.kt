package com.yumeng.libcommon.helper.pickCountry

import android.content.Context
import com.contrarywind.interfaces.IPickerViewData
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.yumeng.libcommon.helper.JsonHelper

data class CountryRegionModel(@SerializedName("country") var countryList: ArrayList<Country>) {
    companion object {
        private const val NULL = "-1"

        fun newInstance(context: Context, language: String): CountryRegionModel =
            Gson().fromJson(
                JsonHelper.getJsonString(
                    context,
                    if (language == "zh") "country_region_zh.json" else "country_region_en.json"
                ), CountryRegionModel::class.java
            )

    }

    data class Country(
        @SerializedName("name") var name: String, //中国
        @SerializedName("code") var code: String,
        @SerializedName("state") var stateList: ArrayList<State>
    ) : IPickerViewData {

        override fun getPickerViewText(): String = name
    }

    data class State(
        @SerializedName("name") var name: String,
        @SerializedName("code") var code: String,
        @SerializedName("city") var cityList: ArrayList<City>
    ) : IPickerViewData {

        override fun getPickerViewText(): String = name
    }

    data class City(
        @SerializedName("name") var name: String, //香港
        @SerializedName("code") var code: String
    ) : IPickerViewData {

        override fun getPickerViewText(): String = name
    }

    fun toString(
        countryCode: String?,
        stateCode: String?,
        cityCode: String?,
        separator: String = "\t"
    ): String {

        val stringBuilder = StringBuilder()

        countryList.firstOrNull { country ->
            countryCode == country.code
        }?.apply {
            stringBuilder.append(name)
        }?.stateList?.firstOrNull { state ->
            stateCode == state.code
        }?.apply {
            if (code != NULL) {
                stringBuilder.append("$separator$name")
            }
        }?.cityList?.firstOrNull { city ->
            cityCode == city.code
        }?.apply {
            if (code != NULL) {
                stringBuilder.append("$separator$name")
            }
        }
        return stringBuilder.toString()
    }
}