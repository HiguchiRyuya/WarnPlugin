package com.kaigun2929.mytestplugin;

import java.util.HashMap;
import java.util.Map;

import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

/**
 * 設定をまとめて取り扱う構造体
 * @author KaigunHAO(RyuyaHiguchi)
 *
 */

public class Config {

    private final Plugin plugin;
    private FileConfiguration config = null;

    private String mainTitleMessage;
    private String subTitleMessage;
    private int fadeInTime;
    private int stayTime;
    private int fadeOutTime;

    private String chatMessage;


    private String soundName;
    private int vol;
    private int pitch;


    public Config(Plugin plugin) {
        this.plugin = plugin;
        // ロードする
        load();
    }

    /**
     * 設定をロードする
     */
    public void load(){
        // 設定ファイルの保存
        plugin.saveDefaultConfig();
        if(config != null){
            plugin.reloadConfig();
        }
        config = plugin.getConfig();

        if (!config.contains("WarnSetting")) { // 存在チェック
            plugin.getLogger().info("config.ymlにWarnSettingがありません。");
        }

        mainTitleMessage = config.getString("WarnSetting.TitleMessage.Main");
        subTitleMessage = config.getString("WarnSetting.TitleMessage.Sub");
        fadeInTime = config.getInt("WarnSetting.TitleMessage.FadeIn");
        stayTime = config.getInt("WarnSetting.TitleMessage.Stay");
        fadeOutTime = config.getInt("WarnSetting.TitleMessage.FadeOut");

        chatMessage = config.getString("WarnSetting.ChatMessage.Target");


        soundName = config.getString("WarnSetting.PlaySound.SoundName");
        vol = config.getInt("WarnSetting.PlaySound.Volume");
        pitch = config.getInt("WarnSetting.PlaySound.Pitch");

    }

    /**
     * ゲッター
     */
    public String getMainTitleMessage(){
        return mainTitleMessage;
    }

    public String getSubTitleMessage() {
        return subTitleMessage;
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public int getFadeInTime() {
        return fadeInTime;
    }

    public int getStayTime() {
        return stayTime;
    }

    public int getFadeOutTime() {
        return fadeOutTime;
    }


    public String getSoundName() { return soundName; }

    public int getVol() { return vol; }

    public int getPitch() { return pitch; }



    /**
     * ロードしたときに返す関数
     */
    public boolean LoadConfigFlag(){
        return true;
    }
}
