package com.yumeng.libcommon.helper.pickCountry

import com.contrarywind.interfaces.IPickerViewData
import com.google.gson.annotations.SerializedName

data class ProvinceModel(@SerializedName("data") var data: ArrayList<Data>) {
    data class Data(@SerializedName("name") var name: String, //香港
                    @SerializedName("city") var city: ArrayList<City>) : IPickerViewData {

        override fun getPickerViewText(): String = name
    }

    data class City(
            @SerializedName("name") var name: String, //香港
            @SerializedName("area") var area: ArrayList<String>
    )
}