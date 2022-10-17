package de.thm.lampgame

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape

open class RectObstacle(var x: Int, var y: Int, var width: Int, var height: Int) {
    val drawable: ShapeDrawable = ShapeDrawable(RectShape())
    val hitbox: Rect = Rect(x, y, x+width, y+height)


    init {
        drawable.setBounds(x,y,x+width,y+height)
        drawable.getPaint().setColor(Color.parseColor("#000000"))
    }

    fun draw(c: Canvas, step: Int) {
        drawable.setBounds(this.x, this.y + step, this.x+width, this.y+height+step )
        hitbox.top = this.y + step
        hitbox.bottom = this.y + height + step

        drawable.draw(c)
    }

    fun getObstacle(index: Int) : RectObstacle {
        if (index <= 1) {
            println("OBSTACLE 1")
            return RectObstacle(0,800,500,200)
        }
        else {
            println("OBSTACLE 2")
            return RectObstacle(500,800,100,1800)
        }
    }

}