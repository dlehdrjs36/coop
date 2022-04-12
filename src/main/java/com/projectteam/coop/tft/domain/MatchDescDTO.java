package com.projectteam.coop.tft.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class MatchDescDTO {
    private Info info;
    private Metadata metadata;

    @Getter @Setter
    public static class Metadata {
        private List<String> participants;
        private String match_id;
        private String data_version;
    }

    @Getter @Setter
    public static class Info {
        private long game_datetime;
        private long game_length;
        private String game_version;
        private List<Participants> participants;

        @Getter @Setter
        public static class Participants {
            private List<String> augments;

            private Companion companion;
            @Getter @Setter
            public static class Companion {
                private String content_ID;
                private int skin_ID;
                private String species;
            }

            private int gold_left;
            private int last_round;
            private int level;
            private int placement;
            private int players_eliminated;
            private String puuid;
            private long time_eliminated;
            private int total_damage_to_players;

            private List<Traits> traits;
            @Getter @Setter
            public static class Traits {
                private String name;
                private int num_units;
                private int style;
                private int tier_current;
                private int tier_total;
            }

            private List<Units> units;
            @Getter @Setter
            public static class Units{
                private String character_id;
                private List<String> itemNames;
                private List<Integer> items;
                private String name;
                private int rarity;
                private int tier;
            }
        }

        private int queue_id;
        private String tft_game_type;
        private int tft_set_number;
    }
}