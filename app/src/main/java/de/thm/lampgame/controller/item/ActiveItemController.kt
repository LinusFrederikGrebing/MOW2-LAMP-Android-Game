package de.thm.lampgame.controller.item

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import de.thm.lampgame.controller.Player
import de.thm.lampgame.controller.item.ActiveItem
import de.thm.lampgame.model.item.BonusJumpModel
import de.thm.lampgame.model.item.DoublePointsModel
import de.thm.lampgame.model.item.ImmortalityModel

class ActiveItemController(context: Context, screenWidth: Int, screenHeight: Int) {
    private var dblPointsItem = ActiveItem(context, screenWidth, screenHeight)
    private var bonusJumpItem = ActiveItem(context, screenWidth, screenHeight)
    private var immortality = ActiveItem(context, screenWidth, screenHeight)

    fun checkItemDurationAndSetItemEffect(canvas: Canvas, paint: Paint, player: Player) {
        if (DoublePointsModel.dblPtsDur > 0) {
            paint.color = Color.RED
            DoublePointsModel.dblPtsDur--
            dblPointsItem.drawCircle(canvas, DoublePointsModel.doublePointsDuration)
        } else paint.color = Color.BLACK

        if (BonusJumpModel.dblJumpDur > 0) {
            BonusJumpModel.dblJumpDur--
            bonusJumpItem.drawCircle(canvas, BonusJumpModel.bonusJumpDuration)
        } else player.playerModel.maxJump = 2

        if (ImmortalityModel.immortalityDur > 0) {
            ImmortalityModel.immortalityDur--
            immortality.drawCircle(canvas, ImmortalityModel.immortalityDuration)
        } else player.playerModel.immortal = false
    }

}