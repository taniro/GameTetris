package classes

class L2(x:Int, y:Int) : Peca(x,y) {
    init {
        pontoB = Ponto(x-1,y)
        pontoC = Ponto(x,y-1)
        pontoD = Ponto(x,y-2)
    }
    override fun moveDown() {
        pontoA.moveDown()
        pontoB.moveDown()
        pontoC.moveDown()
        pontoD.moveDown()
    }

    override fun moveLeft() {
        pontoA.moveLeft()
        pontoB.moveLeft()
        pontoC.moveLeft()
        pontoD.moveLeft()
    }

    override fun moveRight() {
        pontoA.moveRight()
        pontoB.moveRight()
        pontoC.moveRight()
        pontoD.moveRight()

    }

    override fun rotate() {
        pontoB.x += 2
        pontoB.y += 0

        pontoC.x -= 1
        pontoC.y += 1

        pontoD.x += 1
        pontoD.y += 1
    }

}