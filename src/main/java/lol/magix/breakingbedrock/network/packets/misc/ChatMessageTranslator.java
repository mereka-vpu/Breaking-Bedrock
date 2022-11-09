package lol.magix.breakingbedrock.network.packets.misc;

import com.nukkitx.protocol.bedrock.data.command.CommandOriginData;
import com.nukkitx.protocol.bedrock.data.command.CommandOriginType;
import com.nukkitx.protocol.bedrock.packet.CommandRequestPacket;
import com.nukkitx.protocol.bedrock.packet.TextPacket;
import com.nukkitx.protocol.bedrock.packet.TextPacket.Type;
import lol.magix.breakingbedrock.annotations.Translate;
import lol.magix.breakingbedrock.network.translation.Translator;
import lol.magix.breakingbedrock.objects.absolute.PacketType;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;

@Translate(PacketType.JAVA)
public final class ChatMessageTranslator extends Translator<ChatMessageC2SPacket> {
    @Override
    public Class<ChatMessageC2SPacket> getPacketClass() {
        return ChatMessageC2SPacket.class;
    }

    @Override
    public void translate(ChatMessageC2SPacket packet) {
        var message = packet.chatMessage();

        if (message.startsWith("/")) {
            // Process as a command.
            var originData = new CommandOriginData(CommandOriginType.PLAYER,
                    this.data().getIdentity(), "", 0);

            var commandPacket = new CommandRequestPacket();
            commandPacket.setCommand(message);
            commandPacket.setInternal(false);
            commandPacket.setCommandOriginData(originData);

            this.bedrockClient.sendPacket(commandPacket);
        } else {
            // Process as a chat message.
            var textPacket = new TextPacket();
            textPacket.setSourceName(this.data().getDisplayName());
            textPacket.setXuid(this.data().getXuid());
            textPacket.setNeedsTranslation(false);
            textPacket.setType(Type.CHAT);
            textPacket.setMessage(message);

            this.bedrockClient.sendPacket(textPacket);
        }
    }
}
