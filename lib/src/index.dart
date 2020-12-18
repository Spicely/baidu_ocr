part of baidu_ocr;

class BaiduOcr {
  static const MethodChannel _channel = const MethodChannel('com.muka.baidu_ocr');

  static Future<bool?> init(String appKey, String secretKey) async {
    bool? res = await _channel.invokeMethod('init', {
      'appKey': appKey,
      'secretKey': secretKey,
    });
    return res;
  }

  /// 身份证正面拍照识别
  static Future<IdCardFrontOcr> idcardOCROnlineFront() async {
    final dynamic res = await _channel.invokeMethod('idCardOCROnlineFront');
    return IdCardFrontOcr.fromJson(Map<String, dynamic>.from(res));
  }

  /// 身份证反面拍照识别
  static Future<IdCardBackOcr> idcardOCROnlineBack() async {
    final dynamic res = await _channel.invokeMethod('idCardOCROnlineBack');
    return IdCardBackOcr.fromJson(Map<String, dynamic>.from(res));
  }

  /// 银行卡正面拍照识别
  static Future<BankCardOcr> bankCardOCROnline() async {
    final dynamic res = await _channel.invokeMethod('bankCardOCROnline');
    return BankCardOcr.fromJson(Map<String, dynamic>.from(res));
  }
}
