package com.muka.baidu_ocr

import android.content.Context
import java.io.File
import java.util.*


object FileUtil {
    fun getSaveFile(context: Context): File {
        val date = Date(); // 当前时间
        return File(context.filesDir, "pic_${date.time}.jpg")
    }
}
