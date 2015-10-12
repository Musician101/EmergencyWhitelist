package musician101.emergencywhitelist.forge.util;

import java.util.List;
import java.util.UUID;

public class EWLGameProfileList
{
    private List<EWLGameProfile> list;

    public List<EWLGameProfile> getList()
    {
        return list;
    }

    public class EWLGameProfile
    {
        final UUID uuid;
        final String username;

        public EWLGameProfile(UUID uuid, String username)
        {
            this.uuid = uuid;
            this.username = username;
        }

        public String getUsername()
        {
            return username;
        }

        public UUID getUUID()
        {
            return uuid;
        }
    }
}
