package com.muka.baidu_ocr

import android.content.Context
import com.baidu.ocr.sdk.OCR
import com.baidu.ocr.sdk.OnResultListener
import com.baidu.ocr.sdk.exception.OCRError
import com.baidu.ocr.sdk.model.*
import org.json.JSONObject
import java.io.File
import java.util.*
import kotlin.collections.HashMap

/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */


/**
 * Created by ruanshimin on 2017/4/20.
 */
object RecognizeService {
    fun recGeneral(ctx: Context?, filePath: String?, listener: ServiceListener) {
        val param = GeneralParams()
        param.setDetectDirection(true)
        param.setVertexesLocation(true)
        param.setRecognizeGranularity(GeneralParams.GRANULARITY_SMALL)
        param.imageFile = File(filePath)
        OCR.getInstance(ctx).recognizeGeneral(param, object : OnResultListener<GeneralResult> {
            override fun onResult(result: GeneralResult) {
                val sb = StringBuilder()
                for (wordSimple in result.wordList) {
                    val word = wordSimple as Word
                    sb.append(word.words)
                    sb.append("\n")
                }
                listener.onResult(result.jsonRes)
            }

            override fun onError(error: OCRError) {
                listener.onResult(error.message)
            }
        })
    }

    fun recAccurate(ctx: Context?, filePath: String?, listener: ServiceListener) {
        val param = GeneralParams()
        param.setDetectDirection(true)
        param.setVertexesLocation(true)
        param.setRecognizeGranularity(GeneralParams.GRANULARITY_SMALL)
        param.imageFile = File(filePath)
        OCR.getInstance(ctx).recognizeAccurate(param, object : OnResultListener<GeneralResult> {
            override fun onResult(result: GeneralResult) {
                val sb = StringBuilder()
                for (wordSimple in result.wordList) {
                    val word = wordSimple as Word
                    sb.append(word.words)
                    sb.append("\n")
                }
                listener.onResult(result.jsonRes)
            }

            override fun onError(error: OCRError) {
                listener.onResult(error.message)
            }
        })
    }

    fun recAccurateBasic(ctx: Context?, filePath: String?, listener: ServiceListener) {
        val param = GeneralParams()
        param.setDetectDirection(true)
        param.setVertexesLocation(true)
        param.setRecognizeGranularity(GeneralParams.GRANULARITY_SMALL)
        param.imageFile = File(filePath)
        OCR.getInstance(ctx).recognizeAccurateBasic(param, object : OnResultListener<GeneralResult> {
            override fun onResult(result: GeneralResult) {
                val sb = StringBuilder()
                for (wordSimple in result.wordList) {
                    sb.append(wordSimple.words)
                    sb.append("\n")
                }
                listener.onResult(result.jsonRes)
            }

            override fun onError(error: OCRError) {
                listener.onResult(error.message)
            }
        })
    }

    fun recGeneralBasic(ctx: Context?, filePath: String?, listener: ServiceListener) {
        val param = GeneralBasicParams()
        param.setDetectDirection(true)
        param.imageFile = File(filePath)
        OCR.getInstance(ctx).recognizeGeneralBasic(param, object : OnResultListener<GeneralResult> {
            override fun onResult(result: GeneralResult) {
                val sb = StringBuilder()
                for (wordSimple in result.wordList) {
                    sb.append(wordSimple.words)
                    sb.append("\n")
                }
                listener.onResult(result.jsonRes)
            }

            override fun onError(error: OCRError) {
                listener.onResult(error.message)
            }
        })
    }

    fun recGeneralEnhanced(ctx: Context?, filePath: String?, listener: ServiceListener) {
        val param = GeneralBasicParams()
        param.setDetectDirection(true)
        param.imageFile = File(filePath)
        OCR.getInstance(ctx).recognizeGeneralEnhanced(param, object : OnResultListener<GeneralResult> {
            override fun onResult(result: GeneralResult) {
                val sb = StringBuilder()
                for (wordSimple in result.wordList) {
                    sb.append(wordSimple.words)
                    sb.append("\n")
                }
                listener.onResult(result.jsonRes)
            }

            override fun onError(error: OCRError) {
                listener.onResult(error.message)
            }
        })
    }

