package com.huawei.receiptprinter

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class TextFormat(
    val fontHeightSize: Int,
    val fontWidthSize: Int,
    val fontStyle: Int,
    val align: Int,
)

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class BarcodeFormat(
    val option: Int,
    val height: Int,
    val width: Int,
    val align: Int,
    val mode: Int,
)

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Order(val line: Int)

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class FeedFormat(val line: Int)

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class CutFormat(val line: Int, val type: Int)

val ALIGN_LEFT = 0
val ALIGN_CENTER = 1
val ALIGN_RIGHT = 2

val BARCODE_CODE39 = 6
val BARCODE_CODE93 = 9
val BARCODE_CODE128 = 10
