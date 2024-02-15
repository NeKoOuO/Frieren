package FrierenMod.actions;

import FrierenMod.gameHelpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class DoubleMagicInDiscardPileAction extends AbstractGameAction {
    @Override
    public void update() {
        int discard = ChantHelper.getMagicPowerNumInDiscardPile();
        if(discard > 0){
            this.addToBot(new MakeMagicPowerInDiscardAction(discard));
        }
        this.isDone = true;
    }
}
