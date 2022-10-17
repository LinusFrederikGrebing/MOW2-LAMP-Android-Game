package de.thm.lampgame

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix

class Terrain(x: Int,y: Int,width: Int,height: Int, res: Resources) : RectObstacle(x,y, width,height) {
    val solid = true
    val groundUnrotated = BitmapFactory.decodeResource(res, R.drawable.gras2)
    val groundUnresized = groundUnrotated.rotate(90f)


    fun Bitmap.rotate(degrees: Float): Bitmap {
        val matrix = Matrix().apply { postRotate(degrees) }
        return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
    }

    fun drawBitmap(move: Int, c: Canvas) {
        val ground = Bitmap.createScaledBitmap(groundUnresized, width*5, height, true)
        c.drawBitmap(ground,x.toFloat(),(y+move).toFloat(),null)
    }
}