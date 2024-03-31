package FrierenMod.cards.canAutoAdd.white;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BabySleeping extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(BabySleeping.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.SKILL, CardEnums.FRIEREN_CARD, CardRarity.COMMON, CardTarget.SELF);

    public BabySleeping() {
        super(info);
    }

//    public BabySleeping(CardColor color) {
//        super(ID, 1, CardType.SKILL, color, CardRarity.COMMON, CardTarget.SELF);
//    }

    @Override
    public void initSpecifiedAttributes() {
        this.block = baseBlock = 7;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
        this.addToBot(new RetainCardsAction(p, this.magicNumber));
    }
}


