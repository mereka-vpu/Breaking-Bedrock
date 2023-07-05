package lol.magix.breakingbedrock.network.packets.bedrock.world;

import lol.magix.breakingbedrock.annotations.Translate;
import lol.magix.breakingbedrock.network.translation.Translator;
import lol.magix.breakingbedrock.objects.absolute.PacketType;
import lol.magix.breakingbedrock.translators.BlockStateTranslator;
import lol.magix.breakingbedrock.utils.WorldUtils;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.state.property.Properties;
import org.cloudburstmc.protocol.bedrock.packet.UpdateBlockPacket;

import java.util.Objects;

import static lol.magix.breakingbedrock.translators.BlockPaletteTranslator.WATER_BLOCK_ID;

@Translate(PacketType.BEDROCK)
public final class UpdateBlockTranslator extends Translator<UpdateBlockPacket> {
    @Override
    public Class<UpdateBlockPacket> getPacketClass() {
        return UpdateBlockPacket.class;
    }

    @Override
    public void translate(UpdateBlockPacket packet) {
        var block = packet.getDefinition();

        var blockPos = WorldUtils.toBlockPos(packet.getBlockPosition());
        var newState = BlockStateTranslator.getRuntime2Java().get(block.getRuntimeId());

        switch (packet.getDataLayer()) {
            case 0 -> this.javaClient().processPacket(
                    new BlockUpdateS2CPacket(blockPos, newState));
            case 1 -> {
                var world = this.client().world;
                Objects.requireNonNull(world, "Player is not in a world");

                var existing = world.getBlockState(blockPos);
                if (!existing.isAir()) {
                    if (existing.contains(Properties.WATERLOGGED))
                        newState = newState.with(Properties.WATERLOGGED,
                                block.getRuntimeId() == WATER_BLOCK_ID);
                }

                this.javaClient().processPacket(
                        new BlockUpdateS2CPacket(blockPos, newState));
            }
        }
    }
}