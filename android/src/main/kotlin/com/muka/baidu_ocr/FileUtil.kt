package com.muka.baidu_ocr

import android.content.Context
import java.io.File


object FileUtil {
    fun getSaveFile(context: Context): File {
        return File(context.filesDir, "pic.jpg")
    }
}
