package FrierenMod.actions;

import FrierenMod.cards.tempCards.MagicPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

public class DuoChongAction extends AbstractGameAction {
    private AbstractPlayer p;
    private int energyOnUse = -1;
    private boolean isUpgraded = false;

    public DuoChongAction(AbstractPlayer p, int energyOnUse, boolean isUpgraded) {
        this.p = p;
        this.energyOnUse = energyOnUse;
        this.isUpgraded = isUpgraded;
    }

    public void update() {
        int baseDamage = this.energyOnUse + 1;
        int counts = this.isUpgraded?this.energyOnUse+1:this.energyOnUse;
        int magicNum = this.energyOnUse;
        if (this.p.hasRelic("Chemical X")) {
            baseDamage += 2;
            counts += 2;
            magicNum += 2;
            this.p.getRelic("Chemical X").flash();
        }
        for(AbstractPower po:p.powers){
            if(po.ID.matches("Strength")){
                baseDamage += po.amount;
            }
        }
        for(int i = 0; i < counts; ++i) {
            AbstractMonster  mo = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
            if (mo != null) {
                this.addToTop(new DamageAction(mo, new DamageInfo(AbstractDungeon.player, baseDamage, DamageInfo.DamageType.NORMAL), AttackEffect.NONE));
                this.addToTop(new SFXAction("ORB_LIGHTNING_EVOKE", 0.1F));
                this.addToTop(new VFXAction(new LightningEffect(mo.hb.cX, mo.hb.cY)));
            }
            this.addToBot(new MakeTempCardInHandAction(new MagicPower(),magicNum));
            this.p.energy.use(EnergyPanel.totalCount);
        }
        this.isDone = true;
    }
}