    fun recWebimage(ctx: Context?, filePath: String?, listener: ServiceListener) {
        val param = GeneralBasicParams()
        param.setDetectDirection(true)
        param.imageFile = File(filePath)
        OCR.getInstance(ctx).recognizeWebimage(param, object : OnResultListener<GeneralResult> {
            override fun onResult(result: GeneralResult) {
                val sb = StringBuilder()
                for (wordSimple in result.wordList) {
                    sb.append(wordSimple.words)
                    sb.append("\n")
                }
                listener.onResult(result.jsonRes)
            }

            override fun onError(error: OCRError) {
                listener.onResult(error.message)
            }
        })
    }

    fun recBankCard(ctx: Context?, filePath: String?, listener: ServiceListener) {
        val param = BankCardParams()
        param.imageFile = File(filePath)
        OCR.getInstance(ctx).recognizeBankCard(param, object : OnResultListener<BankCardResult> {
            override fun onResult(result: BankCardResult) {
                var data = HashMap<String, String?>()
                data["bankCardNumber"] = result.bankCardNumber?.toString()
                data["bankCardType"] = result.bankCardType.name?.toString()
                data["bankName"] = result.bankName?.toString()
                data["filePath"] = filePath
                listener.onResult(getMapToString(data))
            }

            override fun onError(error: OCRError) {
                listener.onResult(error.message)
            }
        })
    }
    fun getMapToString(map: Map<String, String?>): String? {
        val keySet = map.keys
        //将set集合转换为数组
        val keyArray = keySet.toTypedArray()
        //给数组排序(升序)
        Arrays.sort(keyArray)
        //因为String拼接效率会很低的，所以转用StringBuilder。博主会在这篇博文发后不久，会更新一篇String与StringBuilder开发时的抉择的博文。
        val sb = java.lang.StringBuilder()
        for (i in keyArray.indices) {
            // 参数值为空，则不参与签名 这个方法trim()是去空格
            if (map[keyArray[i]]!!.trim { it <= ' ' }.isNotEmpty()) {
                sb.append(keyArray[i]).append("=").append(map[keyArray[i]]!!.trim { it <= ' ' })
            }
            if (i != keyArray.size - 1) {
                sb.append("&")
            }
        }
        return sb.toString()
    }

    fun recVehicleLicense(ctx: Context?, filePath: String?, listener: ServiceListener) {
        val param = OcrRequestParams()
        param.imageFile = File(filePath)
        OCR.getInstance(ctx).recognizeVehicleLicense(param, object : OnResultListener<OcrResponseResult> {
            override fun onResult(result: OcrResponseResult) {
                listener.onResult(result.jsonRes)
            }

            override fun onError(error: OCRError) {
                listener.onResult(error.message)
            }
        })
    }

    fun recDrivingLicense(ctx: Context?, filePath: String?, listener: ServiceListener) {
        val param = OcrRequestParams()
        param.imageFile = File(filePath)
        OCR.getInstance(ctx).recognizeDrivingLicense(param, object : OnResultListener<OcrResponseResult> {
            override fun onResult(result: OcrResponseResult) {
                listener.onResult(result.jsonRes)
            }

            override fun onError(error: OCRError) {
                listener.onResult(error.message)
            }
        })
    }

    fun recLicensePlate(ctx: Context?, filePath: String?, listener: ServiceListener) {
        val param = OcrRequestParams()
        param.imageFile = File(filePath)
        OCR.getInstance(ctx).recognizeLicensePlate(param, object : OnResultListener<OcrResponseResult> {
            override fun onResult(result: OcrResponseResult) {
                listener.onResult(result.jsonRes)
            }

            override fun onError(error: OCRError) {
                listener.onResult(error.message)
            }
        })
    }

    fun recBusinessLicense(ctx: Context?, filePath: String?, listener: ServiceListener) {
        val param = OcrRequestParams()
        param.imageFile = File(filePath)
        OCR.getInstance(ctx).recognizeBusinessLicense(param, object : OnResultListener<OcrResponseResult> {
            override fun onResult(result: OcrResponseResult) {
                listener.onResult(result.jsonRes)
            }

            override fun onError(error: OCRError) {
                listener.onResult(error.message)
            }
        })
    }

