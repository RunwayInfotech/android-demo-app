-dontwarn com.avocarrot.**
-keep class com.avocarrot.** { *; }
-keepclassmembers class com.avocarrot.** { *; }
-keep public class * extends android.view.View {
  public <init> (android.content.Context);
  public <init> (android.content.Context, android.util.AttributeSet);
  public <init> (android.content.Context, android.util.AttributeSet, int);
  public void set*(...);
}

-dontwarn android.support.v7.**
-keep class android.support.v7.** { *; }
-keep interface android.support.v7.** { *; }

-keep public class com.google.android.gms.**
-dontwarn com.google.android.gms.**

-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}