# ToDo4Android

![Android OS Version Badge](https://img.shields.io/badge/Android-5.0~-green.svg)
![Android SDK Version Badge](https://img.shields.io/badge/Android_SDK-27-green.svg)
![Android Studio Version Badge](https://img.shields.io/badge/AndroidStudio-3.1.4-green.svg)
![Kotlin Version Badge](https://img.shields.io/badge/Kotlin-1.2.60-orange.svg)
![Java Version Badge](https://img.shields.io/badge/Java-8-red.svg)

## 概要

- Todoアプリ


## 設計指針

### 概要

* MVP+DataBinding設計で行う。
* Rxの使用はPresenterより下層に制限する。

![mvp_databinding](https://user-images.githubusercontent.com/1960883/44800976-c2985a80-abf2-11e8-9562-e6176bcaca66.png)

### ディレクトリ構成

```
   + com.syo_sa1982.todo4android
   ++ manager
   ++ model
   +++ api
   +++ entity
   +++ repository API・Modelを統括（アプリケーションクラスで保持する管理クラス）
   ++ presenter
   +++ viewable (プレゼンターからViewへイベントを通知するインタフェース)
   ++ view
   +++ activity (画面)
   +++ fragment (画面)
   +++ adapter (RecyclerViewのAdapter)
   ++ viewmodel
   ++ TodoApplication (アプリケーションクラス)
```

## サードパーティ

* ライブラリなどを追加する際にはREADMEに追記する
* [この手順](https://github.com/cookpad/license-tools-plugin/blob/master/README.md)に従ってライセンスファイルを更新する

### 公式ライブラリ

|名称|提供|概要|
|:--|:--|:--|
|[Lifecycle](https://developer.android.com/topic/libraries/architecture/adding-components#lifecycle)|Google|LiveData ViewModel 管理|
|[DataBinding](https://developer.android.com/topic/libraries/data-binding/?hl=ja)|Google|データバインディング|
|[Support-v4](https://developer.android.com/topic/libraries/support-library/features?hl=ja#v4)|Google|下位バージョンの互換API|
|[AppCompat-v7](https://developer.android.com/topic/libraries/support-library/features?hl=ja#v7)|Google|下位バージョンの互換View|
|[RecyclerView-v7](https://developer.android.com/topic/libraries/support-library/features?hl=ja#v7-recyclerview)|Google|RecyclerView|
|[CardView-v7](https://developer.android.com/topic/libraries/support-library/features?hl=ja#v7-cardview)|Google|CardView|
|[Design](https://developer.android.com/topic/libraries/support-library/features?hl=ja#design)|Google|マテリアルデザイン|
|[Junit](https://developer.android.com/topic/libraries/testing-support-library/index.html?hl=ja)|Google|ユニットテスト|
|[Espresso](https://developer.android.com/topic/libraries/testing-support-library/index.html?hl=ja)|Google|UIテスト|

参考:https://qiita.com/fkm/items/c004e964e2ca4807584e

### 外部ライブラリ

|名称|提供|概要|
|:--|:--|:--|
|[Moshi](https://github.com/square/moshi)|Square|Json 変換|
|[Moshi(adapter)](https://github.com/square/moshi/tree/master/adapters)|Square|Json 日付 アダプター|
|[Kotshi](https://github.com/ansman/kotshi)|Nicklas Ansman Giertz|Json 変換 アダプター|
|[OkHttp3](https://github.com/square/okhttp)|Square|通信 クライアント|
|[Retrofit2](https://github.com/square/retrofit)|Square|通信 APIラッパー|
|[Retrofit2(converter-moshi)](https://github.com/square/retrofit/tree/master/retrofit-converters/moshi)|Square|通信 コンバーター|
|[Retrofit2(adapter-rxjava2)](https://github.com/square/retrofit/tree/master/retrofit-adapters/rxjava2)|Square|通信 リアクティブ|
|[Retrofit2(mock)](https://github.com/square/retrofit/tree/master/retrofit-mock)|Square|通信 モック|
|[RxJava2(rxjava)](https://github.com/ReactiveX/RxJava)|RxJava Contributors|リアクティブ Java|
|[RxJava2(rxandroid)](https://github.com/ReactiveX/RxAndroid)|RxAndroid Contributors|リアクティブ Android|
|[RxJava2(rxkotlin)](https://github.com/ReactiveX/RxKotlin)|RxKotlin Contributors|リアクティブ Kotlin|
|[FragNav](https://github.com/ncapdevi/FragNav)|Nic Capdevila|UI Fragment 管理|
|[OkHttp3(logging-interceptor)](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)|Square|デバッグ 通信 コンソールでログ確認|
|[Chuck](https://github.com/jgilfelt/chuck)|Jeff Gilfelt|デバッグ 通信 UIでログ確認|
|[OkHttp3(mockwebserver)](https://github.com/square/okhttp/tree/master/mockwebserver)|Square|テスト モックサーバー|
|[Robolectric](https://github.com/robolectric/robolectric)|Xtreme Labs, Pivotal Labs and Google|テスト JVM環境|
