package FrierenMod.cards.white;

import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OpenTheWaygate extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(OpenTheWaygate.class.getSimpleName());

    public OpenTheWaygate() {
        super(ID, -2, CardEnums.FRIEREN_CARD, CardRarity.RARE);
    }

    public OpenTheWaygate(CardColor color) {
        super(ID, -2, color, CardRarity.RARE);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.magicNumber = this.baseMagicNumber = 7;
        this.secondMagicNumber = this.baseSecondMagicNumber = 0;
        this.selfRetain = true;
        this.isTaskCard = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(-1);
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (c instanceof AbstractMagicianCard && ((AbstractMagicianCard) c).isMana) {
            this.flash(FLASH_COLOR);
            this.secondMagicNumber = ++this.baseSecondMagicNumber;
        }
        if (secondMagicNumber >= magicNumber) {
            AbstractPlayer p = AbstractDungeon.player;
            this.superFlash();
            this.secondMagicNumber = this.baseSecondMagicNumber = 0;
            this.addToBot(new SkipEnemiesTurnAction());
            this.addToBot(new ExhaustSpecificCardAction(this, p.hand));
        }
    }
}
