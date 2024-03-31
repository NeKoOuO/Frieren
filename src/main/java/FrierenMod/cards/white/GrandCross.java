package FrierenMod.cards.white;

import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.gameHelpers.LegendarySpellHelper;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GrandCross extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(GrandCross.class.getSimpleName());
    public GrandCross() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = this.damage = 12;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(4);
        }
    }
    public void applyPowers() {
        super.applyPowers();
        if(!LegendarySpellHelper.cannotPlayLegendarySpell())
            this.costForTurn = 0;
    }
    public void triggerOnGlowCheck() {
        if(LegendarySpellHelper.cannotPlayLegendarySpell())
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        else {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
    }
}
