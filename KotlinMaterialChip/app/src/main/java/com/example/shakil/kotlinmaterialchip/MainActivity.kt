package com.example.shakil.kotlinmaterialchip

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.chip.Chip
import android.view.LayoutInflater
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.text.StringBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chip_example.setOnCloseIconClickListener {
            Toast.makeText(this@MainActivity, "Close click", Toast.LENGTH_SHORT).show()
        }
        chip_example.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(this@MainActivity, "Checked change : " + isChecked, Toast.LENGTH_SHORT).show()
        }

        btn_add_tag.setOnClickListener {
            val text_array = input.text!!.toString().split(" ".toRegex())
                .dropLastWhile {
                    it.isEmpty()
                }.toTypedArray()
            val inflater = LayoutInflater.from(this@MainActivity)
            for (text in text_array) {
                val chip_item = inflater.inflate(R.layout.chip_item, null, false) as Chip
                chip_item.text = text
                chip_item.setOnCloseIconClickListener { view ->
                    chip_group.removeView(view)
                }
                chip_group.addView(chip_item)
            }
        }

        btn_show_selected.setOnClickListener {
            var result: StringBuilder = StringBuilder("")
            for (i in 0 until chip_group.childCount) {
                val chip = chip_group.getChildAt(i) as Chip
                if (chip.isChecked) {
                    result.append(chip.text).append(",")
                }
            }
            Toast.makeText(this@MainActivity, "" + result.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}
