# Some methods are only called from tests, so make sure the shrinker keeps them.
-keep class com.example.android.architecture.blueprints.** { *; }
-keep class androidx.test.espresso.IdlingResource { *; }
-keep class androidx.test.espresso.IdlingRegistry { *; }
-keep class com.google.common.base.Preconditions { *; }
-keep class com.google.common.collect.CollectPreconditions { *; }
-keep class android.arch.** { *; }

#-keepclasseswithmembers public class androidx.test.espresso.contrib.RecyclerViewActions { *; }
-keepclasseswithmembers public class androidx.recyclerview.widget.RecyclerView { *; }
-keepclasseswithmembers public class androidx.viewpager.widget.ViewPager { *; }
-keepclasseswithmembers public class com.google.common.collect.CollectPreconditions { *; }

# For Guava:
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
-dontwarn sun.misc.Unsafe

# Proguard rules that are applied to your test apk/code.
-ignorewarnings
-keepattributes *Annotation*

-dontnote junit.framework.**
-dontnote junit.runner.**

-dontwarn android.test.**
-dontwarn android.support.test.**
-dontwarn org.junit.**
-dontwarn org.hamcrest.**
-dontwarn com.squareup.javawriter.JavaWriter
# Uncomment this if you use Mockito
-dontwarn org.mockito.**

# add description why TODO
#-keepclasseswithmembers public class android.support.**
-keepclasseswithmembers final class com.google.common.collect.CollectPreconditions { *; }
-keep class com.google.common.** { *; }
-keep class java.lang.** { *; }
-keep class com.android.** { *; }
-keep class androidx.test.** { *; }
