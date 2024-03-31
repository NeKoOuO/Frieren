package FrierenMod.cards.canAutoAdd.white;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.powers.ManaBarricadePower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ManaBarricade extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(ManaBarricade.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 2, CardType.POWER, CardEnums.FRIEREN_CARD, CardRarity.RARE);

    public ManaBarricade() {
        super(info);
    }

//    public ManaBarricade(CardColor color) {
//        super(ID, 2, CardType.POWER, color, CardRarity.RARE);
//    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        boolean powerExists = false;
        for (AbstractPower pow : p.powers) {
            if (pow.ID.equals("ManaBarricadePower")) {
                powerExists = true;
                break;
            }
        }
        if (!powerExists)
            this.addToBot(new ApplyPowerAction(p, p, new ManaBarricadePower(p)));
    }
}
