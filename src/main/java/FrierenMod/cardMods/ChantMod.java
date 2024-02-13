package FrierenMod.cardMods;

import FrierenMod.actions.ChantAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class ChantMod extends AbstractCardModifier {
    public static final String ID = ModInformation.makeID(ChantMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    private final int chantAmt;

    public ChantMod(int chantAmt) {
        this.chantAmt = chantAmt;
    }
    public void onInitialApplication(AbstractCard card) {
        ((AbstractFrierenCard) card).chantX = ((AbstractFrierenCard) card).baseChantX =this.chantAmt;
        ((AbstractFrierenCard) card).isChantCard = true;
    }

    public AbstractCardModifier makeCopy() {
        return new ChantMod(this.chantAmt);
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        this.addToBot(new ChantAction(((AbstractFrierenCard)card).isChantUpgraded, this.chantAmt));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + TEXT[2] + this.chantAmt + TEXT[3];
    }
}