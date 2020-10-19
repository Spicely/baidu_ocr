# baidu_ocr

百度OCR插件

`需要去[百度AI](https://console.bce.baidu.com/ai/?_=1603093563631&fromai=1#/ai/ocr/overview/index "百度AI")注册`

## Android

在`AndroidManifest.xml`添加如下代码
`
 <uses-permission android:name="android.permission.CAMERA" />
`

## IOS

在`Info.plist`添加如下代码

```
    /// 需要修改 Build Settings -> Build Options -> Enable Bitcode 为 NO
    /// 对其他插件是否造成影响 目前不知

    <key>NSCameraUsageDescription</key>
    <string>App需要您的同意,才能唤起摄像头</string>
```

## 支持
 - [x] 身份证正面识别
 - [x] 身份证反面识别
 - [x] 银行卡正面识别

## 使用

```
/// 一般在main文件中初始化
await BaiduOcr.init('appkey', 'secretKey')

/// 身份证正面识别
IdCardFrontOcr data = await BaiduOcr.idcardOCROnlineFront();
print(data.toJson());

/// 身份证反面识别
IdCardBackOcr data = await BaiduOcr.idcardOCROnlineBack();
print(data.toJson());

/// 银行卡正面识别
BankCardOcr data = await BaiduOcr.bankCardOCROnline();
print(data.toJson());
```

