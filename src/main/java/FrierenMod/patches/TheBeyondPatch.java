package FrierenMod.patches;

import FrierenMod.enums.CharacterEnums;
import FrierenMod.monsters.Spiegel_Frieren;
import FrierenMod.utils.Config;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheBeyond;

import java.util.ArrayList;

@SpirePatch(clz = TheBeyond.class, method = "initializeBoss")
public class TheBeyondPatch {
    @SpireInsertPatch(rloc = 0, localvars = {"bossList"})
    public static SpireReturn<Void> Insert(AbstractDungeon _inst, ArrayList<String> bossList){
        if(Config.ENCOUNTER_SPIEGEL && AbstractDungeon.player.chosenClass == CharacterEnums.FRIEREN){
            bossList.add(Spiegel_Frieren.MONSTER_ID);
            bossList.add(Spiegel_Frieren.MONSTER_ID);
            bossList.add(Spiegel_Frieren.MONSTER_ID);
            return SpireReturn.Return();
        }else {
            return SpireReturn.Continue();
        }
    }
}
