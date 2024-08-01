package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.PhantasmalPower;

public class BetaFactor6 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaFactor6.class.getSimpleName());

    public BetaFactor6() {
        super(ID);
        this.magicItemRarity = MagicItemRarity.UNCOMMON;
        this.manaNeedMultipleCoefficient = 4;
    }

    @Override
    public void takeEffect() {
        this.addToBot(new ApplyPowerAction(p, p, new PhantasmalPower(p, this.secondMagicNumber)));
    }
}
