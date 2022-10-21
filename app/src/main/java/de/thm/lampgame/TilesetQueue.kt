package de.thm.lampgame

class TilesetQueue {
    var queue = ArrayDeque<Tileset>(2)

    fun initQueue(t1: Tileset, t2: Tileset, screenWidth: Int){
        queue.add(t1);queue.add(t2)

        for (i in 0 until queue.first().obstacles.size) {
            queue.first().obstacles[i].x += screenWidth
        }

        for (i in 0 until queue.last().obstacles.size) {
            queue.last().obstacles[i].x += screenWidth * 2
        }
        //Alternative Lösung für die for-Schleife mit ClassCastException
        //queue.first().obstacles.forEach {(it as Obstacles).x  += screenWidth}
        //queue.last().obstacles.forEach {(it as Obstacles).x += screenWidth * 2}
    }

    fun insertTileset(screenWidth: Int, t: Tileset){
            queue.removeFirst()
            queue.add(t)
            queue.last().obstacles.forEach {it.x += screenWidth}

    }
}