# Proguard rules that are applied to your test apk/code.
-ignorewarnings

-keepattributes *Annotation*
-dontnote junit.framework.**
-dontnote junit.runner.**

-dontwarn android.test.**
-dontwarn org.junit.**
-dontwarn org.hamcrest.**
-dontwarn com.squareup.javawriter.JavaWriter
# Uncomment this if you use Mockito
-dontwarn org.mockito.**
-keep class com.google.common.collect.CollectPreconditions { *; }
-keepclasseswithmembers final class com.google.common.collect.CollectPreconditions { *; }
