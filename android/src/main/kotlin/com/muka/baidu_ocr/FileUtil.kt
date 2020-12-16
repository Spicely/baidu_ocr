package com.muka.baidu_ocr

import android.content.Context
import java.io.File
import java.util.*


object FileUtil {
    private val map = HashMap<String, Any>()
    fun getSaveFile(context: Context, status: Boolean, field: String): File {
        if (status) {
            val date = Date(); // 当前时间
            map[field] = date.time;
        }
        return File(context.filesDir, "pic_${map[field]}.jpg")
    }
}
