package net.runelite.client.plugins.clanspammer;

import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.clanspammer.SpammerConfig;
import net.runelite.client.util.HotkeyListener;
import com.google.inject.Provides;
import javax.inject.Inject;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.api.Client;
import java.lang.*;
import java.util.Random;
import java.util.Date;
import java.awt.*;
import java.awt.event.*;

@PluginDescriptor(
        name = "Clan Spammer",
        description = "Highlight the player the caller is attacking",
        tags = {"highlight", "overlay"},
        enabledByDefault = false
)


public class SpammerPlugin extends Plugin {

    //Robot tester = makeRobot();
    //@Inject Robot tester;

    @Inject Client client;

    @Inject
    private SpammerConfig config;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private KeyManager keyManager;

    private final HotkeyListener hotkeyListener = new HotkeyListener(() -> config.hotkey())
    {

        @Override
        public void hotkeyPressed()
        {
            Thread t = new Thread(new Runnable(){
                @Override
                public void run() {
                    spamText(config.prependText());
                    return;
                }
            });
            t.start();

        }
    };

    @Provides
    SpammerConfig getConfig(ConfigManager configManager)
    {
        return configManager.getConfig(SpammerConfig.class);
    }

    @Override
    protected void startUp() throws Exception
    {

        keyManager.registerKeyListener(hotkeyListener);
    }

    @Override
    protected void shutDown() throws Exception
    {
        keyManager.unregisterKeyListener(hotkeyListener);
    }

    private void spamText(String prependText)
    {
        int count = 0;
        Boolean notClanMember = false;
        String test = null;
        if (client.getLocalPlayer().getInteracting() != null)
        {
            test = client.getLocalPlayer().getInteracting().getName();
            //notClanMember = client.getPlayers();
        }

        //System.out.println(prependText);
        //System.out.println(test);
        //String test2 = "Hello";
        //Thread t = new Thread(beginSpam(test));
        if (test != null)
        {
            StringBuilder str = new StringBuilder(prependText);
            str.append(" ");
            if (config.charsSpammed() > test.length() || (config.charsSpammed() == 0))
            {
                for (count = 0; count < test.length(); count++)
                {
                    str.append(test.charAt(count));
                }
            }
            else
            {
                for (count = 0; count < config.charsSpammed(); count++)
                {
                    str.append(test.charAt(count));
                }
            }
            //str.append(test);
            test = str.toString();
            //System.out.println(test);
            beginSpam(test);
        }
        else
        {
            String fail = config.failText();
            beginSpam(fail);
        }
    }

    private void beginSpam(String spamText)
    {

        try
        {
            Random rand = new Random();
            int n = 0;
            Robot tester = new Robot();
            int keycode;


            //spam.delay(300);
            n = rand.nextInt(20) + 6;
            Thread.sleep(225 + n + config.startDelay());
                for (int j = 0; j < spamText.length(); j++)
                {
                    keycode = KeyEvent.getExtendedKeyCodeForChar(spamText.charAt(j));
                    if ((Character.isLetter(spamText.charAt(j)) && (Character.isUpperCase(spamText.charAt(j)))))
                    {
                        tester.keyPress(KeyEvent.VK_SHIFT);
                    }

                    else if ((spamText.charAt(j) == ':'))
                    {
                        tester.keyPress(KeyEvent.VK_SHIFT);
                        keycode = KeyEvent.VK_SEMICOLON;
                    }
                    else if ((spamText.charAt(j) == '_'))
                    {
                        tester.keyPress(KeyEvent.VK_SHIFT);
                        keycode = KeyEvent.VK_MINUS;
                    }

                    tester.keyPress(keycode);
                    tester.keyRelease(keycode);
                    tester.keyRelease(KeyEvent.VK_SHIFT);
                    n = rand.nextInt(7) + 3;
                    Thread.sleep(n + config.charDelay());

                    if (config.humanLikeTyping()) {
                        n = rand.nextInt(4) + 1;
                        if (j%n == 0)
                        {
                            n = rand.nextInt(7) + 2;
                            Thread.sleep(n);
                            if ((j+3)%n == 0)
                            {
                                n = rand.nextInt(8)+2;
                                Thread.sleep(n);
                            }
                        }
                        n = rand.nextInt(28) + 4;
                        //System.out.println(n);
                        Thread.sleep(n);
                    }

                }

                if (config.autoEnter()) {
                    n = rand.nextInt(40) + 12;
                    //System.out.println(n);
                    Thread.sleep(n + config.enterDelay());
                    tester.keyPress(KeyEvent.VK_ENTER);
                    tester.keyRelease(KeyEvent.VK_ENTER);
                }


        }
        catch(Exception e)
        {

        }
    }

    private Robot makeRobot()
    {
        try {
            Robot spam = new Robot();
            return spam;
        }
        catch(Exception e)
        {
            return null;
        }


    }

}
