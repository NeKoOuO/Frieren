package FrierenMod.cards.white;

import FrierenMod.actions.ChantAction;
import FrierenMod.cards.tempCards.FangYuMoFa2;
import FrierenMod.helpers.ChantHelper;
import FrierenMod.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

import static FrierenMod.Characters.Frieren.Enums.FRIEREN_CARD;
import static FrierenMod.tags.CustomTags.CHANT;

public class FangYuMoFa extends CustomCard{
    public static final String ID = ModHelper.makePath(FangYuMoFa.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "FrierenModResources/img/cards/DefendMagic_skill.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = FRIEREN_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    public FangYuMoFa() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.block = this.baseBlock = 3;
        this.tags.add(CHANT);
        FangYuMoFa2 c = new FangYuMoFa2();
        if(this.upgraded) {
            c.upgraded = true;
        }
        this.cardsToPreview = c;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(2);
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChantAction(1));
        this.addToBot(new GainBlockAction(p,this.block));
        if(this.upgraded){
            AbstractCard c = new FangYuMoFa2();
            c.upgraded = true;
            this.addToBot(new MakeTempCardInHandAction(c));
        }
        else {
            this.addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeCopy()));
        }
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return new ChantHelper().canChantUse(this,p,m,1);
    }
}
