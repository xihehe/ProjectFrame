package com.yumeng.libcommonview.activity

import android.animation.AnimatorInflater
import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.SparseArray
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.*
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.ViewCompat
import com.google.android.material.appbar.AppBarLayout
import com.gyf.immersionbar.ktx.immersionBar
import com.yumeng.libcommon.event.MessageEvent
import com.yumeng.libcommon.ext.enable
import com.yumeng.libcommon.ext.loadAvatar
import com.yumeng.libcommon.helper.DensityHelper
import com.yumeng.libcommon.helper.Preference
import com.yumeng.libcommon.utils.MobileUtils
import com.yumeng.libcommon.helper.StatusBarHelper
import com.yumeng.libcommonview.R
import com.yumeng.libcommonview.theme.Theme
import com.yumeng.libcommonview.view.ForegroundImageView
import com.yumeng.libcommonview.view.SearchToolbar
import kotlinx.android.synthetic.main.activity_toolbar.*
import kotlinx.android.synthetic.main.activity_toolbar.view.*
import kotlinx.android.synthetic.main.activity_toolbar_normal.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

open class CommonToolbarActivity : CommonActivity() {

    override fun initView() {
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        if (!isNormal()) {
            setTheme(R.style.NoActionBar)
        }
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun isNormal(): Boolean {
        return false
    }

    var isPromptViewVisible = false

    private val viewSparseArray: SparseArray<View> by lazy {
        SparseArray<View>()
    }

    protected fun setFullScreenAndBlackTextMode() {
        if (isNormal()) {
            appBarLayout?.visibility = View.GONE
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fitSystemWindows()
            setStatusBarBlackTextMode(true)
        } else {
            when (MobileUtils.getSystem()) {
                MobileUtils.SYS_FLYME, MobileUtils.SYS_MIUI, MobileUtils.SYS_OPPO -> {
                    fitSystemWindows()
                    setStatusBarBlackTextMode(true)
                }
                else -> {
                    setStatusBarColor(Color.BLACK)
                }
            }
        }
        setTitle(" ")
        setDisplayHomeAsUpEnabled(false)
        setBorderEnable(false)
    }


    protected fun getThemePosition(): Int {
        return Preference<Int>().getValue(Theme.THEME_COLOR_POSITION,0)

    }

    var promptViewMarginTop = 0  //可在搜索等activity界面赋值

    protected val promptViewGroup: LinearLayout by lazy {
        vsPromptViewStub?.inflate()
        val layout = findViewById<LinearLayout>(R.id.prompt_view_group)
        (layout.layoutParams as ConstraintLayout.LayoutParams).topMargin = promptViewMarginTop
        layout
    }

    protected fun showLoadingPromptView() {
        showLoadingPromptView(R.layout.view_loading)
    }

    protected fun showLoadingPromptView(layoutResID: Int = R.layout.view_loading) {
        showPromptView(layoutResID) {}
    }

    protected fun showPromptView(@LayoutRes layoutResID: Int, method: (view: View) -> Unit) {
        var view = viewSparseArray[layoutResID]
        if (view == null) {
            view = View.inflate(this, layoutResID, null)
            view.setOnClickListener {

            }
            viewSparseArray.put(layoutResID, view)
            method(view)
        }
        promptViewGroup.removeAllViews()
        promptViewGroup.addView(view, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        isPromptViewVisible = true
    }

    protected fun dismissPromptView() {
        runOnUiThread {
            if (isPromptViewVisible) {
                promptViewGroup.removeAllViews()
                isPromptViewVisible = false
            }
        }

    }

    override fun setStatusBarColor(@ColorInt color: Int) {
        StatusBarHelper.setWindowStatusBarColor(this, color)
    }

    override fun setStatusBarBlackTextMode(b: Boolean) {
        StatusBarHelper.setStatusBarBlackTextMode(this, b)
    }

    protected fun getNavigationLogoView(@DrawableRes res: Int): ImageView? {
        tbNavigation?.setLogo(res)
        val declaredField = tbNavigation?.javaClass?.getDeclaredField("mLogoView")
        declaredField?.isAccessible = true
//        tbNavigation?.setTitleMargin(DisplayUtils.dp2px(this, 16F), 0, 0, 0)
        return declaredField!![tbNavigation] as ImageView
    }

    protected fun getNavigationLogoView(res: Drawable): ImageView? {
        tbNavigation?.setLogo(res)
        val declaredField = tbNavigation?.javaClass?.getDeclaredField("mLogoView")
        declaredField?.isAccessible = true
//        tbNavigation?.setTitleMargin(DisplayUtils.dp2px(this, 16F), 0, 0, 0)
        return declaredField!![tbNavigation] as ImageView
    }

    protected fun getTitleTextView(): TextView {
        val declaredField = tbNavigation?.javaClass?.getDeclaredField("mTitleTextView")
        declaredField?.isAccessible = true
        return declaredField!![tbNavigation] as TextView
    }


    protected fun setLogoImage(@DrawableRes res: Int) {
        tbNavigation?.setLogo(res)
    }

    protected fun setTitleMargin(start: Int, top: Int, end: Int, bottom: Int) {
        tbNavigation?.setTitleMargin(start, top, end, bottom)
    }

    protected fun setNavigationContentView(view: View) {
        tbNavigation?.addView(view)
        tbNavigation?.contentInsetStartWithNavigation = 0
    }

    protected fun setTitle(content: String) {
        title = content
    }

    protected fun getTitleView(): TextView {
        return getTitleTextView()
    }


    protected fun setStatusBarDarkMode(b: Boolean) {
        StatusBarHelper.setStatusBarDarkIcon(this, b)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home ->
                onBackPressed()
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }


    protected fun setNavigationIcon(@DrawableRes resId: Int) {
        tbNavigation?.setNavigationIcon(resId)
    }

    protected fun setNavigationIcon(drawable: Drawable) {
        tbNavigation?.navigationIcon = drawable
    }

    protected fun setNavigationIconColor(color: Int) {
        val icon = tbNavigation?.navigationIcon
        if (icon != null) {
            DrawableCompat.setTint(icon, color)
        }
    }

    protected fun setTitleTextColor(textColor: Int) {
        tbNavigation?.setTitleTextColor(textColor)
    }

    protected fun fitSystemWindows() {
        if (tbNavigation != null) {
            val statusBarHeight = StatusBarHelper.getStatusBarHeight(this)
            tbNavigation?.setPadding(tbNavigation?.paddingLeft!!, statusBarHeight, tbNavigation?.paddingRight!!,
                tbNavigation?.paddingBottom!!)
            val height = tbNavigation?.layoutParams?.height
            tbNavigation?.layoutParams?.height = height?.plus(statusBarHeight)
            StatusBarHelper.setStatusBarTransparent(this)
        }
    }

    protected fun addMoreView() {
        //获取windowphone下的decorView
        val decorView = window.decorView as ViewGroup
        val count = decorView.childCount;
        //判断是否已经添加了statusBarView
        if (count > 0 && decorView.getChildAt(count - 1) is TextView) {
            decorView.getChildAt(count - 1).setBackgroundColor(Color.parseColor("#3fbe99"));
        } else {
            //新建一个和状态栏高宽的view
            val statusView = StatusBarHelper.createStatusBarView(this)
            decorView.addView(statusView)
        }
        val rootView = (findViewById<ViewGroup>(android.R.id.content)).getChildAt(0) as ViewGroup
        //rootview不会为状态栏留出状态栏空间
//        rootView.fitsSystemWindows=true
        ViewCompat.setFitsSystemWindows(rootView, true);
        rootView.clipToPadding = true
    }

    protected fun removeView() {
        val decorView = window.decorView as ViewGroup
        val count = decorView.childCount
        if (count > 0 && decorView.getChildAt(count - 1) is TextView) {
            decorView.removeViewAt(count - 1)
        }
//        val rootView = (findViewById<ViewGroup>(android.R.id.content)).getChildAt(0) as ViewGroup
//        ViewCompat.setFitsSystemWindows(rootView, false)
//        rootView.clipToPadding = false
    }


    private fun initToolBar() {
        if (tbNavigation == null) {
            return
        }
        setSupportActionBar(tbNavigation)
        val supportActionBar = supportActionBar
        if (!isNormal()) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            val black = ContextCompat.getColor(this, R.color.color_333333)
            setTitleTextColor(black)
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }


    protected fun setRightButtonText(content: String) {
        if (tvRight?.visibility == View.GONE) {
            tvRight?.visibility = View.VISIBLE
        }
        tvRight?.text = content
    }

    fun setSearchIconVisibility(boolean: Boolean) {
        if (boolean) {
            if (ivSearch?.visibility == View.GONE) {
                ivSearch?.visibility = View.VISIBLE
            }
        } else {
            ivSearch?.visibility = View.GONE
        }
    }

    protected fun getSearchIcon(): ImageView? {
        if (ivSearch?.visibility == View.GONE) {
            ivSearch?.visibility = View.VISIBLE
        }
        return ivSearch
    }

    protected fun searchIconOpen() {
        getSearchIcon()?.setOnClickListener {
            searchToolBackDisplay()
        }
    }


    protected fun searchToolBackDisplay() {
        ivSearch?.apply {
            searchToolbar?.display(x + (width / 2),
                y + (height / 2))
        }
    }

    protected fun setTextRightVisible(isExpand: Boolean) {
        if (isExpand) {
            tvRight?.visibility = View.GONE
        } else {
            if (!tvRight?.text.isNullOrEmpty()) {
                tvRight?.visibility = View.VISIBLE
            }
        }
    }

    protected fun getTextRightView(): TextView? {
        return tvRight
    }

    protected fun setNavigationBarColor(@ColorInt color: Int) {
        tbNavigation?.setBackgroundColor(color)
    }

    protected fun setNavigationBackground(drawable: Drawable) {
        tbNavigation?.background = drawable
    }

    protected fun setToolbarVisible(visible: Boolean) {
        tbNavigation?.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun setContentView(@LayoutRes layoutResID: Int) {
        val content = layoutInflater.inflate(layoutResID, null)
        setContentView(content)

        if (!isNormal()) {
            if (isImmersionBar()) {
                initImmersionBar()
            }
        }
        setStatusBarDarkMode(true)
        initView()
    }

    protected fun getTitleContent2(): TextView? {
        if (tvTitleContent2?.visibility == View.GONE) {
            tvTitleContent2?.visibility = View.VISIBLE
        }
        return tvTitleContent2
    }

    protected fun getRightImage(): ImageView? {
        if (ivRightImage?.visibility == View.GONE) {
            ivRightImage?.visibility = View.VISIBLE
        }
        return ivRightImage
    }

    open var tbNavigation: Toolbar? = null
    private var content_container: FrameLayout? = null
    private var tvRight: TextView? = null
    private var tvTitleContent2: TextView? = null
    var ivChatAvatar: ImageView? = null
    private var ivRightImage: ImageView? = null
    private var tvTitleContent: TextView? = null
    var appBarLayout: AppBarLayout? = null
    private var vsPromptViewStub: ViewStub? = null
    private var llTitleContent: LinearLayout? = null
    private var ivSearch: ImageView? = null
    var searchToolbar: SearchToolbar? = null
    var selectedAlbum: TextView? = null
    var actionBack: ImageButton? = null

    override fun setContentView(view: View?) {
        val layout: View?
        @LayoutRes
        val layoutResID: Int
        if (isNormal()) {
            layoutResID = R.layout.activity_toolbar_normal
            layout = layoutInflater.inflate(layoutResID, null)
            ivChatAvatar = layout?.findViewById(R.id.ivAvatar)
            llTitleContent = layout?.findViewById(R.id.llTitleContent)
            tvTitleContent2 = layout?.findViewById(R.id.tvTitleContent2)
            tvTitleContent = layout?.findViewById(R.id.tvTitleContent)
//            appBarLayout = layout?.findViewById(R.id.appBarLayout)//TODO
            ivRightImage = layout?.findViewById(R.id.ivRightImage)
            actionBack = layout?.findViewById(R.id.actionBack)
        } else {
            layoutResID = R.layout.activity_toolbar
            layout = layoutInflater.inflate(layoutResID, null)
            searchToolbar = layout?.findViewById(R.id.search_toolbar)
            selectedAlbum = layout?.findViewById(R.id.selected_album)
        }
        vsPromptViewStub = layout?.findViewById(R.id.vsPromptViewStub)
        tbNavigation = layout?.findViewById(R.id.tbNavigation)
        setBorderEnable(true)
        content_container = layout?.findViewById(R.id.content_container)
        tvRight = layout?.findViewById(R.id.tvRight)
        ivSearch = layout?.findViewById(R.id.ivSearch)
        content_container?.addView(view)
        super.setContentView(layout)
        initToolBar()
        setTitle(" ")
    }

    fun toggleNavigationColor(b: Boolean) {
        if (b) {
            immersionBar {
                statusBarColor(R.color.white)
                statusBarDarkFont(true)
            }
        } else {
            immersionBar {
                statusBarColor(R.color.colorPrimaryDark)
                statusBarDarkFont(true)
            }
        }
    }

    protected fun setContentInsetStartWithNavigation() {
        tbNavigation?.contentInsetStartWithNavigation = 0
    }

    protected fun hideElevation() {
        tbNavigation?.elevation = 0F
        setBorderEnable(false)
    }

    protected fun setDisplayHomeAsUpEnabled(b: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(b)
    }

    protected fun getBadgeView(): View? {
//        val f = tbNavigation?.javaClass?.getDeclaredField("mNavButtonView")
//        f?.isAccessible = true
        return badgeView
    }

    protected fun loadAvatar(url: String?) {
//        if (!url.isNullOrEmpty()) {
        if (ivChatAvatar?.visibility == View.GONE) {
            ivChatAvatar?.visibility = View.VISIBLE
        }
        ivChatAvatar?.loadAvatar(url)
//        }
    }

    protected fun loadAvatar(resId: Int?) {
        if (ivChatAvatar?.visibility == View.GONE) {
            ivChatAvatar?.visibility = View.VISIBLE
        }
        ivChatAvatar?.loadAvatar(resId)
    }

    protected fun getChatAvatar(): ImageView? {
        if (ivChatAvatar?.visibility == View.GONE) {
            ivChatAvatar?.visibility = View.VISIBLE
        }
        return ivChatAvatar
    }

    protected fun getTitleContent(): TextView? {
        tvTitleContent?.visibility = View.VISIBLE
        return tvTitleContent
    }

    protected fun getLlTitleContent(): LinearLayout? {
        return llTitleContent
    }

    protected fun setTitleContentRightDrawable(id: Int?) {
        val selectDrawable = if (id == null) {
            null
        } else {
            getDrawable(id)
        }
        selectDrawable?.bounds = Rect(0, 0, selectDrawable?.minimumWidth
            ?: 0, selectDrawable?.minimumHeight ?: 0)

        tvTitleContent?.setCompoundDrawables(null, null, selectDrawable, null)
        tvTitleContent?.compoundDrawablePadding = DensityHelper.dp2px(this, 10F)
    }

    protected fun setRightButtonClickEnable(enable: Boolean) {
        tvRight?.enable(enable)
    }

    protected fun setBorderEnable(b: Boolean) {
        tbNavigation?.stateListAnimator = AnimatorInflater.loadStateListAnimator(this, if (b) R.animator.appbar_elevation_dp2 else R.animator.appbar_elevation_dp0)
    }

    private fun setBackgroundImageDrawable(imageView: ForegroundImageView?, drawable: Drawable) {
        imageView?.setImageDrawable(drawable)
    }

    private fun setBackgroundImageResource(imageView: ForegroundImageView?, @DrawableRes res: Int) {
        imageView?.setImageResource(res)
    }

    private fun setBackgroundColor(imageView: ForegroundImageView?, @ColorInt color: Int) {
        imageView?.setImageDrawable(ColorDrawable(color))
    }

    fun initNormalConfig() {
        setTitleTextColor(ContextCompat.getColor(this, android.R.color.black))
        setNavigationBarColor(ContextCompat.getColor(this, android.R.color.black))
        setFullScreenAndBlackTextMode()
    }
    protected fun initSearchView(searchView: SearchView?) {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView?.findViewById<View>(androidx.appcompat.R.id.submit_area)?.background = null
        searchView?.findViewById<View>(androidx.appcompat.R.id.search_plate)?.background = null
        searchView?.setSearchableInfo(searchManager.getSearchableInfo(componentName))
//        searchView?.findViewById<ImageView>(androidx.appcompat.R.id.search_button)?.setColorFilter(
//            ContextCompat.getColor(this,R.color.green))
    }
    override fun onBackPressed() {
        if (searchToolbar?.isVisible == true) {
            searchToolbar?.collapse()
            return
        }
        super.onBackPressed()
    }

}