part of baidu_ocr;

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
  static Future<CardFront> idcardOCROnlineFront() async {
    final dynamic res = await _channel.invokeMethod('idcardOCROnlineFront');
    return CardFront.fromJson(Map<String, dynamic>.from(res));
  }

  /// 身份证反面拍照识别
  static Future<CardBack> idcardOCROnlineBack() async {
    final dynamic res = await _channel.invokeMethod('idcardOCROnlineBack');
    return CardBack.fromJson(Map<String, dynamic>.from(res));
  }
}
