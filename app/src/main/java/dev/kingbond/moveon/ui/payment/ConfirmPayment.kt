package dev.kingbond.moveon.ui.payment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import dev.kingbond.moveon.R
import kotlinx.android.synthetic.main.fragment_confirm_payment.*


class ConfirmPayment : Fragment(R.layout.fragment_confirm_payment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnConfirmPaymentMethodsGooglePay.setOnClickListener {
            val intent = Intent(context, googlepay::class.java)
            startActivity(intent)
        }

    }

    fun onConfirmPaymentRadioButtonClicked(view: View) {
        val checked = view as RadioButton
        if (cp_wallet == checked) {

        }
        if (cp_cash == checked) {

        }
        if (cp_paytm == checked) {

        }
        if (cp_google_pay == checked) {

        }
    }

}
/*
{
        var checked = view as RadioButton
        if(rb_wallet == checked) {
            message(rb_wallet.text.toString() + if (rb_wallet.isChecked) " Checked " else " UnChecked ")
        }
        if (rb_cash == checked) {
            message(rb_cash.text.toString() + if (rb_cash.isChecked) " Checked " else " UnChecked ")
        }
        if (rb_paytm == checked) {
            message(rb_paytm.text.toString() + if (rb_paytm.isChecked) " Checked " else " UnChecked ")
        }

        if (rb_google_pay == checked) {
            message(rb_google_pay.text.toString() + if (rb_google_pay.isChecked) " Checked " else " UnChecked ")
        }
    }
 */