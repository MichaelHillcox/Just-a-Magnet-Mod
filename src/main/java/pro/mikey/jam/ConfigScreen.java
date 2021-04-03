package pro.mikey.jam;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.AbstractSlider;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import pro.mikey.jam.packets.UpdateSettingsPacket;

public class ConfigScreen extends Screen {
    public int range;
    public double speed;
    private boolean teleport;

    public ConfigScreen() {
        super(new StringTextComponent(""));
    }

    @Override
    protected void init() {
        super.init();

        CompoundNBT playerTag = this.getMinecraft().player.getPersistentData();
        this.teleport = playerTag.getBoolean(JamNbtKeys.TELEPORT);
        this.range = playerTag.getInt(JamNbtKeys.RANGE);
        this.speed = playerTag.getDouble(JamNbtKeys.SPEED);

        int x = this.width / 2, y = this.height / 2;

        this.addButton(new Button(x - 100, y - 35, 200, 20, new TranslationTextComponent("jam.screen.teleport", this.teleport).getFormattedText(), button -> {
            this.teleport = !this.teleport;
            button.setMessage(new TranslationTextComponent("jam.screen.teleport", this.teleport).getFormattedText());
        }));

        AbstractSlider rangeSlider = new AbstractSlider(x - 100, y - 10, 200, 20, this.range / 100f) {
            @Override
            protected void updateMessage() {
                this.setMessage(new TranslationTextComponent("jam.screen.range", (int) (this.value * 100)).getFormattedText());
            }

            @Override
            protected void applyValue() {
                ConfigScreen.this.range = (int) (this.value * 100);
            }
        };
        rangeSlider.setMessage(new TranslationTextComponent("jam.screen.range", this.range).getFormattedText());
        this.addButton(rangeSlider);

        AbstractSlider speedSlider = new AbstractSlider(x - 100, y + 15, 200, 20, this.speed) {
            @Override
            protected void updateMessage() {
                this.setMessage(new TranslationTextComponent("jam.screen.speed", ((int) (this.value * 100) / 100f)).getFormattedText());
            }

            @Override
            protected void applyValue() {
                ConfigScreen.this.speed = ((int) (this.value * 100) / 100f); // Round
            }
        };
        speedSlider.setMessage(new TranslationTextComponent("jam.screen.speed", ((int) (this.speed * 100) / 100f)).getFormattedText());
        this.addButton(speedSlider);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);

        this.drawCenteredString(this.getMinecraft().fontRenderer, new TranslationTextComponent("jam.mod_name").getString(), this.width / 2, this.height / 2 - 60, 0xFFFFFF);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void onClose() {
        super.onClose();

        // Sync to server
        CompoundNBT playerTag = this.getMinecraft().player.getPersistentData();
        Jam.HANDLER.sendToServer(new UpdateSettingsPacket(playerTag.getBoolean(JamNbtKeys.ENABLED), this.teleport, this.range, this.speed));

        // Sync to client
        playerTag.putBoolean(JamNbtKeys.TELEPORT, this.teleport);
        playerTag.putInt(JamNbtKeys.RANGE, this.range);
        playerTag.putDouble(JamNbtKeys.SPEED, this.speed);
    }
}
