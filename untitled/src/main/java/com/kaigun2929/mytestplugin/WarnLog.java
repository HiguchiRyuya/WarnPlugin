package com.kaigun2929.mytestplugin;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WarnLog{

    public static File pluginDir = new File("plugins//KaigunPlsWarnPlugin");
    public static File logDir = new File(pluginDir, "Logs");

    public WarnLog() {
        CheckFolder();
    }

    public void OnWarningLog(Player target,Player commander,String reason) {

        // 日付の変数
        Date data = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy - hh:mm:ss");
        String date_str = sdf.format(data);

            File playerLog = new File(logDir,target.getName() + ".yml");
            if(!playerLog.exists()){
                try{
                    playerLog.createNewFile();
                }
                catch (IOException e){
                    System.out.println("playerLogのエラー");
                    return;
                }
            }
        try {

            /**
             * Logファイルへの書き込み
             */

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(playerLog,true));

            if(commander instanceof Player) {
                bufferedWriter.write("[ " + date_str + " ]  " +
                        "警告対象者:" + target.getName() + " | " +
                        "UUID:" + target.getUniqueId() + " | " +
                        "警告者:" + commander.getName() + " | " +
                        "理由:" + reason + "\n");
            }
            else {
                bufferedWriter.write("[ " + date_str + " ]  " +
                        "警告対象者:" + target.getName() + " | " +
                        "UUID:" + target.getUniqueId() + " | " +
                        "警告者:プロンプト(cmdP)" + " | " +
                        "理由:" + reason + "\n");
            }
                bufferedWriter.close();
            } catch (IOException e) {
                if(commander instanceof Player){
                    commander.sendMessage(ChatColor.RED + target.getName() + ".ymlを開いているため、ログの保管ができませんでした。");
                }
                else {
                    System.out.println(target.getName() + ".ymlを開いているため、ログの保管ができませんでした。");
                }
        }

    }

    public void CheckFolder() {
        System.out.println("ログのフォルダをチェックしています。");

        if (!pluginDir.exists()) {
            pluginDir.mkdirs();

        }

        if (!logDir.exists()) {
            logDir.mkdirs();
            System.out.println("Logフォルダを生成しました。( ../plugins/KaigunPlsWarnPlugin/Log )");
        }

    }
}
