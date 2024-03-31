package FrierenMod.cards.white;

import FrierenMod.actions.MultipleAttackMagicAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.cards.canAutoAdd.tempCards.Mana;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MultipleOffensiveMagic extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(MultipleOffensiveMagic.class.getSimpleName());

    public MultipleOffensiveMagic() {
        super(ID, -1, CardType.ATTACK, CardEnums.FRIEREN_CARD, CardRarity.COMMON, CardTarget.ALL_ENEMY);
    }

    public MultipleOffensiveMagic(CardColor color) {
        super(ID, -1, CardType.ATTACK, color, CardRarity.COMMON, CardTarget.ALL_ENEMY);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.cardsToPreview = new Mana();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MultipleAttackMagicAction(p, this, this.energyOnUse, this.upgraded));
    }
}
