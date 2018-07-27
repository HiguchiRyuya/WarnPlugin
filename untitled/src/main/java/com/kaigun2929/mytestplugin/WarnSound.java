package com.kaigun2929.mytestplugin;

/**
 * サウンドクラス
 * @author KaigunHAO(RyuyaHiguchi)
 *
 */

import org.bukkit.Location;
import org.bukkit.Sound;

public class WarnSound {

    private String soundName ;
    private int vol;
    private int pitch;

    // サウンドのコンストラクタ
    public WarnSound(Config config){
        // セット
        this.soundName = config.getSoundName();
        this.vol = config.getVol();
        this.pitch = config.getPitch();
    }

    // サウンドを発生
    public void PlaySound(Location targetLocation){
        targetLocation.getWorld().playSound(targetLocation,Sound.valueOf(soundName.toUpperCase()),vol,pitch);


    }
}
