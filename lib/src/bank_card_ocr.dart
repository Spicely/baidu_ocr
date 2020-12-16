part of baidu_ocr;

class BankCardOcr {
  /// 银行卡卡号
  final String bankCardNumber;

  /// 银行卡类型
  final String bankCardType;

  /// 银行卡名称
  final String bankName;

  /// 文件路径
  final String filePath;

  BankCardOcr(
    this.bankName,
    this.bankCardType,
    this.bankCardNumber,
    this.filePath,
  );

  factory BankCardOcr.fromJson(Map<String, dynamic> json) => _$BankCardOcrFromJson(json);

  Map<String, dynamic> toJson() => _$BankCardOcrToJson(this);
}

BankCardOcr _$BankCardOcrFromJson(Map<String, dynamic> json) {
  return BankCardOcr(
    json['bankName'] as String,
    json['bankCardType'] as String,
    json['bankCardNumber'] as String,
    json['filePath'] as String,
  );
}

Map<String, dynamic> _$BankCardOcrToJson(BankCardOcr instance) => <String, dynamic>{
      'bankName': instance.bankName,
      'bankCardType': instance.bankCardType,
      'bankCardNumber': instance.bankCardNumber,
      'filePath': instance.filePath,
    };
