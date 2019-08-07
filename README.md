# Adaptive Icon Bitmap
Get Merged Bitmap from Adaptive Icon's Foreground and Background Drawables.

It's modified from [AdaptiveIconView](https://github.com/fennifith/AdaptiveIconView).

# Usage
## Setup
Make sure your root build.gradle has this
```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
Then add this to your app module's build.gradle
```gradle
implementation 'com.github.sarsamurmu:AdaptiveIconBitmap:-SNAPSHOT'
```

## Simple Usage
You can Use it like this, main stuff
```java
AdaptiveIcon myAdaptiveIcon = new AdaptiveIcon();
myAdaptiveIcon.setForeground(YOUR_FOREGROUND_DRAWABLE);
myAdaptiveIcon.setBackground(YOUR_BACKGROUND_DRAWABLE);

// Rendering Bitmap
Bitmap myIcon = myAdaptiveIcon.render();
```
