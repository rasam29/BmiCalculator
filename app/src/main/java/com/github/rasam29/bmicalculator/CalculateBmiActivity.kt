package com.github.rasam29.bmicalculator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.github.rasam29.bmicalculator.databinding.CalculateBmiActivityBinding
import kotlin.math.roundToLong

class CalculateBmiActivity : AppCompatActivity() {
    val context = this
    val layoutView: CalculateBmiActivityBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.calculate_bmi_activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutView.calculateBmiButton.setOnClickListener {
            when {
                layoutView.heightText.text.isNullOrEmpty() -> layoutView.heightText.error =
                    resources.getString(R.string.please_enter_height)
                layoutView.weightText.text.isNullOrEmpty() -> layoutView.weightText.error =
                    resources.getString(R.string.please_enter_weight)
                layoutView.nameText.text.isNullOrEmpty() -> layoutView.nameText.error = resources.getString(
                    R.string.name_hint
                )
                (!layoutView.female.isChecked && !layoutView.male.isChecked) -> {
                    Toast.makeText(this, R.string.please_select_gender, Toast.LENGTH_SHORT).show()
                }
                else -> calculateButton()
            }
        }
    }

    fun calculateButton() = with(layoutView) {
        if (weightText.text.toString().toFloat() < 0f ||
            heightText.text.toString().toFloat() < 0f ||
            heightText.text.toString().toFloat() > 300f ||
            heightText.text.toString().toFloat() < 30f
        ) {
            Toast.makeText(context, "اطلاعات وارد شده معتبر نمی باشد!", Toast.LENGTH_SHORT).show()
        } else {
            val heightInMeter = heightText.text.toString().toDouble() / 100
            var result = weightText.text.toString().toDouble() / Math.pow(heightInMeter, 2.0)
            if (female.isChecked) {
                result -= 1
            }
            resultContainer.visibility = View.VISIBLE
            result = result.roundToLong().toDouble()
            when {
                result < 18.5 -> {
                    resultText.text = resultBuilder(
                        nameText.text.toString(),
                        result.toString(),
                        UserBodyStatus.OverWeight.message
                    )
                }
                (result >= 18.5 && result <= 24.9) -> {
                    resultText.text = resultBuilder(
                        nameText.text.toString(),
                        result.toString(),
                        UserBodyStatus.OverWeight.message
                    )
                }
                (result > 25 && result < 29.9) -> {
                    resultText.text = resultBuilder(
                        nameText.text.toString(),
                        result.toString(),
                        UserBodyStatus.OverWeight.message
                    )
                }
                (result > 30) -> {
                    resultText.text = resultBuilder(
                        nameText.text.toString(),
                        result.toString(),
                        UserBodyStatus.OverWeight.message
                    )
                }
                else -> throw IllegalArgumentException("no valid")
            }
        }

    }
}


fun resultBuilder(name: String, result: String, bodyType: String) =
    "سلام $name ، bmi شما $result میباشد.\n وضعیت بدن شما :$bodyType است."
