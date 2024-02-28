package com.huawei.receiptprinter

import com.huawei.receiptprinter.model.BarcodePrintableLine
import com.huawei.receiptprinter.model.CutPrintableLine
import com.huawei.receiptprinter.model.FeedPrintableLine
import com.huawei.receiptprinter.model.PrintableLine
import com.huawei.receiptprinter.model.ReceiptContent
import com.huawei.receiptprinter.model.TextPrintableLine

abstract class PrinterDevice {

    interface OnPrintListener {
        fun onSuccess()
        fun onFailure(code: Int, errorMessage: String)
    }

    var listener: OnPrintListener? = null

    abstract fun init()

    abstract fun isReady()

    abstract fun printText(printableLine: TextPrintableLine)

    abstract fun printBarcode(printableLine: BarcodePrintableLine)

    abstract fun paperCut(printableLine: CutPrintableLine)

    abstract fun printFeed(line: Int)

    abstract fun print(receiptContent: ReceiptContent)

    abstract fun close()

    fun <T> printLine(printableLine: PrintableLine<T>) {
        when (printableLine) {
            is TextPrintableLine -> {
                printText(printableLine)
            }

            is BarcodePrintableLine -> {
                printBarcode(printableLine)
            }

            is CutPrintableLine -> {
                paperCut(printableLine)
            }

            is FeedPrintableLine -> {
                printFeed(printableLine.line)
            }
        }
    }
}
