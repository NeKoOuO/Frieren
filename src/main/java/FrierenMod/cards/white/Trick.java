package FrierenMod.cards.white;

import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.cards.canAutoAdd.tempCards.ManaConcealment;
import FrierenMod.enums.CardEnums;
import FrierenMod.powers.TrickPower;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Trick extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(Trick.class.getSimpleName());

    public Trick() {
        super(ID, 1, CardType.POWER, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON, CardTarget.NONE);
    }

    public Trick(CardColor color) {
        super(ID, 1, CardType.POWER, color, CardRarity.UNCOMMON, CardTarget.NONE);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.cardsToPreview = new ManaConcealment();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new TrickPower(p)));
    }
}
