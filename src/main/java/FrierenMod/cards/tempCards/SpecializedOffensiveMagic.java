package FrierenMod.cards.tempCards;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;

public class SpecializedOffensiveMagic extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(SpecializedOffensiveMagic.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 0, CardType.ATTACK, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.ENEMY);
    public String usedModifierText;

    public SpecializedOffensiveMagic() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.usedModifierText = "";
        this.baseDamage = this.damage = 0;
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void initializeDescription() {
        if (this.usedModifierText != null && !this.usedModifierText.isEmpty()) {
            this.rawDescription = this.usedModifierText;
        }
        super.initializeDescription();
    }
}