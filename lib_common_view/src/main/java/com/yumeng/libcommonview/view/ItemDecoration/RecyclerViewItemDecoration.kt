package com.yumeng.libcommonview.view.ItemDecoration

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.LinearLayout

import androidx.annotation.ColorInt
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yumeng.libcommonview.view.ItemDecoration.RVItemDecorationConst

import java.util.regex.Pattern

/**
 * RecycleView item decoration
 * Created by Eminem Lo on 24/11/15.
 * Email arjinmc@hotmail.com
 * 如类名
 * 比较全面的 自定义ItemDecoration
 */
class RecyclerViewItemDecoration internal constructor() : RecyclerView.ItemDecoration() {
    /**
     * spacing for grid mode
     */
    var mGridHorizontalSpacing: Int = 0
    /**
     * image resource id for R.java
     */
    private var mDrawable: Drawable? = null
    /**
     * decoration color
     */
    private var mColor = Color.parseColor(DEFAULT_COLOR)
    /**
     * decoration thickness
     */
    private var mThickness: Int = 0
    /**
     * decoration dash with
     */
    private var mDashWidth = 0
    /**
     * decoration dash gap
     */
    private var mDashGap = 0
    private var mFirstLineVisible: Boolean = false
    private var mLastLineVisible: Boolean = false
    private var mPaddingStart = 0
    private var mPaddingEnd = 0
    /**
     * border line for grid mode
     */
    private var mGridLeftVisible: Boolean = false
    private var mGridRightVisible: Boolean = false
    private var mGridTopVisible: Boolean = false
    private var mGridBottomVisible: Boolean = false
    private var mGridVerticalSpacing: Int = 0
    /**
     * direction mode for decoration
     */
    private var mMode: Int = 0

    private var mPaint: Paint? = null

    //    private Bitmap mBmp;
    private var mNinePatch: NinePatch? = null
    /**
     * choose the real thickness for image or thickness
     */
    private var mCurrentThickness: Int = 0
    /**
     * sign for if the resource image is a ninepatch image
     */
    private var hasNinePatch = false
    /**
     * sign for if has get the parent RecyclerView LayoutManager mode
     */
    private var hasGetParentLayoutMode = false
    private var mContext: Context? = null

    private val isPureLine: Boolean
        get() = mDashGap == 0 && mDashWidth == 0

    fun setParams(context: Context, params: Param) {

        this.mContext = context

        this.mDrawable = params.mDrawable
        this.mColor = params.color
        this.mThickness = params.thickness
        this.mDashGap = params.dashGap
        this.mDashWidth = params.dashWidth
        this.mPaddingStart = params.paddingStart
        this.mPaddingEnd = params.paddingEnd
        this.mFirstLineVisible = params.firstLineVisible
        this.mLastLineVisible = params.lastLineVisible
        this.mGridLeftVisible = params.gridLeftVisible
        this.mGridRightVisible = params.gridRightVisible
        this.mGridTopVisible = params.gridTopVisible
        this.mGridBottomVisible = params.gridBottomVisible
        this.mGridHorizontalSpacing = params.gridHorizontalSpacing
        this.mGridVerticalSpacing = params.gridVerticalSpacing

    }

