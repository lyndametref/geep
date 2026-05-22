plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "net.madscientists.geep.core.database"
    compileSdk = 35

    defaultConfig {
        minSdk = 35
        
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }

    buildTypes {
        debug {
            enableUnitTestCoverage = true
        }
    }
    dependencies {
        // Core model dependency
        implementation(project(":core-model"))

        // Room components
        implementation(libs.room.runtime)
        implementation(libs.room.ktx)
        ksp(libs.room.compiler)

        // Test dependencies - Unit tests with Robolectric for Android context
        testImplementation(libs.junit)
        testImplementation(libs.androidx.junit)
        testImplementation(libs.robolectric)
        testImplementation(libs.androidx.test.runner)
        testImplementation(libs.room.testing)
        testImplementation(libs.androidx.core.ktx)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    
    sourceSets {
        getByName("test") {
            assets.srcDirs(files("$projectDir/schemas"))
        }
    }
}
