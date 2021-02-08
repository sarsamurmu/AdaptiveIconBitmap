# Adaptive Icon Bitmap
[![Jitpack Badge](https://jitpack.io/v/sarsamurmu/AdaptiveIconBitmap.svg)](https://jitpack.io/#sarsamurmu/AdaptiveIconBitmap)

Get merged bitmap from adaptive icon's foreground and background drawables.

It's inspired from [AdaptiveIconView](https://github.com/fennifith/AdaptiveIconView).

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
implementation 'com.github.sarsamurmu:AdaptiveIconBitmap:$version'
```
Replace `$version` with latest version or commit hash. Latest is ![Jitpack Badge](https://jitpack.io/v/sarsamurmu/AdaptiveIconBitmap.svg)

## Rendering Bitmap
You can use it like this
```java
AdaptiveIcon adaptiveIcon = new AdaptiveIcon();
adaptiveIcon.setForeground(FOREGROUND_DRAWABLE);
adaptiveIcon.setBackground(BACKGROUND_DRAWABLE);

// Rendering Bitmap
Bitmap renderedIcon = adaptiveIcon.render();
```
You can use shorthand for setting up the drawables
```java
adaptiveIcon.setDrawables(FOREGROUND_DRAWABLE, BACKGROUND_DRAWABLE);
```
or if you've AdaptiveIconDrawable, you can set like this
```java
adaptiveIcon.setDrawable(adaptiveIconDrawable);
```

## Setting Path
This sets the mask to use in adaptive icons
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

## Setting Size
Sets the size of the icon in pixel
```java
adaptiveIcon.setSize(int size);

// Default
adaptiveIcon.setSize(256);
```
