package com.muka.baidu_ocr

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import androidx.annotation.NonNull
import com.baidu.ocr.sdk.OCR
import com.baidu.ocr.sdk.OnResultListener
import com.baidu.ocr.sdk.exception.OCRError
import com.baidu.ocr.sdk.model.*
import com.baidu.ocr.ui.camera.CameraActivity
import com.muka.baidu_ocr.FileUtil.getSaveFile
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry
import io.flutter.plugin.common.PluginRegistry.Registrar
import java.io.File


/** BaiduOcrPlugin */
public class BaiduOcrPlugin : FlutterPlugin, MethodCallHandler, ActivityAware, PluginRegistry.ActivityResultListener {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel: MethodChannel

    private lateinit var activity: Activity

    private var resultMap = HashMap<String, Result>()

    private  var bankFilePath = ""

    //身份证
    private val REQUEST_CODE_CAMERA = 102

    //银行卡识别
    private val REQUEST_CODE_BANKCARD = 111


    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.getFlutterEngine().dartExecutor, "com.muka.baidu_ocr")
        channel.setMethodCallHandler(this);
    }

    override fun onDetachedFromActivity() {
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        this.activity = binding.activity
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        this.activity = binding.activity
        binding.addActivityResultListener(this)
    }

    override fun onDetachedFromActivityForConfigChanges() {
    }

    // This static function is optional and equivalent to onAttachedToEngine. It supports the old
    // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
    // plugin registration via this function while apps migrate to use the new Android APIs
    // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
    //
    // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
    // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
    // depending on the user's project. onAttachedToEngine or registerWith must both be defined
    // in the same class.
    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "com.muka.baidu_ocr")
            channel.setMethodCallHandler(BaiduOcrPlugin())
        }
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        when (call.method) {
            "init" -> {
                val appKey: String = call.argument<String>("appKey").toString()
                val secretKey: String = call.argument<String>("secretKey").toString()
                OCR.getInstance(activity).initAccessTokenWithAkSk(object : OnResultListener<AccessToken> {
                    override fun onResult(res: AccessToken) {

                    }

                    override fun onError(error: OCRError) {
                        result.success(false)
                        error.printStackTrace()
                    }
                }, activity, appKey, secretKey)
                result.success(true)
            }
            "idCardOCROnlineFront" -> {
                idCardOCROnlineFrontCall(call, result);
            }
            "idCardOCROnlineBack" -> {
                idCardOCROnlineBackCall(call, result)
            }
            "bankCardOCROnline" -> {
                bankCardOCROnlineCall(call, result)
            }
            else -> result.notImplemented()
        }
    }

    /// 身份证正面拍照识别
    private fun idCardOCROnlineFrontCall(call: MethodCall, result: Result) {
        val intent = Intent(activity, CameraActivity::class.java)
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, getSaveFile(activity.applicationContext,true,"idCardOCROnlineFrontCall").absolutePath)
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT)
        activity.startActivityForResult(intent, REQUEST_CODE_CAMERA)
        resultMap[CameraActivity.CONTENT_TYPE_ID_CARD_FRONT] = result
    } /// 身份证反面拍照识别

    private fun idCardOCROnlineBackCall(call: MethodCall, result: Result) {
        val intent = Intent(activity, CameraActivity::class.java)
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, getSaveFile(activity.applicationContext,true,"idCardOCROnlineBackCall").absolutePath)
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_BACK)
        activity.startActivityForResult(intent, REQUEST_CODE_CAMERA)
        resultMap[CameraActivity.CONTENT_TYPE_ID_CARD_BACK] = result
    }

    /// 银行卡识别
    private fun bankCardOCROnlineCall(call: MethodCall, result: Result) {
        val intent = Intent(activity, CameraActivity::class.java)
        bankFilePath = getSaveFile(activity,true,"bankCardOCROnlineCall").absolutePath
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, bankFilePath)
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_BANK_CARD)
        activity.startActivityForResult(intent, REQUEST_CODE_BANKCARD)
        resultMap[java.lang.String.valueOf(REQUEST_CODE_BANKCARD)] = result
    }


    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        // 身份证
        if (requestCode === REQUEST_CODE_CAMERA && resultCode === Activity.RESULT_OK) {
            if (data != null) {
                val contentType: String = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE)
                if (!TextUtils.isEmpty(contentType)) {
                    if (CameraActivity.CONTENT_TYPE_ID_CARD_FRONT == contentType) {
                        val filePath = getSaveFile(activity.applicationContext, false,"idCardOCROnlineFrontCall").absolutePath
                        // 身份证正面
                        recIDCard(IDCardParams.ID_CARD_SIDE_FRONT, filePath, contentType)
                    } else if (CameraActivity.CONTENT_TYPE_ID_CARD_BACK == contentType) {
                        val filePath = getSaveFile(activity.applicationContext, false, "idCardOCROnlineBackCall").absolutePath
                        // 身份证反面
                        recIDCard(IDCardParams.ID_CARD_SIDE_BACK, filePath, contentType)
                    }
                }
            }
        }

        // 识别成功回调，银行卡识别

        // 识别成功回调，银行卡识别
        if (requestCode === REQUEST_CODE_BANKCARD && resultCode === Activity.RESULT_OK) {
            val param = BankCardParams()
            param.imageFile = File(bankFilePath)
            OCR.getInstance(activity).recognizeBankCard(param, object : OnResultListener<BankCardResult> {
                override fun onResult(result: BankCardResult) {
                    var data = HashMap<String, String?>()
                    data["bankCardNumber"] = result.bankCardNumber?.toString()
                    data["bankCardType"] = result.bankCardType.name?.toString()
                    data["bankName"] = result.bankName?.toString()
                    data["filePath"] = bankFilePath
                    val result1 = resultMap[REQUEST_CODE_BANKCARD.toString()]
                    result1?.success(data)
                }

                override fun onError(error: OCRError) {
                    val result1 = resultMap[REQUEST_CODE_BANKCARD.toString()]
                    result1?.success(null)
                }
            })
        }
        return true
    }

    private fun recIDCard(idCardSide: String, filePath: String, contentType: String) {
        val param = IDCardParams()
        param.imageFile = File(filePath)
        // 设置身份证正反面
        param.idCardSide = idCardSide
        // 设置方向检测
        param.isDetectDirection = true
        // 设置图像参数压缩质量0-100, 越大图像质量越好但是请求时间越长。 不设置则默认值为20
        param.imageQuality = 20
        OCR.getInstance(activity).recognizeIDCard(param, object : OnResultListener<IDCardResult?> {
            override fun onResult(result: IDCardResult?) {
                if (result != null) {
                    val result1: Result? = resultMap[contentType]
                    var data = HashMap<String, String?>()
                    data["name"] = result.name?.toString()
                    data["gender"] = result.gender?.toString()
                    data["ethnic"] = result.ethnic?.toString()
                    data["birthday"] = result.birthday?.toString()
                    data["address"] = result.address?.toString()
                    data["number"] = result.idNumber?.toString()
                    data["signDate"] = result.signDate?.toString()
                    data["expiryDate"] = result.expiryDate?.toString()
                    data["issueAuthority"] = result.issueAuthority?.toString()
                    data["filePath"] = filePath
                    result1?.success(data)
                }
            }

            override fun onError(error: OCRError) {
                val result1: Result? = resultMap[contentType]
                result1?.error(error.errorCode.toString(), error.message, null)
            }
        })
    }
}
