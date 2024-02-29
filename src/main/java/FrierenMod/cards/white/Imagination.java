package FrierenMod.cards.white;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.powers.BanManaGainPower;
import FrierenMod.powers.ManaInsteadOfEnergyPower;
import FrierenMod.powers.ChantWithoutManaPower;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Imagination extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(Imagination.class.getSimpleName());
    public Imagination() {
        super(ID, 2, CardRarity.RARE);
        this.exhaust = true;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.selfRetain = true;
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p,p,new ChantWithoutManaPower(p)));
        this.addToBot(new ApplyPowerAction(p,p,new BanManaGainPower(p)));
        this.addToBot(new ApplyPowerAction(p,p,new ManaInsteadOfEnergyPower(p)));
    }
}
