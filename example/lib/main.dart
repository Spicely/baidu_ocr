import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:baidu_ocr/baidu_ocr.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
    Future.delayed(Duration(seconds: 1), () {
      initPlatformState();
    });
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    bool status;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      print(222222222222222222);
      status = await BaiduOcr.init('RuMnPgX54NKYvMyQcdxdEDZV', 'gcUnsrtIQxkRuSXEiaKLOUXZ75rrts8Z');
      print(status);
    } on PlatformException {
      print(1111);
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Column(
          children: [
            RaisedButton(
              onPressed: () async {
                CardFront data = await BaiduOcr.idcardOCROnlineFront();
                print(data.toJson());
              },
              child: Text('身份证识别'),
            ),
            RaisedButton(
              onPressed: () async {
                CardBack data = await BaiduOcr.idcardOCROnlineBack();
                print(data.toJson());
              },
              child: Text('身份证背面识别'),
            ),
          ],
        ),
      ),
    );
  }
}
