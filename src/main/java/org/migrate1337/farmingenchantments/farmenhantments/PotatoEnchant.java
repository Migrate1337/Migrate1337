package org.migrate1337.farmingenchantments.farmenhantments;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.migrate1337.farmingenchantments.FarmingEnchantments;

import java.util.Random;

import static org.migrate1337.farmingenchantments.items.PotatoFragment.createPotatoFragment;

public class PotatoEnchant extends Enchantment implements Listener {

    private final FarmingEnchantments plugin;

    public PotatoEnchant(String namespace, FarmingEnchantments plugin){
        super(new NamespacedKey(plugin, namespace));
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        Material blockType = block.getType();
        Player player = e.getPlayer();
        ItemStack tool = player.getInventory().getItemInMainHand();

        // Используем экземпляр зачарования из основного класса плагина
        PotatoEnchant potatoEnchant = plugin.getPotatoEnchant();

        if (blockType == Material.POTATOES && tool.containsEnchantment(potatoEnchant)) {
            Ageable ageable = (Ageable) block.getBlockData();
            if (ageable.getAge() == ageable.getMaximumAge()) {
                e.setDropItems(false);

                int baseDrop = 1;
                int cropBoostLevel = tool.getEnchantmentLevel(potatoEnchant);
                int extraDrop = baseDrop * (cropBoostLevel + 1);
                int totalDrop = baseDrop + extraDrop;
                float minimumIncreaseDropValue = plugin.getConfig().getInt("values.minimumIncreaseDropValue");
                float maximumIncreaseDropValue = plugin.getConfig().getInt("values.maximumIncreaseDropValue");
                float randomdrop = (float) (Math.random() * (maximumIncreaseDropValue - minimumIncreaseDropValue)) + minimumIncreaseDropValue;
                randomdrop = Math.round(randomdrop * 10) / 10.0f;
                totalDrop *= randomdrop;

                ItemStack potatodrop = new ItemStack(Material.POTATO);
                potatodrop.setAmount(totalDrop);
                block.getWorld().dropItemNaturally(block.getLocation(), potatodrop);

                Random random = new Random();
                int chance = plugin.getConfig().getInt("values.chanceToDropPotatoFragment");
                if ((random.nextInt(100)+1) < chance) {
                    ItemStack fragmentdrop = new ItemStack(createPotatoFragment());
                    fragmentdrop.setAmount(1);
                    block.getWorld().dropItemNaturally(block.getLocation(), fragmentdrop);
                    if(plugin.getConfig().getBoolean("values.enablePotatoFragmentDropMessage")) {
                        player.sendMessage("§a[§eFE§a]§f §fYou got §6potato fragment. §fThe chance of this was §a" + plugin.getConfig().getInt("values.chanceToDropPotatoFragment") + "%§f");
                    }
                }
                if(plugin.getConfig().getBoolean("values.enablePotatoDropMessage")){
                    player.sendMessage("§a[§eFE§a]§f Potato block was broken! Dropped " + totalDrop + " potatoes. The result was multiplied by " + randomdrop);
                }
            }
        }
    }



    @java.lang.Override
    public java.lang.String getName() {
        return "PotatoBoost";
    }

    @java.lang.Override
    public int getMaxLevel() {
        return 2;
    }

    @java.lang.Override
    public int getStartLevel() {
        return 1;
    }

    @java.lang.Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.TOOL;
    }

    @java.lang.Override
    public boolean isTreasure() {
        return false;
    }

    @java.lang.Override
    public boolean isCursed() {
        return false;
    }

    @java.lang.Override
    public boolean conflictsWith(Enchantment enchantment) {
        return false;
    }

    @java.lang.Override
    public boolean canEnchantItem(ItemStack itemStack) {
        return false;
    }
}
