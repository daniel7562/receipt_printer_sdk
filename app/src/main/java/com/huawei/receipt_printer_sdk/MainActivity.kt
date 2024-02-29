package com.huawei.receipt_printer_sdk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.huawei.receipt_printer_sdk.ui.theme.Receipt_printer_sdkTheme
import com.huawei.receiptprinter.PrinterManager
import com.huawei.receiptprinter.device.TestDevice1

class MainActivity : ComponentActivity() {
    private lateinit var printerManager: PrinterManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        printerManager = PrinterManager().apply {
            printerDevice = TestDevice1()
            receipt = PrintSample(
                title = "test title",
                barcode = "test barcode",
            )
        }

        setContent {
            Receipt_printer_sdkTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Button(
                        onClick = {
                            printerManager.print()
                        },
                        modifier = Modifier
                            .width(140.dp)
                            .height(60.dp),
                        content = {
                            Text(
                                text = "Test",
                            )
                        },
                    )
                }
            }
        }
    }
}
