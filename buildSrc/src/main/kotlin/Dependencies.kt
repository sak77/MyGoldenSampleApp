import org.gradle.api.JavaVersion

object Versions {
    val launcherLibraryVersion = "1.2.6"

    val hamcrest = "2.2"
    val framework_full = "30.0.1"
    val materialDesign = "1.4.0"
    val androidCar = "31.0.3"

    val preference = "1.1.1" // Usage of car-ui-lib will not compile without this

    /** Application configuration */
    val versionCode = 30
    val versionName = "1.0"

    val androidMinSdkVersion = 30
    val androidTargetSdkVersion = 31
    val androidCompileSdkVersion = 31

    val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    val javaCompatibility = JavaVersion.VERSION_1_8
    val kotlinJvmTarget = "1.8"

    /** Plugin versions */
    val android_gradle = "7.1.0" // because jacoco doesn't work with AGP 7.0.0 yet
    val detekt = "1.18.0"
    val jacoco = "0.8.7"
    val spotless = "5.14.2"

    /** Library versions */
    val kotlin = "1.6.10"
    val coroutines = "1.6.0"

    val coreKtx = "1.6.0"
    val appCompat = "1.3.1"
    val fragment = "1.3.6"
    val recycleView = "1.2.1"
    val constraintLayout = "2.1.0"
    val viewPager2 = "1.0.0"
    val viewBinding = "7.0.3"

    val javaxAnnotation = "1.0"
    val javaxInject = "1"
    val dagger = "2.40.5"
    val hiltNavigation = "1.0.0"

    val moshi = "1.12.0"
    val okHttp = "4.9.1"
    val retrofit = "2.9.0"

    val room = "2.3.0"
    val navigation = "2.3.5"
    val workmanager = "2.5.0"
    val lifecycle = "2.4.0"
    val coil = "1.3.2"
    val activity = "1.3.1"

    val timber = "5.0.1"
    val leakCanary = "2.7"

    /** Test library versions */
    val junit = "4.13.2"

    val mockitoKotlin = "3.2.0"
    val mockitoInline = "3.10.0"

    val espresso = "3.2.0"
    val robolectric = "4.7"
    val uiAutomator = "2.2.0"

    val androidXTest = "1.2.0"
    val androidXJUnit = "1.1.3"
    val androidXArchTest = "2.1.0"

}

/** Plugins */
object Plugins {
    val android_gradle = "com.android.tools.build:gradle:${Versions.android_gradle}"
    val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.dagger}"
    val navigation = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    val jacoco = "org.jacoco:org.jacoco.core:${Versions.jacoco}"
    val spotless = "com.diffplug.spotless:spotless-plugin-gradle:${Versions.spotless}"
}

/** Application libraries */
object Lib {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    val androidCar = "android:car:${Versions.androidCar}"

    val javaxAnnotation = "javax.annotation:jsr250-api:${Versions.javaxAnnotation}"
    val javaxInject = "javax.inject:javax.inject:${Versions.javaxInject}"
    val dagger = "com.google.dagger:hilt-android:${Versions.dagger}"
    val daggerCompiler = "com.google.dagger:hilt-android-compiler:${Versions.dagger}"
    val hiltNavigation = "androidx.hilt:hilt-navigation-fragment:${Versions.hiltNavigation}"

    val android_core = "androidx.core:core-ktx:${Versions.coreKtx}"
    val android_appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val android_fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"

    val android_activity = "androidx.activity:activity-ktx:${Versions.activity}"
    val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"

    val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    val lifecycleCore = "androidx.lifecycle:lifecycle-livedata-core-ktx:${Versions.lifecycle}"
    val lifecycleCommon = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    val viewBinding = "androidx.databinding:viewbinding:${Versions.viewBinding}"

    val workmanager = "androidx.work:work-runtime-ktx:${Versions.workmanager}"

    val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"

    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recycleView}"
    val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"

    val room = "androidx.room:room-ktx:${Versions.room}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    val moshiCompiler = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"

    val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    val okHttpLogger = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"

    val coil = "io.coil-kt:coil:${Versions.coil}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    val framework_full = "android:framework-full:${Versions.framework_full}"
    //val carUiLib = "android:car-ui-lib:${Versions.carUiLib}@aar"
    val preference = "androidx.preference:preference-ktx:${Versions.preference}"
}

/** Testing libraries */
object TestLib {
    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"

    val junit = "junit:junit:${Versions.junit}"
    val kotlinJUnit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"

    val mockito = "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}"
    val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    val jacoco = "org.jacoco:org.jacoco.agent:${Versions.jacoco}"

    val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    val espressoIntents = "androidx.test.espresso:espresso-intents:${Versions.espresso}"
    val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"

    val androidCore = "androidx.test:core-ktx:${Versions.androidXTest}"
    val androidRules = "androidx.test:rules:${Versions.androidXTest}"
    val androidRunner = "androidx.test:runner:${Versions.androidXTest}"
    val uiAutomator = "androidx.test.uiautomator:uiautomator:${Versions.uiAutomator}"

    val androidJUnit = "androidx.test.ext:junit:${Versions.androidXJUnit}"
    val androidArchCore = "androidx.arch.core:core-testing:${Versions.androidXArchTest}"
    val androidFragment = "androidx.fragment:fragment-testing:${Versions.fragment}"
    val hamcrest = "org.hamcrest:hamcrest:${Versions.hamcrest}"

    val workmanager = "androidx.work:work-testing:${Versions.workmanager}"

    val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"
}
