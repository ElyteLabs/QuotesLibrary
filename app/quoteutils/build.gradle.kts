plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    `maven-publish`
}

android {
    namespace = "com.elytelabs.quoteutils"
    compileSdk = 33

    defaultConfig {
        minSdk = 23

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.3.1")

    implementation("com.google.android.play:review-ktx:2.0.1")
}

// Maven publishing configuration
    publishing {
        publications {
            register<MavenPublication>("release") {
                groupId = "com.elytelabs.quoteutils"
                artifactId = "quoteutils"
                version = "1.2.2"

                afterEvaluate {
                    from(components["release"])
                }

                pom {
                    name.set("QuoteUtils Library")
                    description.set("Quotes Library Utils and Helpers")
                    url.set("https://github.com/ElyteLabs/QuotesLibrary")

                    licenses {
                        license {
                            name.set("Apache 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                }
            }
        }
    }
