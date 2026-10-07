# Build & APK Guide

## GitHub Actions

Open GitHub Actions in the repository.

### Debug APK

Run **Build Debug APK** manually, then download the `MakeLifeGreat-debug-apk` artifact from the completed workflow.

### Release APK

Run **Build Release APK** manually. Without signing secrets this produces an unsigned release APK. With signing secrets it produces a signed release APK.

## Local build

Requirements: JDK 11, Android SDK, Android SDK Platform 36 and network access for Gradle dependencies.

```bash
chmod +x gradlew
./gradlew assembleDebug
```

Debug APK: `app/build/outputs/apk/debug/app-debug.apk`

Release APK: `./gradlew assembleRelease`
