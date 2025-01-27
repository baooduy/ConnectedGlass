package com.supermartijn642.connectedglass.data;

import com.supermartijn642.connectedglass.CGGlassType;
import com.supermartijn642.connectedglass.ConnectedGlass;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created 6/23/2020 by SuperMartijn642
 */
public class CGTagProvider {

    public static final Map<Tag<Block>,List<Block>> BLOCK_TAGS = new HashMap<>();
    public static final Map<Tag<Item>,List<Block>> ITEM_TAGS = new HashMap<>();

    public static void init(){
        ArrayList<Block> glass = new ArrayList<>();
        ArrayList<Block> glassColored = new ArrayList<>();
        ArrayList<Block> glassColorless = new ArrayList<>();
        ArrayList<Block> glassTinted = new ArrayList<>();
        ArrayList<Block> panes = new ArrayList<>();
        ArrayList<Block> panesColored = new ArrayList<>();
        ArrayList<Block> panesColorless = new ArrayList<>();
        ArrayList<Block> panesTinted = new ArrayList<>();

        for(CGGlassType type : CGGlassType.values()){
            glass.addAll(type.blocks);
            if(type.isTinted)
                glassTinted.addAll(type.blocks);
            else{
                glassColored.addAll(type.colored_blocks.values());
                glassColorless.add(type.block);
            }
            if(type.hasPanes){
                panes.addAll(type.panes);
                if(type.isTinted)
                    panesTinted.addAll(type.panes);
                else{
                    panesColored.addAll(type.colored_panes.values());
                    panesColorless.add(type.pane);
                }
            }
        }

        glass.add(ConnectedGlass.tinted_glass);
        glassTinted.add(ConnectedGlass.tinted_glass);

        add(glass, Tags.Blocks.GLASS, Tags.Items.GLASS);
        add(glassColored, Tags.Blocks.STAINED_GLASS, Tags.Items.STAINED_GLASS);
        add(glassColorless, Tags.Blocks.GLASS_COLORLESS, Tags.Items.GLASS_COLORLESS);
        add(glassTinted, new BlockTags.Wrapper(new ResourceLocation("forge", "glass/tinted")), new ItemTags.Wrapper(new ResourceLocation("forge", "glass/tinted")));
        add(panes, Tags.Blocks.GLASS_PANES, Tags.Items.GLASS_PANES);
        add(panesColored, Tags.Blocks.STAINED_GLASS_PANES, Tags.Items.STAINED_GLASS_PANES);
        add(panesColorless, Tags.Blocks.GLASS_PANES_COLORLESS, Tags.Items.GLASS_PANES_COLORLESS);
        add(panesTinted, new BlockTags.Wrapper(new ResourceLocation("forge", "glass_panes/tinted")), new ItemTags.Wrapper(new ResourceLocation("forge", "glass_panes/tinted")));
    }

    private static void add(List<Block> blocks, Tag<Block> blockTag, Tag<Item> itemTag){
        BLOCK_TAGS.put(blockTag, blocks);
        ITEM_TAGS.put(itemTag, blocks);
    }

}
