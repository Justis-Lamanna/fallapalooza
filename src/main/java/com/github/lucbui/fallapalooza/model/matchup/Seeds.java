package com.github.lucbui.fallapalooza.model.matchup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Size;
import java.util.Iterator;
import java.util.List;

@Data
public class Seeds implements Iterable<Seeds.Seed> {
    @Size(min = 16, max = 16)
    @NonNull
    private List<Seed> seeds;

    @Override
    public Iterator<Seed> iterator() {
        return seeds.iterator();
    }

    @Data
    @AllArgsConstructor
    public static class Seed {
        int teamOne;
        int teamTwo;
    }
}
