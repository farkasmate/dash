//apply plugin: 'com.android.library'
//apply plugin: 'kotlin-android'
//apply plugin: 'kotlin-android-extensions'
//
//android {
//    compileSdkVersion 29
//
//    defaultConfig {
//        minSdkVersion 21
//        targetSdkVersion 29
//        versionCode 1
//        versionName "1.0"
//
//        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
//        consumerProguardFiles "consumer-rules.pro"
//    }
//
//    buildTypes {
//        release {
//            minifyEnabled false
//            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
//        }
//    }
//
//    kotlinOptions {
//        jvmTarget = JavaVersion.VERSION_1_8.toString()
//    }
//}
//
//dependencies {
//    implementation "androidx.appcompat:appcompat:1.1.0"
//    implementation "androidx.room:room-runtime:2.2.5"
//    implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"
//    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0"
//    implementation "androidx.fragment:fragment-ktx:1.2.4"
//    implementation "com.android.volley:volley:1.1.1"
//    implementation "androidx.recyclerview:recyclerview:1.1.0"
//    implementation "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0-rc01"
//    implementation "androidx.constraintlayout:constraintlayout:1.1.3"
//
//    api "com.google.android.material:material:1.2.0-alpha06"
//
//}

plugins {
    id("com.android.library")
    id("y2k.dash.build")
}

android {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}