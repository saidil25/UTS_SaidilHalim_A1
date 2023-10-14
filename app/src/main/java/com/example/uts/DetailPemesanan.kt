package com.example.uts

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import com.example.uts.databinding.PemesananBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DetailPemesanan : AppCompatActivity() {
    private lateinit var binding: PemesananBinding
    companion object{
        const val EXTRA_BIOSKOP="EXTRA_BIOSKOP"
        const val EXTRA_SEAT="EXTRA_SEAT"
        const val EXTRA_NUMBER_OF_SEAT="EXTRA_NUMBER_OF_SEAT"
        const val EXTRA_DATE="EXTRA_DATE"
        const val EXTRA_FEE="EXTRA_FEE"
        const val EXTRA_TOTAL_PAYMENT="EXTRA_TOTAL_PAYMENT"
        const val EXTRA_TIME="EXTRA_TIME"
        const val EXTRA_METHOD="EXTRA_METHOD"
    }

    private lateinit var datePickers: EditText
    private lateinit var timePicker: EditText
    private lateinit var BioskopLocation: Array<String>
    private lateinit var BankList: Array<String>
    private lateinit var SeatList: Array<String>
    private lateinit var PaymentMethod: Array<String>
    var TotalPayment=0

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        binding= PemesananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title=intent.getStringExtra("Title")
        val image = intent.getIntExtra("Image",0)
        datePickers=findViewById(R.id.DatePicker)
        timePicker=findViewById(R.id.TimePicker)
        BioskopLocation=resources.getStringArray(R.array.Bioskop)
        BankList=resources.getStringArray(R.array.Bank)
        SeatList=resources.getStringArray(R.array.Jenis_Seat)

        PaymentMethod= arrayOf(
            "Bayar di tempat",
            "Menggunakan Bank"
        )


        with(binding){
            val myCalendar = Calendar.getInstance()


            val datePicker= DatePickerDialog.OnDateSetListener{
                    view,year,month,dayOfMonth->
                myCalendar.set(Calendar.YEAR,year)
                myCalendar.set(Calendar.MONTH,month)
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                updateLable(myCalendar)
            }
            val timePickerDialog = TimePickerDialog(this@DetailPemesanan, TimePickerDialog.OnTimeSetListener { _: TimePicker, hourOfDay: Int, minute: Int ->
                myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                myCalendar.set(Calendar.MINUTE, minute)
                updateTimeLabel(myCalendar)
            }, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), false)

            DatePicker.setOnClickListener{
                val datePickerDialog=DatePickerDialog(this@DetailPemesanan,datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH))
                datePickerDialog.show()
            }
            TimePicker.setOnClickListener {
                timePickerDialog.show()
            }


//            spinner Adapter semua disini
            val BioskopList=ArrayAdapter(this@DetailPemesanan, android.R.layout.simple_spinner_dropdown_item,BioskopLocation)
            BioskopList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerBioskop.adapter=BioskopList

            val BankList=ArrayAdapter(this@DetailPemesanan,android.R.layout.simple_spinner_dropdown_item,BankList)
            BankList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerMethodDetail.adapter=BankList

            val SeatList=ArrayAdapter( this@DetailPemesanan,android.R.layout.simple_spinner_dropdown_item,SeatList)
            SeatList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerSeat.adapter=SeatList

            val PaymentMethodList=ArrayAdapter(/* context = */ this@DetailPemesanan,/* resource = */ android.R.layout.simple_spinner_dropdown_item,PaymentMethod)
            PaymentMethodList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerMethod.adapter=PaymentMethodList

            spinnerMethod.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val paymentMethod = spinnerMethod.selectedItem.toString()
                    if (paymentMethod == "Bayar di tempat") {
                        spinnerMethodDetail.visibility = View.INVISIBLE
                        layoutAccount.visibility = View.INVISIBLE
                    } else {
                        spinnerMethodDetail.visibility = View.VISIBLE
                        layoutAccount.visibility = View.VISIBLE
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
            spinnerSeat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val fee = when (spinnerSeat.selectedItem.toString()) {
                        "Reguler" -> 35000
                        "Premium" -> 50000
                        "VIP" -> 75000
                        "VVIP" -> 100000
                        else -> 0
                    }

                    val numberOfSeatsText = numberOfSeat.text.toString()

                    if (numberOfSeatsText.isNotEmpty()) {
                        val numberOfSeats = numberOfSeatsText.toInt()
                        TotalPayment = fee * numberOfSeats
                        tvJumlahKursi.text = " X "+numberOfSeats.toString()
                        seatCost.text = fee.toString()
                        totalPayment.text = TotalPayment.toString()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }


            btnPemesanan.setOnClickListener {
                val intent= Intent(this@DetailPemesanan,Ticket::class.java)
                intent.putExtra("Title",title)
                intent.putExtra("Image",image)
                intent.putExtra(EXTRA_BIOSKOP,spinnerBioskop.selectedItem.toString())
                intent.putExtra(EXTRA_DATE,DatePicker.text.toString())
                intent.putExtra(EXTRA_TIME,TimePicker.text.toString())
                intent.putExtra(EXTRA_FEE,seatCost.text)
                intent.putExtra(EXTRA_TOTAL_PAYMENT,totalPayment.text)
                intent.putExtra(EXTRA_NUMBER_OF_SEAT,numberOfSeat.text)
                intent.putExtra(EXTRA_SEAT,spinnerSeat.selectedItem.toString())
                intent.putExtra(EXTRA_METHOD,spinnerMethod.selectedItem.toString())
                startActivity(intent)
            }
        }
    }
    private fun updateLable(myCalendar: Calendar){
        val myFormat = "dd-MM-yyyy"
        val sdf= SimpleDateFormat(myFormat, Locale.UK)
        datePickers.setText(sdf.format(myCalendar.time))
    }
    private fun updateTimeLabel(myCalendar: Calendar){
        val myFormat = "HH:mm"
        val sdf= SimpleDateFormat(myFormat, Locale.UK)
        timePicker.setText(sdf.format(myCalendar.time))
    }
}

