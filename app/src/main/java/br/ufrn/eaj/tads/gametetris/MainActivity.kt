package br.ufrn.eaj.tads.gametetris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import android.view.LayoutInflater
import classes.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    val LINHA = 36
    val COLUNA = 26
    var running = true
    var speed:Long = 350

    var pt = L(1,12)

    // Lista de Peças do meu jogo
    val ponto = listOf<Peca>(L(1,12),O(1,12),I(1,12),T(1,12),
                                        L2(1,12),Z(1,12),S(1,12))

    //val board = Array(LINHA, { IntArray(COLUNA) })

    var pecas:Int = 0

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

        // Gerar as paças aleatórias // #Verificar se funcionar quando gerar conflitos
        pecas = Random.nextInt(0, 6)

        val inflater = LayoutInflater.from(this)

        for (i in 0 until LINHA) {
            for (j in 0 until COLUNA) {
                boardView[i][j] = inflater.inflate(R.layout.inflate_image_view, gridboard, false) as ImageView
                gridboard.addView( boardView[i][j])
            }
        }

        buttonLeft.setOnClickListener {
            ponto[pecas].moveLeft()
        }

        buttonRight.setOnClickListener {
            ponto[pecas].moveRight()
        }

        buttonRotate.setOnClickListener {
            ponto[pecas].rotate()
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
                    ponto[pecas].moveDown()
                    //print peça
                    try {
                        boardView[ponto[pecas].pontoA.x][ponto[pecas].pontoA.y]!!.setImageResource(R.drawable.white)
                        boardView[ponto[pecas].pontoB.x][ponto[pecas].pontoB.y]!!.setImageResource(R.drawable.white)
                        boardView[ponto[pecas].pontoC.x][ponto[pecas].pontoC.y]!!.setImageResource(R.drawable.white)
                        boardView[ponto[pecas].pontoD.x][ponto[pecas].pontoD.y]!!.setImageResource(R.drawable.white)

                    }catch (e:ArrayIndexOutOfBoundsException ) {
                        //se a peça passou das bordas eu vou parar o jogo

                        running = false
                    }

                }
            }
        }.start()
    }

}
