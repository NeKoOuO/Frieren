package FrierenMod.cardMods;

import FrierenMod.helpers.ModHelper;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class VulnerableMod extends AbstractCardModifier {
    public static final String ID = ModHelper.makePath(VulnerableMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;
    private int stackAmt;

    public VulnerableMod(int stackAmt) {
        this.stackAmt = stackAmt;
    }

    public AbstractCardModifier makeCopy() {
        return new VulnerableMod(this.stackAmt);
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        for (AbstractMonster mo :AbstractDungeon.getCurrRoom().monsters.monsters){
            this.addToBot(new ApplyPowerAction(mo, AbstractDungeon.player, new VulnerablePower(mo, this.stackAmt, false), 1, true, AbstractGameAction.AttackEffect.NONE));
        }
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + TEXT[2] + this.stackAmt + TEXT[3];
    }
}
