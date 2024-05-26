package FrierenMod.cards.white;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FairyTaleEnd extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(FairyTaleEnd.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final CardInfo info = new CardInfo(ID, 1, CardType.ATTACK, CardEnums.FRIEREN_CARD, CardRarity.RARE, CardTarget.ENEMY);
    private static final int BASE_DAMAGE = 6;
    private static final int UPGRADE_BASE_DAMAGE = 7;
    public FairyTaleEnd() {
        super(info);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.damage = this.baseDamage = 6;
        this.isLegendarySpell = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(1);
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        calculateDamage(mo, false);
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        calculateDamage(m, true);
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    private void calculateDamage(AbstractMonster m, boolean isUsingCard) {
        int baseDamage = this.upgraded ? UPGRADE_BASE_DAMAGE : BASE_DAMAGE;
        int count = CombatHelper.getLegendarySpellUsedVarietyThisCombat(isUsingCard);
        if(this.baseDamage >= 99999 || this.baseDamage == Integer.MIN_VALUE)
            this.damage = this.baseDamage = 99999;
        else
            this.damage = this.baseDamage = (int) (baseDamage * Math.pow(2, count));
        super.calculateCardDamage(m);
    }
}
