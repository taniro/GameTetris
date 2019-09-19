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

    var pt:Peca = O(1,12)

    // Lista de Peças do meu jogo
    //val ponto = listOf<Peca>(L(1,12),O(1,12),I(1,12),T(1,12),
    //                                   L2(1,12),Z(1,12),S(1,12))

    //val board = Array(LINHA, { IntArray(COLUNA) })

    var pecas:Int = Random.nextInt(0, 6)


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

                    verificarParada()
                    //ponto[pecas].moveDown()
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

    //Função que verifica parada dos Obejtos
    fun verificarParada(){

        if(pt.pontoB.x <= 34 && pt.pontoB.y <= 24 &&
            pt.pontoC.x <= 34 && pt.pontoC.y <= 24 &&
            pt.pontoD.x <= 34 && pt.pontoD.y <= 24 &&
            pt.pontoA.x <= 34 && pt.pontoA.y <= 24
        )

        {
            pt.moveDown()
        }else{
            pecas = Random.nextInt(1, 7)
            pecasAleatorias(pecas)
        }
    }

    fun pecasAleatorias(x:Int){

        when(x){
            1 -> pt = L(1,12)
            2 -> pt = I(1,12)
            3 -> pt = O(1,12)
            4 -> pt = L2(1,12)
            5 -> pt = Z(1,12)
            6 -> pt = S(1,12)
            7 -> pt = T(1,12)
        }

    }

}
