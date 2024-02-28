package com.huawei.receiptprinter

import com.huawei.receiptprinter.model.BarcodePrintableLine
import com.huawei.receiptprinter.model.CutPrintableLine
import com.huawei.receiptprinter.model.FeedPrintableLine
import com.huawei.receiptprinter.model.PrintableLine
import com.huawei.receiptprinter.model.ReceiptContent
import com.huawei.receiptprinter.model.TextPrintableLine
import java.lang.reflect.Field

class PrinterManager {

    var printerDevice: PrinterDevice? = null
        set(value) {
            printerDevice = value
            field = value
        }

    var receipt: Any? = null

    var onPrintListener: PrinterDevice.OnPrintListener? = null
        set(value) {
            printerDevice?.let { device ->
                device.listener = value
            }
            field = value
        }

    fun print() {
        printerDevice?.let { device ->
            device.init()
            var printableLines: List<PrintableLine<*>>? = null

            receipt?.let {
                printableLines = createPrintableLines(it)
            }
            printableLines?.let {
                val receiptContent = ReceiptContent(it)
                device.print(receiptContent)
            }
        }

        receipt = null
    }

    private fun <T> createPrintableLines(receipt: T): List<PrintableLine<*>> {
        val printableLines = mutableListOf<PrintableLine<*>>()
        val fields = receipt!!::class.java.declaredFields
        val sortedFields = sortFields(fields)

        sortedFields.forEach { field ->
            field.isAccessible = true
            val annotations = field.annotations

            annotations.forEach { annotation ->
                when (annotation) {
                    is TextFormat -> {
                        var value: Any? = null
                        try {
                            value = field.get(receipt)
                        } catch (ex: Exception) {
                            ex.printStackTrace()
                        }

                        val printableLine = TextPrintableLine().apply {
                            fontHeightSize = annotation.fontHeightSize
                            fontWidthSize = annotation.fontWidthSize
                            align = annotation.align
                            fontStyle = annotation.fontStyle
                            content = value as? String
                        }

                        printableLines.add(printableLine)
                    }

                    is BarcodeFormat -> {
                        var value: Any? = null
                        try {
                            value = field.get(receipt)
                        } catch (ex: Exception) {
                            ex.printStackTrace()
                        }

                        val printableLine = BarcodePrintableLine().apply {
                            option = annotation.option
                            width = annotation.width
                            height = annotation.height
                            mode = annotation.mode
                            align = annotation.align
                            content = value as? String
                        }

                        printableLines.add(printableLine)
                    }

                    is FeedFormat -> {
                        val printableLine = FeedPrintableLine().apply {
                            line = annotation.line
                        }

                        printableLines.add(printableLine)
                    }

                    is CutFormat -> {
                        val printableLine = CutPrintableLine().apply {
                            line = annotation.line
                            type = annotation.type
                        }

                        printableLines.add(printableLine)
                    }
                }
            }
        }

        return printableLines
    }

    private fun sortFields(fields: Array<Field>): Array<Field> {
        return fields.sortedBy { it.getAnnotation(Order::class.java)!!.line }.toTypedArray()
    }
}
