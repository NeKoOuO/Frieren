package FrierenMod.actions;

import FrierenMod.cards.optionCards.ChantDiscardPile;
import FrierenMod.cards.optionCards.ChantDrawPile;
import FrierenMod.cards.optionCards.ChantHand;
import FrierenMod.gameHelpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

public class PreciseChantAction extends AbstractGameAction {
    private final boolean isChantUpgraded;

    public PreciseChantAction(boolean isChantUpgraded) {
        this.isChantUpgraded = isChantUpgraded;
    }

    @Override
    public void update() {
        ChantHelper helper = new ChantHelper();
        int hand = helper.getMagicPowerNumInHand();
        int draw = helper.getMagicPowerNumInDrawPile();
        int discard = helper.getMagicPowerNumInDiscardPile();
        ArrayList<AbstractCard> choices = new ArrayList<>();
        if(draw > 0){
            ChantDrawPile c = new ChantDrawPile();
            c.magicNumber = c.baseMagicNumber = draw;
            c.block = c.baseBlock = draw;
            if(isChantUpgraded){
                c.upgrade();
            }
            c.applyPowers();
            choices.add(c);
        }
        if(hand > 0){
            ChantHand c = new ChantHand();
            c.magicNumber = c.baseMagicNumber = hand;
            if(isChantUpgraded){
                c.upgrade();
            }
            choices.add(c);
        }
        if(discard > 0){
            ChantDiscardPile c = new ChantDiscardPile();
            c.magicNumber = c.baseMagicNumber = discard;
            if(isChantUpgraded){
                c.upgrade();
            }
            choices.add(c);
        }
        if(!choices.isEmpty()){
            this.addToTop(new ChooseOneAction(choices));
        }
        this.isDone = true;
    }
}
