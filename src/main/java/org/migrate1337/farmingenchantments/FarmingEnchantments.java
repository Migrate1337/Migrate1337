package org.migrate1337.farmingenchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.migrate1337.farmingenchantments.bannedthings.PrepareAnvilApply;
import org.migrate1337.farmingenchantments.commands.FarmingEnchantmentsCommand;
import org.migrate1337.farmingenchantments.farmenhantments.CarrotEnchant;
import org.migrate1337.farmingenchantments.farmenhantments.PotatoEnchant;
import org.migrate1337.farmingenchantments.items.CarrotFragment;
import org.migrate1337.farmingenchantments.items.PotatoFragment;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.ResourceBundle;

public final class FarmingEnchantments extends JavaPlugin {
    private static FarmingEnchantments plugin;
    public static PotatoEnchant potatoEnchant;
    public static CarrotEnchant carrotEnchant;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();

        plugin = this;
        getCommand("farmingenchantments").setExecutor(new FarmingEnchantmentsCommand(this));

        // Register Potato Enchantment
        if (potatoEnchant == null) {
            potatoEnchant = new PotatoEnchant("glow", this);
        }
        registerEnchantment(potatoEnchant);

        // Register Carrot Enchantment
        if (carrotEnchant == null) {
            carrotEnchant = new CarrotEnchant("carrot", this);
        }
        registerEnchantment(carrotEnchant);

        // Register events and create items
        this.getServer().getPluginManager().registerEvents(potatoEnchant, this);
        this.getServer().getPluginManager().registerEvents(carrotEnchant, this);
        this.getServer().getPluginManager().registerEvents(new PrepareAnvilApply(this), this);
        PotatoFragment potatoFragment = new PotatoFragment(FarmingEnchantments.getPlugin());
        CarrotFragment carrotFragment = new CarrotFragment(FarmingEnchantments.getPlugin());
        this.getServer().getPluginManager().registerEvents(potatoFragment, this);
        this.getServer().getPluginManager().registerEvents(carrotFragment, this);
        ItemStack customPotatoItem = potatoFragment.createPotatoFragment();
        ItemStack customCarrotItem = carrotFragment.createCarrotFragment();
    }

    @Override
    public void onDisable() {
        Enchantment enchant = Enchantment.ARROW_DAMAGE;
        try {
            Field keyField = Enchantment.class.getDeclaredField("byKey");

            keyField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);

            if (byKey.containsKey(potatoEnchant.getKey())) {
                byKey.remove(potatoEnchant.getKey());
            }
            if (byKey.containsKey(carrotEnchant.getKey())) {
                byKey.remove(carrotEnchant.getKey());
            }
            Field nameField = Enchantment.class.getDeclaredField("byName");

            nameField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);

            if (byName.containsKey(potatoEnchant.getName())) {
                byName.remove(potatoEnchant.getName());
            }
            if (byName.containsKey(carrotEnchant.getName())) {
                byName.remove(carrotEnchant.getName());
            }
        } catch (Exception ignored) { }
    }

    public static FarmingEnchantments getPlugin() {
        return plugin;
    }

    public static void registerEnchantment(Enchantment enchantment) {
        try {
            Field acceptingNewField = Enchantment.class.getDeclaredField("acceptingNew");
            acceptingNewField.setAccessible(true);
            acceptingNewField.set(null, true);

            Field byKeyField = Enchantment.class.getDeclaredField("byKey");
            byKeyField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) byKeyField.get(null);

            if (!byKey.containsKey(enchantment.getKey())) {
                Enchantment.registerEnchantment(enchantment);
                System.out.println("Registered enchantment: " + enchantment.getKey());
            } else {
                System.out.println("Enchantment already registered: " + enchantment.getKey());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public PotatoEnchant getPotatoEnchant() {
        return potatoEnchant;
    }
    public CarrotEnchant getCarrotEnchant() {
        return carrotEnchant;
    }

}
