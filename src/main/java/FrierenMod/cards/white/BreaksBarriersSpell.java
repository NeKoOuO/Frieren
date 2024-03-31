package FrierenMod.cards.white;

import FrierenMod.cardMods.BreaksBarriersSpellMod;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.gameHelpers.LegendarySpellHelper;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BreaksBarriersSpell extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(BreaksBarriersSpell.class.getSimpleName());
    public BreaksBarriersSpell() {
        super(ID, -2, CardRarity.RARE);
        this.block = this.baseBlock = 20;
        this.magicNumber = this.baseMagicNumber = 1;
        this.damage = this.baseDamage = 40;
        this.initTask();
        this.selfRetain = true;
        this.isTaskCard = true;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(5);
            this.upgradeMagicNumber(1);
            this.upgradeDamage(5);
            CardModifierManager.addModifier(this,new BreaksBarriersSpellMod(currentLevel,currentLevelRequiredNumber,currentInLevelProgressNumber));
        }
    }
    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        AbstractPlayer p = AbstractDungeon.player;
        if(c instanceof AbstractMagicianCard &&(((AbstractMagicianCard) c).isMana || ((AbstractMagicianCard) c).isChantCard || ((AbstractMagicianCard) c).isLegendarySpell)){
            switch (currentLevel){
                case 3:
                    if(((AbstractMagicianCard) c).isMana){
                        this.taskProgressIncrease();
                        if(currentInLevelProgressNumber >= currentLevelRequiredNumber){
                            this.addToBot(new GainBlockAction(p,this.block));
                            this.continueToNextLevel(3);
                        }
                    }
                    break;
                case 2:
                    if (((AbstractMagicianCard) c).isChantCard){
                        this.taskProgressIncrease();
                        if(currentInLevelProgressNumber >= currentLevelRequiredNumber){
                            for (int i = 0; i < this.magicNumber; i++) {
                                AbstractCard rewardCard = LegendarySpellHelper.getRandomCard();
                                rewardCard.costForTurn = 0;
                                this.addToBot(new MakeTempCardInHandAction(rewardCard));
                            }
                            this.continueToNextLevel(3);
                        }
                    }
                    break;
                case 1:
                    if (((AbstractMagicianCard) c).isLegendarySpell){
                        this.taskProgressIncrease();
                        if(currentInLevelProgressNumber >= currentLevelRequiredNumber){
                            this.addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE, true));
                            this.endTask();
                        }
                    }
                    break;
                default:
                    System.out.println("ERROR!");
                    break;
            }
        }
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }
    public void initTask(){
        this.currentLevel = 3;
        this.currentLevelRequiredNumber = 4;
        this.currentInLevelProgressNumber = 0;
    }
    public void updateDescriptionAndCardImg(){
        CardModifierManager.addModifier(this, new BreaksBarriersSpellMod(currentLevel,currentLevelRequiredNumber,currentInLevelProgressNumber));
    }
}
