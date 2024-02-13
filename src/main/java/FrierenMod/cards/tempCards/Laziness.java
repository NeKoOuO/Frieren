package FrierenMod.cards.tempCards;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class Laziness extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(Laziness.class.getSimpleName());
    public Laziness() {
        super(ID, -2, CardType.STATUS, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        this.exhaust = true;
    }
    @Override
    public void triggerOnEndOfTurnForPlayingCard() {
        if(AbstractDungeon.player.hand.group.contains(this)){
            this.addToBot(new MakeTempCardInDiscardAction(this.makeCopy(),1));
        }
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if(c instanceof AbstractFrierenCard && ((AbstractFrierenCard) c).isMagicPower){
            this.addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
        }
    }
}
