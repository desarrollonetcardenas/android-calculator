package udemy.kotlin_course.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import udemy.kotlin_course.calculator.databinding.ActivityMainBinding
import java.lang.Exception
import java.math.BigDecimal
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var lastOperator = false
    var lastNumeric = false
    var isFirstDigit = true
    var lastDigit: String = ""
    var lastNumberValue  = ""
    var lastOperatorValue = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initializeValues()

        /*---------------------------------------------------------*/
        /*Operator button section*/
        binding.btnDivide.setOnClickListener {
            setOperator("/")
        }

        binding.btnMultiply.setOnClickListener {
            setOperator("*")
        }

        binding.btnSubstract.setOnClickListener {
            setOperator("-")
        }

        binding.btnAdd.setOnClickListener {
            setOperator("+")
        }

        /*---------------------------------------------------------*/
        /*Numeric Section Buttons*/
        binding.btnZero.setOnClickListener {
            setNumberValue("0")
        }

        binding.btnOne.setOnClickListener {
            setNumberValue("1")
        }

        binding.btnTwo.setOnClickListener {
            setNumberValue("2")
        }

        binding.btnThree.setOnClickListener{
            setNumberValue("3")
        }

        binding.btnFour.setOnClickListener {
            setNumberValue("4")
        }

        binding.btnFive.setOnClickListener {
            setNumberValue("5")
        }

        binding.btnSix.setOnClickListener {
            setNumberValue("6")
        }

        binding.btnSeven.setOnClickListener {
            setNumberValue("7")
        }

        binding.btnEight.setOnClickListener {
            setNumberValue("8")
        }

        binding.btnNine.setOnClickListener {
            setNumberValue("9")
        }

        /*---------------------------------------------------------*/
        /*Operation Buttons*/
        binding.btnEqual.setOnClickListener {

            try {

                if (!isFirstDigit) {

                    var value1 = lastNumberValue.toFloat()
                    var value2 = binding.tvInput.text.toString().toFloat()

                    when (lastOperatorValue) {
                        "+" -> {
                            var result = (value1 + value2).toString()
                            binding.tvInput.text = result
                        }
                        "-" -> {
                            var result = (value1 - value2).toString()
                            binding.tvInput.text = result
                        }
                        "*" -> {
                            var result = (value1 * value2).toString()
                            binding.tvInput.text = result
                        }
                        "/" -> {
                            var result = (value1 / value2).toString()
                            binding.tvInput.text = result
                        }
                    }

                    binding.tvInputLog.text = binding.tvInput.text
                }

            } catch (ex: Exception) {
                //Toast.makeText(this, "Exception ocurred", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnPoint.setOnClickListener {
            val dotCounter = binding.tvInput.text

            if (dotCounter.indexOf('.') == -1) {

                binding.tvInput.append(binding.btnPoint.text)
                binding.tvInputLog.append(".")
            }
        }

        binding.btnClear.setOnClickListener {
            initializeValues()
        }

    }

    fun setNumberValue(lastDigit: String) {

        if (isFirstDigit) {
            binding.tvInput.text = lastDigit
        }
        else if(lastNumeric && !lastOperator) {
            binding.tvInput.append(lastDigit)
        }
        else if(lastOperator && !lastNumeric) {
            binding.tvInput.text = lastDigit
        }

        binding.tvInputLog.append(lastDigit)

        lastNumeric = true
        lastOperator = false
        isFirstDigit = false
    }

    fun setOperator(operator: String) {

        if (lastNumeric && !lastOperator) {
            binding.tvInputLog.append(operator)
            lastNumberValue = binding.tvInput.text.toString()
        }

        lastOperator = true
        lastNumeric = false
        lastOperatorValue = operator
    }

    fun initializeValues() {
        binding.tvInputLog.text = ""
        binding.tvInput.text = "0"

        lastOperator = false
        lastNumeric = false
        isFirstDigit = true
        lastDigit = ""
        lastNumberValue  = ""
        lastOperatorValue = ""
    }

}