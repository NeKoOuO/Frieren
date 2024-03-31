package FrierenMod.cards.canAutoAdd.white;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MageStyleFinisher extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(MageStyleFinisher.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.ATTACK, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON, CardTarget.ENEMY);

    public MageStyleFinisher() {
        super(info);
    }

//    public MageStyleFinisher(CardColor color) {
//        super(ID, 1, CardType.ATTACK, color, CardRarity.UNCOMMON, CardTarget.ENEMY);
//    }

    @Override
    public void initSpecifiedAttributes() {
        this.damage = this.baseDamage = 5;
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
            this.initializeDescription();
        }
    }

    public void applyPowers() {
        super.applyPowers();
        this.baseMagicNumber = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (c instanceof AbstractBaseCard && ((AbstractBaseCard) c).isMana)
                this.baseMagicNumber++;
        }
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < baseMagicNumber; i++) {
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }
}
