package FrierenMod.actions;

import FrierenMod.cards.canAutoAdd.tempCards.GreenApple;
import FrierenMod.powers.SwitchStrengthAndDexterityPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RedAppleMagicAction extends AbstractGameAction {

    @Override
    public void update() {
        this.addToBot(new SwitchStrengthAndDexterityAction());
        this.addToBot(new MakeTempCardInHandAction(new GreenApple()));
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new SwitchStrengthAndDexterityPower(AbstractDungeon.player)));
        this.isDone = true;
    }
}
