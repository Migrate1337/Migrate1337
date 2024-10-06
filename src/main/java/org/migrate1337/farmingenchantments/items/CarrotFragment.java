package org.migrate1337.farmingenchantments.items;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.migrate1337.farmingenchantments.FarmingEnchantments;
import org.migrate1337.farmingenchantments.utils.ColorUtils;

import java.util.Arrays;
import java.util.UUID;

public class CarrotFragment implements Listener {

    private static FarmingEnchantments plugin;
    private static GameProfile profile;

    public CarrotFragment(FarmingEnchantments plugin) {
        this.plugin = plugin;
        // Инициализация профиля один раз
        String textureValue = plugin.getConfig().getString("values.HeadValueCarrotFragment");
        profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", textureValue));
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        ItemStack item = event.getItemInHand();
        if (item != null && item.getType() == Material.PLAYER_HEAD && item.getItemMeta() != null && "§6Carrot fragment".equals(item.getItemMeta().getDisplayName())) {
            event.setCancelled(true);
        }
    }
    public static ItemStack createCarrotFragment() {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();

        // Установка имени и описания предмета
        meta.setDisplayName((ColorUtils.parseMessage(plugin.getConfig().getString("names.carrotFragmentName"))));
        meta.setLore(Arrays.asList(ColorUtils.parseMessage((plugin.getConfig().getString("descriptions.carrotFragmentDescription")))));

        try {
            java.lang.reflect.Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        item.setItemMeta(meta);
        return item;
    }
}