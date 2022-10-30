package de.thm.lampgame.controller

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import de.thm.lampgame.controller.terrain.BitmapGround
import de.thm.lampgame.model.TilesetQueueModel

class TilesetQueue() : TilesetQueueModel() {

    //Collision-Methode muss noch vereinfacht werden

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

    fun tilesetCollision(bitmap: Bitmap, newX: Int, newY: Int): Boolean {
        for (i in 0 until queue.first().obstacles.size) {
            if (checkBitmapCollisions(
                    queue.first().obstacles[i].bmp,
                    queue.first().obstacles[i].changeableX,
                    queue.first().obstacles[i].y,
                    bitmap, newX, newY
                )
            ) return true
        }
        for (i in 0 until queue.last().obstacles.size) {
            if (checkBitmapCollisions(
                    queue.last().obstacles[i].bmp,
                    queue.last().obstacles[i].changeableX,
                    queue.last().obstacles[i].y,
                    bitmap, newX, newY
                )
            ) return true
        }

        return false

    }

    fun checkBitmapCollisions(
        bitmap1: Bitmap,
        x1: Int,
        y1: Int,
        bitmap2: Bitmap,
        x2: Int,
        y2: Int
    ): Boolean {
        val hitboxRect1 = Rect(
            x1, y1, (x1 + bitmap1.width),
            (y1 + bitmap1.height)
        )
        val hitboxRect2 = Rect(
            x2, y2, (x2 + bitmap2.width),
            (y2 + bitmap2.height)
        )
        if (Rect.intersects(hitboxRect1, hitboxRect2)) {
            return true
        }

        return false
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

