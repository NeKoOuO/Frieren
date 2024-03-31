package FrierenMod.cards.white;

import FrierenMod.actions.MakeManaInDiscardAction;
import FrierenMod.actions.MakeManaInDrawPileAction;
import FrierenMod.actions.MakeManaInHandAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.cards.canAutoAdd.tempCards.Mana;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Disperse extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(Disperse.class.getSimpleName());

    public Disperse() {
        super(ID, 1, CardEnums.FRIEREN_CARD, CardRarity.COMMON);
    }

    public Disperse(CardColor color) {
        super(ID, 1, color, CardRarity.COMMON);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.exhaust = true;
        this.cardsToPreview = new Mana();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.exhaust = false;
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MakeManaInDrawPileAction(1));
        this.addToBot(new MakeManaInHandAction(2));
        this.addToBot(new MakeManaInDiscardAction(3));
    }
}
