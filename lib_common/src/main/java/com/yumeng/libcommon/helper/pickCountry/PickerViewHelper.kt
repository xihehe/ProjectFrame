package com.yumeng.libcommon.helper.pickCountry

import android.content.Context
import android.graphics.Color
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView

/**
 * Created by Chauncey on 2018/5/3 12:42.
 * 国家 省份  地区 三级选择辅助类
 */
object PickerViewHelper {

    fun initCountryRegionPickerView(
        context: Context,
        listener: OnAreaSelectListener? = null,
        language: String
    ): OptionsPickerView<Any> {
        val countryRegionModel: CountryRegionModel =
            CountryRegionModel.newInstance(context, language)

//        countryRegionModel.countryList.forEach {
//
//            if (it.stateList.isNullOrEmpty()) {
//                it.stateList =
//                        arrayListOf(CountryRegionModel.State("", "-1", arrayListOf(CountryRegionModel.City("", "-1"))))
//            }
//
//            it.stateList!!.forEach { s ->
//
//                if (s.cityList.isNullOrEmpty()) {
//                    s.cityList = arrayListOf(CountryRegionModel.City("", "-1"))
//                }
//
//                if (s.code == null) {
//                    s.code = "-1"
//                }
//
//                if (s.name == null) {
//                    s.name = ""
//                }
//            }
//        }
        val countryList = countryRegionModel.countryList
        val options2Items = ArrayList<ArrayList<CountryRegionModel.State>>()
        val options3Items = ArrayList<ArrayList<ArrayList<CountryRegionModel.City>>>()

//        FileUtils.outputStringToFile(
//            context.externalCacheDir?.absolutePath + File.separator + "json.txt",
//            JsonHelper.ObjectToJson(countryRegionModel)
//        )

        countryList.forEach { country ->
            val stateData = ArrayList<CountryRegionModel.State>()//该国家的州/省列表（第二级）
            val cityData = ArrayList<ArrayList<CountryRegionModel.City>>()//该州/省的所有地区列表（第三极）

            country.stateList.forEach { state ->
                stateData.add(state)
                cityData.add(state.cityList)
            }

            /**
             * 添加城市数据
             */
            options2Items.add(stateData)

            /**
             * 添加地区数据
             */
            options3Items.add(cityData)
        }

        val pickerView = OptionsPickerBuilder(
            context,
            OnOptionsSelectListener { options1, options2, options3, _ ->
                listener?.onSelected(
                    countryList[options1],
                    options2Items[options1][options2],
                    options3Items[options1][options2][options3]
                )
            })
            .setCancelColor(Color.LTGRAY)
            .build<Any>()

        pickerView.setPicker(countryList.toList(), options2Items.toList(), options3Items.toList())

        return pickerView
    }

    interface OnAreaSelectListener {
        fun onSelected(
            country: CountryRegionModel.Country,
            state: CountryRegionModel.State,
            city: CountryRegionModel.City
        )
    }
}
