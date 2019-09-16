package net.runelite.client.plugins.clanspammer;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Keybind;

@ConfigGroup("Spam")
public interface SpammerConfig extends Config {

    @ConfigItem(
            position = 1,
            keyName = "charsSpammed",
            name = "Characters Spammed",
            description = "Characters spammed in the text. 0 = All"
    )
    default int charsSpammed() { return 0; }


    @ConfigItem(
            position = 2,
            keyName = "prependText",
            name = "Prepended Text",
            description = "..."
    )
    default String prependText() {
        return "";
    }


    @ConfigItem(
            position = 3,
            keyName = "failText",
            name = "Non-Interacting Spam",
            description = "Spams this text if the player is not interacting"
    )
    default String failText() {
        return "";
    }

    @ConfigItem(
            keyName = "hotkey",
            name = "Spammer Hotkey",
            description = "When this key is pressed spam text",
            position = 4
    )
    default Keybind hotkey()
    {
        return Keybind.NOT_SET;
    }

    @ConfigItem(
            keyName = "autoEnter",
            name = "Automated Enter",
            description = "Automate pressing enter into the chat",
            position = 5
    )
    default boolean autoEnter()
    {
        return true;
    }

    @ConfigItem(
            keyName = "humanTyping",
            name = "Human-Like Typing",
            description = "Automate pressing enter into the chat",
            position = 6
    )
    default boolean humanLikeTyping()
    {
        return true;
    }

    @ConfigItem(
            keyName = "startDelay",
            name = "Start Delay (ms)",
            description = "Automate pressing enter into the chat",
            position = 7
    )
    default int startDelay()
    {
        return 0;
    }

    @ConfigItem(
            keyName = "charDelay",
            name = "Character Delay (ms)",
            description = "Automate pressing enter into the chat",
            position = 8
    )
    default int charDelay()
    {
        return 0;
    }


    @ConfigItem(
            keyName = "enterDelay",
            name = "Enter Delay (ms)",
            description = "Automate pressing enter into the chat",
            position = 9
    )
    default int enterDelay()
    {
        return 0;
    }

}
