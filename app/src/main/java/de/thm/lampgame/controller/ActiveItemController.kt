package de.thm.lampgame.controller

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import de.thm.lampgame.model.item.BonusJumpModel
import de.thm.lampgame.model.item.DoublePointsModel
import de.thm.lampgame.model.item.ImmortalityModel
import de.thm.lampgame.view.item.ActiveItem

class ActiveItemController(context: Context, screenWidth : Int, screenHeight: Int) {
    private var activeItem = ActiveItem(context, screenWidth, screenHeight)

    fun checkItemDurAndSetItemEffect(canvas: Canvas, paint: Paint, player: Player) {
        if (DoublePointsModel.dblPtsDur > 0) {
            paint.color = Color.RED
            DoublePointsModel.dblPtsDur--
            activeItem.drawCircle(canvas, DoublePointsModel.doublepointsduration)
        } else paint.color = Color.BLACK

        if (BonusJumpModel.dblJumpDur > 0) {
            BonusJumpModel.dblJumpDur--
            activeItem.drawCircle(canvas, BonusJumpModel.bonusjumpduration)
        } else player.playerModel.maxJump = 2

        if (ImmortalityModel.immortalDur > 0) {
            ImmortalityModel.immortalDur--
            activeItem.drawCircle(canvas, ImmortalityModel.immortalduration)
        } else player.playerModel.immortal = false
    }

}