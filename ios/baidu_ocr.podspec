#
# To learn more about a Podspec see http://guides.cocoapods.org/syntax/podspec.html.
# Run `pod lib lint baidu_ocr.podspec' to validate before publishing.
#
Pod::Spec.new do |s|
  s.name             = 'baidu_ocr'
  s.version          = '0.0.1'
  s.summary          = '百度OCR插件'
  s.description      = <<-DESC
百度OCR插件
                       DESC
  s.homepage         = 'http://muka.com'
  s.license          = { :file => '../LICENSE' }
  s.author           = { 'Spicely' => 'Spicely@outloout.com' }
  s.source           = { :path => '.' }
  s.source_files = 'Classes/**/*', 'Classes/AipBase.framework', 'Classes/AipOcrSdk.framework', 'Classes/IdcardQuality.framework' 
  s.public_header_files = 'Classes/**/*.h'
  s.dependency 'Flutter'
  s.platform = :ios, '8.0'

  s.ios.deployment_target = '8.0'
  s.subspec 'AipBase' do |b|
      b.ios.vendored_libraries ='Classes/AipBase.framework'
  end

  s.subspec 'AipOcrSdk' do |sdk|
    sdk.ios.vendored_libraries ='Classes/AipOcrSdk.framework'
  end

  s.subspec 'IdcardQuality' do |i|
      i.ios.vendored_libraries ='Classes/IdcardQuality.framework'
  end

  # Flutter.framework does not contain a i386 slice. Only x86_64 simulators are supported.
  # s.pod_target_xcconfig = { 'DEFINES_MODULE' => 'YES', 'VALID_ARCHS[sdk=iphonesimulator*]' => 'x86_64' }
end
