# Adaptive Icon Bitmap
[![Jitpack Badge](https://jitpack.io/v/sarsamurmu/AdaptiveIconBitmap.svg)](https://jitpack.io/#sarsamurmu/AdaptiveIconBitmap)

Get the merged bitmap from adaptive icon's foreground and background drawables.

It is inspired from [AdaptiveIconView](https://github.com/fennifith/AdaptiveIconView).

# Usage
## Setup
Make sure your root `build.gradle` has this
```gradle
allprojects {
  repositories {
    // ...

    maven { url 'https://jitpack.io' }
  }
}
```
Then add this as a dependency to your project `build.gradle`
```gradle
dependencies {
    // ...

    implementation 'com.github.sarsamurmu:AdaptiveIconBitmap:$version'
}
```
Replace `$version` with the latest version or commit hash.
Latest version is ![Jitpack Badge](https://jitpack.io/v/sarsamurmu/AdaptiveIconBitmap.svg)

## Rendering Bitmap
You can use it like this
```java
// Create a AdaptiveIcon instance
AdaptiveIcon adaptiveIcon = new AdaptiveIcon();
// Set drawables
adaptiveIcon.setForeground(FOREGROUND_DRAWABLE);
adaptiveIcon.setBackground(BACKGROUND_DRAWABLE);

// Render the icon
Bitmap renderedIcon = adaptiveIcon.render();
```
You can also use shorthand for setting up the drawables
```java
adaptiveIcon.setDrawables(FOREGROUND_DRAWABLE, BACKGROUND_DRAWABLE);
```
or if you've `AdaptiveIconDrawable`, you can set the drawables like this
```java
adaptiveIcon.setDrawable(adaptiveIconDrawable);
```

## Setting Path
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
```java
adaptiveIcon.setSize(size);
// Size is in pixel

// Default
adaptiveIcon.setSize(256);
```

# License
```
Copyright 2021 Sarsa Murmu

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```