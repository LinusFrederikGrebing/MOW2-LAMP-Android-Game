package de.thm.lampgame.controller.helper

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintHelper
import androidx.constraintlayout.widget.ConstraintLayout

class GreatestWidthHelper @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintHelper(context, attrs, defStyleAttr) {

    override fun updatePostMeasure(container: ConstraintLayout) {
        var maxWidth = 0

        // Find the greatest width of the referenced widgets.
        for (i in 0 until this.mCount) {
            val id = this.mIds[i]
            val child = container.getViewById(id)
            val widget = container.getViewWidget(child)
            if (widget.width > maxWidth) {
                maxWidth = widget.width
            }
        }

        // Set the width of all referenced views to the width of the view with the greatest width.
        for (i in 0 until this.mCount) {
            val id = this.mIds[i]
            val child = container.getViewById(id)
            val widget = container.getViewWidget(child)
            if (widget.width != maxWidth) {
                widget.width = maxWidth

                // Fix the gravity.
                if (child is TextView && child.gravity != Gravity.NO_GRAVITY) {
                    // Just toggle the gravity to make it right.
                    child.gravity = child.gravity.let { gravity ->
                        child.gravity = Gravity.NO_GRAVITY
                        gravity
                    }
                }
            }
        }
    }
}