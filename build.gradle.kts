buildscript {
    val hiltVersion by extra("2.41")
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
    }
}

tasks.wrapper {
    gradleVersion = "7.4.2"
    distributionType = Wrapper.DistributionType.ALL
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
