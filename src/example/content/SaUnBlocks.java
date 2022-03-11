package example.content;

import example.content.blocks.SafeUnloader;
import mindustry.ctype.ContentList;
import mindustry.world.Block;

public class SaUnBlocks implements ContentList {

    public static Block safeUnloader;

    public SaUnBlocks() {}

    @Override
    public void load() {
        safeUnloader = new SafeUnloader("safe-unloader");
    }
}
