# Introduction
This project is an Android Doodle prototype. It gives the user an opportunity to interact with a canvas through a brush. There is a tool panel of widgets that allows the user to select various features.

# How to Run
To run this project, clone the repo onto your local computer and open the project with Android Studio. Use either an emulator or connect an Android device to run the app.

# Features

## Clear
On the bottom right is the clear button. It lets the user clear the canvas.

## Brush Size
There is a slider in the tool panel that allows the user to adjust the brush size. The largest brush size allowed is 500.

## Color
The color button pulls up a dialog box that allows the user to select a color based on RGB values. It uses the ColorPicker library from Pes8: https://github.com/Pes8/android-material-color-picker-dialog

## Opacity
There is a slider in the tool panel that lets the user adjust the opacity of the color. It goes from a scale from 0% to 100%.

# Undo/Redo
The user also has the ability to undo/redo path marks on the canvas. The buttons on the right side of the tool bar accomplish this.