    fun recReceipt(ctx: Context?, filePath: String?, listener: ServiceListener) {
        val param = OcrRequestParams()
        param.imageFile = File(filePath)
        param.putParam("detect_direction", "true")
        OCR.getInstance(ctx).recognizeReceipt(param, object : OnResultListener<OcrResponseResult> {
            override fun onResult(result: OcrResponseResult) {
                listener.onResult(result.jsonRes)
            }

            override fun onError(error: OCRError) {
                listener.onResult(error.message)
            }
        })
    }

    fun recPassport(ctx: Context?, filePath: String?, listener: ServiceListener) {
        val param = OcrRequestParams()
        param.imageFile = File(filePath)
        OCR.getInstance(ctx).recognizePassport(param, object : OnResultListener<OcrResponseResult> {
            override fun onResult(result: OcrResponseResult) {
                listener.onResult(result.jsonRes)
            }

            override fun onError(error: OCRError) {
                listener.onResult(error.message)
            }
        })
    }

    fun recVatInvoice(ctx: Context?, filePath: String?, listener: ServiceListener) {
        val param = OcrRequestParams()
        param.imageFile = File(filePath)
        OCR.getInstance(ctx).recognizeVatInvoice(param, object : OnResultListener<OcrResponseResult> {
            override fun onResult(result: OcrResponseResult) {
                listener.onResult(result.jsonRes)
            }

            override fun onError(error: OCRError) {
                listener.onResult(error.message)
            }
        })
    }

    fun recQrcode(ctx: Context?, filePath: String?, listener: ServiceListener) {
        val param = OcrRequestParams()
        param.imageFile = File(filePath)
        OCR.getInstance(ctx).recognizeQrcode(param, object : OnResultListener<OcrResponseResult> {
            override fun onResult(result: OcrResponseResult) {
                listener.onResult(result.jsonRes)
            }

            override fun onError(error: OCRError) {
                listener.onResult(error.message)
            }
        })
    }

    fun recNumbers(ctx: Context?, filePath: String?, listener: ServiceListener) {
        val param = OcrRequestParams()
        param.imageFile = File(filePath)
        OCR.getInstance(ctx).recognizeNumbers(param, object : OnResultListener<OcrResponseResult> {
            override fun onResult(result: OcrResponseResult) {
                listener.onResult(result.jsonRes)
            }

            override fun onError(error: OCRError) {
                listener.onResult(error.message)
            }
        })
    }

    fun recLottery(ctx: Context?, filePath: String?, listener: ServiceListener) {
        val param = OcrRequestParams()
        param.imageFile = File(filePath)
        OCR.getInstance(ctx).recognizeLottery(param, object : OnResultListener<OcrResponseResult> {
            override fun onResult(result: OcrResponseResult) {
                listener.onResult(result.jsonRes)
            }

            override fun onError(error: OCRError) {
                listener.onResult(error.message)
            }
        })
    }

    fun recBusinessCard(ctx: Context?, filePath: String?, listener: ServiceListener) {
        val param = OcrRequestParams()
        param.imageFile = File(filePath)
        OCR.getInstance(ctx).recognizeBusinessCard(param, object : OnResultListener<OcrResponseResult> {
            override fun onResult(result: OcrResponseResult) {
                listener.onResult(result.jsonRes)
            }

            override fun onError(error: OCRError) {
                listener.onResult(error.message)
            }
        })
    }

    fun recHandwriting(ctx: Context?, filePath: String?, listener: ServiceListener) {
        val param = OcrRequestParams()
        param.imageFile = File(filePath)
        OCR.getInstance(ctx).recognizeHandwriting(param, object : OnResultListener<OcrResponseResult> {
            override fun onResult(result: OcrResponseResult) {
                listener.onResult(result.jsonRes)
            }

            override fun onError(error: OCRError) {
                listener.onResult(error.message)
            }
        })
    }

    fun recCustom(ctx: Context?, filePath: String?, listener: ServiceListener) {
        val param = OcrRequestParams()
        param.putParam("templateSign", "")
        param.putParam("classifierId", 0)
        param.imageFile = File(filePath)
        OCR.getInstance(ctx).recognizeCustom(param, object : OnResultListener<OcrResponseResult> {
            override fun onResult(result: OcrResponseResult) {
                listener.onResult(result.jsonRes)
            }

            override fun onError(error: OCRError) {
                listener.onResult(error.message)
            }
        })
    }

    interface ServiceListener {
        fun onResult(result: String?)
    }
}
