package br.ufrn.eaj.tads.gametetris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import android.view.LayoutInflater
import classes.*


class MainActivity : AppCompatActivity() {

    val LINHA = 36
    val COLUNA = 26
    var running = true
    var speed:Long = 300

    var pt = Z(1,12)


    //val board = Array(LINHA, { IntArray(COLUNA) })

    var board = Array(LINHA) {
        Array(COLUNA){0}
    }

    var boardView = Array(LINHA){
        arrayOfNulls<ImageView>(COLUNA)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridboard.rowCount = LINHA
        gridboard.columnCount = COLUNA

        val inflater = LayoutInflater.from(this)

        for (i in 0 until LINHA) {
            for (j in 0 until COLUNA) {
                boardView[i][j] = inflater.inflate(R.layout.inflate_image_view, gridboard, false) as ImageView
                gridboard.addView( boardView[i][j])
            }
        }

        buttonLeft.setOnClickListener {
            pt.moveLeft()
        }

        buttonRight.setOnClickListener {
            pt.moveRight()
        }

        buttonRotate.setOnClickListener {
            pt.rotate()
        }

        buttonSpeed.setOnClickListener {
            speed += 100
        }

        gameRun()

    }

    fun gameRun(){
        Thread{
            while(running){
                Thread.sleep(speed)
                runOnUiThread{
                    //limpa tela
                    for (i in 0 until LINHA) {
                        for (j in 0 until COLUNA) {
                            boardView[i][j]!!.setImageResource(R.drawable.black)
                        }
                    }
                    //move peça atual
                    pt.moveDown()
                    //print peça
                    try {
                        boardView[pt.pontoA.x][pt.pontoA.y]!!.setImageResource(R.drawable.white)
                        boardView[pt.pontoB.x][pt.pontoB.y]!!.setImageResource(R.drawable.white)
                        boardView[pt.pontoC.x][pt.pontoC.y]!!.setImageResource(R.drawable.white)
                        boardView[pt.pontoD.x][pt.pontoD.y]!!.setImageResource(R.drawable.white)
                    }catch (e:ArrayIndexOutOfBoundsException ) {
                        //se a peça passou das bordas eu vou parar o jogo
                        running = false
                    }

                }
            }
        }.start()
    }
}
