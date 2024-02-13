package FrierenMod.cards.white.chant;

import FrierenMod.actions.ChantAllAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.gameHelpers.ChantHelper;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FinalChant extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(FinalChant.class.getSimpleName());
    public FinalChant() {
        super(ID, 2, CardRarity.UNCOMMON);
        this.isChantCard = true;
        this.block = this.baseBlock;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.chantX = this.baseChantX = new ChantHelper().getAllMagicPowerNum();
        this.addToBot(new ChantAllAction(this.isChantUpgraded,this));
    }
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return super.upgradedCanUse(p,m);
    }
}
