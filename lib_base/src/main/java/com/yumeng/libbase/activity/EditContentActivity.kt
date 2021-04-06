package com.yumeng.libbase.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.yumeng.libbase.R
import com.yumeng.libcommon.ext.textWatcher
import com.yumeng.libcommonview.activity.CommonToolbarActivity
import kotlinx.android.synthetic.main.activity_edit_content.*

class EditContentActivity : CommonToolbarActivity() {


    companion object {
        const val TITLE_KEY = "title"
        const val RESULT_KEY = "result"
        //RxActivity
//        fun initIntent(context: Context, title: String, subTitle: String, result: String="") =
//            Intent(context, EditContentActivity::class.java)
//                .putExtra(TITLE_KEY, title)
//                .putExtra(SUBTITLE_KEY, subTitle)
//                .putExtra(RESULT_KEY, result)

        fun getResult(intent: Intent) = intent.getStringExtra(RESULT_KEY) ?: ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNormalConfig()
        setContentView(R.layout.activity_edit_content)
        intent.extras?.apply {
            title = getString(TITLE_KEY)
            etContent.setText(getString(RESULT_KEY, ""))
        }
        etContent.textWatcher {
            afterTextChanged { menuItem.isEnabled = etContent.text.toString().isNotBlank() }
        }
    }


    private lateinit var menuItem: MenuItem

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_confirm, menu)
        menuItem = menu.findItem(R.id.menu_item_confirm)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_confirm -> {
                setResult(Activity.RESULT_OK, Intent().apply {
                    putExtra(RESULT_KEY, etContent.text.toString())
                })
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}