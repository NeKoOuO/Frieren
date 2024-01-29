package FrierenMod.actions;

import FrierenMod.cards.optionCards.ChantFromDiscardPile;
import FrierenMod.cards.optionCards.ChantFromDrawPile;
import FrierenMod.cards.optionCards.ChantFromHand;
import FrierenMod.helpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class ChantAction extends AbstractGameAction {
    private final int x;
    public ChantAction(int x) {
        this.x = x;
    }

    @Override
    public void update() {
        ChantHelper helper = new ChantHelper();
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        if (helper.canChantFromDrawPile(this.x)) {
            ChantFromDrawPile c = new ChantFromDrawPile();
            c.block = c.baseBlock = x;
            c.magicNumber = c.baseMagicNumber = x;
            c.applyPowers();
            stanceChoices.add(c);
        }
        if (helper.canChantFromHand(this.x)) {
            ChantFromHand c = new ChantFromHand();
            c.magicNumber = c.baseMagicNumber = x;
            stanceChoices.add(c);
        }
        if (helper.canChantFromDiscardPile(this.x)) {
            ChantFromDiscardPile c = new ChantFromDiscardPile();
            c.magicNumber = c.baseMagicNumber = x;
            stanceChoices.add(c);
        }
        if (!stanceChoices.isEmpty()) {
            this.addToTop(new ChooseOneAction(stanceChoices));
        }
        this.isDone = true;
    }
}