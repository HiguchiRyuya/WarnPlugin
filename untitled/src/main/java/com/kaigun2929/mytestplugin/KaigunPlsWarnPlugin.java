package com.kaigun2929.mytestplugin;

/**
 * Warn処理を行うMainクラス
 * @author KaigunHAO(RyuyaHiguchi)
 *
 */

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class KaigunPlsWarnPlugin extends JavaPlugin implements Listener {

    // Configセットクラスの宣言
    private Config config;

    @Override
    public void onEnable() {
        config = new Config(this);
        if(config.LoadConfigFlag() == true)
        {
            System.out.print("configファイルを読み込みました。");
        }

        // Plugin startup logic
        System.out.println("WarnPluginが有効になりました。");
        getServer().getPluginManager().registerEvents(this, this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.print("WarnPluginが無効になりました。");

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // 「/warn」コマンドを打った場合
        if (label.equalsIgnoreCase("warn")) {
            // サブコマンドがあるかどうか
            if (args.length == 2) {


                // 指定した警告するプレイヤーインスタンス
                Player target = Bukkit.getPlayerExact(args[0]);
                //target.getLocation();

                boolean targetJoinFlag;

                // ターゲットが存在するか確認
                if (target == null) {
                    targetJoinFlag = false;
                } else {
                    targetJoinFlag = true;
                }

                if (targetJoinFlag == true) {
                    // 処理実行
                    sender.sendMessage(ChatColor.DARK_GREEN + ("コマンドが実行されました"));

                    // 警告処理
                    target.sendTitle(ChatColor.RED + config.getMainTitleMessage(),
                            ChatColor.RED + config.getSubTitleMessage(),
                            config.getFadeInTime(), config.getStayTime(), config.getFadeOutTime());
                    target.sendMessage(ChatColor.DARK_RED + config.getChatMessage());
                    target.sendMessage(ChatColor.DARK_RED + "理由 ： " + args[1]);

                    sender.sendMessage(ChatColor.DARK_GREEN + args[0] + ("に、警告を出しました。"));

                } else {
                    sender.sendMessage(ChatColor.RED + args[0] + "がオフラインです。");
                }
            }
            // プレイヤー未指定のエラーメッセージを返す
            else {
                sender.sendMessage(ChatColor.RED + "/warn <警告対象プレイヤー> <理由> を入力してください。");
            }

        }
        return true;
    }
}

