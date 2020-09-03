part of baidu_ocr;

class CardBack {
  /// 有效日期
  final String signDate;

  /// 失效日期
  final String expiryDate;

  /// 发行
  final String issueAuthority;

  /// 文件路径
  final String filePath;

  CardBack(
    this.signDate,
    this.expiryDate,
    this.issueAuthority,
    this.filePath,
  );

  factory CardBack.fromJson(Map<dynamic, dynamic> json) => _$CardBackFromJson(json);

  Map<String, dynamic> toJson() => _$CardBackToJson(this);
}

CardBack _$CardBackFromJson(Map<String, dynamic> json) {
  return CardBack(
    json['signDate'] as String,
    json['expiryDate'] as String,
    json['issueAuthority'] as String,
    json['filePath'] as String,
  );
}

Map<String, dynamic> _$CardBackToJson(CardBack instance) => <String, dynamic>{
      'signDate': instance.signDate,
      'expiryDate': instance.expiryDate,
      'issueAuthority': instance.issueAuthority,
      'filePath': instance.filePath,
    };
