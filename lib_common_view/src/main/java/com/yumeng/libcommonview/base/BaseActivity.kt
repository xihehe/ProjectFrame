package  com.yumeng.libcommonview.base

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.yumeng.libcommonview.activity.CommonToolbarActivity

abstract class BaseActivity : CommonToolbarActivity() {

    protected open fun isFullScreenAndBlackTextModeEnabled(): Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTitleTextColor(ContextCompat.getColor(this, android.R.color.black))
        setNavigationBarColor(ContextCompat.getColor(this, android.R.color.black))
        if (isFullScreenAndBlackTextModeEnabled()) {
            setFullScreenAndBlackTextMode()
        }
    }


}