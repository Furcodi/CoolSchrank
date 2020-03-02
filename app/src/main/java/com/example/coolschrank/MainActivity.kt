package com.example.coolschrank

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var scannedResult: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnScan.setOnClickListener {
            run {
                val integrator = IntentIntegrator(this)
                integrator.setPrompt("Scan a barcode")
                //integrator.setCameraId(0) // Use a specific camera of the device
                integrator.setOrientationLocked(false)
                integrator.setBeepEnabled(true)
                //integrator.captureActivity = CaptureActivityPortrait.class
                integrator.initiateScan()
                //IntentIntegrator(this@MainActivity).initiateScan();

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result: IntentResult? = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if(result != null){

            if(result.contents != null){
                scannedResult = result.contents
                txtValue.text = scannedResult
            } else {
                txtValue.text = "scan failed"
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {

        outState.putString("scannedResult", scannedResult)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState.let {
            scannedResult = it.getString("scannedResult")
            txtValue.text = scannedResult
        }
    }


}
