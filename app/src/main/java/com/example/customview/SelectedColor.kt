package com.example.customview

enum class SelectedColor(val action: Action) {
    RED(Action.TEXT),
    ORANGE(Action.IMAGE),
    YELLOW(Action.TEXT),
    GREEN(Action.IMAGE),
    CYAN(Action.TEXT),
    BLUE(Action.IMAGE),
    PURPLE(Action.TEXT)
}

enum class Action{
    TEXT,
    IMAGE
}