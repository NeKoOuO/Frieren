package FrierenMod.cards.white;

import FrierenMod.actions.ThunderMagicAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ThunderMagic extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(ThunderMagic.class.getSimpleName());
    public ThunderMagic() {
        super(ID, 3, CardRarity.RARE);
        this.isLegendMagicCard = true;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ThunderMagicAction());
    }
}
