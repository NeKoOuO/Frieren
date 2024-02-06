package FrierenMod.actions;

import FrierenMod.cards.tempCards.CustomLegendMagic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;

public class ZeerieMagicBookAction extends AbstractGameAction {
    private final boolean upgraded;

    public ZeerieMagicBookAction(boolean upgraded) {
        this.upgraded = upgraded;
    }

    @Override
    public void update() {
        CustomLegendMagic c = new CustomLegendMagic();
        this.addToBot(new CustomCardCostAction(new CustomCardLayer2Action(new CustomCardLayer3Action(new CustomCardLayer4Action(new MakeTempCardInHandAction(c),c,upgraded),c,upgraded),c,upgraded),c));
        this.isDone = true;
    }
}
