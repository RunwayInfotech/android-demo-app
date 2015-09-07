-dontwarn com.avocarrot.**
-keep class com.avocarrot.** { *; }
-keepclassmembers class com.avocarrot.** { *; }
-keep public class * extends android.view.View {
  public <init> (android.content.Context);
  public <init> (android.content.Context, android.util.AttributeSet);
  public <init> (android.content.Context, android.util.AttributeSet, int);
  public void set*(...);
}
