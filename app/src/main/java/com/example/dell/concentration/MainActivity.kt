package com.example.dell.concentration

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        makeDeck()
        val cardLineSet = arrayOf(cardLine01,cardLine02,cardLine03,cardLine04)
        for ((i,cls) in cardLineSet.withIndex()){
            setCard(cls,debagTxt,i,nextOpen,getCard,reset)
        }

        reset.setOnClickListener {
            reset.visibility = View.GONE
            makeDeck()
            for ((i,cls) in cardLineSet.withIndex()){
                cls.removeAllViews()
                setCard(cls,debagTxt,i,nextOpen,getCard,reset)
            }
            getCardCol.removeAllViews()
        }
    }
}
