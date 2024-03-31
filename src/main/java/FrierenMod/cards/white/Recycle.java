package FrierenMod.cards.white;

import FrierenMod.actions.RecycleAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Recycle extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(Recycle.class.getSimpleName());
    public Recycle() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        this.magicNumber = this.baseMagicNumber = 2;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(-1);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new RecycleAction(this.magicNumber));
    }
}
