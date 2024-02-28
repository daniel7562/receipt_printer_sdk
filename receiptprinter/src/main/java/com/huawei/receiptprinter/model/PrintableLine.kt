package com.huawei.receiptprinter.model

abstract class PrintableLine<T> {

    var content: T? = null

    var align: Int = -1
}
