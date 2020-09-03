part of baidu_ocr;

class CardFront {
  /// 姓名
  final String name;

  /// 性别
  final String gender;

  /// 民族
  final String ethnic;

  /// 出身日期
  final String birthday;

  /// 户籍
  final String address;

  /// 身份证号
  final String number;

  /// 文件路径
  final String filePath;

  CardFront({
    this.name,
    this.gender,
    this.ethnic,
    this.birthday,
    this.address,
    this.number,
    this.filePath,
  });

  factory CardFront.fromJson(Map<dynamic, dynamic> json) => _$CardFrontFromJson(json);

  Map<String, dynamic> toJson() => _$CardFrontToJson(this);
}

CardFront _$CardFrontFromJson(Map<String, dynamic> json) {
  return CardFront(
    name: json['name'] as String,
    gender: json['gender'] as String,
    ethnic: json['ethnic'] as String,
    birthday: json['birthday'] as String,
    address: json['address'] as String,
    number: json['number'] as String,
    filePath: json['filePath'] as String,
  );
}

Map<String, dynamic> _$CardFrontToJson(CardFront instance) => <String, dynamic>{
      'name': instance.name,
      'gender': instance.gender,
      'ethnic': instance.ethnic,
      'birthday': instance.birthday,
      'address': instance.address,
      'number': instance.number,
      'filePath': instance.filePath,
    };
