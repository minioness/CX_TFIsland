plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.tfisland"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tfisland"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.appcompat)


    // Retrofit: 네트워크 통신을 위한 라이브러리
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // Gson 변환기: JSON 데이터를 Kotlin/Java 객체로 변환하기 위한 변환기
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    // Coroutine 지원 (선택 사항): Retrofit에서 코루틴을 사용하기 위한 어댑터
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    // ViewModel and LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

    // Coroutines support for ViewModel
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation(libs.androidx.espresso.idling.resource)
    implementation(libs.material)

    implementation("com.github.prolificinteractive:material-calendarview:2.0.1")

    // Unit Test dependencies
    testImplementation("junit:junit:4.13.2") // JUnit 4 for unit tests
    testImplementation("org.mockito:mockito-core:3.12.4") // Mockito for mocking
    testImplementation("org.mockito:mockito-inline:3.12.4") // Mockito inline for final classes
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4") // Coroutine test support
    testImplementation("androidx.arch.core:core-testing:2.1.0") // LiveData test helper

    // Android Instrumentation Test dependencies
    androidTestImplementation("androidx.test.ext:junit:1.1.5") // AndroidX JUnit support
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1") // Espresso for UI testing
    androidTestImplementation("org.mockito:mockito-android:3.12.4") // Mockito for Android tests
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4") // Coroutine test support

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
