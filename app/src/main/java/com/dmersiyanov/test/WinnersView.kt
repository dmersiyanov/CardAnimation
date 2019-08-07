package com.dmersiyanov.test

import android.animation.Animator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.layout_winner_view_item.view.*


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class WinnersView @kotlin.jvm.JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private var firstViewOffset = -1F

    private var items = 0

    private val callback = object: Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {

        }

        override fun onAnimationCancel(animation: Animator?) {
        }

        override fun onAnimationStart(animation: Animator?) {
        }

        override fun onAnimationEnd(animation: Animator?) {

        }
    }


    init {
        View.inflate(context, R.layout.layout_winner_view_item, this)

        items = childCount


        button.setOnClickListener {

            tvTitle.visibility = View.VISIBLE
            tvTitle2.visibility = View.VISIBLE
            tvTitle3.visibility = View.VISIBLE

            tvTitle.alpha = 0.0F
            tvTitle2.alpha = 0.0F
            tvTitle3.alpha = 0.0F

//            val slideAnimation = TranslateAnimation(0F, 0F, 0F, 500F)
//            slideAnimation.apply {
//                duration = 400
//                interpolator = AccelerateInterpolator(1.0f)
//            }
//
//            tvTitle.animation = slideAnimation
//            slideAnimation.start()


            tvTitle.animate()
                .translationY(getFirstViewOffset(tvTitle))
                .alphaBy(1.0f)
                .setInterpolator(AccelerateInterpolator(1.0f))
                .setDuration(400)
                .start()

            tvTitle2.animate()
                    .translationY(firstViewOffset + tvTitle2.height + 100)
                    .alphaBy(1.0f)
                    .setDuration(400)
                    .setInterpolator(AccelerateInterpolator(1.0f))
                    .setStartDelay(500)
                    .start()

            tvTitle3.animate()
                    .translationY(firstViewOffset + tvTitle2.height + tvTitle3.height + 200)
                    .alphaBy(1.0f)
                    .setInterpolator(AccelerateInterpolator(1.0f))
                    .setDuration(400)
                    .setStartDelay(1000)
                    .setListener(callback)
                    .start()


        }

        clear.setOnClickListener {
            tvTitle.clearAnimation()
            tvTitle2.clearAnimation()
            tvTitle3.clearAnimation()

            tvTitle.visibility = View.GONE
            tvTitle2.visibility = View.GONE
            tvTitle3.visibility = View.GONE
        }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


    }


    private fun getFirstViewOffset(view: View): Float {
        firstViewOffset = -(height.toFloat() - view.height)
        return firstViewOffset
    }

    private fun isViewVisible(view: View): Boolean {
        val scrollBounds = Rect()
        this.getDrawingRect(scrollBounds)

        val top = view.y
        val bottom = top + view.height

        return scrollBounds.top < top && scrollBounds.bottom > bottom
    }


}


