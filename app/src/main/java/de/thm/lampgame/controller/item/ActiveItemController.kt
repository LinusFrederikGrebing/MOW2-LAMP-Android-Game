package de.thm.lampgame.controller.item

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import de.thm.lampgame.controller.player.Player
import de.thm.lampgame.model.item.BonusJumpModel
import de.thm.lampgame.model.item.DoublePointsModel
import de.thm.lampgame.model.item.ImmortalityModel

class ActiveItemController(context: Context, screenWidth: Int, screenHeight: Int) {

    // an activeItem object is created for each item that has a duration
    private var dblPointsItem = ActiveItem(context, screenWidth, screenHeight, DoublePointsModel.textur, DoublePointsModel.doublePointsDuration, 0F)
    private var bonusJumpItem = ActiveItem(context, screenWidth, screenHeight, BonusJumpModel.textur, BonusJumpModel.bonusJumpDuration, (0.1*screenWidth).toFloat())
    private var immortality = ActiveItem(context, screenWidth, screenHeight, ImmortalityModel.textur, ImmortalityModel.immortalityDuration, (-0.1*screenWidth).toFloat())

    fun checkItemDurationAndSetItemEffect(canvas: Canvas, paint: Paint, player: Player) {
        // if the current itemduration is equal to the starting value of the item's duration, then the percentage of the progressbar should be reset
        if(DoublePointsModel.dblPtsDur == DoublePointsModel.doublePointsDuration) dblPointsItem.resetProportion()
        if(BonusJumpModel.dblJumpDur == BonusJumpModel.bonusJumpDuration) bonusJumpItem.resetProportion()
        if(ImmortalityModel.immortalityDur == ImmortalityModel.immortalityDuration) immortality.resetProportion()

        // if the current item duration is greater than 0, the respective effect should be carried out and reset otherwise
        if (DoublePointsModel.dblPtsDur > 0) {
            paint.color = Color.RED
            DoublePointsModel.dblPtsDur--
            dblPointsItem.drawCircle(canvas)
        } else { paint.color = Color.BLACK ; player.playerModel.hasDblPts = false }

        if (BonusJumpModel.dblJumpDur > 0) {
            BonusJumpModel.dblJumpDur--
            bonusJumpItem.drawCircle(canvas)
        } else player.playerModel.maxJump = 2

        if (ImmortalityModel.immortalityDur > 0) {
            ImmortalityModel.immortalityDur--
            immortality.drawCircle(canvas)
        } else player.playerModel.immortal = false
    }

}