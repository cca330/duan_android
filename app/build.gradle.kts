plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.duanlonmain"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.duanlonmain"
        minSdk = 21
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)



    implementation(libs.jtds)
    implementation("me.relex:circleindicator:2.1.6")
    implementation("com.google.android.material:material:1.0.0")
    implementation("androidx.fragment:fragment-ktx:1.8.2")
    implementation("com.github.bumptech.glide:glide:4.13.0")//xu ly hinh anh
    implementation("com.google.code.gson:gson:2.9.0")//chuyen doi du lieu img json - object
    implementation("com.google.ai.client.generativeai:generativeai:0.9.0")//connect api gemini

    implementation("com.google.guava:guava:32.1.3-android")
    implementation ("androidx.room:room-runtime:2.6.1")
    annotationProcessor ("androidx.room:room-compiler:2.6.1")

    // RecyclerView (để hiển thị danh sách chat)
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    // (Tuỳ chọn) CardView nếu bạn muốn làm ô tin nhắn bo góc
    implementation("androidx.cardview:cardview:1.0.0")
    //api tu vung
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")


}