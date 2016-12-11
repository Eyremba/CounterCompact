package com.CodingGrid.CounterCompact.check.other;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

import com.CodingGrid.CounterCompact.check.HackData;

public class FastbreakACheck {
	
	public static boolean check(Player p, HackData data, BlockBreakEvent e){
		if(p.hasPermission("countercompact.bypass")){
			return false;
		}
		 if (p.getGameMode().equals(GameMode.CREATIVE)) {
		      return false;
		    }
		    if (canBreakFast(e.getBlock().getType())) {
		      return false;
		    }
		    if (p.getItemInHand().containsEnchantment(Enchantment.DIG_SPEED)) {
		      return false;
		    }
		    if (data.fastbreakTime == 0L)
		    {
		      long now = System.currentTimeMillis();
		      data.fastbreakTime = now;
		      return false;
		    }
		    long now = System.currentTimeMillis();
		    if (now - data.fastbreakTime < 15L)
		    {
		      e.setCancelled(true);
		      p.sendMessage("§a§oYou are breaking blocks too fast!");
		      return true;
		    }
		return false;
	}

	 public static boolean canBreakFast(Material material)
	  {
	    Material[] array = { Material.LONG_GRASS, Material.GRASS, Material.DIRT, 
	      Material.SAND, Material.SOUL_SAND, Material.GRAVEL, Material.WHEAT, Material.SEEDS, 
	      Material.COCOA, Material.MELON_SEEDS, Material.PUMPKIN_SEEDS, Material.SUGAR_CANE, Material.NETHERRACK, 
	      Material.DOUBLE_PLANT, Material.WATER_LILY, Material.LEAVES, Material.LEAVES_2, Material.YELLOW_FLOWER, 
	      Material.RED_ROSE, Material.TORCH, Material.FLOWER_POT, Material.FLOWER_POT_ITEM, Material.BOOKSHELF };
	    Material[] arrayOfMaterial1;
	    int j = (arrayOfMaterial1 = array).length;
	    for (int i = 0; i < j; i++)
	    {
	      Material cho = arrayOfMaterial1[i];
	      if (material.equals(cho)) {
	        return true;
	      }
	    }
	    return false;
	  }
	}
