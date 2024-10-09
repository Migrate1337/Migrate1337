package org.migrate1337.farmingenchantments.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            // Основные команды
            if (sender.hasPermission("farmingenchants.*")) {
                completions.addAll(Arrays.asList("reload", "list", "help", "enchant", "giveitem"));
            }
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("enchant")) {
                // Варианты для команды enchant
                completions.addAll(Arrays.asList("potatobooster", "carrotbooster"));
            } else if (args[0].equalsIgnoreCase("giveitem") && sender instanceof Player) {
                // Варианты для команды giveitem
                completions.add("<player>");
            }
        } else if (args.length == 3) {
            if (args[0].equalsIgnoreCase("giveitem")) {
                // Предметы для выдачи
                completions.addAll(Arrays.asList("carrotfragment", "potatofragment"));
            }
            if (args[0].equalsIgnoreCase("enchant")) {
                completions.addAll(Arrays.asList("1", "2"));
            }
        } else if (args.length == 4) {
            if (args[0].equalsIgnoreCase("giveitem")) {
                // Количество предметов
                completions.add("<amount>");
            }
        }

        return completions;
    }
}
