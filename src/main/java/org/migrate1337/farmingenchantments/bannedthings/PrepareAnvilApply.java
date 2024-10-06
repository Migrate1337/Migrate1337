package org.migrate1337.farmingenchantments.bannedthings;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.migrate1337.farmingenchantments.FarmingEnchantments;

public class PrepareAnvilApply implements Listener {
    private final FarmingEnchantments plugin;

    public PrepareAnvilApply(FarmingEnchantments plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        ItemStack firstItem = event.getInventory().getItem(0); // Левый слот наковальни
        ItemStack secondItem = event.getInventory().getItem(1); // Правый слот наковальни
        ItemStack resultItem = event.getResult(); // Результат наковальни

        // Проверяем, является ли первый предмет тем, который мы хотим заблокировать
        if (firstItem != null && firstItem.getType() == Material.DIAMOND_HOE &&
                firstItem.getEnchantmentLevel(FarmingEnchantments.potatoEnchant) == 1 || firstItem.getEnchantmentLevel(FarmingEnchantments.potatoEnchant) == 2 || firstItem.getEnchantmentLevel(FarmingEnchantments.carrotEnchant) == 1 || firstItem.getEnchantmentLevel(FarmingEnchantments.carrotEnchant) == 2) {

            // Проверяем, если второй предмет - книга
            if (secondItem != null && secondItem.getType() == Material.ENCHANTED_BOOK) {
                event.setResult(null); // Отменяем результат наковальни
                return; // Выходим из метода, чтобы не проверять дальше
            }

            // Проверяем, изменилось ли имя предмета
            if (resultItem != null && resultItem.getItemMeta().getDisplayName() != (firstItem.getItemMeta().getDisplayName())) {
                event.setResult(null); // Отменяем результат наковальни
                return; // Выходим из метода, чтобы не проверять дальше
            }
        }
    }
}
