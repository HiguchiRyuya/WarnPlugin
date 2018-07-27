package com.kaigun2929.mytestplugin;

/**
 * Warn処理を行うMainクラス
 * @author KaigunHAO(RyuyaHiguchi)
 *
 */

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

public class KaigunPlsWarnPlugin extends JavaPlugin implements Listener {

    private Config config;
    private WarnLog log;
    private WarnSound sound;

    @Override
    public void onEnable() {

        PluginManager pluginManager = this.getServer().getPluginManager();

        config = new Config(this);

        if(getDataFolder().exists()){
            getDataFolder().mkdirs();
        }

        if(config.LoadConfigFlag() == true)
        {
            System.out.print("configファイルを読み込みました。");
        }

        sound = new WarnSound(config);
        log = new WarnLog();

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
                Player commander = Bukkit.getPlayerExact(sender.getName());
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

                    // サウンドを鳴らす
                    sound.PlaySound(target.getLocation());
                    // 警告処理
                    target.sendTitle(ChatColor.RED + config.getMainTitleMessage(),
                            ChatColor.RED + config.getSubTitleMessage(),
                            config.getFadeInTime(), config.getStayTime(), config.getFadeOutTime());
                    target.sendMessage(ChatColor.DARK_RED + config.getChatMessage());
                    target.sendMessage(ChatColor.RED + "理由 ： " + args[1]);

                    sender.sendMessage(ChatColor.DARK_GREEN + args[0] + ("に、警告を出しました。"));

                    // ログの書き込みクラスの呼び出し

                    log.OnWarningLog(target,commander,args[1]);

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

