package de.thm.lampgame.controller

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import de.thm.lampgame.controller.terrain.BitmapGround
import de.thm.lampgame.model.TilesetQueueModel

class TilesetQueue() : TilesetQueueModel() {

    fun drawTilesetsAndCheckCollisions(
        canvas: Canvas,
        velocity: Int,
        player: Player,
        ground: BitmapGround
    ) {
        //draw first Tileset  and its Obstacles
        queue.first().drawTileset(velocity)
        queue.first().obstacles.forEach {
            it.draw(canvas, velocity)
        }

        //draw first Tileset  and its Obstacles
        queue.last().drawTileset(velocity)
        queue.last().obstacles.forEach {
            it.draw(canvas, velocity)
        }

        collision = this.checkCollisions(
            ground.death,
            ground.bmp,
            player.charX,
            ground.y,
            player
        )

        //check Tileset Obstacles-Collision
        for (i in 0 until queue.first().obstacles.size) {
            if (this.checkCollisions(
                    queue.first().obstacles[i].death,
                    queue.first().obstacles[i].bmp,
                    queue.first().obstacles[i].changeableX,
                    queue.first().obstacles[i].y,
                    player
                )
            )
                collision = true
        }

        for (i in 0 until queue.last().obstacles.size) {
            if (this.checkCollisions(
                    queue.last().obstacles[i].death,
                    queue.last().obstacles[i].bmp,
                    queue.last().obstacles[i].changeableX,
                    queue.last().obstacles[i].y,
                    player
                )
            )
                collision = true
        }

    }


    private fun checkCollisions(
        death: Boolean,
        obstacleBitmap: Bitmap,
        obstacleX: Int,
        obstacleY: Int,
        player: Player
    ): Boolean {
        val playerHitbox = Rect(
            player.charX,
            player.charY,
            (player.charX + player.rechar[1]!!.width),
            (player.charY + player.rechar[1]!!.height)
        )
        return if (death) checkDeathCollisions(playerHitbox, obstacleBitmap, obstacleX, obstacleY)
        else checkObstacleCollisions(playerHitbox, obstacleBitmap, obstacleX, obstacleY, player)
    }


    private fun checkDeathCollisions(
        playerHitbox: Rect,
        obstacleBitmap: Bitmap,
        obstacleX: Int,
        obstacleY: Int
    ): Boolean {
        val obstacleHitbox = Rect(
            obstacleX,
            obstacleY,
            (obstacleX + obstacleBitmap.width),
            (obstacleY + obstacleBitmap.height)
        )
        if (Rect.intersects(obstacleHitbox, playerHitbox)) {
            gameover = true
        }
        return false
    }

    private fun checkObstacleCollisions(
        playerHitbox: Rect,
        obstacleBitmap: Bitmap,
        obstacleX: Int,
        obstacleY: Int,
        player: Player
    ): Boolean {
        val obstacleHitboxOben = Rect(
            obstacleX,
            obstacleY,
            (obstacleX + obstacleBitmap.width),
            (obstacleY + player.maxVelocity + 5)
        )
        val obstacleHitboxUnten = Rect(
            obstacleX,
            obstacleY + player.maxVelocity + 5,
            (obstacleX + obstacleBitmap.width),
            (obstacleY + obstacleBitmap.height - player.maxVelocity + 5)
        )
        if (Rect.intersects(obstacleHitboxUnten, playerHitbox)) {
            gameover = true
        } else if (Rect.intersects(obstacleHitboxOben, playerHitbox)) {

            return true
        }
        return false
    }

}

