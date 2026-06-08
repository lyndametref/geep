# Hilt
-keepclassmembers class * {
    @dagger.hilt.android.internal.managers.ViewComponentManager$FragmentContextWrapper *;
}

# Room
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**

# Keep Compose
-dontwarn androidx.compose.**
