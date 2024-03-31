package FrierenMod.cards.white;

import FrierenMod.actions.MagicBookAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FolkGrimoire extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(FolkGrimoire.class.getSimpleName());

    public FolkGrimoire() {
        super(ID, 2, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON);
    }

    public FolkGrimoire(CardColor color) {
        super(ID, 2, color, CardRarity.UNCOMMON);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MagicBookAction(this.upgraded));
    }
}
