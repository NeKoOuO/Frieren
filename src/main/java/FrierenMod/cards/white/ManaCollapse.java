package FrierenMod.cards.white;

import FrierenMod.actions.MakeManaInHandAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ManaCollapse extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(ManaCollapse.class.getSimpleName());
    public ManaCollapse() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.damage = this.baseDamage = 9;
        this.magicNumber = this.baseMagicNumber = 1;
        this.secondMagicNumber = this.baseSecondMagicNumber = 20;
        this.shuffleBackIntoDrawPile = true;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() >= 10){
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.secondMagicNumber, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        else {
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            this.addToBot(new MakeManaInHandAction(this.magicNumber));
        }
    }
    public void applyPowers() {
        super.applyPowers();
        this.secondMagicNumber = this.baseSecondMagicNumber = this.damage + 11;
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() >= 10) {
            this.shuffleBackIntoDrawPile = false;
        }
        initializeDescription();
    }
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() >= 10) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
}
