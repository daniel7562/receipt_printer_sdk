package com.huawei.receipt_printer_sdk

import com.huawei.receiptprinter.ALIGN_CENTER
import com.huawei.receiptprinter.BarcodeFormat
import com.huawei.receiptprinter.CutFormat
import com.huawei.receiptprinter.FeedFormat
import com.huawei.receiptprinter.Order
import com.huawei.receiptprinter.TextFormat

data class PrintSample(
    @Order(value = 1)
    @TextFormat(fontHeightSize = 1, fontWidthSize = 1, fontStyle = 1, align = 1)
    val title: String,
    @Order(value = 2)
    @BarcodeFormat(option = 1, width = 20, height = 10, align = ALIGN_CENTER, mode = 1)
    val barcode: String,
    @Order(value = 3)
    @FeedFormat(line = 3)
    val feed: Unit = Unit,
    @Order(value = 4)
    @CutFormat(line = 1, type = 1)
    val cut: Unit = Unit
)
