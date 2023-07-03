package lol.magix.breakingbedrock.objects.absolute;


import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v589.Bedrock_v589;

/**
 * Constants related to networking.
 */
public interface NetworkConstants {
    /* This will be the latest supported version. */
    BedrockCodec PACKET_CODEC = Bedrock_v589.CODEC;

    // Constants for Xbox Live authentication.
    String XBOX_ANDROID_CID = "0000000048183522";
    String XBOX_AUTH_SCOPES = "service::user.auth.xboxlive.com::MBI_SSL";
    String XBOX_AUTH_GRANTS = "urn:ietf:params:oauth:grant-type:device_code";
    String XBOX_AUTH_RES_TYPE = "device_code";

    // Collection of URLs to authenticate to Minecraft: Bedrock through Xbox Live.
    String XBOX_TOKEN_AUTH = "https://sisu.xboxlive.com/authorize";
    String XBOX_DEVICE_AUTH = "https://device.auth.xboxlive.com/device/authenticate";
    String XBOX_CONNECT_START = "https://login.live.com/oauth20_connect.srf";
    String XBOX_CONNECT_TOKEN = "https://login.live.com/oauth20_token.srf";

    String MINECRAFT_AUTH = "https://multiplayer.minecraft.net/authentication";

    // Deprecated URLs used in Xbox Live wrapper V1.
    @Deprecated String XBOX_USER_AUTH = "https://user.auth.xboxlive.com/user/authenticate";
    @Deprecated String XBOX_AUTHORIZE = "https://xsts.auth.xboxlive.com/xsts/authorize";
    @Deprecated String XBOX_TITLE_AUTH = "https://title.auth.xboxlive.com/title/authenticate";
}
