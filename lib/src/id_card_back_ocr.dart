part of baidu_ocr;

class IdCardBackOcr {
  /// 有效日期
  final String signDate;

  /// 失效日期
  final String expiryDate;

  /// 发行
  final String issueAuthority;

  /// 文件路径
  final String filePath;

  IdCardBackOcr(
    this.signDate,
    this.expiryDate,
    this.issueAuthority,
    this.filePath,
  );

  factory IdCardBackOcr.fromJson(Map<String, dynamic> json) => _$IdCardBackOcrFromJson(json);

  Map<String, dynamic> toJson() => _$IdCardBackOcrToJson(this);
}

IdCardBackOcr _$IdCardBackOcrFromJson(Map<String, dynamic> json) {
  return IdCardBackOcr(
    json['signDate'] as String,
    json['expiryDate'] as String,
    json['issueAuthority'] as String,
    json['filePath'] as String,
  );
}

Map<String, dynamic> _$IdCardBackOcrToJson(IdCardBackOcr instance) => <String, dynamic>{
      'signDate': instance.signDate,
      'expiryDate': instance.expiryDate,
      'issueAuthority': instance.issueAuthority,
      'filePath': instance.filePath,
    };
