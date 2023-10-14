package com.example.uts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uts.databinding.TicketBinding
import java.sql.Time

class Ticket : AppCompatActivity() {
    private lateinit var binding: TicketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=TicketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title=intent.getStringExtra("Title")
        val image=intent.getIntExtra("Image",0)
        val Date=intent.getStringExtra(DetailPemesanan.EXTRA_DATE)
        val Time=intent.getStringExtra(DetailPemesanan.EXTRA_TIME)
        val Seat=intent.getStringExtra(DetailPemesanan.EXTRA_SEAT)
        val Bioskop=intent.getStringExtra(DetailPemesanan.EXTRA_BIOSKOP)
        val Fee=intent.getStringExtra(DetailPemesanan.EXTRA_FEE)
        val TotalPayment=intent.getStringExtra(DetailPemesanan.EXTRA_TOTAL_PAYMENT)
        val NumberOfSeat=intent.getStringExtra(DetailPemesanan.EXTRA_NUMBER_OF_SEAT)
        val PaymentMethod=intent.getStringExtra(DetailPemesanan.EXTRA_METHOD)

        with(binding){
            titleMovie.text=title
            tvBioskop.text=Bioskop
            dateMovie.text=Date+" "+Time
            seat.text=Seat
            payment.text=Fee
            numberOfSeat.text=NumberOfSeat
            totalPayment.text=TotalPayment
            paymentMethod.text=PaymentMethod
            imageMovie.setImageResource(image)
        }
    }
}