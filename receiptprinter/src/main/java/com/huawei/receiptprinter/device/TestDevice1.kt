package com.huawei.receiptprinter.device

import android.util.Log
import com.huawei.receiptprinter.PrinterDevice
import com.huawei.receiptprinter.model.BarcodePrintableLine
import com.huawei.receiptprinter.model.CutPrintableLine
import com.huawei.receiptprinter.model.ReceiptContent
import com.huawei.receiptprinter.model.TextPrintableLine

class TestDevice1 : PrinterDevice() {
    override fun init() {
        Log.d(TAG, "init")
    }

    override fun isReady(): Boolean {
        Log.d(TAG, "isReady")
        return true
    }

    override fun printText(printableLine: TextPrintableLine) {
        Log.d(TAG, "printText")
        Log.d(TAG, "Text: ${printableLine.content}")
    }

    override fun printBarcode(printableLine: BarcodePrintableLine) {
        Log.d(TAG, "printBarcode")
        Log.d(TAG, "Barcode: ${printableLine.content}")
    }

    override fun paperCut(printableLine: CutPrintableLine) {
        Log.d(TAG, "paperCut")
        Log.d(TAG, "lines: ${printableLine.line}, type: ${printableLine.type}")
    }

    override fun printFeed(line: Int) {
        Log.d(TAG, "printFeed")
        Log.d(TAG, "line: $line")
    }

    override fun print(receiptContent: ReceiptContent) {
        Log.d(TAG, "print")
        receiptContent.contentList.forEach {
            printLine(it)
        }
    }

    override fun close() {
        Log.d(TAG, "close")
    }

    companion object {
        private val TAG = TestDevice1::class.java.simpleName
    }
}