    private fun initPaint() {

        if (mDrawable != null && mDrawable is BitmapDrawable) {
            val bitmap = (mDrawable as BitmapDrawable).bitmap
            if (bitmap.ninePatchChunk != null) {
                hasNinePatch = true
                mNinePatch = NinePatch(bitmap, bitmap.ninePatchChunk, null)
            }

        }

        if (mMode == RVItemDecorationConst.MODE_HORIZONTAL)
            mCurrentThickness = if (mThickness == 0) mDrawable!!.intrinsicHeight else mThickness
        if (mMode == RVItemDecorationConst.MODE_VERTICAL)
            mCurrentThickness = if (mThickness == 0) mDrawable!!.intrinsicWidth else mThickness

        mPaint = Paint()
        mPaint!!.color = mColor
        mPaint!!.style = Paint.Style.STROKE
        mPaint!!.strokeWidth = mThickness.toFloat()
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {

        if (parent.childCount == 0) return
        mPaint!!.color = mColor
        when (mMode) {
            RVItemDecorationConst.MODE_HORIZONTAL -> drawHorizontal(c, parent)
            RVItemDecorationConst.MODE_VERTICAL -> drawVertical(c, parent)
            RVItemDecorationConst.MODE_GRID -> drawGrid(c, parent)
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        if (!hasGetParentLayoutMode) {
            compatibleWithLayoutManager(parent)
            hasGetParentLayoutMode = true
        }
        val viewPosition = parent.getChildAdapterPosition(view)

        if (mMode == RVItemDecorationConst.MODE_HORIZONTAL) {

            if (!(!mLastLineVisible && viewPosition == parent.adapter?.itemCount ?: 0 - 1)) {
                if (mDrawable != null) {
                    outRect.set(0, 0, 0, mCurrentThickness)
                } else {
                    outRect.set(0, 0, 0, mThickness)
                }
            }

            if (mFirstLineVisible && viewPosition == 0) {
                if (mDrawable != null) {
                    outRect.set(0, mCurrentThickness, 0, mCurrentThickness)
                } else {
                    outRect.set(0, mThickness, 0, mThickness)
                }
            }

        } else if (mMode == RVItemDecorationConst.MODE_VERTICAL) {
            if (!(!mLastLineVisible && viewPosition == parent.adapter?.itemCount ?: 0 - 1)) {
                if (mDrawable != null) {
                    outRect.set(0, 0, mCurrentThickness, 0)
                } else {
                    outRect.set(0, 0, mThickness, 0)
                }
            }
            if (mFirstLineVisible && viewPosition == 0) {
                if (mDrawable != null) {
                    outRect.set(mCurrentThickness, 0, mCurrentThickness, 0)
                } else {
                    outRect.set(mThickness, 0, mThickness, 0)
                }
            }

        } else if (mMode == RVItemDecorationConst.MODE_GRID) {
            val columnSize = (parent.layoutManager as GridLayoutManager).spanCount
            val itemSize = parent.adapter?.itemCount ?: 0

            if (mDrawable != null) {
                if (hasNinePatch) {
                    setGridOffsets(outRect, viewPosition, columnSize, itemSize, 0)
                } else {
                    setGridOffsets(outRect, viewPosition, columnSize, itemSize, 1)
                }
            } else {
                setGridOffsets(outRect, viewPosition, columnSize, itemSize, -1)
            }
        }

    }

    /**
     * draw horizontal decoration
     *
     * @param c
     * @param parent
     */
    private fun drawHorizontal(c: Canvas, parent: RecyclerView) {
        val childrenCount = parent.childCount
        if (mDrawable != null) {

            if (mFirstLineVisible) {
                val childView = parent.getChildAt(0)
                val myY = childView.top

                if (hasNinePatch) {
                    val rect = Rect(
                        mPaddingStart,
                        myY - mCurrentThickness,
                        parent.width - mPaddingEnd,
                        myY
                    )
                    mNinePatch!!.draw(c, rect)
                } else {
                    mDrawable!!.setBounds(
                        mPaddingStart,
                        myY - mCurrentThickness,
                        mPaddingStart + mDrawable!!.intrinsicWidth,
                        myY - mCurrentThickness + mDrawable!!.intrinsicHeight
                    )
                    mDrawable!!.draw(c)
                    //                    c.drawBitmap(mBmp, mPaddingStart, myY - mCurrentThickness, mPaint);
                }
            }

            for (i in 0 until childrenCount) {
                if (!mLastLineVisible && i == childrenCount - 1)
                    break
                val childView = parent.getChildAt(i)
                val myY = childView.bottom

                if (hasNinePatch) {
                    val rect = Rect(
                        mPaddingStart,
                        myY,
                        parent.width - mPaddingEnd,
                        myY + mCurrentThickness
                    )
                    mNinePatch!!.draw(c, rect)
                } else {
                    mDrawable!!.setBounds(
                        mPaddingStart,
                        myY,
                        mPaddingStart + mDrawable!!.intrinsicWidth,
                        myY + mDrawable!!.intrinsicHeight
                    )
                    mDrawable!!.draw(c)
                    //                    c.drawBitmap(mBmp, mPaddingStart, myY, mPaint);
                }

            }

        } else {

            val isPureLine = isPureLine
            if (!isPureLine) {
                val effects = DashPathEffect(
                    floatArrayOf(0f, 0f, mDashWidth.toFloat(), mThickness.toFloat()),
                    mDashGap.toFloat()
                )
                mPaint!!.pathEffect = effects
            }
            if (mFirstLineVisible) {
                val childView = parent.getChildAt(0)
                val myY = childView.top - mThickness / 2

                val path = Path()
                path.moveTo(mPaddingStart.toFloat(), myY.toFloat())
                path.lineTo((parent.width - mPaddingEnd).toFloat(), myY.toFloat())
                c.drawPath(path, mPaint!!)
            }

            for (i in 0 until childrenCount) {
                if (!mLastLineVisible && i == childrenCount - 1)
                    break
                val childView = parent.getChildAt(i)
                val myY = childView.bottom + mThickness / 2

                val path = Path()
                path.moveTo(mPaddingStart.toFloat(), myY.toFloat())
                path.lineTo((parent.width - mPaddingEnd).toFloat(), myY.toFloat())
                c.drawPath(path, mPaint!!)

            }

        }
    }

    /**
     * draw vertival decoration
     *
     * @param c
     * @param parent
     */
    private fun drawVertical(c: Canvas, parent: RecyclerView) {
        val childrenCount = parent.childCount
        if (mDrawable != null) {

            if (mFirstLineVisible) {
                val childView = parent.getChildAt(0)
                val myX = childView.left
                if (hasNinePatch) {
                    val rect = Rect(
                        myX - mCurrentThickness,
                        mPaddingStart,
                        myX,
                        parent.height - mPaddingEnd
                    )
                    mNinePatch!!.draw(c, rect)
                } else {
                    mDrawable!!.setBounds(
                        myX - mCurrentThickness,
                        mPaddingStart,
                        myX - mCurrentThickness + mDrawable!!.intrinsicWidth,
                        mPaddingStart + mDrawable!!.intrinsicHeight
                    )
                    mDrawable!!.draw(c)
                    //                    c.drawBitmap(mBmp, myX - mCurrentThickness, mPaddingStart, mPaint);
                }
            }
            for (i in 0 until childrenCount) {
                if (!mLastLineVisible && i == childrenCount - 1)
                    break
                val childView = parent.getChildAt(i)
                val myX = childView.right
                if (hasNinePatch) {
                    val rect = Rect(
                        myX,
                        mPaddingStart,
                        myX + mCurrentThickness,
                        parent.height - mPaddingEnd
                    )
                    mNinePatch!!.draw(c, rect)
                } else {
                    val height = childView.height
                    mDrawable!!.setBounds(
                        myX,
                        mPaddingStart + height / 2 - mDrawable!!.intrinsicHeight / 2,
                        myX + mDrawable!!.intrinsicWidth,
                        mPaddingStart + height / 2 - mDrawable!!.intrinsicHeight / 2 + mDrawable!!.intrinsicHeight
                    )
                    mDrawable!!.draw(c)
                    //                    c.drawBitmap(mBmp, myX, mPaddingStart, mPaint);
                }
            }

        } else {

            val isPureLine = isPureLine
            if (!isPureLine) {
                val effects = DashPathEffect(
                    floatArrayOf(0f, 0f, mDashWidth.toFloat(), mThickness.toFloat()),
                    mDashGap.toFloat()
                )
                mPaint!!.pathEffect = effects
            }

            if (mFirstLineVisible) {
                val childView = parent.getChildAt(0)
                val myX = childView.left - mThickness / 2
                val path = Path()
                path.moveTo(myX.toFloat(), mPaddingStart.toFloat())
                path.lineTo(myX.toFloat(), (parent.height - mPaddingEnd).toFloat())
                c.drawPath(path, mPaint!!)
            }

            for (i in 0 until childrenCount) {
                if (!mLastLineVisible && i == childrenCount - 1)
                    break
                val childView = parent.getChildAt(i)
                val myX = childView.right + mThickness / 2
                val path = Path()
                path.moveTo(myX.toFloat(), mPaddingStart.toFloat())
                path.lineTo(myX.toFloat(), (parent.height - mPaddingEnd).toFloat())
                c.drawPath(path, mPaint!!)

            }
        }
    }

    /**
     * draw grid decoration
     *
     * @param c
     * @param parent
     */
    private fun drawGrid(c: Canvas, parent: RecyclerView) {

        val childrenCount = parent.childCount
        val columnSize = (parent.layoutManager as GridLayoutManager).spanCount
        val itemSize = parent.adapter?.itemCount ?: 0

        if (mDrawable != null) {

            mPaint!!.strokeWidth = mThickness.toFloat()

            for (i in 0 until childrenCount) {
                val childView = parent.getChildAt(i)
                val myT = childView.top
                val myB = childView.bottom
                val myL = childView.left
                val myR = childView.right
                val viewPosition = parent.getChildLayoutPosition(childView)

                //when columnSize/spanCount is One
                if (columnSize == 1) {
                    if (isFirstGridRow(viewPosition, columnSize)) {

                        if (mGridLeftVisible) {
                            if (hasNinePatch) {
                                val rect = Rect(myL - mThickness, myT, myL, myB)
                                mNinePatch!!.draw(c, rect)
                            } else {
                                mDrawable!!.setBounds(
                                    myL - mThickness,
                                    myT,
                                    myL - mThickness + mDrawable!!.intrinsicWidth,
                                    myT + mDrawable!!.intrinsicHeight
                                )
                                mDrawable!!.draw(c)
                                //                                c.drawBitmap(mBmp, myL - mThickness, myT, mPaint);
                            }
                        }
                        if (mGridTopVisible) {
                            if (hasNinePatch) {
                                val rect =
                                    Rect(myL - mThickness, myT - mThickness, myR + mThickness, myT)
                                mNinePatch!!.draw(c, rect)
                            } else {
                                mDrawable!!.setBounds(
                                    myL - mThickness,
                                    myT,
                                    myL - mThickness + mDrawable!!.intrinsicWidth,
                                    myT + mDrawable!!.intrinsicHeight
                                )
                                mDrawable!!.draw(c)
                                //                                c.drawBitmap(mBmp, myL - mThickness, myT - mThickness, mPaint);
                            }
                        }
                        if (mGridRightVisible) {
                            if (hasNinePatch) {
                                val rect = Rect(myR, myT, myR + mThickness, myB)
                                mNinePatch!!.draw(c, rect)
                            } else {
                                mDrawable!!.setBounds(
                                    myR,
                                    myT,
                                    myR + mDrawable!!.intrinsicWidth,
                                    myT + mDrawable!!.intrinsicHeight
                                )
                                mDrawable!!.draw(c)
                                //                                c.drawBitmap(mBmp, myR, myT, mPaint);
                            }
                        }

                        //not first row
                    } else {

                        if (mGridLeftVisible) {
                            if (hasNinePatch) {
                                val rect = Rect(myL - mThickness, myT - mThickness, myL, myB)
                                mNinePatch!!.draw(c, rect)
                            } else {
                                mDrawable!!.setBounds(
                                    myL - mThickness,
                                    myT - mThickness,
                                    myL - mThickness + mDrawable!!.intrinsicWidth,
                                    myT - mThickness + mDrawable!!.intrinsicHeight
                                )
                                mDrawable!!.draw(c)
                                //                                c.drawBitmap(mBmp
                                //                                        , myL - mThickness
                                //                                        , myT - mThickness
                                //                                        , mPaint);
                            }
                        }

                        if (mGridRightVisible) {
                            if (hasNinePatch) {
                                val rect = Rect(myR, myT - mThickness, myR + mThickness, myB)
                                mNinePatch!!.draw(c, rect)
                            } else {
                                mDrawable!!.setBounds(
                                    myR,
                                    myT - mThickness,
                                    myR + mDrawable!!.intrinsicWidth,
                                    myT - mThickness + mDrawable!!.intrinsicHeight
                                )
                                mDrawable!!.draw(c)
                                //                                c.drawBitmap(mBmp, myR, myT - mThickness, mPaint);
                            }
                        }

                    }

                    if (isLastGridRow(viewPosition, itemSize, columnSize)) {
                        if (mGridBottomVisible) {
                            if (hasNinePatch) {
                                val rect =
                                    Rect(myL - mThickness, myB, myR + mThickness, myB + mThickness)
                                mNinePatch!!.draw(c, rect)
                            } else {
                                mDrawable!!.setBounds(
                                    myL - mThickness,
                                    myB,
                                    myL - mThickness + mDrawable!!.intrinsicWidth,
                                    myB + mDrawable!!.intrinsicHeight
                                )
                                mDrawable!!.draw(c)
                                //                                c.drawBitmap(mBmp, myL - mThickness, myB, mPaint);
                            }
                        }
                    } else {
                        if (hasNinePatch) {
                            val rect = Rect(myL, myB, myR + mThickness, myB + mThickness)
                            mNinePatch!!.draw(c, rect)
                        } else {
                            mDrawable!!.setBounds(
                                myL,
                                myB,
                                myL + mDrawable!!.intrinsicWidth,
                                myB + mDrawable!!.intrinsicHeight
                            )
                            mDrawable!!.draw(c)
                            //                            c.drawBitmap(mBmp
                            //                                    , myL
                            //                                    , myB
                            //                                    , mPaint);
                        }
                    }

                    //when columnSize/spanCount is Not One
                } else {
                    if (isFirstGridColumns(viewPosition, columnSize) && isFirstGridRow(
                            viewPosition,
                            columnSize
                        )
                    ) {

                        if (mGridLeftVisible) {
                            if (hasNinePatch) {
                                val rect = Rect(myL - mThickness, myT - mThickness, myL, myB)
                                mNinePatch!!.draw(c, rect)
                            } else {
                                mDrawable!!.setBounds(
                                    myL - mThickness,
                                    myT - mThickness,
                                    myL - mThickness + mDrawable!!.intrinsicWidth,
                                    myT - mThickness + mDrawable!!.intrinsicHeight
                                )
                                mDrawable!!.draw(c)
                                //                                c.drawBitmap(mBmp, myL - mThickness, myT - mThickness, mPaint);
                            }
                        }

                        if (mGridTopVisible) {
                            if (hasNinePatch) {
                                val rect = Rect(myL, myT - mThickness, myR, myT)
                                mNinePatch!!.draw(c, rect)
                            } else {
                                mDrawable!!.setBounds(
                                    myL,
                                    myT - mThickness,
                                    myL + mDrawable!!.intrinsicWidth,
                                    myT - mThickness + mDrawable!!.intrinsicHeight
                                )
                                mDrawable!!.draw(c)
                                //                                c.drawBitmap(mBmp, myL, myT - mThickness, mPaint);
                            }
                        }

                        if (itemSize == 1) {
                            if (mGridRightVisible) {
                                if (hasNinePatch) {
                                    val rect = Rect(myR, myT - mThickness, myR + mThickness, myB)
                                    mNinePatch!!.draw(c, rect)
                                } else {
                                    mDrawable!!.setBounds(
                                        myR,
                                        myT - mThickness,
                                        myR + mDrawable!!.intrinsicWidth,
                                        myT - mThickness + mDrawable!!.intrinsicHeight
                                    )
                                    mDrawable!!.draw(c)
                                    //                                    c.drawBitmap(mBmp, myR, myT - mThickness, mPaint);
                                }
                            }
                        } else {
                            if (hasNinePatch) {
                                val rect = Rect(myR, myT - mThickness, myR + mThickness, myB)
                                mNinePatch!!.draw(c, rect)
                            } else {
                                mDrawable!!.setBounds(
                                    myR,
                                    myT - mThickness,
                                    myR + mDrawable!!.intrinsicWidth,
                                    myT - mThickness + mDrawable!!.intrinsicHeight
                                )
                                mDrawable!!.draw(c)
                                //                                c.drawBitmap(mBmp
                                //                                        , myR
                                //                                        , myT - mThickness
                                //                                        , mPaint);
                            }
                        }

                    } else if (isFirstGridRow(viewPosition, columnSize)) {

                        if (mGridTopVisible) {
                            if (hasNinePatch) {
                                val rect = Rect(myL, myT - mThickness, myR, myT)
                                mNinePatch!!.draw(c, rect)
                            } else {
                                mDrawable!!.setBounds(
                                    myL,
                                    myT - mThickness,
                                    myL + mDrawable!!.intrinsicWidth,
                                    myT - mThickness + mDrawable!!.intrinsicHeight
                                )
                                mDrawable!!.draw(c)
                                //                                c.drawBitmap(mBmp, myL, myT - mThickness, mPaint);
                            }
                        }

                        if (isLastGridColumns(viewPosition, itemSize, columnSize)) {
                            if (mGridRightVisible) {
                                if (hasNinePatch) {
                                    val rect = Rect(myR, myT - mThickness, myR + mThickness, myB)
                                    mNinePatch!!.draw(c, rect)
                                } else {
                                    mDrawable!!.setBounds(
                                        myR,
                                        myT - mThickness,
                                        myR + mDrawable!!.intrinsicWidth,
                                        myT - mThickness + mDrawable!!.intrinsicHeight
                                    )
                                    mDrawable!!.draw(c)
                                    //                                    c.drawBitmap(mBmp, myR, myT - mThickness, mPaint);
                                }
                            }
                        } else {
                            if (hasNinePatch) {
                                val rect = Rect(myR, myT - mThickness, myR + mThickness, myB)
                                mNinePatch!!.draw(c, rect)
                            } else {
                                mDrawable!!.setBounds(
                                    myR,
                                    myT - mDrawable!!.intrinsicHeight,
                                    myR + mDrawable!!.intrinsicWidth,
                                    myT
                                )
                                mDrawable!!.draw(c)
                                //                                c.drawBitmap(mBmp
                                //                                        , myR
                                //                                        , myT - mBmp.getHeight()
                                //                                        , mPaint);
                            }
                        }

                    } else if (isFirstGridColumns(viewPosition, columnSize)) {
                        if (mGridLeftVisible) {
                            if (hasNinePatch) {
                                val rect = Rect(myL - mThickness, myT, myL, myB)
                                mNinePatch!!.draw(c, rect)
                            } else {
                                mDrawable!!.setBounds(
                                    myL - mThickness,
                                    myT,
                                    myL - mThickness + mDrawable!!.intrinsicWidth,
                                    myT + mDrawable!!.intrinsicHeight
                                )
                                mDrawable!!.draw(c)
                                //                                c.drawBitmap(mBmp, myL - mThickness, myT, mPaint);
                            }
                        }

                        if (hasNinePatch) {
                            val rect = Rect(myR, myT, myR + mThickness, myB)
                            mNinePatch!!.draw(c, rect)
                        } else {
                            mDrawable!!.setBounds(
                                myR,
                                myT,
                                myR + mDrawable!!.intrinsicWidth,
                                myT + mDrawable!!.intrinsicHeight
                            )
                            mDrawable!!.draw(c)
                            //                            c.drawBitmap(mBmp
                            //                                    , myR
                            //                                    , myT
                            //                                    , mPaint);
                        }
                    } else {
                        if (isLastGridColumns(viewPosition, itemSize, columnSize)) {
                            if (mGridRightVisible) {
                                if (hasNinePatch) {
                                    val rect = Rect(myR, myT - mThickness, myR + mThickness, myB)
                                    mNinePatch!!.draw(c, rect)
                                } else {
                                    mDrawable!!.setBounds(
                                        myR,
                                        myT - mDrawable!!.intrinsicHeight,
                                        myR + mDrawable!!.intrinsicWidth,
                                        myT
                                    )
                                    mDrawable!!.draw(c)
                                    //                                    c.drawBitmap(mBmp
                                    //                                            , myR
                                    //                                            , myT - mBmp.getHeight()
                                    //                                            , mPaint);
                                }
                            }
                        } else {
                            if (hasNinePatch) {
                                val rect = Rect(myR, myT, myR + mThickness, myB)
                                mNinePatch!!.draw(c, rect)
                            } else {
                                mDrawable!!.setBounds(
                                    myR,
                                    myT,
                                    myR + mDrawable!!.intrinsicWidth,
                                    myT + mDrawable!!.intrinsicHeight
                                )
                                mDrawable!!.draw(c)
                                //                                c.drawBitmap(mBmp
                                //                                        , myR
                                //                                        , myT
                                //                                        , mPaint);
                            }
                        }
                    }

                    //bottom line
                    if (isLastGridRow(viewPosition, itemSize, columnSize)) {
                        if (mGridBottomVisible) {
                            if (itemSize == 1) {
                                if (hasNinePatch) {
                                    val rect = Rect(
                                        myL - mThickness,
                                        myB,
                                        myR + mThickness,
                                        myB + mThickness
                                    )
                                    mNinePatch!!.draw(c, rect)
                                } else {
                                    mDrawable!!.setBounds(
                                        myL - mThickness,
                                        myB,
                                        myL - mThickness + mDrawable!!.intrinsicWidth,
                                        myB + mDrawable!!.intrinsicHeight
                                    )
                                    mDrawable!!.draw(c)
                                    //                                    c.drawBitmap(mBmp
                                    //                                            , myL - mThickness
                                    //                                            , myB
                                    //                                            , mPaint);
                                }
                            } else if (isLastGridColumns(viewPosition, itemSize, columnSize)) {
                                if (hasNinePatch) {
                                    val rect = Rect(
                                        myL - mThickness,
                                        myB,
                                        myR + mThickness,
                                        myB + mThickness
                                    )
                                    mNinePatch!!.draw(c, rect)
                                } else {
                                    mDrawable!!.setBounds(
                                        myL - mThickness,
                                        myB + mThickness / 2,
                                        myL - mThickness + mDrawable!!.intrinsicWidth,
                                        myB + mThickness / 2 + mDrawable!!.intrinsicHeight
                                    )
                                    mDrawable!!.draw(c)
                                    //                                    c.drawBitmap(mBmp
                                    //                                            , myL - mThickness
                                    //                                            , myB + mThickness / 2
                                    //                                            , mPaint);
                                }
                            } else {
                                if (hasNinePatch) {
                                    val rect = Rect(
                                        myL - mThickness,
                                        myB,
                                        myR + if (mGridHorizontalSpacing == 0) mThickness else mGridHorizontalSpacing,
                                        myB + mThickness
                                    )
                                    mNinePatch!!.draw(c, rect)
                                } else {
                                    mDrawable!!.setBounds(
                                        myL - mThickness,
                                        myB,
                                        myL - mThickness + mDrawable!!.intrinsicWidth,
                                        myB + mDrawable!!.intrinsicHeight
                                    )
                                    mDrawable!!.draw(c)
                                    //                                    c.drawBitmap(mBmp
                                    //                                            , myL - mThickness
                                    //                                            , myB, mPaint);
                                }
                            }

                        }
                    } else {
                        if (hasNinePatch) {
                            val rect = Rect(
                                myL - mThickness,
                                myB,
                                myR,
                                myB + if (mGridVerticalSpacing == 0) mThickness else mGridVerticalSpacing
                            )
                            mNinePatch!!.draw(c, rect)
                        } else {
                            mDrawable!!.setBounds(
                                myL - mDrawable!!.intrinsicWidth,
                                myB,
                                myL,
                                myB + mDrawable!!.intrinsicHeight
                            )
                            mDrawable!!.draw(c)
                            //                            c.drawBitmap(mBmp
                            //                                    , myL - mBmp.getWidth()
                            //                                    , myB
                            //                                    , mPaint);
                        }
                    }
                }

            }

        } else {
            if (!isPureLine) {
                val effects = DashPathEffect(
                    floatArrayOf(0f, 0f, mDashWidth.toFloat(), mThickness.toFloat()),
                    mDashGap.toFloat()
                )
                mPaint!!.pathEffect = effects
            }
            for (i in 0 until childrenCount) {
                val childView = parent.getChildAt(i)
                val myT = childView.top
                val myB = childView.bottom
                val myL = childView.left
                val myR = childView.right
                val viewPosition = parent.getChildAdapterPosition(childView)

                //when columnSize/spanCount is One
                if (columnSize == 1) {
                    if (isFirstGridRow(viewPosition, columnSize)) {

                        if (mGridLeftVisible) {
                            mPaint!!.strokeWidth = mThickness.toFloat()
                            val path = Path()
                            path.moveTo((myL - mThickness / 2).toFloat(), myT.toFloat())
                            path.lineTo((myL - mThickness / 2).toFloat(), myB.toFloat())
                            c.drawPath(path, mPaint!!)
                        }
                        if (mGridTopVisible) {
                            mPaint!!.strokeWidth = mThickness.toFloat()
                            val path = Path()
                            path.moveTo(
                                (myL - mThickness).toFloat(),
                                (myT - mThickness / 2).toFloat()
                            )
                            path.lineTo(
                                (myR + mThickness).toFloat(),
                                (myT - mThickness / 2).toFloat()
                            )
                            c.drawPath(path, mPaint!!)
                        }
                        if (mGridRightVisible) {
                            mPaint!!.strokeWidth = mThickness.toFloat()
                            val path = Path()
                            path.moveTo((myR + mThickness / 2).toFloat(), myT.toFloat())
                            path.lineTo((myR + mThickness / 2).toFloat(), myB.toFloat())
                            c.drawPath(path, mPaint!!)
                        }

                        //not first row
                    } else {

                        if (mGridLeftVisible) {
                            mPaint!!.strokeWidth = mThickness.toFloat()
                            val path = Path()
                            path.moveTo(
                                (myL - mThickness / 2).toFloat(),
                                (myT - if (mGridVerticalSpacing == 0) mThickness else mGridVerticalSpacing).toFloat()
                            )
                            path.lineTo((myL - mThickness / 2).toFloat(), myB.toFloat())
                            c.drawPath(path, mPaint!!)
                        }

                        if (mGridRightVisible) {
                            val path = Path()
                            mPaint!!.strokeWidth = mThickness.toFloat()
                            path.moveTo((myR + mThickness / 2).toFloat(), myT.toFloat())
                            path.lineTo((myR + mThickness / 2).toFloat(), myB.toFloat())
                            c.drawPath(path, mPaint!!)
                        }

                    }

                    if (isLastGridRow(viewPosition, itemSize, columnSize)) {
                        if (mGridBottomVisible) {
                            mPaint!!.strokeWidth = mThickness.toFloat()
                            val path = Path()
                            path.moveTo(
                                (myL - mThickness).toFloat(),
                                (myB + mThickness / 2).toFloat()
                            )
                            path.lineTo(
                                (myR + mThickness).toFloat(),
                                (myB + mThickness / 2).toFloat()
                            )
                            c.drawPath(path, mPaint!!)
                        }
                    } else {
                        mPaint!!.strokeWidth = mThickness.toFloat()
                        if (mGridVerticalSpacing != 0) {
                            mPaint!!.strokeWidth = mGridVerticalSpacing.toFloat()
                        }
                        val path = Path()
                        path.moveTo(
                            myL.toFloat(),
                            (myB + (if (mGridVerticalSpacing == 0) mThickness else mGridVerticalSpacing) / 2).toFloat()
                        )
                        path.lineTo(
                            (myR + mThickness).toFloat(),
                            (myB + (if (mGridVerticalSpacing == 0) mThickness else mGridVerticalSpacing) / 2).toFloat()
                        )
                        c.drawPath(path, mPaint!!)
                    }

                    //when columnSize/spanCount is Not One
                } else {
                    if (isFirstGridColumns(viewPosition, columnSize) && isFirstGridRow(
                            viewPosition,
                            columnSize
                        )
                    ) {

                        if (mGridLeftVisible) {
                            mPaint!!.strokeWidth = mThickness.toFloat()
                            val path = Path()
                            path.moveTo(
                                (myL - mThickness / 2).toFloat(),
                                (myT - mThickness).toFloat()
                            )
                            path.lineTo((myL - mThickness / 2).toFloat(), myB.toFloat())
                            c.drawPath(path, mPaint!!)
                        }

                        if (mGridTopVisible) {
                            mPaint!!.strokeWidth = mThickness.toFloat()
                            val path = Path()
                            path.moveTo(myL.toFloat(), (myT - mThickness / 2).toFloat())
                            path.lineTo(myR.toFloat(), (myT - mThickness / 2).toFloat())
                            c.drawPath(path, mPaint!!)

                        }

                        if (itemSize == 1) {
                            if (mGridRightVisible) {
                                mPaint!!.strokeWidth = mThickness.toFloat()
                                val path = Path()
                                path.moveTo(
                                    (myR + mThickness / 2).toFloat(),
                                    (myT - mThickness).toFloat()
                                )
                                path.lineTo((myR + mThickness / 2).toFloat(), myB.toFloat())
                                c.drawPath(path, mPaint!!)
                            }
                        } else {
                            mPaint!!.strokeWidth = mThickness.toFloat()
                            if (mGridHorizontalSpacing != 0) {
                                mPaint!!.strokeWidth = mGridHorizontalSpacing.toFloat()
                            }
                            val path = Path()
                            path.moveTo(
                                (myR + (if (mGridHorizontalSpacing == 0) mThickness else mGridHorizontalSpacing) / 2).toFloat(),
                                (myT - mThickness).toFloat()
                            )
                            path.lineTo(
                                (myR + (if (mGridHorizontalSpacing == 0) mThickness else mGridHorizontalSpacing) / 2).toFloat(),
                                myB.toFloat()
                            )
                            c.drawPath(path, mPaint!!)
                        }

                    } else if (isFirstGridRow(viewPosition, columnSize)) {

                        if (mGridTopVisible) {
                            mPaint!!.strokeWidth = mThickness.toFloat()
                            val path = Path()
                            path.moveTo(myL.toFloat(), (myT - mThickness / 2).toFloat())
                            path.lineTo(myR.toFloat(), (myT - mThickness / 2).toFloat())
                            c.drawPath(path, mPaint!!)

                        }

                        if (isLastGridColumns(viewPosition, itemSize, columnSize)) {
                            mPaint!!.strokeWidth = mThickness.toFloat()
                            if (mGridRightVisible) {

                                var alterY = 0
                                if (isLastSecondGridRowNotDivided(
                                        viewPosition,
                                        itemSize,
                                        columnSize
                                    )
                                ) {
                                    alterY =
                                        if (mGridVerticalSpacing == 0) mThickness else mGridVerticalSpacing
                                }
                                val path = Path()
                                path.moveTo(
                                    (myR + mThickness / 2).toFloat(),
                                    (myT - mThickness).toFloat()
                                )
                                path.lineTo(
                                    (myR + mThickness / 2).toFloat(),
                                    (myB + alterY).toFloat()
                                )
                                c.drawPath(path, mPaint!!)
                            }
                        } else {
                            if (mGridHorizontalSpacing != 0) {
                                mPaint!!.strokeWidth = mGridHorizontalSpacing.toFloat()
                            }
                            val path = Path()
                            path.moveTo(
                                (myR + (if (mGridHorizontalSpacing == 0) mThickness else mGridHorizontalSpacing) / 2).toFloat(),
                                (myT - mThickness).toFloat()
                            )
                            path.lineTo(
                                (myR + (if (mGridHorizontalSpacing == 0) mThickness else mGridHorizontalSpacing) / 2).toFloat(),
                                myB.toFloat()
                            )
                            c.drawPath(path, mPaint!!)
                        }

                    } else if (isFirstGridColumns(viewPosition, columnSize)) {

                        if (mGridLeftVisible) {
                            mPaint!!.strokeWidth = mThickness.toFloat()
                            val path = Path()
                            path.moveTo((myL - mThickness / 2).toFloat(), myT.toFloat())
                            path.lineTo((myL - mThickness / 2).toFloat(), myB.toFloat())
                            c.drawPath(path, mPaint!!)
                        }

                        mPaint!!.strokeWidth = mThickness.toFloat()
                        if (mGridHorizontalSpacing != 0) {
                            mPaint!!.strokeWidth = mGridHorizontalSpacing.toFloat()
                        }
                        val path = Path()
                        path.moveTo(
                            (myR + (if (mGridHorizontalSpacing == 0) mThickness else mGridHorizontalSpacing) / 2).toFloat(),
                            myT.toFloat()
                        )
                        path.lineTo(
                            (myR + (if (mGridHorizontalSpacing == 0) mThickness else mGridHorizontalSpacing) / 2).toFloat(),
                            myB.toFloat()
                        )
                        c.drawPath(path, mPaint!!)

                    } else {

                        mPaint!!.strokeWidth = mThickness.toFloat()

                        if (isLastGridColumns(viewPosition, itemSize, columnSize)) {

                            if (mGridRightVisible) {

                                var alterY = 0
                                if (isLastSecondGridRowNotDivided(
                                        viewPosition,
                                        itemSize,
                                        columnSize
                                    )
                                ) {
                                    alterY =
                                        if (mGridVerticalSpacing == 0) mThickness else mGridVerticalSpacing
                                }
                                val path = Path()
                                path.moveTo(
                                    (myR + mThickness / 2).toFloat(),
                                    (myT - if (mGridVerticalSpacing == 0) mThickness else mGridVerticalSpacing).toFloat()
                                )
                                path.lineTo(
                                    (myR + mThickness / 2).toFloat(),
                                    (myB + alterY).toFloat()
                                )
                                c.drawPath(path, mPaint!!)
                            }
                        } else {
                            if (mGridHorizontalSpacing != 0) {
                                mPaint!!.strokeWidth = mGridHorizontalSpacing.toFloat()
                            }
                            val path = Path()
                            path.moveTo(
                                (myR + (if (mGridHorizontalSpacing == 0) mThickness else mGridHorizontalSpacing) / 2).toFloat(),
                                myT.toFloat()
                            )
                            path.lineTo(
                                (myR + (if (mGridHorizontalSpacing == 0) mThickness else mGridHorizontalSpacing) / 2).toFloat(),
                                myB.toFloat()
                            )
                            c.drawPath(path, mPaint!!)
                        }
                    }

                    //bottom line
                    if (isLastGridRow(viewPosition, itemSize, columnSize)) {
                        if (mGridBottomVisible) {
                            mPaint!!.strokeWidth = mThickness.toFloat()
                            if (itemSize == 1) {
                                val path = Path()
                                path.moveTo(
                                    (myL - mThickness).toFloat(),
                                    (myB + mThickness / 2).toFloat()
                                )
                                path.lineTo(
                                    (myR + mThickness).toFloat(),
                                    (myB + mThickness / 2).toFloat()
                                )
                                c.drawPath(path, mPaint!!)
                            } else if (isLastGridColumns(viewPosition, itemSize, columnSize)) {
                                val path = Path()
                                path.moveTo(
                                    (myL - if (mGridHorizontalSpacing == 0) mThickness else mGridHorizontalSpacing).toFloat(),
                                    (myB + mThickness / 2).toFloat()
                                )
                                path.lineTo(
                                    (myR + mThickness).toFloat(),
                                    (myB + mThickness / 2).toFloat()
                                )
                                c.drawPath(path, mPaint!!)
                            } else {
                                val path = Path()
                                path.moveTo(
                                    (myL - if (mGridHorizontalSpacing == 0) mThickness else mGridHorizontalSpacing).toFloat(),
                                    (myB + mThickness / 2).toFloat()
                                )
                                path.lineTo(
                                    (myR + if (mGridHorizontalSpacing == 0) mThickness else mGridHorizontalSpacing).toFloat(),
                                    (myB + mThickness / 2).toFloat()
                                )
                                c.drawPath(path, mPaint!!)
                            }

                        }
                    } else {
                        mPaint!!.strokeWidth = mThickness.toFloat()
                        if (mGridVerticalSpacing != 0) {
                            mPaint!!.strokeWidth = mGridVerticalSpacing.toFloat()
                        }
                        val path = Path()
                        path.moveTo(
                            (myL - if (mGridHorizontalSpacing == 0) mThickness else mGridHorizontalSpacing).toFloat(),
                            (myB + (if (mGridVerticalSpacing == 0) mThickness else mGridVerticalSpacing) / 2).toFloat()
                        )
                        path.lineTo(
                            myR.toFloat(),
                            (myB + (if (mGridVerticalSpacing == 0) mThickness else mGridVerticalSpacing) / 2).toFloat()
                        )
                        c.drawPath(path, mPaint!!)
                    }
                }

            }
        }
    }

    /**
     * set offsets for grid mode
     *
     * @param outRect
     * @param viewPosition
     * @param columnSize
     * @param itemSize
     * @param tag          0 for ninepatch,1 for drawable bitmap
     */
    private fun setGridOffsets(
        outRect: Rect,
        viewPosition: Int,
        columnSize: Int,
        itemSize: Int,
        tag: Int
    ) {

        val x: Int
        val y: Int
        val borderThickness = mThickness
        when (tag) {
            0 -> {
                y = mThickness
                x = y
                mGridHorizontalSpacing = 0
                mGridVerticalSpacing = mGridHorizontalSpacing
            }
            1 -> {
                x = mDrawable!!.intrinsicWidth
                y = mDrawable!!.intrinsicHeight
                mGridHorizontalSpacing = 0
                mGridVerticalSpacing = mGridHorizontalSpacing
            }
            else -> {

                x = if (mGridHorizontalSpacing != 0)
                    mGridHorizontalSpacing
                else
                    mThickness

                y = if (mGridVerticalSpacing != 0)
                    mGridVerticalSpacing
                else
                    mThickness
            }
        }

        val isFirstGridRow = isFirstGridRow(viewPosition, columnSize)
        val isFirstGridColumns = isFirstGridColumns(viewPosition, columnSize)
        val isLastGridRow = isLastGridRow(viewPosition, itemSize, columnSize)
        val isLastGridColumns = isLastGridColumns(viewPosition, itemSize, columnSize)

        val halfBorderThickness = borderThickness / 2

        val left: Int = halfBorderThickness
        val top: Int = halfBorderThickness
        val right: Int = halfBorderThickness
        val bottom: Int = halfBorderThickness

//        when {
//            columnSize == 1 -> {
//                left = if (mGridLeftVisible) borderThickness else 0
//                top = if (mGridTopVisible) borderThickness else 0
//                right = if (mGridRightVisible) borderThickness else 0
//                bottom = if (mGridBottomVisible) borderThickness else 0
//            }
//
//            itemSize <= columnSize -> {
//                left =
//                    if (isFirstGridColumns) {
//                        if (mGridLeftVisible) borderThickness else 0
//                    } else halfBorderThickness
//
//                top = if (mGridTopVisible) borderThickness else 0
//
//                right =
//                    if (isLastGridColumns) {
//                        if (mGridRightVisible) borderThickness else 0
//                    } else halfBorderThickness
//
//                bottom = if (mGridBottomVisible) borderThickness else 0
//            }
//
//            isFirstGridRow && isFirstGridColumns -> {
//                left = if (mGridLeftVisible) borderThickness else 0
//                top = if (mGridTopVisible) borderThickness else 0
//                right = halfBorderThickness
//                bottom = halfBorderThickness
//            }
//
//            isFirstGridRow && isLastGridColumns -> {
//                left = halfBorderThickness
//                top = if (mGridTopVisible) borderThickness else 0
//                right = if (mGridLeftVisible) borderThickness else 0
//                bottom = halfBorderThickness
//            }
//
//            isFirstGridColumns && isLastGridRow -> {
//                left = if (mGridLeftVisible) borderThickness else 0
//                top = halfBorderThickness
//                right = halfBorderThickness
//                bottom = if (mGridBottomVisible) borderThickness else 0
//            }
//
//            isLastGridColumns && isLastGridRow -> {
//                left = halfBorderThickness
//                top = halfBorderThickness
//                right = if (mGridRightVisible) borderThickness else 0
//                bottom = if (mGridBottomVisible) borderThickness else 0
//            }
//
//            isFirstGridRow -> {
//                left = halfBorderThickness
//                top = if (mGridTopVisible) borderThickness else 0
//                right = halfBorderThickness
//                bottom = halfBorderThickness
//            }
//
//            isLastGridRow -> {
//                left = halfBorderThickness
//                top = halfBorderThickness
//                right = halfBorderThickness
//                bottom = if (mGridBottomVisible) borderThickness else 0
//            }
//
//            isFirstGridColumns -> {
//                left = if (mGridLeftVisible) borderThickness else 0
//                top = halfBorderThickness
//                right = halfBorderThickness
//                bottom = halfBorderThickness
//            }
//
//            isLastGridColumns -> {
//                left = halfBorderThickness
//                top = halfBorderThickness
//                right = if (mGridRightVisible) borderThickness else 0
//                bottom = halfBorderThickness
//            }
//
//            else -> {
//                left = halfBorderThickness
//                top = halfBorderThickness
//                right = halfBorderThickness
//                bottom = halfBorderThickness
//            }
//        }

        outRect.set(left, top, right, bottom)
        //when columnSize/spanCount is One
//        if (columnSize == 1) {
//            if (isFirstGridRow) {
//                if (isLastGridRow) {
//                    outRect.set(
//                        if (mGridLeftVisible) borderThickness else 0,
//                        if (mGridTopVisible) borderThickness else 0,
//                        if (mGridRightVisible) borderThickness else 0,
//                        if (mGridBottomVisible) borderThickness else y
//                    )
//                } else {
//                    outRect.set(
//                        if (mGridLeftVisible) borderThickness else 0,
//                        if (mGridTopVisible) borderThickness else 0,
//                        if (mGridRightVisible) borderThickness else 0,
//                        y
//                    )
//                }
//
//            } else {
//                if (isLastGridRow) {
//                    outRect.set(
//                        if (mGridLeftVisible) borderThickness else 0,
//                        0,
//                        if (mGridRightVisible) borderThickness else 0,
//                        if (mGridBottomVisible) borderThickness else 0
//                    )
//                } else {
//                    outRect.set(
//                        if (mGridLeftVisible) borderThickness else 0,
//                        0,
//                        if (mGridRightVisible) borderThickness else 0,
//                        y
//                    )
//                }
//            }
//        } else {
//
//            if (isFirstGridColumns && isFirstGridRow) {
//                outRect.set(
//                    if (mGridLeftVisible) borderThickness / 2 else 0,
//                    if (mGridTopVisible) borderThickness else 0,
//                    /* if (itemSize == 1) borderThickness else x*/
//                    borderThickness / 2,
//                    /* if (isLastGridRow(viewPosition, itemSize, columnSize)) borderThickness else y*/
//                    if (mGridBottomVisible) borderThickness else 0
//                )
//
//            } else if (isFirstGridRow) {
//
//                if (isLastGridColumns) {
//                    outRect.set(
//                        borderThickness / 2,
//                        if (mGridTopVisible) borderThickness else 0,
//                        if (mGridRightVisible) borderThickness else 0,
//                        if (isLastGridRow
//                        ) borderThickness else borderThickness / 2
//                    )
//                } else {
//                    outRect.set(
//                        borderThickness / 2,
//                        if (mGridTopVisible) borderThickness else 0,
//                        borderThickness / 2,
//                        if (isLastGridRow) borderThickness else borderThickness / 2
//                    )
//                }
//
//            } else if (isFirstGridColumns) {
//
//                if (isLastGridRow) {
//                    outRect.set(
//                        if (mGridLeftVisible) borderThickness else 0,
//                        borderThickness / 2,
//                        if (isLastGridColumns && mGridRightVisible) borderThickness else 0,
//                        if (mGridBottomVisible) borderThickness else 0
//                    )
//                } else {
//                    outRect.set(
//                        if (mGridLeftVisible) borderThickness else 0,
//                        0,
//                        if (isLastGridColumns
//                        ) borderThickness else x,
//                        y
//                    )
//                }
//
//            } else {
//
//                if (isLastGridColumns) {
//                    if (isLastGridRow) {
//                        outRect.set(
//                            0,
//                            0,
//                            if (mGridRightVisible) borderThickness else 0,
//                            if (mGridBottomVisible) borderThickness else 0
//                        )
//                    } else {
//                        outRect.set(0, 0, if (mGridRightVisible) borderThickness else 0, y)
//                    }
//                } else {
//                    if (isLastGridRow) {
//                        outRect.set(0, 0, x, if (mGridBottomVisible) borderThickness else 0)
//                    } else {
//                        outRect.set(0, 0, x, y)
//                    }
//
//                }
//            }
//        }
    }

    /**
     * check if is one of the first columns
     *
     * @param position
     * @param columnSize
     * @return
     */
    private fun isFirstGridColumns(position: Int, columnSize: Int): Boolean {
        return position % columnSize == 0
    }

    /**
     * check if is one of the last columns
     *
     * @param position
     * @param columnSize
     * @return
     */
    private fun isLastGridColumns(position: Int, itemSize: Int, columnSize: Int): Boolean {
        var isLast = false
        if ((position + 1) % columnSize == 0) {
            isLast = true
        }
        return isLast
    }

    /**
     * check if is the first row of th grid
     *
     * @param position
     * @param columnSize
     * @return
     */
    private fun isFirstGridRow(position: Int, columnSize: Int): Boolean {
        return position < columnSize
    }

    /**
     * check if is the last row of the grid
     *
     * @param position
     * @param itemSize
     * @param columnSize
     * @return
     */
    private fun isLastGridRow(position: Int, itemSize: Int, columnSize: Int): Boolean {
        val temp = itemSize % columnSize
        return if (temp == 0 && position >= itemSize - columnSize) {
            true
        } else
            position >= itemSize / columnSize * columnSize
    }

    /**
     * check if is the last second row of the grid when the itemSize cannot be divided by columnSize
     *
     * @param position
     * @param itemSize
     * @param columnSize
     * @return
     */
    private fun isLastSecondGridRowNotDivided(
        position: Int,
        itemSize: Int,
        columnSize: Int
    ): Boolean {
        val temp = itemSize % columnSize
        return temp != 0 && itemSize - 1 - temp == position
    }

    /**
     * compatible with recyclerview layoutmanager
     *
     * @param parent
     */
    private fun compatibleWithLayoutManager(parent: RecyclerView) {

        if (parent.layoutManager != null) {
            if (parent.layoutManager is GridLayoutManager) {
                mMode = RVItemDecorationConst.MODE_GRID
            } else if (parent.layoutManager is LinearLayoutManager) {
                if ((parent.layoutManager as LinearLayoutManager).orientation == LinearLayout.HORIZONTAL) {
                    mMode = RVItemDecorationConst.MODE_VERTICAL
                } else {
                    mMode = RVItemDecorationConst.MODE_HORIZONTAL
                }
            }

        } else {
            mMode = RVItemDecorationConst.MODE_UNKNOWN
        }

        initPaint()

    }

    class Builder(private val context: Context) {

        private val params: Param

        init {

            params = Param()

        }

        fun create(): RecyclerViewItemDecoration {
            val recyclerViewItemDecoration = RecyclerViewItemDecoration()
            recyclerViewItemDecoration.setParams(context, params)
            return recyclerViewItemDecoration
        }

        fun drawable(drawable: Drawable): Builder {
            params.mDrawable = drawable
            return this
        }

        fun color(@ColorInt color: Int): Builder {
            params.color = color
            return this
        }

        fun color(color: String): Builder {
            if (isColorString(color)) {
                params.color = Color.parseColor(color)
            }
            return this
        }

        fun thickness(thickness: Int): Builder {
            var thickness = thickness
            if (thickness % 2 != 0) {
                thickness += 1
            }
            if (thickness <= 2) {
                thickness = 2
            }
            params.thickness = thickness
            return this
        }

        fun dashWidth(dashWidth: Int): Builder {
            var dashWidth = dashWidth
            if (dashWidth < 0) {
                dashWidth = 0
            }
            params.dashWidth = dashWidth
            return this
        }

        fun dashGap(dashGap: Int): Builder {
            var dashGap = dashGap
            if (dashGap < 0) {
                dashGap = 0
            }
            params.dashGap = dashGap
            return this
        }

        fun lastLineVisible(visible: Boolean): Builder {
            params.lastLineVisible = visible
            return this
        }

        fun firstLineVisible(visible: Boolean): Builder {
            params.firstLineVisible = visible
            return this
        }

        fun paddingStart(padding: Int): Builder {
            params.paddingStart = padding
            return this
        }

        fun paddingEnd(padding: Int): Builder {
            var padding = padding
            if (padding < 0) {
                padding = 0
            }
            params.paddingEnd = padding
            return this
        }

        fun gridLeftVisible(visible: Boolean): Builder {
            params.gridLeftVisible = visible
            return this
        }

        fun gridRightVisible(visible: Boolean): Builder {
            params.gridRightVisible = visible
            return this
        }

        fun gridTopVisible(visible: Boolean): Builder {
            params.gridTopVisible = visible
            return this
        }

        fun gridBottomVisible(visible: Boolean): Builder {
            params.gridBottomVisible = visible
            return this
        }

        fun gridHorizontalSpacing(spacing: Int): Builder {
            var spacing = spacing
            if (spacing < 0) {
                spacing = 0
            }
            params.gridHorizontalSpacing = spacing
            return this
        }

        fun gridVerticalSpacing(spacing: Int): Builder {
            var spacing = spacing
            if (spacing < 0) {
                spacing = 0
            }
            params.gridVerticalSpacing = spacing
            return this
        }

    }

    class Param {

        var mDrawable: Drawable? = null
        var color = Color.parseColor(DEFAULT_COLOR)
        var thickness: Int = 0
        var dashWidth = 0
        var dashGap = 0
        var lastLineVisible: Boolean = false
        var firstLineVisible: Boolean = false
        var paddingStart: Int = 0
        var paddingEnd: Int = 0
        var gridLeftVisible: Boolean = false
        var gridRightVisible: Boolean = false
        var gridTopVisible: Boolean = false
        var gridBottomVisible: Boolean = false
        var gridHorizontalSpacing = 0
        var gridVerticalSpacing = 0
    }

    companion object {

        /**
         * default decoration color
         */
        private val DEFAULT_COLOR = "#bdbdbd"

        /**
         * judge is a color string like #xxxxxx or #xxxxxxxx
         *
         * @param colorStr
         * @return
         */
        fun isColorString(colorStr: String): Boolean {
            return Pattern.matches("^#([0-9a-fA-F]{6}||[0-9a-fA-F]{8})$", colorStr)
        }
    }

}