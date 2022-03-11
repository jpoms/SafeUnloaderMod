package example.content.blocks;

import arc.util.io.Writes;
import mindustry.Vars;
import mindustry.content.Items;
import mindustry.game.Team;
import mindustry.type.Category;
import mindustry.type.Item;
import mindustry.world.Tile;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.blocks.storage.StorageBlock;
import mindustry.world.blocks.storage.Unloader;
import mindustry.world.meta.BlockGroup;

import java.util.Objects;
import java.util.Optional;

import static mindustry.type.ItemStack.with;

public class SafeUnloader extends Unloader {
    public static final float PERCENT_OF_MAX = 0.2f;

    public SafeUnloader(String name) {
        super(name);
        requirements(Category.effect, with(Items.titanium, 25, Items.silicon, 30, Items.lead, 10));
        speed = 60f / 11f;
        group = BlockGroup.transportation;
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation) {
        if(tile == null) return false;
        for(short s = 0; s<4 ; s++) {
            Tile checkTile = tile.nearby(((int) s));
            if(checkTile.block() instanceof CoreBlock) {
                return true;
            }
            if(checkTile.build instanceof StorageBlock.StorageBuild) {
               return !Objects.isNull(((StorageBlock.StorageBuild)checkTile.build).linkedCore);
            }
        }
        return false;
    }

    public class SafeUnloaderBuild extends UnloaderBuild {

        public SafeUnloaderBuild() {
            super();
            this.sortItem = Items.copper;
        }

        @Override
        public void write(Writes write) {
            write.s(sortItem == null ? Items.copper.id : sortItem.id);
        }

        @Override
        public void updateTile() {
            Optional<Item> opItem = Optional.ofNullable(sortItem);
            int max = Vars.player.core().storageCapacity;
            if (opItem.isPresent() && !Vars.player.core().items.has(opItem.get(), Math.round(max * PERCENT_OF_MAX))) return;
            super.updateTile();
        }
    }
}
