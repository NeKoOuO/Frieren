package FrierenMod.cards.white;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ModifyBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Confrontation extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(Confrontation.class.getSimpleName());
    public Confrontation() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        this.block = baseBlock = 5;
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
        this.addToBot(new GainBlockAction(p,p,this.block));
        this.addToBot(new DrawCardAction(1));
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard cardPlayed) {
        if(cardPlayed instanceof AbstractFrierenCard && ((AbstractFrierenCard) cardPlayed).isChantCard)
            this.addToBot(new ModifyBlockAction(this.uuid,3));
    }
}
