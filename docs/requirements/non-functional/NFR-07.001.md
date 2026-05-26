# NFR-07.001 Android Version Support

## Description

The Android mobile application must support a minimum of Android 15 (API 35) and must target the latest stable Android API level at the time of each release to ensure compatibility with modern platform versions.

## Rationale

Field workers use devices that may not receive timely OS updates, so a minimum floor ensures broad device coverage. Targeting the latest API level ensures access to modern platform security features, optimizations, and compliance with Google Play Store requirements (if distribution channel changes post-MVP).

## Verification Method

Review build configuration to confirm `minSdk = 35`. Verify `targetSdk` and `compileSdk` are set to the latest stable API level. Run the app on an emulator or device running API 35 to confirm basic functionality. Run the app on the latest available Android version emulator (e.g., API 36+) to confirm forward compatibility.
