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
AdaptiveIcon myIcon = new AdaptiveIcon();
myIcon.setForeground(FOREGROUND_DRAWABLE);
myIcon.setBackground(BACKGROUND_DRAWABLE);

// Rendering Bitmap
Bitmap renderedIcon = myIcon.render();
```
You can use Shorthand for Setting up the Drawables
```java
myIcon.setDrawables(FOREGROUND_DRAWABLE, BACKGROUND_DRAWABLE);
```
Setting Path
```java
myIcon.setPath(path);

// From Predefined Paths
myIcon.setPath(AdaptiveIcon.PATH_CIRCLE);
myIcon.setPath(AdaptiveIcon.PATH_SQUIRCLE);
myIcon.setPath(AdaptiveIcon.PATH_ROUNDED_SQUARE);
myIcon.setPath(AdaptiveIcon.PATH_SQUARE);
myIcon.setPath(AdaptiveIcon.PATH_TEARDROP);

// Custom Path
myIcon.setPath("M 50,0 C 10,0 0,10 0,50 C 0,90 10,100 50,100 C 90,100 100,90 100,50 C 100,10 90,0 50,0 Z");
```
Setting Scale
```java
myIcon.setScale(double);

// The Large the Number, The Small the Icon
myIcon.setScale(0.6); // Default

// Max is 1.9 and Min is 0.1
```