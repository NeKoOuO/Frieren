package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class DarkChantAction extends AbstractGameAction {
    private final int magicNumber;

    public DarkChantAction(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    @Override
    public void update() {
        this.addToBot(new ExhaustMagicPowerInHandAction(1));
        for(AbstractMonster mo:AbstractDungeon.getCurrRoom().monsters.monsters){
            this.addToBot(new ApplyPowerAction(mo, AbstractDungeon.player, new VulnerablePower(mo, this.magicNumber, false), 1, true, AbstractGameAction.AttackEffect.NONE));
        }
        this.isDone = true;
    }
}
