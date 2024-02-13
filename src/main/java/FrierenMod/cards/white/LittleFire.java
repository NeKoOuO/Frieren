package FrierenMod.cards.white;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LittleFire extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(LittleFire.class.getSimpleName());
    public  LittleFire(){
     super(ID,1,CardType.ATTACK,CardRarity.COMMON,CardTarget.ENEMY);
     this.damage = this.baseDamage = 6;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(
                m,
                new DamageInfo(
                        p,
                        this.damage,
                        this.damageTypeForTurn
                ),
                AbstractGameAction.AttackEffect.FIRE,true
        ));
        this.addToBot(new ArmamentsAction(this.upgraded));
    }

}