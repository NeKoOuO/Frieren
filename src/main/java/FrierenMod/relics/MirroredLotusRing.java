package FrierenMod.relics;

import FrierenMod.cards.tempCards.Himmel;
import FrierenMod.effects.HimmelEffect;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class MirroredLotusRing extends AbstractBaseRelic {
    public static final String ID = ModInformation.makeID(MirroredLotusRing.class.getSimpleName());
    private static final int TURNS = 7;

    public MirroredLotusRing() {
        super(ID, RelicTier.RARE);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atBattleStart() {
        this.counter = 0;
    }
    public void atTurnStart() {
        this.counter++;
        if (this.counter == 4){
            flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new VFXAction(new HimmelEffect()));
            this.addToBot(new MakeTempCardInHandAction(new Himmel(),1));
            this.grayscale = true;
        }
    }
    public AbstractRelic makeCopy() {
        return new MirroredLotusRing();
    }
    public void onVictory() {
        this.counter = -1;
    }
}