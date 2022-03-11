package example;

import arc.util.Log;
import example.content.SaUnBlocks;
import mindustry.mod.Mod;

public class SafeUnloaderMod extends Mod{

    public SafeUnloaderMod(){
        Log.info("Loaded SafeUnloaderMod constructor.");
    }

    @Override
    public void loadContent(){
        Log.info("Loading some example content.");
        new SaUnBlocks().load();
    }
}
