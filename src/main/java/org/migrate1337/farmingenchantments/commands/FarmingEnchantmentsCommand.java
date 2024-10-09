package org.migrate1337.farmingenchantments.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.migrate1337.farmingenchantments.FarmingEnchantments;
import org.migrate1337.farmingenchantments.utils.ColorUtils;

import java.util.ArrayList;

import static org.migrate1337.farmingenchantments.items.CarrotFragment.createCarrotFragment;
import static org.migrate1337.farmingenchantments.items.PotatoFragment.createPotatoFragment;

public class FarmingEnchantmentsCommand implements CommandExecutor {
    private final FarmingEnchantments plugin;

    public FarmingEnchantmentsCommand(FarmingEnchantments plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender.hasPermission("farmingenchants.*")){
            if (args.length == 0) {
                sender.sendMessage(ColorUtils.parseMessage("&#08FB54[F&#18F84Fa&#28F44Ar&#38F145m&#49ED41i&#59EA3Cn&#69E637g&#79E332E&#89DF2Dn&#99DC28c&#A9D823h&#BAD51Fa&#CAD11An&#DACE15t&#EACA10s]§f\nVersion: 1-0-0 SNAPSHOT\nUse /farmingenchantments help or\n/fe help to get commands list"));
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                plugin.reloadConfig();
                sender.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10]&f Config reloaded!"));
            }
            if(args[0].equalsIgnoreCase("list")){
                sender.sendMessage(ColorUtils.parseMessage("&7================ &#08FB54[F&#18F84Fa&#28F44Ar&#38F145m&#49ED41i&#59EA3Cn&#69E637g&#79E332E&#89DF2Dn&#99DC28c&#A9D823h&#BAD51Fa&#CAD11An&#DACE15t&#EACA10s]&7 ================\n" +
                        "&e&lPotato booster&f - Increase chance to drop more potatoes and can drop &6potato fragment &7(Use 'potatofragment' to give)\n" +
                        "&e&lCarrot booster&f - Increase chance to drop more carrots and can drop &6carrot fragment &7(Use 'carrotfragment' to give)\n" +
                        "&7================================================="));

            }
            if(args[0].equalsIgnoreCase("help")){
                sender.sendMessage(ColorUtils.parseMessage("&7================ &#08FB54[F&#18F84Fa&#28F44Ar&#38F145m&#49ED41i&#59EA3Cn&#69E637g&#79E332E&#89DF2Dn&#99DC28c&#A9D823h&#BAD51Fa&#CAD11An&#DACE15t&#EACA10s]&7 ================\n" +
                        "&e/fe reload - &fReload plugin\n" +
                        "&e/fe enchant (enchantment) (level) &f- Enchant hoe\n" +
                        "&e/fe giveitem (player) (item) (amount) &f- Give an item to player\n" +
                        "&e/fe list &f- Show enchantment list\n" +
                        "&7================================================="));

            }
            if (args[0].equalsIgnoreCase("enchant")) {
                Player player = (Player) sender;
                ItemStack itemInHand = player.getInventory().getItemInMainHand();

                if (itemInHand.getType().name().endsWith("_HOE")) {
                    ItemMeta meta = itemInHand.getItemMeta();
                    ArrayList<String> lore = new ArrayList<>();

                    if (args.length < 2) {
                        player.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10] §fType the enchantment."));
                        return true;
                    }

                    if (args[1].equalsIgnoreCase("potatobooster")) {
                        if (args.length < 3) {
                            player.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10] §fType the enchantment level."));
                            return true;
                        }

                        if (args.length == 3) {
                            if (args[2].equalsIgnoreCase("1")) {
                                meta.addEnchant(FarmingEnchantments.potatoEnchant, 1, true);
                                lore.add(ColorUtils.parseMessage(plugin.getConfig().getString("names.potatoEnchantName") + " I"));
                                meta.setLore(lore);
                                itemInHand.setItemMeta(meta);
                                player.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10] §aYour hoe was enchanted!"));
                            } else if (args[2].equalsIgnoreCase("2")) {
                                meta.addEnchant(FarmingEnchantments.potatoEnchant, 2, true);
                                lore.add(ColorUtils.parseMessage(plugin.getConfig().getString("names.potatoEnchantName") + " II"));
                                meta.setLore(lore);
                                itemInHand.setItemMeta(meta);
                                player.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10] §aYour hoe was enchanted!"));
                            } else {
                                player.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10] §cYou have entered an incorrect enchantment level!"));
                            }
                        }
                    } else if (args[1].equalsIgnoreCase("carrotbooster")) {
                        if (args.length < 3) {
                            player.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10] §fType the enchantment level."));
                            return true;
                        }

                        if (args[2].equalsIgnoreCase("1")) {
                            meta.addEnchant(FarmingEnchantments.carrotEnchant, 1, true);
                            lore.add(ColorUtils.parseMessage(plugin.getConfig().getString("names.carrotEnchantName") + " I"));
                            meta.setLore(lore);
                            itemInHand.setItemMeta(meta);
                            player.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10] §aYour hoe was enchanted!"));
                        } else if (args[2].equalsIgnoreCase("2")) {
                            meta.addEnchant(FarmingEnchantments.carrotEnchant, 2, true);
                            lore.add(ColorUtils.parseMessage(plugin.getConfig().getString("names.carrotEnchantName") + " II"));
                            meta.setLore(lore);
                            itemInHand.setItemMeta(meta);
                            player.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10] §aYour hoe was enchanted!"));
                        } else {
                            player.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10] §cType a correct enchantment level!"));
                        }
                    } else {
                        player.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10] §cType a correct enchantment!"));
                    }
                } else {
                    player.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10] §cYou need to hold the hoe in your hands to enchant it."));
                }
                return true;
            }
            if (args[0].equalsIgnoreCase("giveitem")) {
                // Проверяем, является ли отправитель консолью
                if (!(sender instanceof Player)) {
                    if (args.length < 4) {
                        sender.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10]&f Using: /fe giveitem <player> <item> <amount>")); // Сообщение об ошибке
                        return true;
                    }

                    // Проверяем, существует ли игрок с указанным именем
                    Player targetPlayer = Bukkit.getPlayer(args[1]);
                    if (targetPlayer == null) {
                        sender.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10]&f Player not found.")); // Сообщение, если игрок не найден
                        return true;
                    }

                    // Проверяем количество аргументов
                    if (args.length < 3) {
                        sender.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10]&f Type the amount of the item issued.")); // Сообщение об ошибке
                        return true;
                    }

                    int amount;
                    try {
                        amount = Integer.parseInt(args[3]); // Пробуем преобразовать третий аргумент в число
                        if (amount <= 0) {
                            sender.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10]&f The amount must be positive.")); // Сообщение для неверного количества
                            return true;
                        }
                    } catch (NumberFormatException e) {
                        sender.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10]&f Type a correct number.")); // Сообщение для некорректного ввода
                        return true;
                    }

                    // Проверяем, какой предмет нужно выдать
                    if (args[2].equalsIgnoreCase("carrotfragment")) {
                        ItemStack carrotfragment = new ItemStack(createCarrotFragment());
                        carrotfragment.setAmount(amount); // Устанавливаем количество
                        targetPlayer.getInventory().addItem(carrotfragment);
                    } else if (args[2].equalsIgnoreCase("potatofragment")) {
                        ItemStack potatofragment = new ItemStack(createPotatoFragment());
                        potatofragment.setAmount(amount); // Устанавливаем количество
                        targetPlayer.getInventory().addItem(potatofragment);
                    } else {
                        sender.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10]&f Incorrect. Try 'carrotfragment' or 'potatofragment'.")); // Сообщение для неверного предмета
                        return true;
                    }

                    // Отправляем сообщения игрокам
                    sender.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10]&f You give §e" + amount + " " + args[2] + " §ato §b" + targetPlayer.getName() + "."));
                    targetPlayer.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10]§f You get §e" + amount + " " + args[2] + " §afrom console."));
                } else {
                    Player player = (Player) sender;

                    // Создаем предметы
                    ItemStack carrotfragment = new ItemStack(createCarrotFragment());
                    ItemStack potatofragment = new ItemStack(createPotatoFragment());

                    if (args.length < 4) {
                        player.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10]§f Using: /fe giveitem <player> <item> <amount>")); // Сообщение об ошибке
                        return true;
                    }

                    Player targetPlayer = Bukkit.getPlayer(args[1]);
                    if (targetPlayer == null) {
                        player.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10]§f Player not found.")); // Сообщение, если игрок не найден
                        return true;
                    }

                    // Проверяем количество аргументов
                    if (args.length < 3) {
                        player.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10]§f Type the amount of the item issued.")); // Сообщение об ошибке
                        return true;
                    }

                    int amount;
                    try {
                        amount = Integer.parseInt(args[3]); // Пробуем преобразовать третий аргумент в число
                        if (amount <= 0) {
                            player.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10]§f The amount must be positive.")); // Сообщение для неверного количества
                            return true;
                        }
                    } catch (NumberFormatException e) {
                        player.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10]§f Type a correct number.")); // Сообщение для некорректного ввода
                        return true;
                    }

                    // Проверяем, какой предмет нужно выдать
                    if (args[2].equalsIgnoreCase("carrotfragment")) {
                        carrotfragment.setAmount(amount); // Устанавливаем количество
                        targetPlayer.getInventory().addItem(carrotfragment);
                    } else if (args[2].equalsIgnoreCase("potatofragment")) {
                        potatofragment.setAmount(amount); // Устанавливаем количество
                        targetPlayer.getInventory().addItem(potatofragment);
                    } else {
                        player.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10]§f Incorrect item. Try 'carrotfragment' or 'potatofragment'.")); // Сообщение для неверного предмета
                        return true;
                    }

                    // Отправляем сообщения игрокам
                    if (!player.getName().equals(targetPlayer.getName())) {
                        targetPlayer.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10]§f You get §e" + amount + " " + args[2] + " §afrom §b" + player.getName() + "."));
                    }
                    player.sendMessage(ColorUtils.parseMessage("&#08FB54[&#53EB3DF&#9FDA27E&#EACA10]§f You give §e" + amount + " " + args[2] + " §ato §b" + targetPlayer.getName() + "."));
                }

                return true;
            }
        } else{
            sender.sendMessage(plugin.getConfig().getString(ColorUtils.parseMessage("messages.noPermission")));
        }
        return true;

    }
}


