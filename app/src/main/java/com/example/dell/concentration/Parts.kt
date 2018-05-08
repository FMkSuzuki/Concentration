package com.example.dell.concentration

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.*
import org.jetbrains.anko.*

//セットの作成
fun makeDeck() {
    trumps.clear()
    //セットで作成するため2回回す
    for((j,pic) in PICSET.withIndex()){
        trumps.add(Trump(pic,j,CARDID01[j],IMGID[j]))
    }
    for((k,pic) in PICSET.withIndex()){
        trumps.add(Trump(pic,k,CARDID02[k],IMGID[k]))
    }
    trumps.shuffle()
    println("${trumps.size}")
}


//カード配置
@SuppressLint("ResourceType")
fun setCard(cardLine: LinearLayout,txt: TextView,lineCount:Int,nextView:FrameLayout,getCard:LinearLayout,resetBtn:Button){
    val indFix = lineCount*LINENUM //配列番号調整用
    for ((i,t) in trumps.withIndex()){

        //行数*配置カード分進める
        val cIndex = i-indFix

        //もう置いた時
        if(i < indFix){
            continue
        }

        //置ききった時
        if(i > (LINENUM-1) + indFix ){
            break
        }

        //フィールドに追加
        cardLine.linearLayout{
            imageView {
                setImageResource(BACKIMG)
                id = t.pid
            }.lparams(width = cardLine.width) {
                width = dip(CARDW)
                height = dip(CARDH)
                gravity = Gravity.CENTER
                horizontalMargin = dip(5)
                verticalMargin = dip(5)
            }
        }.setOnClickListener {
            //オープンカード情報の保持
            val cardInf = Hand(cardLine,cIndex,t)

            //同一カード
            if(OPENCARD.size == 1 && OPENCARD[0].trump.pid == t.pid){
                return@setOnClickListener
            }

            //カードのオープン
            val cid = cardInf.trump.pid
            val imgid = cardInf.trump.imgid
            cardInf.cardLine.findViewById<ImageView>(cid).setImageResource(imgid)

            OPENCARD.add(cardInf)

            //以下2枚引いてからの処理
            if(OPENCARD.size < 2){
                return@setOnClickListener
            }

            //判定
            val card1 = OPENCARD[0].trump.suit
            val card2 = OPENCARD[1].trump.suit

            //揃ってる時の処理
            if(card1.indexOf(card2) == 0){
                println("Hit!!")
                //フィールドカードの非表示化
                for (oc in OPENCARD){
                    oc.cardLine.getChildAt(oc.index).visibility = View.INVISIBLE
                }

                //持ち札に加える
                getCard.linearLayout{
                    imageView {
//                        cardInf.cardLine.findViewById<ImageView>(cid).setImageResource(imgid)
                        setImageResource(cardInf.trump.imgid)
                    }.lparams(width = getCard.width) {
                        width = dip(CARDW)
                        height = dip(CARDH)
                        gravity = Gravity.CENTER
                        horizontalMargin = dip(5)
                        verticalMargin = dip(5)
                    }
                }
                //オープン情報の破棄
                OPENCARD.clear()

                //全て引いた時の処理
                if(getCard.childCount == IMGID.size){
                    resetBtn.visibility = View.VISIBLE
                }

                return@setOnClickListener
            }

            //揃ってない時の処理
            nextView.visibility = View.VISIBLE

            //カードを裏返す
            nextView.setOnClickListener{
                nextView.visibility = View.GONE
                for (oc in OPENCARD){
                    val cid2 = oc.trump.pid
                    oc.cardLine.findViewById<ImageView>(cid2).setImageResource(BACKIMG)
                }
                OPENCARD.clear()
                txt.text = ""
            }
        }
    }
}