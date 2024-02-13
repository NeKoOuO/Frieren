package FrierenMod.cards.white;

import FrierenMod.actions.TimeTravelAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TimeTravel extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(TimeTravel.class.getSimpleName());
    public TimeTravel() {
        super(ID, 2, CardRarity.RARE);
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
        this.addToBot(new TimeTravelAction(this,upgraded));
    }
}
