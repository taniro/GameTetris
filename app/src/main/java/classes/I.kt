package classes

class I(x:Int, y:Int) : Peca(x,y) {
    init {
        pontoB = Ponto(x-1,y)
        pontoC = Ponto(x+1,y)
        pontoD = Ponto(x+2,y)
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
        pontoB.x += 1
        pontoB.y += 1

        pontoC.x -= 1
        pontoC.y -= 1

        pontoD.x -= 2
        pontoD.y -= 2
    }

}