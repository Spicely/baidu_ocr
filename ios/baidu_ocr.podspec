#
# To learn more about a Podspec see http://guides.cocoapods.org/syntax/podspec.html.
# Run `pod lib lint baidu_ocr.podspec' to validate before publishing.
#
Pod::Spec.new do |s|
  s.name             = 'baidu_ocr'
  s.version          = '0.0.1'
  s.summary          = 'A new flutter plugin project.'
  s.description      = <<-DESC
A new flutter plugin project.
                       DESC
  s.homepage         = 'http://example.com'
  s.license          = { :file => '../LICENSE' }
  s.author           = { 'Your Company' => 'email@example.com' }
  s.source           = { :path => '.' }
  s.source_files = 'Classes/**/*'
  s.public_header_files = 'Classes/**/*.h'
  s.dependency 'Flutter'
  s.platform = :ios, '8.0'

  s.ios.deployment_target = '8.0'
  s.subspec 'AipBase' do |b|
      b.vendored_frameworks ='Classes/AipBase.framework'
  end

  s.subspec 'AipOcrSdk' do |sdk|
    sdk.vendored_frameworks ='Classes/AipOcrSdk.framework'
  end

  s.subspec 'IdcardQuality' do |i|
      i.vendored_frameworks ='Classes/IdcardQuality.framework'
  end

  # Flutter.framework does not contain a i386 slice. Only x86_64 simulators are supported.
  # s.pod_target_xcconfig = { 'DEFINES_MODULE' => 'YES', 'VALID_ARCHS[sdk=iphonesimulator*]' => 'x86_64' }
end
