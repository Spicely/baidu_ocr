import 'dart:async';
import 'dart:convert';

import 'package:flutter/services.dart';

class BaiduOcr {
  static const MethodChannel _channel = const MethodChannel('com.muka.baidu_ocr');

  static Future<bool> init(String appKey, String secretKey) async {
    bool res = await _channel.invokeMethod('init', {
      'appKey': appKey,
      'secretKey': secretKey,
    });
    return res;
  }

  /// 身份证正面拍照识别
  static Future<Map<String, dynamic>> idcardOCROnlineFront() async {
    final String jsonStr = await _channel.invokeMethod('idcardOCROnlineFront');

    Map<String, dynamic> res = json.decode(jsonStr);
    return res;
  }
}
