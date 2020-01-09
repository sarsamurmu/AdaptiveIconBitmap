# Adaptive Icon Bitmap
[![Jitpack Badge](https://jitpack.io/v/sarsamurmu/AdaptiveIconBitmap.svg)](https://jitpack.io/#sarsamurmu/AdaptiveIconBitmap)

Get merged bitmap from adaptive icon's foreground and background drawables.

It's modified from [AdaptiveIconView](https://github.com/fennifith/AdaptiveIconView).

# Usage
## Setup
Make sure your root `build.gradle` has this
```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
Then add this to your app module's `build.gradle`
```gradle
implementation 'com.github.sarsamurmu:AdaptiveIconBitmap:$commitHash'
```
Replace `$commitHash` with latest commit hash. Latest is ![Jitpack Badge](https://jitpack.io/v/sarsamurmu/AdaptiveIconBitmap.svg)

## Rendering Bitmap
You can use it like this
```java
AdaptiveIcon adaptiveIcon = new AdaptiveIcon();
adaptiveIcon.setForeground(FOREGROUND_DRAWABLE);
adaptiveIcon.setBackground(BACKGROUND_DRAWABLE);

// Rendering Bitmap
Bitmap renderedIcon = adaptiveIcon.render();
```
You can use Shorthand for Setting up the Drawables
```java
adaptiveIcon.setDrawables(FOREGROUND_DRAWABLE, BACKGROUND_DRAWABLE);
```
or if you've AdaptiveIconDrawable, you can set like this
```java
adaptiveIcon.setDrawable(adaptiveIconDrawable)
```

## Setting Path
This sets the mask to use in adaptive icon.
```java
adaptiveIcon.setPath(path);

// From predefined paths
adaptiveIcon.setPath(AdaptiveIcon.PATH_CIRCLE);
adaptiveIcon.setPath(AdaptiveIcon.PATH_SQUIRCLE);
adaptiveIcon.setPath(AdaptiveIcon.PATH_ROUNDED_SQUARE);
adaptiveIcon.setPath(AdaptiveIcon.PATH_SQUARE);
adaptiveIcon.setPath(AdaptiveIcon.PATH_TEARDROP);

// Custom Path
adaptiveIcon.setPath("M 50,0 C 10,0 0,10 0,50 C 0,90 10,100 50,100 C 90,100 100,90 100,50 C 100,10 90,0 50,0 Z");
```

## Setting Scale
```java
adaptiveIcon.setScale(double);

// The Large the Number, The Small the Icon
adaptiveIcon.setScale(0.6); // Default

// Max is 1.9 and Min is 0.1
```

## Setting Size
```java
adaptiveIcon.setSize(int size);


// Default
adaptiveIcon.setSize(256);
```

End.
