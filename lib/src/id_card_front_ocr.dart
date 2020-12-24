part of baidu_ocr;

class IdCardFrontOcr {
  /// 姓名
  final String? name;

  /// 性别
  final String? gender;

  /// 民族
  final String? ethnic;

  /// 出身日期
  final String? birthday;

  /// 户籍
  final String? address;

  /// 身份证号
  final String? number;

  /// 文件路径
  final String? filePath;

  IdCardFrontOcr(
    this.name,
    this.gender,
    this.ethnic,
    this.birthday,
    this.address,
    this.number,
    this.filePath,
  );

  factory IdCardFrontOcr.fromJson(Map<String, dynamic> json) => _$IdCardFrontOcrFromJson(json);

  Map<String, dynamic> toJson() => _$IdCardFrontOcrToJson(this);
}

IdCardFrontOcr _$IdCardFrontOcrFromJson(Map<String, dynamic> json) {
  return IdCardFrontOcr(
    json['name'] as String?,
    json['gender'] as String?,
    json['ethnic'] as String?,
    json['birthday'] as String?,
    json['address'] as String?,
    json['number'] as String?,
    json['filePath'] as String?,
  );
}

Map<String, dynamic> _$IdCardFrontOcrToJson(IdCardFrontOcr instance) => <String, dynamic>{
      'name': instance.name,
      'gender': instance.gender,
      'ethnic': instance.ethnic,
      'birthday': instance.birthday,
      'address': instance.address,
      'number': instance.number,
      'filePath': instance.filePath,
    };
