package com.yumeng.libbaseProject.view.emoji

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.vanniktech.emoji.EmojiView
import com.vanniktech.emoji.RecentEmojiManager
import com.vanniktech.emoji.VariantEmojiManager
import com.vanniktech.emoji.listeners.OnEmojiBackspaceClickListener
import com.vanniktech.emoji.listeners.OnEmojiClickListener
import com.yumeng.libbaseProject.R
import com.yumeng.libcommonview.theme.Theme

import kotlinx.android.synthetic.main.widget_emoji_layout.view.*

class MyEmojiView(context: Context?, attrs: AttributeSet? = null) :
        LinearLayout(context, attrs) {

    private var recentManager: RecentEmojiManager? = null
    private var variantManager: VariantEmojiManager? = null

    init {
        initView()
        recentManager = RecentEmojiManager(context!!)
        variantManager = VariantEmojiManager(context!!)
    }

    private fun initView() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.widget_emoji_layout, this)

    }

    fun getRecent(): RecentEmojiManager {
        return recentManager!!
    }

    fun getVariant(): VariantEmojiManager {
        return variantManager!!
    }

    fun setView(onEmojiClickListener: OnEmojiClickListener, onEmojiBackspaceClickListener: OnEmojiBackspaceClickListener) {
        val emojiView = EmojiView(
                context,
                onEmojiClickListener,
                null,
                recentManager!!,
                variantManager!!,
                Color.parseColor("#ffffff"),
                Color.parseColor("#000000"),
                Color.parseColor("#000000"),
                Theme.getThemeColor(),
                null
        )
        emojiView.setOnEmojiBackspaceClickListener(onEmojiBackspaceClickListener)

        val params =
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        llEmoji.addView(emojiView, params)
    }

}