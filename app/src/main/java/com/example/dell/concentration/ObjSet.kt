package com.example.dell.concentration

import android.widget.LinearLayout

/*定数*/
////カードルール
const val LINENUM:Int = 5 //1行にセットするカード
////レイアウト
const val CARDW:Int = 45 //cardの横幅
const val CARDH:Int = 60 //cardの縦幅


val PICSET = listOf("PIC01","PIC02","PIC03","PIC04","PIC05","PIC06","PIC07","PIC08","PIC09","PIC10") //画面書き込み用
val CARDID01 = listOf(R.id.card01a,R.id.card02a,R.id.card03a,R.id.card04a,R.id.card05a,
                                R.id.card06a,R.id.card07a,R.id.card08a,R.id.card09a,R.id.card10a,
                                R.id.card11a,R.id.card12a,R.id.card13a,R.id.card14a,R.id.card15a,
                                R.id.card16a,R.id.card17a,R.id.card18a,R.id.card19a,R.id.card20a) //画面書き込み用
val CARDID02 = listOf(R.id.card01b,R.id.card02b,R.id.card03b,R.id.card04b,R.id.card05b,
                                R.id.card06b,R.id.card07b,R.id.card08b,R.id.card09b,R.id.card10b,
                                R.id.card11b,R.id.card12b,R.id.card13b,R.id.card14b,R.id.card15b,
                                R.id.card16b,R.id.card17b,R.id.card18b,R.id.card19b,R.id.card20b) //画面書き込み用
val IMGID = listOf(R.drawable.test01,R.drawable.test02,R.drawable.test03,R.drawable.test04,R.drawable.test05,
                                R.drawable.test06,R.drawable.test07,R.drawable.test08,R.drawable.test09,R.drawable.test10) //画面書き込み用

val BACKIMG = R.drawable.test00
val trumps = mutableListOf<Trump>() //デッキ
//val hand = mutableListOf<Hand>() //手札(プレイヤー)
var OPENCARD = mutableListOf<Hand>() //デッキ

/**
 * 山札
 * @suit 絵札
 * @num カード番号
 * @isFace 表裏 初回は裏
 * @pid 手札処理用
 */
open class Trump(val suit: String, val num: Int,val pid:Int,val imgid:Int)

/**
 * カード
 * @card カードを表示するView
 * @Trump カードの情報
 * @hidFlg 裏表
 */
//class Hand(val card: View, val trump: Trump)
class Hand(val cardLine: LinearLayout, val index: Int, val trump: Trump, var selFlg: Boolean = true) {
    fun select(selFlg: Boolean) {this.selFlg = !selFlg}
}